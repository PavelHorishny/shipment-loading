package com.example.shipmentloading.fleet.infrastructure;

import com.example.shipmentloading.fleet.domain.RegistrationNumber;
import com.example.shipmentloading.fleet.domain.Vehicle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository {

    void save(Vehicle vehicle);

    Optional<Vehicle> findById(UUID id);

    Optional<Vehicle> findByRegistrationNumber(RegistrationNumber registrationNumber);

    List<Vehicle> findAll();
}
