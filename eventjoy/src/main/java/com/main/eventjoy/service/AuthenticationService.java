package com.main.eventjoy.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.main.eventjoy.dto.request.AuthenticationRequest;
import com.main.eventjoy.dto.request.RegisterRequest;
import com.main.eventjoy.dto.response.AuthenticationResponse;
import com.main.eventjoy.model.AdminModel;
import com.main.eventjoy.model.Role;
import com.main.eventjoy.model.User;
import com.main.eventjoy.model.UserModel;
import com.main.eventjoy.repository.AdminRepository;
import com.main.eventjoy.repository.UserModelRepository;
import com.main.eventjoy.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserModelRepository userModelRepository;
    private final AdminRepository adminModelRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if(request.getUserRole().equals("user")){   
                var user = User
                        .builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.USER)
                        .build();
                Optional<User> existing = userRepository.findByEmail(request.getEmail());
                if(!existing.isEmpty()) {
                        return AuthenticationResponse.builder()
                        .token("Email Already exists")
                        .build();
                }
                User savedUser = userRepository.save(user);
                UserModel userModel = new UserModel();
                userModel.setMobileNumber(request.getMobileNumber());
                userModel.setUser(savedUser);
                userModelRepository.save(userModel);
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                        .token(jwtToken)
                        .build();
        }
        var user = User
                .builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if(!existing.isEmpty()) {
                return AuthenticationResponse.builder()
                    .token("Email Already exists")
                    .build();
        }
        User savedUser = userRepository.save(user);
        AdminModel adminModel = new AdminModel();
        adminModel.setMobileNumber(request.getMobileNumber());
        adminModel.setUser(savedUser);
        adminModelRepository.save(adminModel);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println(request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
