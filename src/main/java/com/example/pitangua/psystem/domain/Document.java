package com.example.pitangua.psystem.domain;

import java.util.Date;

public class Document {
	private Integer id;
	private Integer psychologist_id;
	private Integer client_id;
	private Integer type;
	private Date date;
	private String text;
	
	public Document(Integer id, Integer psychologist_id, Integer client_id, Integer type, Date date, String text) {
		super();
		this.id = id;
		this.psychologist_id = psychologist_id;
		this.client_id = client_id;
		this.type = type;
		this.date = date;
		this.text = text;
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
