package com.example.demo.repo;

import java.util.List;

import com.example.demo.domain.CarPosting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarPostRepository extends JpaRepository<CarPosting, Integer> {
    
    @Query("select cp from CarPosting cp where cp.id = :id")
	public CarPosting findCarPostById(@Param("id") int id);
    // @Query("select cp from CarPosting cp where cp.users.id = :id")
	// public List<CarPosting> findCarPostByUserId(@Param("id") int id);
}
