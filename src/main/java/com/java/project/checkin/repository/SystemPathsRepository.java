package com.java.project.checkin.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.java.project.checkin.models.SystemPaths;


@Repository
public interface SystemPathsRepository extends CrudRepository<SystemPaths,Integer> {

	@Override
	<S extends SystemPaths> S save(S s);
	
	@Modifying
	@Query(value = "truncate checkincontrol.systempaths", nativeQuery = true)
	public void truncateSystemPaths();
}
