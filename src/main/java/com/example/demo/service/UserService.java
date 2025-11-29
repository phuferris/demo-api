package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> findAllChildrenByUserIdAndAgeRange(Long userId, int minAge, int maxAge) {
        LocalDate today = LocalDate.now();
        User parent = this.userRepository.getReferenceById(userId);
        return this.userRepository.findByParentAndDateOfBirthBetween(parent, today.minusYears(maxAge), today.minusYears(minAge));
    }
}
