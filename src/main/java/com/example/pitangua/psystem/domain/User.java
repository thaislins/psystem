package com.example.pitangua.psystem.domain;

public class User {
	private Integer id;
	private String cpf;
	private Integer clinicId;
	private String name;
	private String email;
	private String password;
	private String phone;
	private boolean admin;
	private boolean psychologist;
	private String crp;

	private String passwordConfirm;

	public User() {
	}

	public User(String cpf, Integer clinicId, String name, String email, String password, String phone,
			boolean admin, boolean psychologist, String crp) {
		this.cpf = cpf;
		this.clinicId = clinicId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.admin = admin;
		this.psychologist = psychologist;
		this.crp = crp;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public boolean isPsychologist() {
		return psychologist;
	}

	public void setPsychologist(boolean psychologist) {
		this.psychologist = psychologist;
	}

	public String getCrp() {
		return crp;
	}

	public void setCrp(String crp) {
		this.crp = crp;
	}

	public Integer getId() {
		return id;
	}

	public Integer getClinicId() {
		return clinicId;
	}

	public void setClinicId(Integer clinicId) {
		this.clinicId = clinicId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
