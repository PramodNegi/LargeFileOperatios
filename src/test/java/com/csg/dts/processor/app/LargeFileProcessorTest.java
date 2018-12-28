package com.csg.dts.processor.app;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext
@SpringBootApplication
@ComponentScan(basePackages = {"com.csg.dts"})
@EntityScan(basePackages= {"com.csg.dts.event.model"})
@Profile("test")
public class LargeFileProcessorTest {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(LargeFileProcessorTest.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);
	}

}
