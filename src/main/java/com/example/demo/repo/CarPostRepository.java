package com.example.demo.repo;

import java.util.List;

import com.example.demo.domain.CarPosting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarPostRepository extends JpaRepository<CarPosting, Integer> {
    
    @Query("SELECT cp FROM CarPosting cp WHERE cp.id = :id")
	public CarPosting findCarPostById(@Param("id") int id);

    @Query("SELECT cp from CarPosting cp WHERE (:brand IS NULL OR cp.brand = :brand) AND cp.price> :minPrice AND cp.price < :maxPrice AND (:description IS NULL OR cp.description like %:description%)")
    public List<CarPosting> filterAllIgnoreCase(@Param("brand")String brand,@Param("minPrice")int minPrice,@Param("maxPrice")int maxPrice, @Param("description") String description);

    @Query("SELECT cp from CarPosting cp WHERE cp.user.userId = :id")
    public List<CarPosting> findCarPostByUserId(@Param("id")int id);

    
}
