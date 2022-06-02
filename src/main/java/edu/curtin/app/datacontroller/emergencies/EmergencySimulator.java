package edu.curtin.app.datacontroller.emergencies;

import edu.curtin.app.model.states.EmergencyState;

public abstract class EmergencySimulator {

    public static void distributor(Integer time, String type, String location) throws InterruptedException {
        EmergencySimulator emergencySimulator;
        //System.out.println("The type is :"+type);
        switch (type){
            case "fire" :
                emergencySimulator = new FireEmergency(time,type,location);
                //emergencySimulator.initialize(time,type,location);
                //System.out.println("Switch Fire");
                break;
            case "flood" :
                emergencySimulator = new FloodEmergency(time, type, location);
                //emergencySimulator.initialize(time, type, location);
                break;
            case "chemical" :
                emergencySimulator = new ChemicalEmergency(time, type, location);
                //emergencySimulator.initialize(time, type, location);
                break;
            default:
                break;
        }
        //return emergencySimulator;
    }
    public abstract void initialize();

    public abstract void setState(EmergencyState emergencyState);
    public abstract void idle();
    public abstract void running();
    public abstract void high_Intensity();
    public abstract void low_Intensity();
    public abstract void cleaned_Up();


}
