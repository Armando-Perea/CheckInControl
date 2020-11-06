package com.java.project.checkin.reports;

import java.sql.Connection;
import java.sql.DriverManager;


import org.apache.log4j.Logger;

public class ReportConnection {

	public static Logger logger = Logger.getLogger(ReportConnection.class.getName());
	
	Connection connect;

	public Connection conexion() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:33061/checkincontrol?useSSL=false", "root", "mysql");
		} catch (Exception e) {
			logger.info("Connection Report Exception: "+e.getMessage());
		}
		return connect;
	}

	public void desconectar() {
		connect = null;
	}

	public static void main(String[] args) {
		ReportConnection o = new ReportConnection();
		o.conexion();
	}
}

