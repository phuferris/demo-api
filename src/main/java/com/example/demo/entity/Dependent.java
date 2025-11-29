package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Dependent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int user_id;
    int _user_id;
    RelationshipType relationship;
}

enum RelationshipType {
    PARENT,
    CHILD,
    HUSBAND,
    WIFE,
    GUARDIAN
}
