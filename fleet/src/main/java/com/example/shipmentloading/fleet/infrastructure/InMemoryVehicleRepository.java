package com.example.shipmentloading.fleet.infrastructure;

import com.example.shipmentloading.fleet.domain.RegistrationNumber;
import com.example.shipmentloading.fleet.domain.Vehicle;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryVehicleRepository implements VehicleRepository {

    private final Map<UUID, Vehicle> vehicles = new ConcurrentHashMap<>();

    @Override
    public void save(Vehicle vehicle) {
        vehicles.put(vehicle.getId(), vehicle);
    }

    @Override
    public Optional<Vehicle> findById(UUID id) {
        return Optional.ofNullable(vehicles.get(id));
    }

    @Override
    public Optional<Vehicle> findByRegistrationNumber(RegistrationNumber registrationNumber) {
        return vehicles.values().stream()
                .filter(vehicle -> vehicle.getRegistrationNumber().equals(registrationNumber))
                .findFirst();
    }

    @Override
    public List<Vehicle> findAll() {
        return new ArrayList<>(vehicles.values());
    }
}
