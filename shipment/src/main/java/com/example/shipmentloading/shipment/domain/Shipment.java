package com.example.shipmentloading.shipment.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Shipment {

    @EqualsAndHashCode.Include
    private final UUID id;
    private final String sourceOrderId;
    private final Dimensions dimensions;
    private final Weight weight;
    private ShipmentStatus status;


    private Shipment(UUID id, String sourceOrderId, Dimensions dimensions, Weight weight) {
        if (sourceOrderId == null || sourceOrderId.isBlank()) {
            throw new IllegalArgumentException("Source order id must not be empty");
        }
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.sourceOrderId = sourceOrderId;
        this.dimensions = Objects.requireNonNull(dimensions, "dimensions must not be null");
        this.weight = Objects.requireNonNull(weight, "weight must not be null");
        this.status = ShipmentStatus.RECEIVED;
    }


    public static Shipment receive(String sourceOrderId, Dimensions dimensions, Weight weight) {
        return new Shipment(UUID.randomUUID(), sourceOrderId, dimensions, weight);
    }


    public void markClassified() {
        moveTo(ShipmentStatus.CLASSIFIED);
    }

    public void markAwaitingAssignment() {
        moveTo(ShipmentStatus.AWAITING_ASSIGNMENT);
    }

    public void assign() {
        moveTo(ShipmentStatus.ASSIGNED);
    }

    public void markLoaded() {
        moveTo(ShipmentStatus.LOADED);
    }

    public void sendToQuarantine() {
        moveTo(ShipmentStatus.IN_QUARANTINE);
    }

    public void cancel() {
        moveTo(ShipmentStatus.CANCELLED);
    }

    private void moveTo(ShipmentStatus target) {
        if (!status.canTransitionTo(target)) {
            throw new IllegalStateException(
                    "Shipment " + id + " cannot move from " + status + " to " + target);
        }
        this.status = target;
    }

}
