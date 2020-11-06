package com.java.project.checkin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.project.checkin.models.CheckIn;
import com.java.project.checkin.repo.impl.CheckInRepoImpl;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("checkincontrol/system/checkin")
public class CheckInController {
	
	@Autowired
	CheckInRepoImpl checkInRepoImpl;
	
	@GetMapping("/getAllCheckIn")
	public List<CheckIn> getAllCheckIn(){
		log.info("getCategoryByName Controller");
	 return checkInRepoImpl.getAllCheckIn();
	}
	
	@GetMapping("/getCheckInById/{id}")
	public Optional<CheckIn> getCheckInById(@PathVariable Integer id){
		log.info("getCheckInById Controller");
	 return checkInRepoImpl.getCheckInById(id);
	}
	
	@GetMapping("/getCheckInByName/{name}")
	public List<CheckIn> getCheckInById(@PathVariable String name){
		log.info("getCheckInById Controller");
	 return checkInRepoImpl.getCheckInByName(name);
	}
	
	@PostMapping("/createCheckIn")
	public CheckIn createCheckIn(@RequestBody CheckIn checkIn){
		log.info("createCheckIn Controller");
	 return checkInRepoImpl.saveCheckIn(checkIn);
	}
	
	@PutMapping("/updateCheckIn")
	public CheckIn updateCheckIn(@RequestBody CheckIn checkIn){
		log.info("updateCash Controller");
	 return checkInRepoImpl.saveCheckIn(checkIn);
	}
	
	@DeleteMapping("/deleteCheckIn/{id}")
	public void deleteCheckIn(@PathVariable Integer id){
		log.info("deleteCash Controller");
		checkInRepoImpl.deleteCheckIn(id);
	}
	
	@Transactional
	@GetMapping("/truncateCheckin")
	public String truncateCheckin(){
		log.info("truncateCheckin Controller");
		checkInRepoImpl.truncateCheckin();
		return "Truncated";
	}

}
