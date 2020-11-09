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
@Table(name="checkin", schema="checkincontrol")
@Entity
@ToString
public class CheckIn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idCheckin")
	Integer idCheckin;
	
	@Column(name="employeeName")
	String employeename;
	
	@Column(name="role")
	String role;
	
	@Column(name="arrivalHour")
	String arrivalHour;
	
	@Column(name="arrivalDate")
	String arrivalDate;
	
	@Column(name="concept")
	String concept;

}
