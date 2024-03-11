package com.main.eventjoy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.eventjoy.model.EventModel;
import com.main.eventjoy.model.User;
import com.main.eventjoy.model.UserModel;
import com.main.eventjoy.repository.EventRepository;
import com.main.eventjoy.repository.UserModelRepository;
import com.main.eventjoy.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserModelRepository userRepository;
    @Autowired
    UserRepository userRepo;
    @Autowired
    EventRepository eventRepository;

    public String addUser(UserModel userModel) {
        userRepository.save(userModel);
        return "Successfully added";
    }

    public Optional<UserModel> getUser(String email){
        return userRepository.findByUserEmail(email);
    }

    public List<UserModel> getAllUser(){
        return userRepository.findAll();
    }

    public String updateUserModel(UserModel userModel){
        Optional<User> user = userRepo.findByEmail(userModel.getUser().getEmail());
        System.out.println(user);
        if(user.isEmpty()){
            return "User Not Found";
        }
        user.get().setName(userModel.getUser().getName());
        userRepo.save(user.get());
        Optional<UserModel> userModelRepo = userRepository.findByUser(user.get());
        userModelRepo.get().setMobileNumber(userModel.getMobileNumber());
        userRepository.save(userModelRepo.get());
        return "Updated Successfully";
    }

    public String addEvent(String email,EventModel event){
        Optional<UserModel> userModel = userRepository.findByUserEmail(email);
        if(userModel.isEmpty()){
            return "User not found";
        }
        event.setUserModel(userModel.get());
        eventRepository.save(event);
        return "Event Added Successfully";
    }

    public List<EventModel> getEventsBookedByUser(String email) {
        Optional<UserModel> userOptional = userRepository.findByUserEmail(email);
            UserModel user = userOptional.get();
            return user.getEvents();
        
    }

    public EventModel getEventById(int eventId){
        return eventRepository.findById(eventId).get();
    }

    public String updateEvent(EventModel eventModel){
        Optional<EventModel> event = eventRepository.findById(eventModel.getEventId());
        if(event.isEmpty()){
            return "Event does not exist";
        }
        event.get().setEventName(eventModel.getEventName());
        event.get().setApplicantAddress(eventModel.getApplicantAddress());
        event.get().setAttendees(eventModel.getAttendees());
        event.get().setApplicantMobile(eventModel.getApplicantMobile());
        event.get().setReference(eventModel.getReference());
        event.get().setEventAddress(eventModel.getEventAddress());
        event.get().setEventDescription(eventModel.getEventDescription());
        event.get().setEventDate(eventModel.getEventDate());
        event.get().setEventTime(eventModel.getEventTime());
        event.get().setEventThemeId(eventModel.getEventThemeId());
        event.get().setEventFoodId(eventModel.getEventFoodId());
        event.get().setAddonId(eventModel.getAddonId());
        event.get().setEventCost(eventModel.getEventCost());
        eventRepository.save(event.get());
        return "Event updated successfully";
    }

}
