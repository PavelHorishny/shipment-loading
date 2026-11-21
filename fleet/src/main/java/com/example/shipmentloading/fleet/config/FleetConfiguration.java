package com.example.shipmentloading.fleet.config;

import com.example.shipmentloading.fleet.infrastructure.InMemoryVehicleRepository;
import com.example.shipmentloading.fleet.infrastructure.VehicleRepository;
import com.example.shipmentloading.fleet.infrastructure.VehicleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FleetConfiguration {

    @Bean
    public VehicleRepository vehicleRepository() {
        return new InMemoryVehicleRepository();
    }

    @Bean
    public VehicleService vehicleService(VehicleRepository vehicleRepository) {
        return new VehicleService(vehicleRepository);
    }
}
