package com.example.shipmentloading.fleet.infrastructure;

import com.example.shipmentloading.fleet.domain.CargoSpace;
import com.example.shipmentloading.fleet.domain.Equipment;
import com.example.shipmentloading.fleet.domain.RegistrationNumber;
import com.example.shipmentloading.fleet.domain.Vehicle;
import com.example.shipmentloading.fleet.web.exceptions.VehicleAlreadyExistsException;
import com.example.shipmentloading.fleet.web.exceptions.VehicleNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public Vehicle register(RegistrationNumber registrationNumber, CargoSpace cargoSpace,
                            Set<Equipment> equipment, String homeBranchId) {
        if (vehicleRepository.findByRegistrationNumber(registrationNumber).isPresent()) {
            throw new VehicleAlreadyExistsException(registrationNumber);
        }

        Vehicle vehicle = Vehicle.register(registrationNumber, cargoSpace, equipment, homeBranchId);
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public Vehicle getById(UUID id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
    }

    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }
}
