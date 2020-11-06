package com.java.project.checkin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.project.checkin.models.CheckIn;

@Repository
public interface CheckInRepository extends CrudRepository<CheckIn,Integer>{

	@Override
	<S extends CheckIn> S save(S s);
	
	@Query("select check from CheckIn check where check.employeename like %?1%")
	public List<CheckIn> findByName(@Param("employeename") String name);
	
	@Modifying
	@Query(value = "truncate checkincontrol.checkin", nativeQuery = true)
	public void truncateCheckin();
}

