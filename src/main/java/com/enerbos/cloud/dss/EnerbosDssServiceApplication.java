package com.enerbos.cloud.dss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * All rights Reserved, Designed By 翼虎能源
 * Copyright:    Copyright(C) 2015-2017
 * Company   北京翼虎能源科技有限公司
 *
 * @author 严作宇
 * @version 1.0
 * @date 2017年5月31日 上午11:28:07
 * @Description Spring boot 启动文件
 */
@SpringBootApplication
@SpringBootConfiguration
@EnableDiscoveryClient
@EnableEurekaClient
@EnableResourceServer
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.enerbos.cloud.dss.service"})
public class EnerbosDssServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnerbosDssServiceApplication.class, args);
	}
}
