package com.serviceharbor.auth.repository;


import com.serviceharbor.auth.model.Notification;
import com.serviceharbor.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser(User user);
}
