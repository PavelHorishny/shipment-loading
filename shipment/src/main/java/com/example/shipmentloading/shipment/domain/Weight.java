package com.example.shipmentloading.shipment.domain;

public record Weight (long grams){

    public Weight {
        if(grams<=0){
            throw new IllegalArgumentException("Weight must be positive, but was: "+ grams);
        }
    }

    public static Weight ofKilograms(double kilograms){
        return new Weight(Math.round(kilograms*1000));
    }

    public double kilograms(){
        return grams/1000.0;
    }

    public boolean isHeavierThan(Weight other){
        return this.grams > other.grams;
    }
}
