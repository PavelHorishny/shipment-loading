package com.example.shipmentloading.domain;

public record Dimensions(int lengthCm, int widthCm, int heightCm) {

    public Dimensions{
        if(lengthCm <=0){
            throw new IllegalArgumentException("Length must be positive, but was: " + lengthCm);
        }
        if(widthCm <=0){
            throw new IllegalArgumentException("Width must be positive, but was: " + widthCm);
        }
        if(heightCm <=0) {
            throw new IllegalArgumentException("Height must be positive, but was: "+heightCm);
        }
    }

    public long volumeCm3(){
        return (long) lengthCm*widthCm*heightCm;
    }

    public int longestSideCm(){
        return Math.max(lengthCm,Math.max(widthCm,heightCm));
    }
}
