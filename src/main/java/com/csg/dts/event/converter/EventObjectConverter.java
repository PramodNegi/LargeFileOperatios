package com.csg.dts.event.converter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.csg.dts.event.model.Event;
import com.csg.dts.execption.ApplicationException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EventObjectConverter implements Converter<String, Event>{
	private static final ObjectMapper JSON_OBJECT_MAPPER = new ObjectMapper();
	
	@Override
	public Event convert(String eventLine) throws ApplicationException {
		Event event;
		try {
			event = JSON_OBJECT_MAPPER.readValue(eventLine, Event.class);
		} catch (IOException e) {
			throw new ApplicationException("Exception Occured while converting log entry", e);
		}
		return event;
	}

}
