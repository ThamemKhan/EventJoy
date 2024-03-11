package com.main.eventjoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.eventjoy.model.EventModel;


public interface EventRepository extends JpaRepository<EventModel,Integer> {

}
