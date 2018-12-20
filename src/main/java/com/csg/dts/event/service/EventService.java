package com.csg.dts.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.dts.event.dao.EventDAO;
import com.csg.dts.event.model.Event;

@Service
public class EventService {
	
	@Autowired
	private EventDAO eventDAO;
	
	@Transactional(readOnly=true)
	public List<Event> getAllAlertedEvents(){
		return eventDAO.getAllAlertedEvents();
	}

	
	@Transactional(readOnly=true)
	public List<Event> getAllEvents(){
		return eventDAO.getAllEvents();
	}
	
	@Transactional
	public void create(Event event) {
		eventDAO.create(event);
	}
	
	@Transactional
	public void update(Event event) {
		eventDAO.update(event);
	}
	
	@Transactional(readOnly=true)
	public Event getEventById(String evetId) {
		return eventDAO.getEventById(evetId);
	}
	
}
