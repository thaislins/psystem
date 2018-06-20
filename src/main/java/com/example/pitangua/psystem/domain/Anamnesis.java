package com.example.pitangua.psystem.domain;

public class Anamnesis {
	private Integer clientId;
	private String date;
	private String humor;
	private String perception;
	private String desire;
	private String information;
	private String language;
	private String appeareance;
	private String thought;
	private String affection;

	public Anamnesis() {
	}

	public Anamnesis(Integer clientId, String date, String humor, String perception, String desire, String information,
			String language, String appeareance, String thought, String affection) {
		this.clientId = clientId;
		this.date = date;
		this.humor = humor;
		this.perception = perception;
		this.desire = desire;
		this.information = information;
		this.language = language;
		this.appeareance = appeareance;
		this.thought = thought;
		this.affection = affection;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHumor() {
		return humor;
	}

	public void setHumor(String humor) {
		this.humor = humor;
	}

	public String getPerception() {
		return perception;
	}

	public void setPerception(String perception) {
		this.perception = perception;
	}

	public String getDesire() {
		return desire;
	}

	public void setDesire(String desire) {
		this.desire = desire;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAppeareance() {
		return appeareance;
	}

	public void setAppeareance(String appeareance) {
		this.appeareance = appeareance;
	}

	public String getThought() {
		return thought;
	}

	public void setThought(String thought) {
		this.thought = thought;
	}

	public String getAffection() {
		return affection;
	}

	public void setAffection(String affection) {
		this.affection = affection;
	}
}
