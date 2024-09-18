package com.leucine.LeucineCDMS.service;

import com.leucine.LeucineCDMS.model.AdministratorProfile;
import com.leucine.LeucineCDMS.repository.AdministratorProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorProfileService {

    @Autowired
    private AdministratorProfileRepository administratorProfileRepository;

    public List<AdministratorProfile> findAll() {
        return administratorProfileRepository.findAll();
    }

    public AdministratorProfile save(AdministratorProfile administratorProfile) {
        return administratorProfileRepository.save(administratorProfile);
    }
}
