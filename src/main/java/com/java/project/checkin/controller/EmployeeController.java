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

import com.java.project.checkin.models.Employee;
import com.java.project.checkin.repo.impl.EmployeeRepoImpl;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("checkincontrol/system/employee")
public class EmployeeController {

	@Autowired
	EmployeeRepoImpl employeeRepoImpl;
	
	@GetMapping("/getAllEmployee")
	public List<Employee> getAllEmployee(){
		log.info("getAllEmployee Controller");
	 return employeeRepoImpl.getAllEmployee();
	}
	
	@GetMapping("/getEmployeeById/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable Integer id){
		log.info("getEmployeeById Controller");
	 return employeeRepoImpl.getEmployeeById(id);
	}
	
	@GetMapping("/getEmployeeByName/{name}")
	public List<Employee> getEmployeeByName(@PathVariable String name){
		log.info("getEmployeeByName Controller");
	 return employeeRepoImpl.getEmployeeByName(name);
	}
	
	@PostMapping("/createEmployee")
	public Employee createEmployee(@RequestBody Employee employee){
		log.info("createEmployee Controller");
	 return employeeRepoImpl.saveEmployee(employee);
	}
	
	@PutMapping("/updateEmployee")
	public Employee updateEmployee(@RequestBody Employee employee){
		log.info("updateEmployee Controller");
	 return employeeRepoImpl.updateEmployee(employee);
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public void deleteEmployee(@PathVariable Integer id){
		log.info("deleteEmployee Controller");
		employeeRepoImpl.deleteEmployee(id);
	}
	
	@Transactional
	@GetMapping("/truncateEmployee")
	public String truncateEmployee(){
		log.info("truncateEmployee Controller");
		employeeRepoImpl.truncateEmployee();
		return "Truncated";
	}
	
}
