package com.example.shipmentloading.fleet.domain;

public record CargoSpace(int lengthCm, int widthCm, int heightCm, int maxPayloadKg) {

    public CargoSpace {
        if (lengthCm <= 0 || widthCm <= 0 || heightCm <= 0) {
            throw new IllegalArgumentException(
                    "Cargo space dimensions must be positive: " + lengthCm + "x" + widthCm + "x" + heightCm);
        }
        if (maxPayloadKg <= 0) {
            throw new IllegalArgumentException("Max payload must be positive, but was: " + maxPayloadKg);
        }
    }

    public long volumeCm3() {
        return (long) lengthCm * widthCm * heightCm;
    }
}
