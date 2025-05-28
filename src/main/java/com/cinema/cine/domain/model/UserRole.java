package com.cinema.cine.domain.model;

public enum UserRole {
    ADMIN("Administrator"),
    EMPLOYEE("Employee"),
    CUSTOMER("Customer");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}