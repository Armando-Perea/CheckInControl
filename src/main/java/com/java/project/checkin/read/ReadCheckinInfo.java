package com.java.project.checkin.read;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.java.project.checkin.client.CheckInClient;
import com.java.project.checkin.gui.CheckinMainFrame;
import com.java.project.checkin.models.CheckIn;

public class ReadCheckinInfo {

	private static Logger logger = Logger.getLogger(ReadCheckinInfo.class.getName());
   	private static final String CHECKIN_EXCEPTION = "Excepcion al consultar Checkin Info";
	
	/**
	 * searchByStudentName search process to be shown at list table. {"IdEmail", "Descripcion","Contraseña"};
	 */ 
	public static void fillAllCheckinTable() {
		try {
			CheckIn[] checkInList ;
			checkInList = CheckInClient.getAllCheckIn();
			if(checkInList.length>0) {
				CheckinMainFrame.tableModelCheckin.setRowCount(0);
				for (CheckIn check : checkInList) {
					Object[] checkinItems = {check.getIdCheckin(),check.getConcept(),check.getEmployeename(),check.getRole(),check.getArrivalDate(),check.getArrivalHour()};
					CheckinMainFrame.tableModelCheckin.addRow(checkinItems);
				}
				CheckinMainFrame.scrollPaneCheckIn.setViewportView(CheckinMainFrame.tblCheckIn);
			}else {
				CheckinMainFrame.tableModelCheckin.setRowCount(0);
				}
		}catch(Exception ex) {
			logger.error("EXCEPCION AL CONSULTAR CHECKIN: "+ex);
			JOptionPane.showMessageDialog(null, CHECKIN_EXCEPTION);
		}
		
	}
}
