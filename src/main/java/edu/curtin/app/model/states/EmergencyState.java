package edu.curtin.app.model.states;

import edu.curtin.app.datacontroller.EmergencyController;

public interface EmergencyState {
    void start(EmergencyController emergencyController);
    void run(EmergencyController emergencyController);
    void cancel(EmergencyController emergencyController);
}
