package com.example.pitangua.psystem.domain;

public class Clinic {
	private Integer id;
	private String name;
	private String phone;
	private CepAdress cep;
	private Integer number;
	
	public Clinic(Integer id, String name, String phone, CepAdress cep, Integer number) {
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

	public CepAdress getCep() {
		return cep;
	}

	public void setCep(CepAdress cep) {
		this.cep = cep;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
