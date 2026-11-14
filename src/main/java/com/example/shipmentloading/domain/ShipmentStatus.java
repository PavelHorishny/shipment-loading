package com.example.shipmentloading.domain;

import java.util.Set;

public enum ShipmentStatus {
    RECEIVED,
    CLASSIFIED,
    AWAITING_ASSIGNMENT,
    ASSIGNED,
    LOADED,
    IN_QUARANTINE,
    REJECTED,
    CANCELLED;

    public boolean canTransitionTo(ShipmentStatus target) {
        return allowedNextStatuses().contains(target);
    }

    private Set<ShipmentStatus> allowedNextStatuses() {
        return switch (this) {
            case RECEIVED -> Set.of(CLASSIFIED, IN_QUARANTINE, CANCELLED);
            case CLASSIFIED -> Set.of(AWAITING_ASSIGNMENT, CANCELLED);
            case AWAITING_ASSIGNMENT -> Set.of(ASSIGNED, IN_QUARANTINE, CANCELLED);
            case ASSIGNED -> Set.of(LOADED, AWAITING_ASSIGNMENT, CANCELLED);
            case IN_QUARANTINE -> Set.of(RECEIVED, CLASSIFIED, REJECTED, CANCELLED);
            case LOADED, REJECTED, CANCELLED -> Set.of();
        };
    }
}
