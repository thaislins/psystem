package com.example.pitangua.psystem.domain;

public class AppointmentNote {

	private Integer id;
	private String notes;
	private String date;
	private ScheduleAppointment appointment;

	public AppointmentNote() {

	}

	public AppointmentNote(Integer id, String notes, String date) {
		super();
		this.id = id;
		this.notes = notes;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ScheduleAppointment getAppointment() {
		return appointment;
	}

	public void setAppointment(ScheduleAppointment appointment) {
		this.appointment = appointment;
	}

}
