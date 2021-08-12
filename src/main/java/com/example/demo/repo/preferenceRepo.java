package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Preferences;

public interface preferenceRepo extends JpaRepository<Preferences, Integer> {
	
}
