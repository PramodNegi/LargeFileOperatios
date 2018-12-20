package com.csg.dts.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.csg.dts"})
@EntityScan(basePackages= {"com.csg.dts.event.model"})
public class LargeFileProcessor implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LargeFileProcessor.class);
	
	@Autowired
	private FileProcessor fileProcessor;

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(LargeFileProcessor.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("File Path : {}", args[0]);
		fileProcessor.process(args[0]);
	}

}
