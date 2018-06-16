package com.example.pitangua.psystem.domain;

public class Clinic {
	private Integer id;
	private String name;
	private String phone;
	private CepAddress cep;
	private String number;

	public Clinic() {
	}

	public Clinic(Integer id, String name, String phone, CepAddress cep, String number) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.cep = cep;
		this.number = number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public CepAddress getCep() {
		return cep;
	}

	public void setCep(CepAddress cep) {
		this.cep = cep;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
