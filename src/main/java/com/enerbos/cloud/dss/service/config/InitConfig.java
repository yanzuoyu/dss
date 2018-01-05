package com.enerbos.cloud.dss.service.config;

import com.enerbos.cloud.dss.service.constants.UploadSystemConstants;
import com.enerbos.cloud.dss.service.service.UploadConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class InitConfig implements CommandLineRunner {

	@Autowired
	private UploadConfigService uploadConfigService;

	@Override
	public void run(String... args) throws Exception {

		//上传配置
 		List<Map<String, String>> uploadConfigMap =    uploadConfigService.uploadConfig();

		for (Map<String, String> map : uploadConfigMap) {
			UploadSystemConstants.UPLOAD_CONFIG.put(map.get("name"), map.get("content"));
		}
	}

}
