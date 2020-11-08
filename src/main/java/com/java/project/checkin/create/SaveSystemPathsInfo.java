package com.java.project.checkin.create;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import com.java.project.checkin.client.SystemPathsClient;
import com.java.project.checkin.gui.CheckinMainFrame;
import com.java.project.checkin.models.SystemPaths;
import com.java.project.checkin.read.ReadSystemPaths;

public class SaveSystemPathsInfo {

	private static Logger logger = Logger.getLogger(SaveSystemPathsInfo.class.getName());
	public final static String PATH_SAVE_SUCCESSFULLY = "Path guardado con éxito!";
	public final static String PATH_SAVE_FAILED = "No es posible guardar path en este momento";
	public final static String PATH_SAVE_EXC = "Ocurrio una Excepcion al intentar guardar Path";

	public static void createNewEmailConfig() {
		SystemPaths systemPaths = new SystemPaths();
		try {
			systemPaths.setIdPath(0);
			systemPaths.setCheckinPdf(CheckinMainFrame.txtNewCheckinPdfPath.getText());
			systemPaths.setEmployeePdf(CheckinMainFrame.txtNewEmployPdfPath.getText());
			systemPaths = SystemPathsClient.addSystemPath(systemPaths);
			if (systemPaths != null) {
				JOptionPane.showMessageDialog(null, PATH_SAVE_SUCCESSFULLY);
				clearNewPathsSaveFields();
				ReadSystemPaths.fillAllPathsTable();
			} else {
				JOptionPane.showMessageDialog(null, PATH_SAVE_FAILED);
			}
		} catch (Exception ex) {
			logger.error("ERROR: " + ex);
			JOptionPane.showMessageDialog(null, PATH_SAVE_EXC);
		}

	}

	public static boolean validateNewPathsSaveFields() {
		if (CheckinMainFrame.txtNewCheckinPdfPath.getText().isEmpty() || 
				CheckinMainFrame.txtNewEmployPdfPath.getText().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	private static void clearNewPathsSaveFields() {
		CheckinMainFrame.txtNewCheckinPdfPath.setText(null);
		CheckinMainFrame.txtNewEmployPdfPath.setText(null);
	}
}
