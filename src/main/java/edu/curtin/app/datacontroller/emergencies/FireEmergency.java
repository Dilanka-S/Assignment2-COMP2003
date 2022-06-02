package edu.curtin.app.datacontroller.emergencies;

import edu.curtin.app.model.exceptions.IncorrectEmergencyType;
import edu.curtin.app.model.states.EmergencyState;
import edu.curtin.app.model.responders.ResponderComm;
import edu.curtin.app.model.responders.ResponderCommImpl;
import edu.curtin.app.model.states.HighIntensityState;
import edu.curtin.app.model.states.IdleState;
import edu.curtin.app.model.states.LowIntensityState;

import java.lang.Thread;
import java.text.DecimalFormat;
import java.util.List;

public class FireEmergency extends EmergencySimulator{
    private EmergencyState emergencyState;
    private EmergencySimulator emergencySimulator;
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
        EmergencyState state = new LowIntensityState(emergencySimulator);
        try{
            if(!type.equals("fire")){
                throw new IncorrectEmergencyType("Incorrect Emergency Type has been passed to the Fire Emergency State");
            }else{
                ResponderComm responderComm = new ResponderCommImpl();
                List<String> temp = null;
                responderComm.send("fire start "+location);
                responderComm.send("fire low "+location);
                int casualtyCount = 0;
                int damageCount = 0;
                boolean end = false;
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                double probability;
                while (time>0){
                    /*
                    USE OF STATE PATTERN WITH instanceof
                     */
                    while(!end){

                    }
                    if(state instanceof LowIntensityState){
                        int lowTime = FIRE_LOW_TO_HIGH_TIME;
                        while(lowTime > 0){
                            probability = Double.parseDouble(decimalFormat.format(Math.random()));
                            if (probability == FIRE_LOW_CASUALTY_PROB){
                                casualtyCount++;
                            }
                            else if (probability == FIRE_LOW_DAMAGE_PROB) {
                                damageCount++;
                            }
                            temp = responderComm.poll();
                            System.out.println(temp);
                            Thread.sleep(1000);
                            lowTime--;
                        }
                        time = time - FIRE_LOW_TO_HIGH_TIME;
                    }
                    state = new HighIntensityState(emergencySimulator);
                    if(state instanceof HighIntensityState){
                        int highTime = FIRE_HIGH_TO_LOW_TIME;
                        while(highTime > 0){
                            probability = Double.parseDouble(decimalFormat.format(Math.random()));
                            if (probability == FIRE_HIGH_CASUALTY_PROB){
                                casualtyCount++;
                            }
                            else if (probability == FIRE_HIGH_DAMAGE_PROB) {
                                damageCount++;
                            }
                            temp = responderComm.poll();
                            System.out.println(temp);
                            Thread.sleep(1000);
                            highTime--;
                        }
                        time = time - FIRE_HIGH_TO_LOW_TIME;
                    }
                    time--;
                }
                //setting the state to idle since the fire has dissipated by becoming low
                state = new IdleState(emergencySimulator);
                responderComm.send("fire casualty "+casualtyCount+" "+location);
                System.out.println();
                responderComm.send("fire damage "+damageCount+" "+location);
                System.out.println();
                responderComm.send("fire end "+location);
                temp = responderComm.poll();
                System.out.println(temp);
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

    private boolean checkEnd(String pollResult){
        return pollResult.contains("end");
    }
}
