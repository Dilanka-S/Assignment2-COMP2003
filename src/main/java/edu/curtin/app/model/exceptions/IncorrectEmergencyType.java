package edu.curtin.app.model.exceptions;

public class IncorrectEmergencyType extends EmergencyException{

    public IncorrectEmergencyType() {
    }

    public IncorrectEmergencyType(String message) {
        super(message);
    }
}
