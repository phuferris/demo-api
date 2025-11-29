package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private Float houseHoldIncome;
    private Boolean married;
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "parent_user_id")
    private User parent;

    // Required by JPA
    protected User() {}

    public User(String name, String email) {
        this.email = email;
        this.name = name;
    }

    public Long getId() { return this.id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return this.name; }

    public String getEmail() { return this.email; }

    public Float getHouseHoldIncome() { return this.houseHoldIncome; }

    public void setHouseHoldIncome(Float income) { this.houseHoldIncome = income; }

    public Boolean isMarried() { return this.married; }

    public void setMarried(Boolean married) { this.married = married; }

    public LocalDate getDateOfBirth() { return this.dateOfBirth; }

    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public User getParent() { return this.parent; }

    public void setParent(User parent) { this.parent = parent; }
}
