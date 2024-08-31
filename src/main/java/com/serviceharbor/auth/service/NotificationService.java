package com.serviceharbor.auth.service;


import com.serviceharbor.auth.model.Notification;
import com.serviceharbor.auth.model.User;
import com.serviceharbor.auth.repository.NotificationRepository;
import com.serviceharbor.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public Notification createNotification(Integer userId, String message) {

        User user =userRepository.findById(userId).orElse(null);
        if(user==null) return null;
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setStatus(false);
        notification.setUser(user);
        return notificationRepository.save(notification);
    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    public List<Notification> getNotificationsByUserId(Integer userId) {
        User user =userRepository.findById(userId).orElse(null);
        if(user==null) return null;
        return notificationRepository.findByUser(user);
    }

    public Notification updateNotification(Long id, Notification notification) {
        if (notificationRepository.existsById(id)) {
            return notificationRepository.save(notification);
        }
        return null;
    }

    public boolean deleteNotification(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }



}
