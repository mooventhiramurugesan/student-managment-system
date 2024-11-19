

package com.example.StudentSystem.services;

import com.example.StudentSystem.Exception.RecordNotFoundException;
import com.example.StudentSystem.models.Registration;
import com.example.StudentSystem.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServices implements RegistrationServiceInt {

    @Autowired
    private RegistrationRepository registrationRepository;


    @Override
    public Registration save(Registration registration) {
        return registrationRepository.save(registration);
    }

    @Override
    public List<Registration> findAll() {
        return registrationRepository.findAll();
    }

    @Override
    public Optional<Registration> findById(long id) {
        return Optional.of(registrationRepository.getOne(id));
    }


    @Override
    public Registration update(Registration registration) {
        return registrationRepository.save(registration);
    }


    @Override
    public void deleteEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<Registration> employee = registrationRepository.findById(id);

        if(employee.isPresent())
        {
            registrationRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

}
