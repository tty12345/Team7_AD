package com.example.demo.repo;

import com.example.demo.domain.Preference;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference,Integer> {
    
}
