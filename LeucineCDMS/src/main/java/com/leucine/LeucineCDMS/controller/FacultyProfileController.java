package com.leucine.LeucineCDMS.controller;

import com.leucine.LeucineCDMS.model.FacultyProfile;
import com.leucine.LeucineCDMS.service.FacultyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leucine/faculty")
@CrossOrigin(origins = "*")
public class FacultyProfileController {

    @Autowired
    private FacultyProfileService facultyProfileService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public List<FacultyProfile> getAllFaculty() {
        return facultyProfileService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<FacultyProfile> createFacultyProfile(@RequestBody FacultyProfile facultyProfile) {
        FacultyProfile savedProfile = facultyProfileService.save(facultyProfile);
        return ResponseEntity.ok(savedProfile);
    }
}
