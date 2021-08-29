package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Notifications;
import com.example.demo.repo.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImplementation implements NotificationService {

    @Autowired
    NotificationRepository nrepo;

    @Override
    public List<Notifications> findByUserId(Integer id) {
        return nrepo.findByUserId(id);
    }

    @Override
    public List<Notifications> findAll() {
        return nrepo.findAll();
    }

    @Override
    public void save(Notifications notification1) {
        nrepo.save(notification1);

    }

    @Override
    public void delete(Notifications notification) {
        nrepo.delete(notification);

    }

    @Override
    public Notifications findNotificationById(Integer id) {
        return nrepo.findNotificationById(id);
    }

}
