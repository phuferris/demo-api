package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildCareBenefitServiceImpl implements ChildCareBenefitService {
    UserService userService;
    @Override
    public boolean isEligibleForChildDayCare(long userId) {
        List<User> users = userService.findAllChildrenByUserIdAndAgeRange(
                userId,
                0,
                5
        );
        return !users.isEmpty();
    }

    @Override
    public boolean isEligibleForExtendedCare(long userId) {
        List<User> dependents = userService.findAllChildrenByUserIdAndAgeRange(
                userId,
                5,
                13
        );
        return !dependents.isEmpty();
    }

    @Override
    public boolean isEligibleForSpecialNeedsCare(long userId) {
        List<User> dependents = userService.findAllChildrenByUserIdAndAgeRange(
                userId,
                13,
                22
        );
        return !dependents.isEmpty();
    }
}
