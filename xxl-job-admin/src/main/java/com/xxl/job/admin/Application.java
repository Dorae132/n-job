package com.xxl.job.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource({"classpath:spring/applicationcontext-base.xml", "classpath:spring/applicationcontext-xxl-job-admin.xml", "classpath:spring/springmvc-context.xml"})
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
	}

}