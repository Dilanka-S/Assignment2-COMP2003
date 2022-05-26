package edu.curtin.app.model.states;

import edu.curtin.app.datacontroller.EmergencyController;

public class HighIntensityState implements EmergencyState{
    private static final int FIRE_LOW_TO_HIGH_TIME = 20;
    private static final int FIRE_HIGH_TO_LOW_TIME = 20;
    private static final int FIRE_LOW_CLEANUP_TIME = 20;
    private static int fire_Low_Casualty_Prob, fire_Low_Damage_Prob;
    private static int fire_High_Casualty_Prob, fire_High_Damage_Prob;
    @Override
    public void start(EmergencyController emergencyController) {
        System.out.println(FIRE_HIGH_TO_LOW_TIME+FIRE_LOW_TO_HIGH_TIME);

    }

    @Override
    public void idle(EmergencyController emergencyController) {

    }

    @Override
    public void running(EmergencyController emergencyController) {

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
