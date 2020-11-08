package com.java.project.checkin.delete;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.java.project.checkin.client.SystemPathsClient;
import com.java.project.checkin.gui.CheckinMainFrame;
import com.java.project.checkin.read.ReadSystemPaths;



public class DeleteSystemPaths {

	private static Logger logger = Logger.getLogger(DeleteSystemPaths.class.getName());
	private static final String PATH_DELETED = "Path Borrado con Éxito!";
	private static final String PATH_DELETED_FAILED = "No es posible borrar Path";
	private static final String SELECT_PATH = "Seleccione Path en la Tabla";
	private static final String VALIDATION_UPDATE_TITLE = "Validacion";

	public static void deleteSystemPath() {
		int row;
		int systemPathId;
		int status = 0;
		try {
			row = CheckinMainFrame.tblPaths.getSelectedRow();
			if (row > -1) {
				systemPathId = Integer.parseInt(CheckinMainFrame.tblPaths.getValueAt(row, 0).toString());
				status = SystemPathsClient.deleteSystemPath(systemPathId);
				if (status > 0 && status < 300) {
					JOptionPane.showMessageDialog(null, PATH_DELETED, VALIDATION_UPDATE_TITLE,
							JOptionPane.INFORMATION_MESSAGE);
					ReadSystemPaths.fillAllPathsTable();
				} else {
					JOptionPane.showMessageDialog(null, PATH_DELETED_FAILED, VALIDATION_UPDATE_TITLE,
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, SELECT_PATH, VALIDATION_UPDATE_TITLE,
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception ex) {
			logger.error("ERROR AT DELETE PATHS: " + ex);
			JOptionPane.showMessageDialog(null, PATH_DELETED_FAILED+ " Exception Detected!", VALIDATION_UPDATE_TITLE,
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
