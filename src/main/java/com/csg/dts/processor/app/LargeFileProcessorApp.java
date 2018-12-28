package com.csg.dts.processor.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

import com.csg.dts.event.validator.Validator;
import com.csg.dts.execption.ApplicationException;
import com.csg.dts.processor.FileProcessor;

@SpringBootApplication
@ComponentScan(basePackages = { "com.csg.dts"})
@EntityScan(basePackages= {"com.csg.dts.event.model"})
@Profile("!test")
public class LargeFileProcessorApp implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LargeFileProcessorApp.class);
	
	@Autowired
	@Qualifier("fileProcessor")
	private FileProcessor fileProcessor;

	@Autowired
	private Validator<String> validator;
	
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(LargeFileProcessorApp.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		String filePath = args[0];
		LOGGER.info("File Path : {}", filePath);
		try{
			validator.validate(filePath);
			fileProcessor.process(filePath);
		}catch(ApplicationException ex) {
			LOGGER.error("Could not Proceed due to {}: {}. Must be file.", ex.getMessage(), filePath);
		}
	}

}
