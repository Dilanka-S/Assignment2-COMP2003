package edu.curtin.app.model.states;

import edu.curtin.app.datacontroller.EmergencyController;
import edu.curtin.app.datacontroller.emergencies.EmergencySimulator;

public class IdleState implements EmergencyState {
    private EmergencySimulator emergencySimulator;
    public IdleState(EmergencySimulator emergencySimulator) {
        this.emergencySimulator = emergencySimulator;
    }

    @Override
    public void idle(EmergencyController emergencyController) {

    }

    @Override
    public void running(EmergencyController emergencyController) {
        emergencySimulator.setState(new RunningState(emergencySimulator));
    }

    @Override
    public void high_Intensity(EmergencyController emergencyController) {

    }

    @Override
    public void low_Intensity(EmergencyController emergencyController) {
        emergencySimulator.setState(new LowIntensityState(emergencySimulator));
    }

    @Override
    public void cleaned_Up(EmergencyController emergencyController) {

    }

}
