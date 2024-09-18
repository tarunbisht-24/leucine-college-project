package com.leucine.LeucineCDMS.controller;

import com.leucine.LeucineCDMS.model.*;
import com.leucine.LeucineCDMS.security.JwtHelper;
import com.leucine.LeucineCDMS.service.AdministratorProfileService;
import com.leucine.LeucineCDMS.service.FacultyProfileService;
import com.leucine.LeucineCDMS.service.StudentProfileService;
import com.leucine.LeucineCDMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/leucine/auth")
public class AuthController {
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private StudentProfileService studentProfileService;

    @Autowired
    private FacultyProfileService facultyProfileService;

    @Autowired
    private AdministratorProfileService administratorProfileService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> optionalUser = userService.findByUsername(user.getUsername());
        if (optionalUser.isPresent() && bCryptPasswordEncoder.matches(user.getPassword(), optionalUser.get().getPassword())) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String token = jwtHelper.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticationResponse(token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // Convert the role string to the Role enum
        try {
            Role role = Role.valueOf(String.valueOf(user.getRole()));
            user.setRole(role);
        } catch (IllegalArgumentException e) {
            return "Invalid role specified";
        }

        userService.save(user);

        // Populate respective profile table based on role
        switch (user.getRole()) {
            case STUDENT:
                StudentProfile studentProfile = new StudentProfile();
                studentProfile.setUser(user);
                // Set other necessary fields for student profile
                studentProfileService.save(studentProfile);
                break;
            case FACULTYMEMBER:
                FacultyProfile facultyProfile = new FacultyProfile();
                facultyProfile.setUser(user);
                // Set other necessary fields for faculty profile
                facultyProfileService.save(facultyProfile);
                break;
            case ADMINISTRATOR:
                AdministratorProfile administratorProfile = new AdministratorProfile();
                administratorProfile.setUser(user);
                // Set other necessary fields for administrator profile
                administratorProfileService.save(administratorProfile);
                break;
        }

        return "User registered successfully";
    }

    class AuthenticationResponse {
        private String jwt;

        public AuthenticationResponse(String jwt) {
            this.jwt = jwt;
        }

        public String getJwt() {
            return jwt;
        }

        public void setJwt(String jwt) {
            this.jwt = jwt;
        }
    }

}
