package com.example.shipmentloading.fleet.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    private Vehicle newVehicle() {
        return Vehicle.register(
                new RegistrationNumber("WX12345"),
                new CargoSpace(600, 240, 250, 3500),
                Set.of(Equipment.TAIL_LIFT),
                "WAW-1");
    }

    @Test
    void newVehicleShouldBeAvailable() {
        Vehicle vehicle = newVehicle();

        assertEquals(VehicleStatus.AVAILABLE, vehicle.getStatus());
        assertTrue(vehicle.canAcceptShipments());
    }

    @Test
    void vehicleOnRouteShouldNotAcceptShipments() {
        Vehicle vehicle = newVehicle();
        vehicle.startLoading();
        vehicle.depart();

        assertFalse(vehicle.canAcceptShipments());
    }

    @Test
    void shouldNotDepartWithoutLoading() {
        Vehicle vehicle = newVehicle();

        assertThrows(IllegalStateException.class, vehicle::depart);
    }

    @Test
    void equipmentShouldNotChangeWhenOriginalSetChanges() {
        Set<Equipment> equipment = new HashSet<>(Set.of(Equipment.ADR));
        Vehicle vehicle = Vehicle.register(
                new RegistrationNumber("WX12345"),
                new CargoSpace(600, 240, 250, 3500),
                equipment,
                "WAW-1");

        equipment.add(Equipment.REFRIGERATOR);

        assertFalse(vehicle.hasEquipment(Equipment.REFRIGERATOR));
    }

    @Test
    void equipmentShouldNotBeModifiableFromOutside() {
        Vehicle vehicle = newVehicle();

        assertThrows(UnsupportedOperationException.class,
                () -> vehicle.getEquipment().add(Equipment.ADR));
    }
}
