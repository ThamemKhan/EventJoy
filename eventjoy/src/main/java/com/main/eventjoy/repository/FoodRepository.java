package com.main.eventjoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.eventjoy.model.FoodModel;

public interface FoodRepository extends JpaRepository<FoodModel,Integer> {

}
