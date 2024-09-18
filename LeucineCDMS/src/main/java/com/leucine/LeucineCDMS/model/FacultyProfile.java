package com.leucine.LeucineCDMS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "faculty_profiles")
    public class FacultyProfile {

        @Id
        private Long userId;

        @OneToOne
        @MapsId
        @JoinColumn(name = "user_id")
        private User user;

        private String photo;

        @ManyToOne
        @JoinColumn(name = "department_id")
        private Department department;

        private String officeHours;
}
