package com.csg.dts.event.dao;

import java.util.List;

import com.csg.dts.event.model.Event;

public interface EventDAO {

	void create(Event event);
	
	void update(Event event);
	
	Event getEventById(String evetId);
	
	List<Event> getAllAlertedEvents();
	
	List<Event> getAllEvents();

	List<Event> getEventsWithMaxDuration();
}
