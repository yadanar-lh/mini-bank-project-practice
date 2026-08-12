package com.ylh.dto;

import java.time.LocalDate;

public class UserDto {
    public UserDto(String fullName, String password, LocalDate dateOfBirth, String email, String phoneNumber, double balance) {
        this.fullName = fullName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    private final String fullName;
    private final String password;
    private final LocalDate dateOfBirth;
    private final String email;
    private final String phoneNumber;
    private final double balance;
}


