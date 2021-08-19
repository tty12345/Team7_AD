package com.example.demo.repo;

import java.util.List;

import com.example.demo.domain.CarPosting;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarPostRepository extends JpaRepository<CarPosting, Integer> {
    
    @Query("SELECT cp FROM CarPosting cp WHERE cp.id = :id")
	public CarPosting findCarPostById(@Param("id") int id);
    
    @Query("SELECT cp FROM CarPosting cp WHERE cp.description = :model AND cp.brand = :brand")
   	public List<CarPosting> findCarPostByPref(@Param("model") String model, @Param("brand")String brand);

    @Query("select cp from CarPosting cp where cp.description=:model AND cp.brand=:brand AND cp.engineCapacity>=:engineCapacity AND cp.category=:category")
    public List<CarPosting> findCarPostsByPreferences(@Param("model") String model,@Param("brand")String brand,
    @Param("engineCapacity") int engineCapacity,@Param("category") String category);

    @Query("SELECT cp FROM CarPosting cp WHERE (:brand IS NULL OR cp.brand = :brand) AND cp.price> :minPrice AND cp.price < :maxPrice AND (:description IS NULL OR cp.description LIKE %:description%)")
    public List<CarPosting> filterAllIgnoreCase(@Param("brand")String brand,@Param("minPrice")int minPrice,@Param("maxPrice")int maxPrice, @Param("description") String description);

    @Query("SELECT cp FROM CarPosting cp WHERE cp.owner.userId = :id")
    public List<CarPosting> findCarPostByUserId(@Param("id")int id);

	@Query("SELECT cp FROM CarPosting cp WHERE cp.views > 2")
	public List<CarPosting> findMostViewedCars();
}
