package com.example.shipmentloading.fleet.domain;

import java.util.Set;

public enum VehicleStatus {
    AVAILABLE,
    LOADING,
    ON_ROUTE,
    IN_SERVICE,
    DISABLED;

    public boolean canTransitionTo(VehicleStatus target) {
        return allowedNextStatuses().contains(target);
    }


    public boolean canAcceptShipments() {
        return this == AVAILABLE || this == LOADING;
    }

    private Set<VehicleStatus> allowedNextStatuses() {
        return switch (this) {
            case AVAILABLE -> Set.of(LOADING, IN_SERVICE, DISABLED);
            case LOADING -> Set.of(ON_ROUTE, AVAILABLE, IN_SERVICE);
            case ON_ROUTE -> Set.of(AVAILABLE, IN_SERVICE);
            case IN_SERVICE -> Set.of(AVAILABLE, DISABLED);
            case DISABLED -> Set.of(AVAILABLE);
        };
    }
}
