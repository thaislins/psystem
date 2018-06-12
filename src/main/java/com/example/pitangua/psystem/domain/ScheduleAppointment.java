package com.example.pitangua.psystem.domain;

import java.util.Date;

public class ScheduleAppointment {
	private Integer id;
	private Integer psychologist_id;
	private Integer client_id;
	private Date date;
	
	public ScheduleAppointment(Integer id, Integer psychologist_id, Integer client_id, Date date) {
		super();
		this.id = id;
		this.psychologist_id = psychologist_id;
		this.client_id = client_id;
		this.date = date;
	}

	public Integer getPsychologist_id() {
		return psychologist_id;
	}

	public void setPsychologist_id(Integer psychologist_id) {
		this.psychologist_id = psychologist_id;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}
}
