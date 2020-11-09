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
@Table(name="employees", schema="checkincontrol")
@Entity
@ToString
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idEmployee")
	Integer idEmployee;
	
	@Column(name="employeeName")
	String employeeName;
	
	@Column(name="address")
	String address;
	
	@Column(name="cellphone")
	String cellphone;
	
	@Column(name="role")
	String role;
	
	@Column(name="password")
	String password;
	
}
