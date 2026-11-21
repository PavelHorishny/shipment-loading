package com.example.shipmentloading.fleet.web;

import com.example.shipmentloading.fleet.domain.Equipment;
import com.example.shipmentloading.fleet.domain.Vehicle;
import com.example.shipmentloading.fleet.domain.VehicleStatus;

import java.util.Set;
import java.util.UUID;

public record VehicleResponse(
        UUID id,
        String registrationNumber,
        int lengthCm,
        int widthCm,
        int heightCm,
        long volumeCm3,
        int maxPayloadKg,
        Set<Equipment> equipment,
        String homeBranchId,
        VehicleStatus status
) {

    public static VehicleResponse from(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getRegistrationNumber().value(),
                vehicle.getCargoSpace().lengthCm(),
                vehicle.getCargoSpace().widthCm(),
                vehicle.getCargoSpace().heightCm(),
                vehicle.getCargoSpace().volumeCm3(),
                vehicle.getCargoSpace().maxPayloadKg(),
                vehicle.getEquipment(),
                vehicle.getHomeBranchId(),
                vehicle.getStatus()
        );
    }
}
