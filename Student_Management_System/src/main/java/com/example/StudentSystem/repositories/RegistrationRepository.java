package com.example.StudentSystem.repositories;

import com.example.StudentSystem.models.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RegistrationRepository  extends JpaRepository<Registration,Long> {

}
