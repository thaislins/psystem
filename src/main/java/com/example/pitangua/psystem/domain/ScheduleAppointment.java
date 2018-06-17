package com.example.pitangua.psystem.domain;

import java.util.Date;

public class ScheduleAppointment {
	private Integer id;
	private Integer psychologistId;
	private Integer clientId;
	private Date date;

	public ScheduleAppointment(Integer id, Integer psychologistId, Integer clientId, Date date) {
		super();
		this.id = id;
		this.psychologistId = psychologistId;
		this.clientId = clientId;
		this.date = date;
	}

	public Integer getPsychologistId() {
		return psychologistId;
	}

	public void setPsychologistId(Integer psychologistId) {
		this.psychologistId = psychologistId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
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
