package ru.dst.analyze.locarus.handlers;

public abstract class Handler {

    private String name;
    private int startBit;
    private int length;
    private int multiply;
    private int shift;

//    public Handler(String name, int startBit, int length, int multiply, int shift) {
//        this.name = name;
//        this.startBit = startBit;
//        this.length = length;
//        this.multiply = multiply;
//        this.shift = shift;
//    }

    public String getName() {
        return name;
    }

    public int getStartBit() {
        return startBit;
    }

    public int getLength() {
        return length;
    }

    public int getMultiply() {
        return multiply;
    }

    public int getShift() {
        return shift;
    }
}
