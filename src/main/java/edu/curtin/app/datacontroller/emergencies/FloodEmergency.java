package edu.curtin.app.datacontroller.emergencies;

import edu.curtin.app.model.states.EmergencyState;

public class FloodEmergency extends EmergencySimulator{
    private EmergencyState emergencyState;
    public final int FLOOD_END_TIME = 40;
    public final double FLOOD_CASUALTY_PROB = 
    public FloodEmergency() {
        System.out.println("Flood Method is working");
    }

    public void testCase(){
        System.out.println("Flood Method is working");
    }

    @Override
    public void callDistributor(Integer time, String type, String location) {
    }

    @Override
    public void setState(EmergencyState emergencyState) {
        this.emergencyState = emergencyState;
    }


    @Override
    public void start() {

    }

    @Override
    public void idle() {

    }

    @Override
    public void running() {

    }

    @Override
    public void high_Intensity() {

    }

    @Override
    public void low_Intensity() {

    }

    @Override
    public void cleaned_Up() {

    }
}
