package com.csg.dts.event.handler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.csg.dts.event.constants.Constants;
import com.csg.dts.execption.ApplicationException;
import com.csg.dts.execption.ApplicationRuntimeException;

@Component
public class EventReader {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EventReader.class);
	
	public void startReading(final String filePath, final BlockingQueue<String> blockingQueue) {
		Thread readerThread = new Thread(new EventReaderThread(filePath, blockingQueue));
		readerThread.start();
	}
	
	private class EventReaderThread implements Runnable {
		
		private BlockingQueue<String> blockingQueue;
		private String filePath;
		
		public EventReaderThread(final String filePath, BlockingQueue<String> blockingQueue) {
			this.blockingQueue = blockingQueue;
			this.filePath = filePath;
		}
		
		@Override
		public void run() {
			LOGGER.info("EventFileReader executing run()");
			processFileLineByLine();
		}
		
		private void processFileLineByLine() {
			
			try (FileInputStream inputStream = new FileInputStream(this.filePath); Scanner sc = new Scanner(inputStream, "UTF-8")){
			    while (sc.hasNextLine()) {
			        String line = sc.nextLine();
					LOGGER.info("Processing line : {}", line);
			        this.blockingQueue.put(line);
			    }
			    this.blockingQueue.put(Constants.EOF.value());
			    if (sc.ioException() != null) {
			        throw new ApplicationException("Exception Occurred while Reading File.");
			    }
			} catch (IOException | ApplicationException | InterruptedException e) {
				throw new ApplicationRuntimeException("Exception Occurred While reading line of file", e);
			} 
		}
	}
}
