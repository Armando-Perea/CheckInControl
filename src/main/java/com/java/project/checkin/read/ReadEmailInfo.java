package com.java.project.checkin.read;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.java.project.checkin.client.EmailConfigClient;
import com.java.project.checkin.gui.CheckinMainFrame;
import com.java.project.checkin.models.EmailConfig;

public class ReadEmailInfo {

	private static Logger logger = Logger.getLogger(ReadEmailInfo.class.getName());
	private static final String CHECKIN_EXCEPTION = "Excepcion al consultar Email Info";

	public static void fillAllEmailTable() {
		try {
			EmailConfig[] emailConfigList;
			emailConfigList = EmailConfigClient.getAllEmailConfig();
			if (emailConfigList.length > 0) {
				CheckinMainFrame.tableModelEmailConfig.setRowCount(0);
				for (EmailConfig emailConfig : emailConfigList) {
					Object[] emailConfigItems = { emailConfig.getIdEmail(), emailConfig.getEmail(),
							emailConfig.getPassword(), emailConfig.getIsActiveService() };
					CheckinMainFrame.tableModelEmailConfig.addRow(emailConfigItems);
				}
				CheckinMainFrame.scrollEmailPane.setViewportView(CheckinMainFrame.tblEmail);
			} else {
				CheckinMainFrame.tableModelEmailConfig.setRowCount(0);
			}
		} catch (Exception ex) {
			logger.error("EXCEPCION AL CONSULTAR EMPLOYEES: " + ex);
			JOptionPane.showMessageDialog(null, CHECKIN_EXCEPTION);
		}

	}

}
