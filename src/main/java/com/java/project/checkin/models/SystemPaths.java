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
@Table(name="systempaths", schema="checkincontrol")
@Entity
@ToString
public class SystemPaths {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idPath")
	Integer idPath;
	
	@Column(name="checkinPdf")
	String checkinPdf;
	
	@Column(name="employeePdf")
	String employeePdf;
	
}

