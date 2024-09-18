package com.leucine.LeucineCDMS.controller;

import com.leucine.LeucineCDMS.model.Enrollment;
import com.leucine.LeucineCDMS.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leucine/enrollments")
@CrossOrigin(origins = "*")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('FACULTYMEMBER') or hasRole('ADMINISTRATOR')")
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('FACULTYMEMBER') or hasRole('ADMINISTRATOR')")
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment savedEnrollment = enrollmentService.save(enrollment);
        return ResponseEntity.ok(savedEnrollment);
    }
}
