package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Notifications;

public interface NotificationService {
    
    public List<Notifications> findByUserId(Integer id);
    public List<Notifications> findAll();
    public void save(Notifications notification1);
    public void delete(Notifications notification);
    public Notifications findNotificationById(Integer id);
}
