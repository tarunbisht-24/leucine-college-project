package com.leucine.LeucineCDMS.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardData {
    private int totalStudents;
    private int totalFaculty;
    private int activeCourses;
    // Add other relevant fields
}

