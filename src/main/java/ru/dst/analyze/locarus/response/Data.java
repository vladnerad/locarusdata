package ru.dst.analyze.locarus.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties({ "referenceID", "navigationID", "objectID", "objectType", "dist", "mileage", "voltage",
        "message", "extra", "nativeFlags", "digitalIn", "generalListIn", "Coords", "Filter", "Flags", "Satellites",
        "framePacket"})
public class Data {
    private Time time;
//    private String referenceID;
//    private String navigationID;
//    private String objectID;
//    private String objectType;
//    private String dist;
//    private String mileage;
//    private String voltage;
//    private String message;
//    private List<String> extra;
//    private String nativeFlags;
//    private String digitalIn;
    private Map<String, Double> analogIn;
//    private List<String> generalListIn;
//    private List<String> Coords;
//    private List<String> Filter;
//    private List<String> Flags;
//    private List<String> Satellites;
//    private boolean framePacket;

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Map<String, Double> getAnalogIn() {
        return analogIn;
    }

    public void setAnalogIn(Map<String, Double> analogIn) {
        this.analogIn = analogIn;
    }
}
