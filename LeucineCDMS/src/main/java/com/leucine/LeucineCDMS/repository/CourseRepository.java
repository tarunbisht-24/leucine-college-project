package com.leucine.LeucineCDMS.repository;

import com.leucine.LeucineCDMS.model.Course;
import com.leucine.LeucineCDMS.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
