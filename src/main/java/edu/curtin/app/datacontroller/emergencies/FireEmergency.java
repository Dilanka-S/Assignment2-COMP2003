package edu.curtin.app.datacontroller.emergencies;

import edu.curtin.app.exceptions.IncorrectEmergencyType;
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
    public int time;
    public String type,location;

    // boolean for responders arrived or not

    public FireEmergency(Integer time, String type, String location) {
        //System.out.println("FIRE Method is working");
        this.time = time;
        this.type = type;
        this.location = location;
        initialize();
    }

    public FireEmergency() {

    }

    @Override
    public void initialize() {
        //System.out.println("Entered fire initialize : "+time+","+type+","+location);
        try{
            if(!type.equals("fire")){
                throw new IncorrectEmergencyType("Incorrect Emergency Type has been passed to the Fire Emergency State");
            }else{
                ResponderComm responderComm = new ResponderCommImpl();
                List<String> temp = null;
                responderComm.send("fire start "+location);
                int casualtyCount = 0;
                int damageCount = 0;
                while (time>0){
                    //Thread.sleep(1000);
                    //if(type.equals("fire")){
                    DecimalFormat decimalFormat = new DecimalFormat("#.#");
                    double probability = Double.parseDouble(decimalFormat.format(Math.random()));
                    //System.out.println("- WHILE Loop FIRE : "+time+","+probability);
                    if (probability == FIRE_LOW_CASUALTY_PROB){
                        //System.out.println("> FIRE LOW CASUALTY PROB");
                        //time = time - FIRE_LOW_TO_HIGH_TIME;
                        casualtyCount++;
                    }else if (probability == FIRE_HIGH_CASUALTY_PROB){
                        //System.out.println("> FIRE HIGH CASUALTY PROB");
                        //temp = responderComm.poll();
                        //time = time - FIRE_HIGH_TO_LOW_TIME;
                        casualtyCount++;
                    } else if (probability == FIRE_LOW_DAMAGE_PROB) {
                        //System.out.println("> FIRE LOW DAMAGE PROB");
                        //temp = responderComm.poll();
                        damageCount++;
                    } else if (probability == FIRE_HIGH_DAMAGE_PROB) {
                        //System.out.println("> FIRE HIGH DAMAGE PROB");
                        //temp = responderComm.poll();
                        damageCount++;
                    }
//            }else{
//                System.out.println("Not correct type");
//            }
                    //responder class poll and send
                    //System.out.println(temp);
                    time--;
                }
                responderComm.send("fire casualty "+casualtyCount+" "+location);
                System.out.println();
                responderComm.send("fire damage "+damageCount+" "+location);
                System.out.println();
                responderComm.send("fire end "+location);
                temp = responderComm.poll();
                System.out.println(temp);
                Thread.sleep(1000);
            }

        }catch (InterruptedException interruptedException){
            System.err.println("An exception has occurred when running the threads : "+interruptedException.getMessage());
        }catch (IncorrectEmergencyType incorrectEmergencyType){
            System.err.println("An Incorrect Emergency Type Exception has occurred :"+incorrectEmergencyType.getMessage());
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
