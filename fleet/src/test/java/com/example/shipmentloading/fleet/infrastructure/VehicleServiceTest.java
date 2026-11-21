package com.example.shipmentloading.fleet.infrastructure;

import com.example.shipmentloading.fleet.domain.CargoSpace;
import com.example.shipmentloading.fleet.domain.RegistrationNumber;
import com.example.shipmentloading.fleet.domain.Vehicle;
import com.example.shipmentloading.fleet.web.exceptions.VehicleAlreadyExistsException;
import com.example.shipmentloading.fleet.web.exceptions.VehicleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VehicleServiceTest {

    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        vehicleService = new VehicleService(new InMemoryVehicleRepository());
    }

    @Test
    void shouldRegisterAndFindVehicle() {
        Vehicle registered = registerVehicle("WX12345");

        Vehicle found = vehicleService.getById(registered.getId());

        assertEquals(registered, found);
    }

    @Test
    void shouldNotRegisterSameRegistrationNumberTwice() {
        registerVehicle("WX12345");

        assertThrows(VehicleAlreadyExistsException.class, () -> registerVehicle("wx 12345"));
    }

    @Test
    void shouldThrowWhenVehicleNotFound() {
        assertThrows(VehicleNotFoundException.class, () -> vehicleService.getById(UUID.randomUUID()));
    }

    private Vehicle registerVehicle(String registrationNumber) {
        return vehicleService.register(
                new RegistrationNumber(registrationNumber),
                new CargoSpace(600, 240, 250, 3500),
                Set.of(),
                "WAW-1");
    }
}
