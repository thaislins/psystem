package com.example.pitangua.psystem.domain;

import java.util.Date;

public class Client {
	private Integer id;
	private Integer psychologistId;
	private String cpf;
	private String name;
	private Date birthDate;
	private String phone;
	private CepAddress cep;
	private String number;
	private String occupation;
	private String gender;
	private String bloodType;
	private String nationality;
	private String scholarity;

	public Client(Integer id, Integer psychologistId, String cpf, String name, Date birthDate, String phone,
			CepAddress cep, String number, String occupation, String gender, String bloodType, String nationality,
			String scholarity) {
		super();
		this.id = id;
		this.psychologistId = psychologistId;
		this.cpf = cpf;
		this.name = name;
		this.birthDate = birthDate;
		this.phone = phone;
		this.cep = cep;
		this.number = number;
		this.occupation = occupation;
		this.gender = gender;
		this.bloodType = bloodType;
		this.nationality = nationality;
		this.scholarity = scholarity;
	}

	public Integer getId() {
		return id;
	}

	public Integer getPsychologistId() {
		return psychologistId;
	}

	public void setPsychologistId(Integer psychologistId) {
		this.psychologistId = psychologistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getScholarity() {
		return scholarity;
	}

	public void setScholarity(String scholarity) {
		this.scholarity = scholarity;
	}
}
