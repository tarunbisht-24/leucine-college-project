package com.leucine.LeucineCDMS.controller;

import com.leucine.LeucineCDMS.model.StudentProfile;
import com.leucine.LeucineCDMS.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leucine/students")
@CrossOrigin(origins = "*")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('FACULTYMEMBER') or hasRole('ADMINISTRATOR') or hasRole('STUDENT')")
    public List<StudentProfile> getAllStudents() {
        return studentProfileService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('FACULTYMEMBER') or hasRole('ADMINISTRATOR') or hasRole('STUDENT')")
    public ResponseEntity<StudentProfile> createStudentProfile(@RequestBody StudentProfile studentProfile) {
        StudentProfile savedProfile = studentProfileService.save(studentProfile);
        return ResponseEntity.ok(savedProfile);
    }
}
