package com.java.project.checkin.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Table(name="emailConfig", schema="checkincontrol")
@Entity
@ToString
public class EmailConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idEmail")
	Integer idEmail;
	
	@Column(name="email")
	String email;
	
	@Column(name="password")
	String password;
	
	@Column(name="isActiveService")
	Boolean isActiveService;
	
	
}
