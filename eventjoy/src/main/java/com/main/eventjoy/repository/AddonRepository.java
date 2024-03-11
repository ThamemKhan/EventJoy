package com.main.eventjoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.eventjoy.model.AddonModel;

public interface AddonRepository extends JpaRepository<AddonModel,Integer> {

}
