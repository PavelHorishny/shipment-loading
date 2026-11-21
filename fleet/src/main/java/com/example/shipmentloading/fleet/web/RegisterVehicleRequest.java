package com.example.shipmentloading.fleet.web;

import com.example.shipmentloading.fleet.domain.Equipment;

import java.util.Set;

public record RegisterVehicleRequest(
        String registrationNumber,
        int lengthCm,
        int widthCm,
        int heightCm,
        int maxPayloadKg,
        Set<Equipment> equipment,
        String homeBranchId
) {
}
