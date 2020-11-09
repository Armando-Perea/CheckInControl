package com.java.project.checkin;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.java.project.checkin.gui.CheckinMainFrame;
import com.java.project.checkin.models.SystemPaths;
import com.java.project.checkin.utils.CheckinConstants;
import com.java.project.checkin.repo.impl.SystemPathsRepoImpl;

/**
 * CheckinApp!
 *
 */
@SpringBootApplication
public class CheckinApp {
	
	public static final String SYSTEM_URL = "http://localhost:3001/checkincontrol/system";
	
	@Autowired
	SystemPathsRepoImpl systemPathsRepoImpl;
	
    public static void main( String[] args ) {
    	    SpringApplication.run(CheckinApp.class, args);
    	    initializeMainFrame();
    }
    
    @PostConstruct
	private void initializeSystemPaths() {
		List<SystemPaths> systemPathsList = systemPathsRepoImpl.getAllSystemPaths();
		if(!systemPathsList.isEmpty()) {
			CheckinConstants.CHECKIN_PATH = systemPathsList.get(0).getCheckinPdf();
			CheckinConstants.EMPLOYEE_PATH = systemPathsList.get(0).getEmployeePdf();
		}
	}
    
    
	private static void initializeMainFrame() {
    	System.setProperty("java.awt.headless", "false");
    	 SwingUtilities.invokeLater(() -> {
 	    	CheckinMainFrame checkin = new CheckinMainFrame();
 	    	checkin.setDefaultCloseOperation(CheckinMainFrame.EXIT_ON_CLOSE);
 	    	checkin.setVisible(true);
 	    });
	}
}
