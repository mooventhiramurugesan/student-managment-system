package com.example.StudentSystem.services;

import com.example.StudentSystem.models.Registration;

import java.util.List;
import java.util.Optional;

public interface RegistrationServiceInt {
	
	 Registration save(Registration registration);
	    List<Registration> findAll();
	    Optional<Registration> findById(long id);
	    Registration update(Registration registration);
	    void deleteEmployeeById(Long id);
}
