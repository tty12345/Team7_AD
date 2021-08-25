package com.example.demo.repo;

import com.example.demo.domain.Preference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PreferenceRepository extends JpaRepository<Preference,Integer> {

    @Query("select u.preference from User u where u.id = :id")
    public Preference findprefByuserId(@Param("id") int id);    
}
