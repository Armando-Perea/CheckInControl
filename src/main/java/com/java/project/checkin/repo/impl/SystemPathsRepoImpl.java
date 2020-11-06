package com.java.project.checkin.repo.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.project.checkin.models.SystemPaths;
import com.java.project.checkin.repository.SystemPathsRepository;


@Repository
public class SystemPathsRepoImpl {

	@Autowired
	SystemPathsRepository systemPathsRepository;
	
	public List<SystemPaths> getAllSystemPaths() {
		return (List<SystemPaths>) systemPathsRepository.findAll();
	}
	public Optional<SystemPaths> getSystemPathById(Integer id) {
		return systemPathsRepository.findById(id);
	}
	
	public SystemPaths saveSystemPath(SystemPaths systemPaths) {
		return systemPathsRepository.save(systemPaths);
	}
	
	public SystemPaths updateSystemPath(SystemPaths systemPaths) {
		return systemPathsRepository.save(systemPaths);
	}
	
	public void deleteSystemPath(Integer id) {
		systemPathsRepository.deleteById(id);
	}
	
	public void truncateSystemPaths() {
		systemPathsRepository.truncateSystemPaths();
	}
}
