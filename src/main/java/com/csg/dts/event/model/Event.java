package com.csg.dts.event.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name = "EVENTS")
@NamedQueries(value={@NamedQuery(name=Event.FIND_ALL_ALERTED_EVENTS, query="SELECT E FROM Event E where E.alert=true"),
				@NamedQuery(name=Event.FIND_ALL_EVENTS, query="SELECT E FROM Event E"),
				@NamedQuery(name=Event.FIND_ALL_ALERTED_EVENTS_MAX_DURATION, query="SELECT E FROM Event E where E.alert = true and E.duration = (SELECT MAX(E2.duration) FROM Event E2)")})
public class Event {

	public static final String FIND_ALL_EVENTS="findAllEvents";
	public static final String FIND_ALL_ALERTED_EVENTS="findAllAlerted";
	public static final String FIND_ALL_ALERTED_EVENTS_MAX_DURATION="findAllAlertedWithMaxDuration";
	
	
	@Id
	@Column(name = "EVENT_ID", nullable = false, unique=true)
	private String id;

	@Column(name = "TIMESTAMP", nullable = false)
	private Long timestamp;

	@Column(name = "HOST", nullable = true)
	private String host;

	@Column(name = "TYPE", nullable = true)
	private String type;

	@Column(name = "DURATION", nullable = true)
	private Long duration;

	@Column(name = "ALERT", nullable = true)
	private Boolean alert;

	public String getId() {
		return this.id;
	}

	public void setEventId(String id) {
		this.id = id;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Boolean getAlert() {
		return alert;
	}

	public void setAlert(Boolean alert) {
		this.alert = alert;
	}

	@Override
	public String toString() {
		return "Event [id= " + id + ", duration= " + duration + ", alert= " + alert + "]";
	}
	
	

}
