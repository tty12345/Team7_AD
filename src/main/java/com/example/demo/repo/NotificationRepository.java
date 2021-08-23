package com.example.demo.repo;

import java.util.List;
import com.example.demo.domain.Notifications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationRepository extends JpaRepository<Notifications, Integer> {

    @Query("select ntf from Notifications ntf where ntf.notificationId = :id")
    public Notifications findNotificationById(@Param("id") int id);

    @Query("select ntf from Notifications ntf where ntf.user.userId= :id")
    public List<Notifications> findByUserId(@Param("id") int id);
}
