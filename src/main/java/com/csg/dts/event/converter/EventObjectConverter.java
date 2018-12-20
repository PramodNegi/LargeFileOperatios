package com.csg.dts.event.converter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.csg.dts.event.model.Event;
import com.csg.dts.execption.ApplicationException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EventObjectConverter implements Converter<String, Event>{
//	https://www.google.com/search?ei=x-YTXOytGI_-9QOB14egCg&q=invoke+method+after+component+initialization+inSpring&oq=invoke+method+after+component+initialization+inSpring&gs_l=psy-ab.3..33i160l2j33i10.98211.118822..119145...6.0..0.182.4624.31j16......0....1..gws-wiz.......0j0i71j0i131j0i67j0i131i67j0i22i30j0i22i10i30j33i22i29i30j33i21.DcZpt0BFRMg
//	https://www.baeldung.com/running-setup-logic-on-startup-in-spring
//	https://github.com/eugenp/tutorials/tree/master/spring-boot
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
