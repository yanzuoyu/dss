package com.enerbos.cloud.dss.service.util.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.enerbos.cloud.dss.service.constants.UploadSystemConstants;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * All rights Reserved, Designed By 翼虎能源
 * Copyright:    Copyright(C) 2015-2017
 * Company   北京翼虎能源科技有限公司
 *
 * @author 严作宇
 * @version 1.0
 * @date 2017年5月31日 上午11:28:07
 * @Description OSS  授权 方法
 */
public class Policy {

//	private static final Logger logger = LoggerFactory.getLogger(this.getClass());


    public Map<String, Object> policy(HttpServletRequest request, String userName, String orgId, String siteId, String quoteId, String quoteType ) throws ServletException, IOException {

        //配置文件参数赋值
        String endpoint = UploadSystemConstants.UPLOAD_CONFIG.get("endpoint");//阿里云服务器地址
        String accessId = UploadSystemConstants.UPLOAD_CONFIG.get("accessId");//key id
        String accessKey = UploadSystemConstants.UPLOAD_CONFIG.get("accessKey");//秘钥
        String bucket = UploadSystemConstants.UPLOAD_CONFIG.get("bucket");//bucket
        String dir = UploadSystemConstants.UPLOAD_CONFIG.get("dir");//文件夹
        String host = "http://" + bucket + "." + endpoint.substring(7); //文件地址
        String timeout = UploadSystemConstants.UPLOAD_CONFIG.get("timeout");//文件夹
        String callbackUrl = UploadSystemConstants.UPLOAD_CONFIG.get("callbackUrl");
        String callbackHost = UploadSystemConstants.UPLOAD_CONFIG.get("callbackHost");

        String ipHost = getIpAddress(request);
        OSSClient client = new OSSClient(endpoint, accessId, accessKey); // 连接服务器
        try {
            long expireTime = Long.valueOf(timeout); //设置失效时间(秒)
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);

            Map<String, Object> respMap = new LinkedHashMap<String, Object>();
            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            //respMap.put("expire", formatISO8601Date(expiration));
            //文件夹策略 ：
            respMap.put("dir", getDirName(dir,orgId,siteId,quoteId,quoteType));
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            //callback　设置　　

            Map<String, Object> callbackMap = new LinkedHashMap<String, Object>();
            callbackMap.put("callbackUrl", callbackUrl);
            callbackMap.put("callbackHost", callbackHost);
            callbackMap.put("callbackBody", "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}&userName=" + userName + "&ipHost=" + ipHost);
            callbackMap.put("callbackBodyType", "application/json");
            String callback_string = String.valueOf(JSONObject.fromObject(callbackMap));
            String base64_callback_body = BinaryUtil.toBase64String(callback_string.getBytes("utf-8"));

            //自定义参数

            respMap.put("callback", base64_callback_body);
//            JSONObject ja1 = JSONObject.fromObject(respMap);
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
//            response(request, response, ja1.toString());
            return respMap;
        } catch (Exception e) {
//			logger.error("-------policy----",e);
            return null;
        }

    }
private String getDirName(String dir , String orgId ,String siteId, String quoteId ,String quoteType){
    StringBuffer dirName = new StringBuffer() ;
    dirName.append(dir) ;
    if(StringUtils.isNotEmpty(orgId)){
        dirName.append(orgId +"/");
    }
    if(StringUtils.isNotEmpty(siteId)){
        dirName.append(siteId +"/");
    }
    if(StringUtils.isNotEmpty(quoteType)){
        dirName.append(quoteType +"/");
    }
    if(StringUtils.isNotEmpty(quoteId)){
        dirName.append(quoteId +"/");
    }
        return dirName.toString();
}
    //	private void response(HttpServletRequest request, HttpServletResponse response, String results) throws IOException {
// 		String callbackFunName = request.getParameter("callback");
//		if (callbackFunName==null || callbackFunName.equalsIgnoreCase(""))
//			response.getWriter().println(results);
//		else
//			response.getWriter().println(callbackFunName + "( "+results+" )");
////		System.out.println(callbackFunName + "( "+results+" )");
//		response.setStatus(HttpServletResponse.SC_OK);
//        response.flushBuffer();
//	}
//	public static void main(String[] args) throws ServletException, IOException {
//		HttpServletRequest request = null  ;
//		HttpServletResponse response = null ;
//		//测试properties参数 是否有问题
//		new Policy().policy(request, response);
//	}
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();

            // 这里主要是获取本机的ip,可有可无
            if (ipAddress.equals("127.0.0.1")
                    || ipAddress.endsWith("0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        // 或者这样也行,对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        // return
        // ipAddress!=null&&!"".equals(ipAddress)?ipAddress.split(",")[0]:null;
        return ipAddress;
    }
}
