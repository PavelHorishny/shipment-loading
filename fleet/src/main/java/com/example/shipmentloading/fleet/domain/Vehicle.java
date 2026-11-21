package com.example.shipmentloading.fleet.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Vehicle {

    @EqualsAndHashCode.Include
    private final UUID id;
    private final RegistrationNumber registrationNumber;
    private final CargoSpace cargoSpace;
    private final Set<Equipment> equipment;
    private final String homeBranchId;
    private VehicleStatus status;

    private Vehicle(UUID id, RegistrationNumber registrationNumber, CargoSpace cargoSpace,
                    Set<Equipment> equipment, String homeBranchId) {
        if (homeBranchId == null || homeBranchId.isBlank()) {
            throw new IllegalArgumentException("Home branch must not be empty");
        }
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.registrationNumber = Objects.requireNonNull(registrationNumber, "registrationNumber must not be null");
        this.cargoSpace = Objects.requireNonNull(cargoSpace, "cargoSpace must not be null");
        this.equipment = copyOf(equipment);
        this.homeBranchId = homeBranchId;
        this.status = VehicleStatus.AVAILABLE;
    }

    public static Vehicle register(RegistrationNumber registrationNumber, CargoSpace cargoSpace,
                                   Set<Equipment> equipment, String homeBranchId) {
        return new Vehicle(UUID.randomUUID(), registrationNumber, cargoSpace, equipment, homeBranchId);
    }


    public void startLoading() {
        moveTo(VehicleStatus.LOADING);
    }

    public void depart() {
        moveTo(VehicleStatus.ON_ROUTE);
    }

    public void markAvailable() {
        moveTo(VehicleStatus.AVAILABLE);
    }

    public void sendToService() {
        moveTo(VehicleStatus.IN_SERVICE);
    }

    public void disable() {
        moveTo(VehicleStatus.DISABLED);
    }


    public boolean canAcceptShipments() {
        return status.canAcceptShipments();
    }

    public boolean hasEquipment(Equipment required) {
        return equipment.contains(required);
    }

    private void moveTo(VehicleStatus target) {
        if (!status.canTransitionTo(target)) {
            throw new IllegalStateException(
                    "Vehicle " + registrationNumber.value() + " cannot move from " + status + " to " + target);
        }
        this.status = target;
    }


    private static Set<Equipment> copyOf(Set<Equipment> equipment) {
        if (equipment == null || equipment.isEmpty()) {
            return Set.of();
        }
        return Set.copyOf(EnumSet.copyOf(equipment));
    }

}
