-- -----------------------------------------------------
-- Schema pSystem
-- -----------------------------------------------------
CREATE DATABASE psystem_db CHARACTER SET utf8 COLLATE utf8_general_ci;
USE psystem_db;

-- -----------------------------------------------------
-- Table cep_address
-- -----------------------------------------------------
CREATE TABLE cep_address (
  cep CHAR(8) NOT NULL,
  street VARCHAR(255) NULL,
  city VARCHAR(255) NULL,
  state VARCHAR(255) NULL,
  PRIMARY KEY (cep)
);


-- -----------------------------------------------------
-- Table clinic
-- -----------------------------------------------------
CREATE TABLE clinic (
  id		INT				NOT NULL	AUTO_INCREMENT,
  name		VARCHAR(255)	NULL,
  phone		VARCHAR(255)	NULL,
  cep		CHAR(8)			NULL,
  number	VARCHAR(10)		NULL,
  PRIMARY KEY (id),
  INDEX fk_clinic_cep_idx (cep ASC),
  CONSTRAINT fk_clinic_cep
    FOREIGN KEY (cep)
    REFERENCES cep_address (cep)
    ON DELETE RESTRICT
);


-- -----------------------------------------------------
-- Table user
-- -----------------------------------------------------
CREATE TABLE user (
  id			INT 			NOT NULL	AUTO_INCREMENT,
  cpf			CHAR(11)		NULL,
  clinic_id		INT				NOT NULL,
  name			VARCHAR(255)	NULL,
  email			VARCHAR(255)	NOT NULL,
  password		VARCHAR(255)	NULL,
  phone			VARCHAR(20)		NULL,
  ADMIN			TINYINT(1)		NULL		DEFAULT 0,
  PSYCHOLOGIST	TINYINT(1)		NULL,
  crp			CHAR(6)			NULL,
  PRIMARY KEY (id),
  INDEX fk_user_clinic_idx (clinic_id ASC),
  UNIQUE INDEX crp_UNIQUE (crp ASC),
  UNIQUE INDEX cpf_UNIQUE (cpf ASC),
  UNIQUE INDEX email_UNIQUE (email ASC),
  CONSTRAINT fk_user_clinic
    FOREIGN KEY (clinic_id)
    REFERENCES clinic (id)
    ON DELETE RESTRICT
);


-- -----------------------------------------------------
-- Table client
-- -----------------------------------------------------
CREATE TABLE client (
  id			INT				NOT NULL	AUTO_INCREMENT,
  pcpf			CHAR(11)		NOT NULL,
  cpf			CHAR(11) 		NULL,
  name			VARCHAR(255)	NULL,
  birth_date	DATE			NULL,
  phone			VARCHAR(20)		NULL,
  cep			CHAR(8)			NULL,
  number		VARCHAR(10)		NULL,
  occupation	VARCHAR(255)	NULL,
  gender		CHAR			NULL,
  blood_type	VARCHAR(3)		NULL,
  nationality	VARCHAR(255)	NULL,
  scholarity	VARCHAR(255)	NULL,
  PRIMARY KEY (id),
  INDEX fk_client_new_user1_idx (pcpf ASC),
  INDEX fk_client_cep_1_idx (cep ASC),
  UNIQUE INDEX cpf_UNIQUE (cpf ASC),
  CONSTRAINT fk_client_user
    FOREIGN KEY (pcpf)
    REFERENCES user (cpf)
    ON DELETE RESTRICT,
  CONSTRAINT fk_client_cep
    FOREIGN KEY (cep)
    REFERENCES cep_address (cep)
    ON DELETE RESTRICT
);


-- -----------------------------------------------------
-- Table anamnesis
-- -----------------------------------------------------
CREATE TABLE anamnesis (
  client_id		INT				NOT NULL,
  date			DATE			NULL,
  humor			VARCHAR(255)	NULL,
  perception	VARCHAR(255)	NULL,
  desire		VARCHAR(255)	NULL,
  information	VARCHAR(255)	NULL,
  language		VARCHAR(255)	NULL,
  appeareance	VARCHAR(255)	NULL,
  thought		VARCHAR(255)	NULL,
  affection		VARCHAR(255)	NULL,
  PRIMARY KEY (client_id),
  CONSTRAINT fk_anamnesis_client
    FOREIGN KEY (client_id)
    REFERENCES client (id)
    ON DELETE CASCADE
);


