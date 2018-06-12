package com.example.pitangua.psystem.domain;

import java.util.Date;

public class Client {
    private Integer id;            
    private String pcpf;          
    private String cpf;           
    private String name;          
    private Date birth_date;    
    private String phone;         
    private CepAdress cep;           
    private String number;        
    private String occupation;    
    private String gender;        
    private String blood_type;    
    private String nationality;   
    private String scholarity;
    
	public Client(Integer id, String pcpf, String cpf, String name, Date birth_date, String phone, CepAdress cep,
			String number, String occupation, String gender, String blood_type, String nationality, String scholarity) {
		super();
		this.id = id;
		this.pcpf = pcpf;
		this.cpf = cpf;
		this.name = name;
		this.birth_date = birth_date;
		this.phone = phone;
		this.cep = cep;
		this.number = number;
		this.occupation = occupation;
		this.gender = gender;
		this.blood_type = blood_type;
		this.nationality = nationality;
		this.scholarity = scholarity;
	}

	public Integer getId() {
		return id;
	}

	public String getPcpf() {
		return pcpf;
	}

	public String getCpf() {
		return cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
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

	public String getBlood_type() {
		return blood_type;
	}

	public void setBlood_type(String blood_type) {
		this.blood_type = blood_type;
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
