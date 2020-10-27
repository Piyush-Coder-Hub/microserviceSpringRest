package com.piyush.springframework.mssccloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MsscCloudConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsscCloudConfigApplication.class, args);
	}

}
