package ru.dst.analyze.locarus.response;

public class Time {

    private String value;

    public Time(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
