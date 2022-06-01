package edu.curtin.app.datacontroller.emergencies;

import edu.curtin.app.model.Emergency;
import edu.curtin.app.model.states.EmergencyState;

public abstract class EmergencySimulator {
    public static EmergencySimulator distributor(Integer time, String type, String location){
        EmergencySimulator emergencySimulator = null;
        System.out.println("The type is :"+type);
        switch (type){
            case "fire" :
                emergencySimulator = new FireEmergency();
                System.out.println("Switch Fire");
                break;
            case "flood" :
                emergencySimulator = new FloodEmergency();
                break;
            case "chemical" :
                emergencySimulator = new ChemicalEmergency();
                break;
            default:
                break;
        }
        return emergencySimulator;
    }
    public abstract void testCase();
    public abstract void callDistributor(Integer time, String type, String location) throws InterruptedException;
    public abstract void setState(EmergencyState emergencyState);
    public abstract void start();
    public abstract void idle();
    public abstract void running();
    public abstract void high_Intensity();
    public abstract void low_Intensity();
    public abstract void cleaned_Up();


}
