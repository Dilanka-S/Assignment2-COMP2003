package edu.curtin.app.datacontroller.emergencies;

import edu.curtin.app.model.exceptions.IncorrectEmergencyType;
import edu.curtin.app.model.states.*;
import edu.curtin.app.model.responders.ResponderComm;
import edu.curtin.app.model.responders.ResponderCommImpl;

import java.text.DecimalFormat;
import java.util.List;

public class FloodEmergency extends EmergencySimulator{
    private EmergencyState emergencyState;
    private EmergencySimulator simulator;
    public final int FLOOD_END_TIME = 100;
    public final double FLOOD_CASUALTY_PROB = 0.5;
    public final double FLOOD_DAMAGE_PROB = 0.6;
    public int time;
    public String type,location;

    public FloodEmergency(Integer time, String type, String location) {
        //System.out.println("Flood Method is working");
        this.time = time;
        this.type = type;
        this.location = location;
        initialize();
    }


    @Override
    public void initialize()  {
        try {
            if(!type.equals("flood")){
                throw new IncorrectEmergencyType("Incorrect Emergency Type has been passed to the Flood Emergency State");
            }else{
                EmergencyState floodState  = new RunningState(simulator);
                int casualtyCount=0, damageCount=0;
                ResponderComm responderComm = new ResponderCommImpl();
                List<String> temp = null;
                responderComm.send("flood start "+location);
                while(time>0){
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        double probability = Double.parseDouble(decimalFormat.format(Math.random()));
                        if(probability == FLOOD_CASUALTY_PROB){
                            casualtyCount++;
                        } else if (probability == FLOOD_DAMAGE_PROB) {
                            damageCount++;
                        }
                        time--;


                }
                responderComm.send("flood casualty "+casualtyCount+" "+location);
                System.out.println();
                responderComm.send("flood damage "+damageCount+" "+location);
                System.out.println();
                responderComm.send("flood end "+location);
                temp = responderComm.poll();
                System.out.println(temp);
                Thread.sleep(1000);
            }

        }
        catch (InterruptedException interruptedException){
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

    }

    @Override
    public void low_Intensity() {

    }

    @Override
    public void cleaned_Up() {

    }
}
