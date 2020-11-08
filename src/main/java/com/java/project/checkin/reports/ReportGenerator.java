package com.java.project.checkin.reports;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import org.apache.log4j.Logger;

import com.java.project.checkin.client.SystemPathsClient;
import com.java.project.checkin.mail.SendCheckinMail;
import com.java.project.checkin.models.SystemPaths;
import com.java.project.checkin.utils.CheckinConstants;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportGenerator {
	
	public static Logger logger = Logger.getLogger(ReportGenerator.class.getName());
	
	@SuppressWarnings("deprecation")
	public static void checkinReport() throws SQLException{
		ReportConnection cn = new ReportConnection();
	    JasperReport jr = null;
	       try{
	       Map<String, Object> parameters = new HashMap<>();
    	   InputStream inputStream = ReportGenerator.class.getClassLoader().getResourceAsStream(CheckinConstants.PATH_IMAGE);
    	   parameters.put("makeUpImage", ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream))));
    	   InputStream is = ReportGenerator.class.getClassLoader().getResourceAsStream(CheckinConstants.JASPER_PATH_CHECKIN);
    	   jr = (JasperReport) JRLoader.loadObject(is);
    	   JasperPrint jp = JasperFillManager.fillReport(jr, parameters,cn.conexion());
	       JRExporter exp = new JRPdfExporter();
	       exp.setParameter(JRExporterParameter.JASPER_PRINT, jp);
	       exp.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(CheckinConstants.CHECKIN_PATH));
	       exp.exportReport();
	       }catch(JRException | IOException ex){
	           java.util.logging.Logger.getLogger(ReportGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	           logger.info(CheckinConstants.REPORT_GENERATION_ERROR+" "+ex);
	       } 
	    }   
	
	@SuppressWarnings("deprecation")
	public static void employeeReport() throws SQLException{
		ReportConnection cn = new ReportConnection();
	    JasperReport jr = null;
	       try{
	       Map<String, Object> parameters = new HashMap<>();
    	   InputStream inputStream = ReportGenerator.class.getClassLoader().getResourceAsStream(CheckinConstants.PATH_IMAGE);
    	   parameters.put("makeUpImage", ImageIO.read(new ByteArrayInputStream(JRLoader.loadBytes(inputStream))));
    	   InputStream is = ReportGenerator.class.getClassLoader().getResourceAsStream(CheckinConstants.JASPER_PATH_EMPLOYEE);
    	   jr = (JasperReport) JRLoader.loadObject(is);
    	   JasperPrint jp = JasperFillManager.fillReport(jr, parameters,cn.conexion());
	       JRExporter exp = new JRPdfExporter();
	       exp.setParameter(JRExporterParameter.JASPER_PRINT, jp);
	       exp.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(CheckinConstants.EMPLOYEE_PATH));
	       exp.exportReport();
	       }catch(JRException | IOException ex){
	           java.util.logging.Logger.getLogger(ReportGenerator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	           logger.info(CheckinConstants.REPORT_GENERATION_ERROR+" "+ex);
	       } 
	    }  
	
	public static boolean generateAndSendReport() {
		try {
			ReportGenerator.checkinReport();
			ReportGenerator.employeeReport();
			SendCheckinMail.sendMail();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
//	public static void main(String []args) throws SQLException{
//		List<SystemPaths> systemPathsList = Arrays.asList(SystemPathsClient.getAllSystemPaths());
//		if(!systemPathsList.isEmpty()) {
//			CheckinConstants.CHECKIN_PATH = systemPathsList.get(0).getCheckinPdf();
//			CheckinConstants.EMPLOYEE_PATH = systemPathsList.get(0).getEmployeePdf();
//		}
//		ReportGenerator.checkinReport();
//		ReportGenerator.employeeReport();
//	} 
	
}
