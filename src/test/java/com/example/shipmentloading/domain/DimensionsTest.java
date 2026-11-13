package com.example.shipmentloading.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DimensionsTest {

    @Test
    void shouldCalculateVolume(){
        Dimensions dimensions = new Dimensions(30,20,10);

        assertEquals(6000, dimensions.volumeCm3());
    }

    @Test
    void shouldCalculateBigVolumeWithoutOverflow(){
        Dimensions dimensions = new Dimensions(2000,2000,2000);

        assertEquals(8_000_000_000L, dimensions.volumeCm3());
    }

    @Test
    void shouldFindLongestSide() {
        Dimensions dimensions = new Dimensions(30,120,10);

        assertEquals(120, dimensions.longestSideCm());
    }

    @Test
    void shouldRejectZeroLength(){
        assertThrows(IllegalArgumentException.class,()-> new Dimensions(0,20,10));
    }

    @Test
    void shouldRejectNegativeHeight(){
        assertThrows(IllegalArgumentException.class, () -> new Dimensions(30,20,-1));
    }

    @Test
    void shouldBeEqualWhenValuesAreEquals(){
        Dimensions first = new Dimensions(30,20,10);
        Dimensions second = new Dimensions(30,20,10);

        assertEquals(first,second);
    }


}