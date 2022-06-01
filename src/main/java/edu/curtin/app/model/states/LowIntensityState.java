package edu.curtin.app.model.states;

import edu.curtin.app.datacontroller.EmergencyController;
import edu.curtin.app.datacontroller.emergencies.EmergencySimulator;

public class LowIntensityState implements EmergencyState{
    private EmergencySimulator emergencySimulator;

    public LowIntensityState(EmergencySimulator emergencySimulator) {
        this.emergencySimulator = emergencySimulator;
    }



    @Override
    public void idle(EmergencyController emergencyController) {

    }

    @Override
    public void running(EmergencyController emergencyController) {

    }

    @Override
    public void high_Intensity(EmergencyController emergencyController) {
        emergencySimulator.setState(new HighIntensityState(emergencySimulator));
    }

    @Override
    public void low_Intensity(EmergencyController emergencyController) {

    }

    @Override
    public void cleaned_Up(EmergencyController emergencyController) {
        emergencySimulator.setState(new CleanupState(emergencySimulator));
    }

}
