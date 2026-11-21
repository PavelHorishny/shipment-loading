package com.example.shipmentloading.shipment.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeightTest {

    @Test
    void shouldReturnWeightInGrams(){
        assertEquals(2500, Weight.ofKilograms(2.5).grams());
    }

    @Test
    void shouldReturnWeightInKilos(){
        assertEquals(1.5, new Weight(1500).kilograms());
    }

    @Test
    void throwsExceptionIfWeightIsZeroOrLess(){
        assertThrows(IllegalArgumentException.class, () -> new Weight(0));
        assertThrows(IllegalArgumentException.class, () -> new Weight(-100));
    }

    @Test
    void shouldCheckIfOneHeavierThenOther(){
        Weight lesser = new Weight(1000);
        Weight bigger = new Weight(2000);

        assertTrue(bigger.isHeavierThan(lesser));
        assertFalse(lesser.isHeavierThan(bigger));
    }

    @Test
    void result(){
        System.out.println(Math.round(0.4));
    }

}
