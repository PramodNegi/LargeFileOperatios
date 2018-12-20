package com.csg.dts.processor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csg.dts.event.handler.EventProcessor;
import com.csg.dts.event.handler.EventReader;

@Component
public class FileProcessor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileProcessor.class);
	
	@Autowired
	private EventReader eventFileReader;
	
	@Autowired
	private EventProcessor eventProcessor;
	
	public void process(String filePath) {
		LOGGER.info("File Path: {}", filePath);
		BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
		eventFileReader.startReading(filePath, blockingQueue);
		eventProcessor.startProcessing(blockingQueue);
	}
}
