package com.java.project.checkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.java.project.checkin.repo.impl.SystemPathsRepoImpl;

/**
 * CheckinApp!
 *
 */
@SpringBootApplication
public class CheckinApp {
	
	@Autowired
	SystemPathsRepoImpl systemPathsRepoImpl;
	
    public static void main( String[] args ) {
    	    SpringApplication.run(CheckinApp.class, args);
    }
    
    
}
