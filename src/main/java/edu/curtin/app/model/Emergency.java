package edu.curtin.app.model;

import java.time.LocalDateTime;

public class Emergency {
    private String emergencyType;
    private String location;
    private Integer time;

    public Emergency(String emergencyType, String location, Integer time){
        this.setEmergencyType(emergencyType);
        this.setLocation(location);
        this.setTime(time);
    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public final void setEmergencyType(String emergencyType) {

        this.emergencyType = emergencyType;
    }

    public String getLocation() {
        return location;
    }

    public final void setLocation(String location) {
        this.location = location;
    }

    public Integer getTime() {
        return time;
    }

    public final void setTime(Integer time) {
        this.time = time;
    }
}