-- -----------------------------------------------------
-- Table document
-- -----------------------------------------------------
CREATE TABLE document (
  id				INT			NOT NULL	AUTO_INCREMENT,
  psychologist_id	INT			NOT NULL,
  client_id			INT			NULL,
  type				INT			NULL,
  date				DATETIME	NULL,
  text				TEXT		NULL,
  PRIMARY KEY (id),
  INDEX fk_document_client_idx (client_id ASC),
  INDEX fk_document_user_idx (psychologist_id ASC),
  CONSTRAINT fk_document_user
    FOREIGN KEY (psychologist_id)
    REFERENCES user (id)
    ON DELETE CASCADE,
  CONSTRAINT fk_document_client
    FOREIGN KEY (client_id)
    REFERENCES client (id)
    ON DELETE SET NULL
);


-- -----------------------------------------------------
-- Table payment
-- -----------------------------------------------------
CREATE TABLE payment (
  id				INT				NOT NULL	AUTO_INCREMENT,
  psychologist_id	INT				NOT NULL,
  client_id			INT				NULL,
  value				INT				NULL,
  date				DATE			NULL,
  notes				VARCHAR(255)	NULL,
  PRIMARY KEY (id),
  INDEX fk_payment_client_idx (client_id ASC),
  INDEX fk_payment_user_idx (psychologist_id ASC),
  CONSTRAINT fk_payment_user
    FOREIGN KEY (psychologist_id)
    REFERENCES user (id)
    ON DELETE CASCADE,
  CONSTRAINT fk_payment_client
    FOREIGN KEY (client_id)
    REFERENCES client (id)
    ON DELETE SET NULL
);


-- -----------------------------------------------------
-- Table appointment_notes
-- -----------------------------------------------------
CREATE TABLE appointment_notes (
  id	INT			NOT NULL	AUTO_INCREMENT,
  notes	TEXT		NULL,
  date	DATETIME	NULL,
  PRIMARY KEY (id)
);


-- -----------------------------------------------------
-- Table schedule_appointment
-- -----------------------------------------------------
CREATE TABLE schedule_appointment (
  id				INT			NOT NULL	AUTO_INCREMENT,
  psychologist_id	INT			NOT NULL,
  client_id			INT			NOT NULL,
  date				DATETIME	NULL,
  PRIMARY KEY (id),
  INDEX fk_sched_appt_client_idx (client_id ASC),
  INDEX fk_sched_appt_user_idx (psychologist_id ASC),
  CONSTRAINT fk_sched_appt_user
    FOREIGN KEY (psychologist_id)
    REFERENCES user (id)
    ON DELETE CASCADE,
  CONSTRAINT fk_schedule_appointment_client
    FOREIGN KEY (client_id)
    REFERENCES client (id)
    ON DELETE CASCADE
);


-- -----------------------------------------------------
-- Table schedule_appointment_notes
-- -----------------------------------------------------
CREATE TABLE schedule_appointment_notes (
  sched_appt_id	INT	NOT NULL,
  appt_notes_id	INT	NOT NULL,
  INDEX fk_appt_notes_idx (appt_notes_id ASC),
  PRIMARY KEY (sched_appt_id, appt_notes_id),
  INDEX fk_sched_appt_idx (sched_appt_id ASC),
  CONSTRAINT fk_appt_notes
    FOREIGN KEY (appt_notes_id)
    REFERENCES appointment_notes (id)
    ON DELETE CASCADE,
  CONSTRAINT fk_sched_appt
    FOREIGN KEY (sched_appt_id)
    REFERENCES schedule_appointment (id)
    ON DELETE CASCADE
);


-- INSERT FIRST CLINIC
START TRANSACTION;
INSERT INTO cep_address VALUES("59015000", "Av. Senador Salgado Filho", "Natal", "Rio Grande do Norte");
INSERT INTO clinic(name, phone, cep, number) VALUES("Clinic", "84 9 9999-9999", "59015000", "123");
COMMIT;

-- INSERT FIRST USER (with an encrypted password - 123456)
INSERT INTO user(cpf, clinic_id, name, email, password, phone, ADMIN, PSYCHOLOGIST, crp)
VALUES("11111111111", 1, "John Doe", "john@mail.com", "$2a$10$MGw3Gp4uL1jR9s2RNDYODekYaSEmNHjpE.m/X7leE0YVLLMVETfZq", "11 1 1111-1111", true, true, "111111");
