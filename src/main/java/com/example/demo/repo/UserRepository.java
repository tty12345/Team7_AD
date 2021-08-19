package com.example.demo.repo;

import java.util.List;

import com.example.demo.domain.CarPosting;
import com.example.demo.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.userId = :id")
	public User finduserById(@Param("id") int id);
    
    public User findUserByUsername(String un);

    @Query("select u.favourites from User u where u.userId= :id")
    public List<CarPosting> findFavouritesByUserId(@Param("id")int id);
    @Query("select u.favourites from User u")
    public List<CarPosting> findAllFavourites();
}