package com.java.project.checkin.repo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.project.checkin.models.CheckIn;
import com.java.project.checkin.repository.CheckInRepository;


@Repository
@Transactional
public class CheckInRepoImpl {
	
	@Autowired
	CheckInRepository checkInRepository;
	
	public List<CheckIn> getAllCheckIn() {
		return (List<CheckIn>) checkInRepository.findAll();
	}
	
	public Optional<CheckIn> getCheckInById(Integer id) {
		return checkInRepository.findById(id);
	}
	
	public List<CheckIn> getCheckInByName(String name) {
		return checkInRepository.findByName(name);
	}
	
	public CheckIn saveCheckIn(CheckIn checkIn) {
		return checkInRepository.save(checkIn);
	}
	
	public CheckIn updateCheckIn(CheckIn checkIn) {
		return checkInRepository.save(checkIn);
	}
	
	public void deleteCheckIn(Integer id) {
		checkInRepository.deleteById(id);
	}
	
	public void truncateCheckin() {
		checkInRepository.truncateCheckin();
	}

}
