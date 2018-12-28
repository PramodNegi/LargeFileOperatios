package com.csg.dts.processor;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import com.csg.dts.processor.app.LargeFileProcessorTest;

@SpringBootTest(classes = LargeFileProcessorTest.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class FileProcessorTest {

	@Autowired
	@Qualifier("fileProcessor")
	private FileProcessor fileProcessor;
	
	@Test
	public void testFileProcessing() {
		try {
			File file = ResourceUtils.getFile("classpath:F_Small_File.json");
			fileProcessor.process(file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
