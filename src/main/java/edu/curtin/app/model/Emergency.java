package edu.curtin.app.model;

import java.time.LocalDateTime;

public class Emergency {
    private String emergencyType;
    private String location;
    private LocalDateTime time;

    public Emergency(String emergencyType, String location, LocalDateTime time){
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

    public LocalDateTime getTime() {
        return time;
    }

    public final void setTime(LocalDateTime time) {
        this.time = time;
    }
}
