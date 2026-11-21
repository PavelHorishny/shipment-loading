package com.example.shipmentloading.fleet.web;

import com.example.shipmentloading.fleet.domain.CargoSpace;
import com.example.shipmentloading.fleet.domain.RegistrationNumber;
import com.example.shipmentloading.fleet.domain.Vehicle;
import com.example.shipmentloading.fleet.infrastructure.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponse> register(@RequestBody RegisterVehicleRequest request) {
        Vehicle vehicle = vehicleService.register(
                new RegistrationNumber(request.registrationNumber()),
                new CargoSpace(request.lengthCm(), request.widthCm(), request.heightCm(), request.maxPayloadKg()),
                request.equipment(),
                request.homeBranchId()
        );

        URI location = URI.create("/api/v1/vehicles/" + vehicle.getId());
        return ResponseEntity.created(location).body(VehicleResponse.from(vehicle));
    }

    @GetMapping("/{id}")
    public VehicleResponse getById(@PathVariable UUID id) {
        return VehicleResponse.from(vehicleService.getById(id));
    }

    @GetMapping
    public List<VehicleResponse> getAll() {
        return vehicleService.getAll().stream()
                .map(VehicleResponse::from)
                .toList();
    }
}
