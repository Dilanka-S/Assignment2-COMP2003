package edu.curtin.app.datacontroller.emergencies;

import edu.curtin.app.model.states.EmergencyState;

public abstract class EmergencySimulator {
    public abstract void initialize(Integer time, String type, String location) throws InterruptedException;
    public abstract void setState(EmergencyState emergencyState);
    public abstract void idle();
    public abstract void running();
    public abstract void high_Intensity();
    public abstract void low_Intensity();
    public abstract void cleaned_Up();


}
