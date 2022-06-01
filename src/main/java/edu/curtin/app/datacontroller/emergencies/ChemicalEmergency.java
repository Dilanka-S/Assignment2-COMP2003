package edu.curtin.app.datacontroller.emergencies;

import edu.curtin.app.model.Emergency;
import edu.curtin.app.model.states.EmergencyState;
import edu.curtin.app.model.states.IdleState;

public class ChemicalEmergency extends EmergencySimulator {
    private EmergencyState emergencyState;
    public ChemicalEmergency(){
        System.out.println("Chemical Method is working");
    }
    public void testCase(){
        System.out.println("Chemical Method is working");
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
