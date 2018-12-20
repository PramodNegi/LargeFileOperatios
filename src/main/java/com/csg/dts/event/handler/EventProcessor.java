package com.csg.dts.event.handler;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csg.dts.event.constants.Constants;
import com.csg.dts.event.converter.Converter;
import com.csg.dts.event.model.Event;
import com.csg.dts.event.service.EventService;
import com.csg.dts.execption.ApplicationException;
import com.csg.dts.processor.LargeFileProcessor;

@Component
public class EventProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(LargeFileProcessor.class);
	
	@Autowired
	private Converter<String, Event> eventObjectConverter;

	@Autowired
	private EventService eventService;
	
	public void startProcessing(BlockingQueue<String> blockingQueue) {
		Thread eventrocessorThread = new Thread(new LoggedEventProcessor(blockingQueue));
		eventrocessorThread.start();
	}

	private class LoggedEventProcessor implements Runnable {

		private BlockingQueue<String> blockingQueue;

		public LoggedEventProcessor(BlockingQueue<String> blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		@Override
		public void run() {
			try {
				String loggedEvent;
				while((loggedEvent = this.blockingQueue.take())!= Constants.EOF.value()){
				LOGGER.info("Processing Event : {}", loggedEvent);
					Event event = eventObjectConverter.convert(loggedEvent);
					processEvent(event);
				}
			} catch (InterruptedException | ApplicationException e) {
				LOGGER.error("Exception occurred while Retrieving/Removing Logged Event: {}", e); 
			}
			LOGGER.info("Events Processed: {}", eventService.getAllEvents());

		}

		private void processEvent(Event event) {
			Event persistedEvent = eventService.getEventById(event.getId());
			if (persistedEvent == null) {
				eventService.create(event);
			} else {
				Long timeStampDiff = getTimestampDiff(persistedEvent.getTimestamp(), event.getTimestamp());
				persistedEvent.setDuration(timeStampDiff);
				if(timeStampDiff >= 4) {
					persistedEvent.setAlert(true);
				}
				eventService.update(persistedEvent);
			}
		}

		private long getTimestampDiff(long timeStamp1, long timeStamp2) {
			if (timeStamp1 < timeStamp2) {
				return timeStamp2 - timeStamp1;
			}
			return timeStamp1 - timeStamp2;
		}
	}
}
