package com.example.pitangua.psystem.domain;

import java.sql.Date;

public class Document {
	private Integer id;
	private Integer psychologistId;
	private Integer clientId;
	private Integer type;
	private Date date;
	private String text;

	public Document(Integer id, Integer psychologistId, Integer clientId, Integer type, Date date, String text) {
		super();
		this.id = id;
		this.psychologistId = psychologistId;
		this.clientId = clientId;
		this.type = type;
		this.date = date;
		this.text = text;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getId() {
		return id;
	}
}
