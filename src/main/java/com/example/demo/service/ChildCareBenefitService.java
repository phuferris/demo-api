package com.example.demo.service;

public interface ChildCareBenefitService {
    boolean isEligibleForChildDayCare(long userId);
    boolean isEligibleForExtendedCare(long userId);
    boolean isEligibleForSpecialNeedsCare(long userId);
}
