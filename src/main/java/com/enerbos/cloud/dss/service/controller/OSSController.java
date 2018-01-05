package com.enerbos.cloud.dss.service.controller;

import com.enerbos.cloud.common.EnerbosMessage;
import com.enerbos.cloud.dss.service.domain.UploadLog;
import com.enerbos.cloud.dss.service.service.UploadLogService;
import com.enerbos.cloud.dss.service.util.oss.Callback;
import com.enerbos.cloud.dss.service.util.oss.Policy;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Date;

/**
 * All rights Reserved, Designed By 翼虎能源
 * Copyright:    Copyright(C) 2015-2017
 * Company   北京翼虎能源科技有限公司
 *
 * @author 严作宇
 * @version 1.0
 * @date 2017年5月31日 上午11:28:07
 * @Description OSS上传处理
 */
@RestController
@RequestMapping("/dss/oss")
public class OSSController {

 	@Autowired
 	private UploadLogService uploadLogService;

 	Logger logger = Logger.getLogger(this.getClass());
	@RequestMapping("/policy")
	public EnerbosMessage policy(String orgId ,String siteId ,String quoteTpye ,String quoteId , HttpServletRequest request, HttpServletResponse response  , Principal user ) {

		String userName  = user.getName() ;
		try {
			return   EnerbosMessage.createSuccessMsg(new Policy().policy(request, userName ,orgId,siteId,quoteId,quoteTpye),"获取成功","")  ;
			// System.out.println(new Date());
		} catch (ServletException | IOException e) {
			logger.error("",e);
			return  null  ;
		}

	}

	@RequestMapping("/callback")
	public void callback(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		try {
			//this.response(request, response, "{\"Status\":\"OK\"}", HttpServletResponse.SC_OK);
			// 单独把callBack的参数拿出来 记日志
			String ossCallbackBody = GetPostBody(request.getInputStream(),
					Integer.parseInt(request.getHeader("content-length")));
			String a[] = ossCallbackBody.split("&");
			UploadLog uploadLog = new UploadLog();
			for (int i = 0; i < a.length; i++) {
				String values[] = a[i].split("=");
				if (values[0].equals("filename")) {
					uploadLog.setFilename(values[1].replaceAll("\"", ""));
				} else if (values[0].equals("userName")) {
					String	userName = values[1];
					uploadLog.setUserName(userName);
				} else if (values[0].equals("ipHost")) {
					uploadLog.setIpHost(values[1]);
				} else if (values[0].equals("size")) {
					uploadLog.setSize(values[1]);
				} else if (values[0].equals("mimeType")) {
					uploadLog.setMimetype(values[1].replaceAll("\"", ""));
				} else if (values[0].equals("height")) {
					uploadLog.setHeight(Integer.parseInt(values[1]));
				} else if (values[0].equals("width")) {
					uploadLog.setWidth(Integer.parseInt(values[1]));
				}
			}
			uploadLogService.insertUploadLog(uploadLog);
//			this.response(request, response, "{\"Status\":\"OK\"}", HttpServletResponse.SC_OK);
			 new Callback().callback(request, response, ossCallbackBody);

		} catch ( IOException   e) {
			 logger.error("-------callback---------",e);
		}
	}
	private void response(HttpServletRequest request, HttpServletResponse response, String results, int status) throws IOException {
		String callbackFunName = request.getParameter("callback");
		response.addHeader("Content-Length", String.valueOf(results.length()));
		if (callbackFunName == null || callbackFunName.equalsIgnoreCase(""))
			response.getWriter().println(results);
		else
			response.getWriter().println(callbackFunName + "( " + results + " )");
		response.setStatus(status);
		response.flushBuffer();
	}
	public String GetPostBody(InputStream is, int contentLen) {
		if (contentLen > 0) {
			int readLen = 0;
			int readLengthThisTime = 0;
			byte[] message = new byte[contentLen];
			try {
				while (readLen != contentLen) {
					readLengthThisTime = is.read(message, readLen, contentLen
							- readLen);
					if (readLengthThisTime == -1) {// Should not happen.
						break;
					}
					readLen += readLengthThisTime;
				}
				return new String(message);
			} catch (IOException e) {
			}
		}
		return "";
	}

}
