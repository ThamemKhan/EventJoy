package com.main.eventjoy.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.main.eventjoy.model.AdminModel;
import com.main.eventjoy.model.User;


public interface AdminRepository extends CrudRepository<AdminModel, Integer>{
    Optional<AdminModel> findByUser(User user);
    Optional<AdminModel> findByUserEmail(String email);
}

