package edu.curtin.app.datacontroller.emergencies;

import edu.curtin.app.model.states.EmergencyState;

public class ChemicalEmergency extends EmergencySimulator {
    private EmergencyState emergencyState;
    public final int CHEM_CLEANUP_TIME = 30;
    public final double CHEM_CASUALTY_PROB = 0.3;
    public final double CHEM_CONTAM_PROB = 0.1;

    public ChemicalEmergency(){
        System.out.println("Chemical Method is working");
    }

    @Override
    public void initialize(Integer time, String type, String location) {
        double probability;
        if(type.equals("chemical")){
            while (time!=0){
                probability = Math.random();
                if (probability == CHEM_CASUALTY_PROB){
                    System.out.println("CHEMICAL CASUALTY PROB");
                } else if (probability == CHEM_CONTAM_PROB) {
                    System.out.println("CHEMICAL CONTAMINATION PROB");
                }
                time--;
            }
        }else{
            System.out.println("Incorrect type");
        }

    }

    @Override
    public void setState(EmergencyState emergencyState) {
        this.emergencyState = emergencyState;
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
