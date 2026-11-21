package com.example.shipmentloading.fleet.domain;

public record RegistrationNumber(String value) {

    public RegistrationNumber {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Registration number must not be empty");
        }
        // Нормализация: " wx 12345 " → "WX12345"
        value = value.replace(" ", "").toUpperCase();

        if (!value.matches("[A-Z0-9]{4,8}")) {
            throw new IllegalArgumentException("Invalid registration number: " + value);
        }
    }
}
