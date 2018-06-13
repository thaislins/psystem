package com.example.pitangua.psystem.domain;

public class CepAddress {

	private String cep;
	private String street;
	private String city;
	private String state;
	
	public CepAddress(String cep, String street, String city, String state) {
		super();
		this.cep = cep;
		this.street = street;
		this.city = city;
		this.state = state;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
