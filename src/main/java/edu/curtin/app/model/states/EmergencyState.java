package edu.curtin.app.model.states;

import edu.curtin.app.datacontroller.EmergencyController;

public interface EmergencyState {

    void idle(EmergencyController emergencyController);
    void running(EmergencyController emergencyController);
    void high_Intensity(EmergencyController emergencyController);
    void low_Intensity(EmergencyController emergencyController);
    void cleaned_Up(EmergencyController emergencyController);


}
