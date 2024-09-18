package com.leucine.LeucineCDMS.controller;

import com.leucine.LeucineCDMS.model.Course;
import com.leucine.LeucineCDMS.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leucine/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('FACULTYMEMBER') or hasRole('ADMINISTRATOR')")
    public List<Course> getAllCourses() {
        return courseService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('FACULTYMEMBER') or hasRole('ADMINISTRATOR')")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course savedCourse = courseService.save(course);
        return ResponseEntity.ok(savedCourse);
    }
}
