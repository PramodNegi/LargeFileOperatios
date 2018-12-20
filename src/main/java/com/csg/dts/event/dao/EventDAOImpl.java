package com.csg.dts.event.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.csg.dts.event.model.Event;

@Repository("eventDAOImpl")
public class EventDAOImpl implements EventDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void create(Event event) {
		entityManager.persist(event);
	}

	@Override
	public void update(Event event) {
		entityManager.merge(event);
	}

	@Override
	public Event getEventById(String evetId) {
		return entityManager.find(Event.class, evetId);
	}

	@Override
	public List<Event> getAllAlertedEvents() {
		TypedQuery<Event> query = entityManager.createNamedQuery(Event.FIND_ALL_ALERTED_EVENTS, Event.class);
		List<Event> eventsList = (List<Event>)query.getResultList();
		return eventsList;
	}

	@Override
	public List<Event> getAllEvents() {
		TypedQuery<Event> query = entityManager.createNamedQuery(Event.FIND_ALL_EVENTS, Event.class);
		List<Event> eventsList = (List<Event>)query.getResultList();
		return eventsList;
	}
}
