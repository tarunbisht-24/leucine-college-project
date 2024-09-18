package com.leucine.LeucineCDMS.controller;

import com.leucine.LeucineCDMS.model.AdministratorProfile;
import com.leucine.LeucineCDMS.service.AdministratorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leucine/admin")
@CrossOrigin(origins = "*")
public class AdministratorProfileController {

    @Autowired
    private AdministratorProfileService administratorProfileService;

    @GetMapping("/all")
//    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public List<AdministratorProfile> getAllAdmins() {
        return administratorProfileService.findAll();
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<AdministratorProfile> createAdminProfile(@RequestBody AdministratorProfile administratorProfile) {
        AdministratorProfile savedProfile = administratorProfileService.save(administratorProfile);
        return ResponseEntity.ok(savedProfile);
    }
}
