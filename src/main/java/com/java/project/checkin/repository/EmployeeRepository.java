package com.java.project.checkin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.java.project.checkin.models.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {

	@Override
	<S extends Employee> S save(S s);
	
	@Query("select employ from Employee employ where employ.employeeName like %:employeeName%")
	public List<Employee> findByName(@Param("employeeName") String employeeName);
	
	@Modifying
	@Query(value = "truncate checkincontrol.employee", nativeQuery = true)
	public void truncateEmployee();
}
