package com.main.eventjoy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.eventjoy.model.User;
import com.main.eventjoy.model.UserModel;


public interface UserModelRepository extends JpaRepository<UserModel,Integer> {
    Optional<UserModel> findByUser(User user);
    Optional<UserModel> findByUserEmail(String email);
}
