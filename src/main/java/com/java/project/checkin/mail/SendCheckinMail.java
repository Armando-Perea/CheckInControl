package com.java.project.checkin.mail;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.java.project.checkin.client.EmailConfigClient;
import com.java.project.checkin.client.SystemPathsClient;
import com.java.project.checkin.models.EmailConfig;
import com.java.project.checkin.models.SystemPaths;
import com.java.project.checkin.utils.CheckinConstants;

public class SendCheckinMail {

	public static Logger logger = Logger.getLogger(SendCheckinMail.class.getName());

	public static boolean sendMail() throws Exception {

		String bodyClosureMail;
		boolean sentOk = true;
		bodyClosureMail = "";
		EmailConfig emailConfig = EmailConfigClient.getEmailConfigById(1);
		System.out.println("EMAIL CONFIG: "+emailConfig.toString());
		SystemPaths paths = SystemPathsClient.getSystemPathById(1);
		Properties props = new Properties();
		props.put(MailAuthData.SMPT_AUTH, MailAuthData.SMTP_ENABLE);
		props.put(MailAuthData.SMPT_STARTTLS, MailAuthData.SMTP_ENABLE);
		props.put(MailAuthData.SMPT_HOST, MailAuthData.GMAIL_HOST);
		props.put(MailAuthData.SMTP_PORT, MailAuthData.SMTP_PORT_NUMBER);
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				try {
					return new PasswordAuthentication(EncryptSecurity.decrypt(emailConfig.getEmail()),
							EncryptSecurity.decrypt(emailConfig.getPassword()));
				} catch (Exception e) {
					logger.error("Exception: "+e.getMessage());
					logger.error(MailAuthData.FAIL_AUTH);
				}
				return null;
			}
		});
		try {
			BodyPart messageBodyPart = new MimeBodyPart();
			BodyPart messageBodyPartCheckin = new MimeBodyPart();
			BodyPart messageBodyPartEmployee = new MimeBodyPart();

			messageBodyPart.setText(bodyClosureMail);

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messageBodyPart);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(EncryptSecurity.decrypt(emailConfig.getEmail())));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(EncryptSecurity.decrypt(emailConfig.getEmail())));
			message.setSubject(MailAuthData.CLOSURE_NOTIF);
			message.setText(bodyClosureMail);

			DataSource sourceIncomes = new FileDataSource(CheckinConstants.CHECKIN_PATH);
			DataSource sourceOutgoings = new FileDataSource(CheckinConstants.EMPLOYEE_PATH);

			messageBodyPartCheckin.setDataHandler(new DataHandler(sourceIncomes));
			messageBodyPartEmployee.setDataHandler(new DataHandler(sourceOutgoings));

			messageBodyPartCheckin.setFileName(MailAuthData.CHECKIN_REPORT);
			messageBodyPartEmployee.setFileName(MailAuthData.EMPLOYEE_REPORT);

			multipart.addBodyPart(messageBodyPartCheckin);
			multipart.addBodyPart(messageBodyPartEmployee);

			message.setContent(multipart);
			Transport.send(message);
			logger.info("Emails Successfully Sent!");
			System.out.println("Emails Successfully Sent!");
		} catch (MessagingException e) {
			logger.error("Exception Sending Mail: "+e.getMessage());
			sentOk = false;
			return sentOk;
		}
		return sentOk;
	}

//	public static void main(String[] args) throws Exception {
//		//LocalDateTime myDateObj = LocalDateTime.now();
//		List<SystemPaths> systemPathsList = Arrays.asList(SystemPathsClient.getAllSystemPaths());
//		if(!systemPathsList.isEmpty()) {
//			CheckinConstants.CHECKIN_PATH = systemPathsList.get(0).getCheckinPdf();
//			CheckinConstants.EMPLOYEE_PATH = systemPathsList.get(0).getEmployeePdf();
//		}
//		SendCheckinMail.sendMail();
//	}
	
}
