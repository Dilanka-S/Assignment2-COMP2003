package edu.curtin.app.datacontroller.emergencies;

import edu.curtin.app.model.states.EmergencyState;
import edu.curtin.app.responders.ResponderComm;
import edu.curtin.app.responders.ResponderCommImpl;

import java.lang.Thread;
import java.text.DecimalFormat;
import java.util.List;

public class FireEmergency extends EmergencySimulator{
    private EmergencyState emergencyState;
    public final int FIRE_LOW_TO_HIGH_TIME = 20;
    public final int FIRE_HIGH_TO_LOW_TIME = 30;
    public final int FIRE_LOW_CLEANUP_TIME = 20;
    public final double FIRE_LOW_CASUALTY_PROB = 0.1;
    public final double FIRE_HIGH_CASUALTY_PROB = 0.7;
    public final double FIRE_LOW_DAMAGE_PROB = 0.2;
    public final double FIRE_HIGH_DAMAGE_PROB = 0.6;

    // boolean for responders arrived or not

    public FireEmergency() {
        System.out.println("FIRE Method is working");
    }

    @Override
    public void initialize(Integer time, String type, String location) throws InterruptedException {
        System.out.println("Entered fire initialize : "+time+","+type+","+location);
        ResponderComm responderComm = new ResponderCommImpl();
        List<String> temp = null;
        while (time>0){
            //Thread.sleep(1000);
            //if(type.equals("fire")){
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            double probability = Double.parseDouble(decimalFormat.format(Math.random()));
            //System.out.println("- WHILE Loop FIRE : "+time+","+probability);
                if (probability == FIRE_LOW_CASUALTY_PROB){
                    System.out.println("> FIRE LOW CASUALTY PROB");
                    temp =  responderComm.poll();

                    time = time - FIRE_LOW_TO_HIGH_TIME;
                }else if (probability == FIRE_HIGH_CASUALTY_PROB){
                    System.out.println("> FIRE HIGH CASUALTY PROB");
                    temp = responderComm.poll();
                    time = time - FIRE_HIGH_TO_LOW_TIME;
                } else if (probability == FIRE_LOW_DAMAGE_PROB) {
                    System.out.println("> FIRE LOW DAMAGE PROB");
                    temp = responderComm.poll();
                } else if (probability == FIRE_HIGH_DAMAGE_PROB) {
                    System.out.println("> FIRE HIGH DAMAGE PROB");
                    temp = responderComm.poll();
                }
//            }else{
//                System.out.println("Not correct type");
//            }
            //responder class poll and send
            //`System.out.println(temp);
            time--;
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
        int running = 0;
        if(Math.random()==0){
            running = FIRE_LOW_TO_HIGH_TIME;
        }else{
            running = FIRE_HIGH_TO_LOW_TIME;
        }

    }

    @Override
    public void low_Intensity() {

    }

    @Override
    public void cleaned_Up() {

    }
}
