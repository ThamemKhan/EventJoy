package com.main.eventjoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.eventjoy.model.ThemeModel;

public interface ThemeRepository extends JpaRepository<ThemeModel,Integer> {

}
