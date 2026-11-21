package com.example.shipmentloading.shipment.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentTest {

    private Shipment newShipment() {
        return Shipment.receive("ORDER-1", new Dimensions(30, 20, 10), Weight.ofKilograms(2));
    }

    @Test
    void newShipmentShouldBeReceived() {
        Shipment shipment = newShipment();

        assertEquals(ShipmentStatus.RECEIVED, shipment.getStatus());
        assertNotNull(shipment.getId());
    }

    @Test
    void shouldGoThroughHappyPathToLoaded() {
        Shipment shipment = newShipment();

        shipment.markClassified();
        shipment.markAwaitingAssignment();
        shipment.assign();
        shipment.markLoaded();

        assertEquals(ShipmentStatus.LOADED, shipment.getStatus());
    }

    @Test
    void shouldNotAssignWithoutClassification() {
        Shipment shipment = newShipment();

        assertThrows(IllegalStateException.class, shipment::assign);
        assertEquals(ShipmentStatus.RECEIVED, shipment.getStatus()); // статус не изменился
    }

    @Test
    void shouldRejectEmptyOrderId() {
        assertThrows(IllegalArgumentException.class,
                () -> Shipment.receive("  ", new Dimensions(30, 20, 10), Weight.ofKilograms(2)));
    }

    @Test
    void twoShipmentsWithSameDataShouldNotBeEqual() {
        Shipment first = newShipment();
        Shipment second = newShipment();

        assertNotEquals(first, second); // разные id → разные посылки
    }
}
