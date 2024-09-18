package com.leucine.LeucineCDMS.service;

import com.leucine.LeucineCDMS.model.FacultyProfile;
import com.leucine.LeucineCDMS.repository.FacultyProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyProfileService {

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    public List<FacultyProfile> findAll() {
        return facultyProfileRepository.findAll();
    }

    public FacultyProfile save(FacultyProfile facultyProfile) {
        return facultyProfileRepository.save(facultyProfile);
    }
}
