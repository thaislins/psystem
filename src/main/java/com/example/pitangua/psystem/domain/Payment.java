package com.example.pitangua.psystem.domain;

public class Payment {
	private Integer id;
	private Integer psychologistId;
	private Integer clientId;
	private Integer value;
	private String date;
	private String notes;

	public Payment() {
		notes = "";
	}

	public Payment(Integer id, Integer psychologistId, Integer clientId, Integer value, String date, String notes) {
		this.id = id;
		this.psychologistId = psychologistId;
		this.clientId = clientId;
		this.value = value;
		this.date = date;
		this.notes = notes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "id: " + id + ", psychologistId: " + psychologistId + ", clientId: " + clientId + ", value: " + value
				+ ", date: " + date + ", notes: " + notes;
	}

}
