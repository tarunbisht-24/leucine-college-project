package com.leucine.LeucineCDMS.service;


import com.leucine.LeucineCDMS.model.StudentProfile;
import com.leucine.LeucineCDMS.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    public List<StudentProfile> findAll() {
        return studentProfileRepository.findAll();
    }

    public StudentProfile save(StudentProfile studentProfile) {
        return studentProfileRepository.save(studentProfile);
    }
}
