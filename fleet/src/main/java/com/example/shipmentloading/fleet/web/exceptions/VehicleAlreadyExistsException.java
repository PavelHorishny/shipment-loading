package com.example.shipmentloading.fleet.web.exceptions;

import com.example.shipmentloading.fleet.domain.RegistrationNumber;

public class VehicleAlreadyExistsException extends RuntimeException {

    public VehicleAlreadyExistsException(RegistrationNumber registrationNumber) {
        super("Vehicle with registration number already exists: " + registrationNumber.value());
    }
}
