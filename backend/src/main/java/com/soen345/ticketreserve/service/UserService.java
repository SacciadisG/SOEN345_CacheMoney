package com.soen345.ticketreserve.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.soen345.ticketreserve.dto.UserRegistrationRequest;
import com.soen345.ticketreserve.exception.BadRequestException;
import com.soen345.ticketreserve.model.User;
import com.soen345.ticketreserve.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.soen345.ticketreserve.dto.LoginRequest;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegistrationRequest request) {
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new BadRequestException("Name is required");
        }

        boolean hasEmail = request.getEmail() != null && !request.getEmail().trim().isEmpty();
        boolean hasPhone = request.getPhoneNumber() != null && !request.getPhoneNumber().trim().isEmpty();

        if (!hasEmail && !hasPhone) {
            throw new BadRequestException("User must provide either email or phone number");
        }

        if (hasEmail && userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email is already registered");
        }

        if (hasPhone && userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new BadRequestException("Phone number is already registered");
        }

        if (request.getPassword() == null || request.getPassword().length() < 6) {
            throw new BadRequestException("Password must be at least 6 characters");
        }

        User user = new User();
        user.setName(request.getName().trim());
        user.setEmail(hasEmail ? request.getEmail().trim() : null);
        user.setPhoneNumber(hasPhone ? request.getPhoneNumber().trim() : null);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole("CUSTOMER");

        return userRepository.save(user);
    }

    public User loginUser(LoginRequest request) {
        boolean hasEmail = request.getEmail() != null && !request.getEmail().trim().isEmpty();
        boolean hasPhone = request.getPhoneNumber() != null && !request.getPhoneNumber().trim().isEmpty();

        if (!hasEmail && !hasPhone) {
            throw new BadRequestException("Email or phone number is required");
        }

        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new BadRequestException("Password is required");
        }

        Optional<User> userOptional;

        if (hasEmail) {
            userOptional = userRepository.findByEmail(request.getEmail().trim());
        } else {
            userOptional = userRepository.findByPhoneNumber(request.getPhoneNumber().trim());
        }

        User user = userOptional.orElseThrow(() ->
                new BadRequestException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Invalid credentials");
        }

        return user;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User not found with ID: " + id));
    }
}
