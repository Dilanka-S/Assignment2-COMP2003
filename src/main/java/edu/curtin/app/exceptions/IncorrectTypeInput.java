package edu.curtin.app.exceptions;

public class IncorrectTypeInput extends EmergencyException{
    public IncorrectTypeInput() {
    }

    public IncorrectTypeInput(String message) {
        super(message);
    }
}
