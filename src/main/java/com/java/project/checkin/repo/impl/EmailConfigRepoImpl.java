package com.java.project.checkin.repo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.project.checkin.models.EmailConfig;
import com.java.project.checkin.repository.EmailConfigRepository;

@Repository
public class EmailConfigRepoImpl {

	@Autowired
	EmailConfigRepository emailConfigRepository;
	
	public List<EmailConfig> getAllEmailConfig() {
		return (List<EmailConfig>) emailConfigRepository.findAll();
	}
	
	public Optional<EmailConfig> getEmailConfigById(Integer id) {
		return emailConfigRepository.findById(id);
	}
	
	public EmailConfig saveEmailConfig(EmailConfig emailConfig) {
		return emailConfigRepository.save(emailConfig);
	}
	
	public EmailConfig updateEmailConfig(EmailConfig emailConfig) {
		return emailConfigRepository.save(emailConfig);
	}
	
	public void deleteEmailConfig(Integer id) {
		emailConfigRepository.deleteById(id);
	}
	
	public void truncateEmailConfig() {
		emailConfigRepository.truncateEmailConfig();
	}
	
}

