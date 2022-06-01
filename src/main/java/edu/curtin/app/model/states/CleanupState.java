package edu.curtin.app.model.states;

import edu.curtin.app.datacontroller.EmergencyController;
import edu.curtin.app.datacontroller.emergencies.EmergencySimulator;

public class CleanupState implements EmergencyState {
    private EmergencySimulator emergencySimulator;

    public CleanupState(EmergencySimulator emergencySimulator) {
        this.emergencySimulator = emergencySimulator;
    }


    @Override
    public void idle(EmergencyController emergencyController) {
        emergencySimulator.setState(new IdleState(emergencySimulator));
    }

    @Override
    public void running(EmergencyController emergencyController) {
    //ignored
    }

    @Override
    public void high_Intensity(EmergencyController emergencyController) {


    }

    @Override
    public void low_Intensity(EmergencyController emergencyController) {

    }

    @Override
    public void cleaned_Up(EmergencyController emergencyController) {

    }
}
