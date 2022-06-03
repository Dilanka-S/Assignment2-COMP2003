package edu.curtin.app.datacontroller.emergencies;

import edu.curtin.app.model.exceptions.IncorrectEmergencyType;
import edu.curtin.app.model.states.*;
import edu.curtin.app.model.responders.ResponderComm;
import edu.curtin.app.model.responders.ResponderCommImpl;
import edu.curtin.app.userinterface.MainMenu;

import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Logger;

public class FloodEmergency extends EmergencySimulator{
    private EmergencyState emergencyState;
    private EmergencySimulator simulator;
    private final int FLOOD_END_TIME = 10;
    private final double FLOOD_CASUALTY_PROB = 0.5;
    private final double FLOOD_DAMAGE_PROB = 0.6;
    private int time;
    private String type,location;

    private static Logger floodLogger = Logger.getLogger(MainMenu.class.getName());

    public FloodEmergency(Integer time, String type, String location) {
        //System.out.println("Flood Method is working");
        this.time = time;
        this.type = type;
        this.location = location;
        floodLogger.info("New Flood Emergency created with : "+time+"\t"+type+"\t"+location);
        initialize();
    }


    @Override
    public void initialize()  {
        try {
            if(!type.equals("flood")){
                throw new IncorrectEmergencyType("Incorrect Emergency Type has been passed to the Flood Emergency State");
            }else{
                EmergencyState floodState  = new RunningState(simulator);
                floodLogger.info("Flood Emergency set to : Running State");
                int casualtyCount=0, damageCount=0;
                ResponderComm responderComm = new ResponderCommImpl();
                List<String> pollResult;
                String tempString;
                boolean end = false;
                responderComm.send("flood start "+location);
                floodLogger.info("Flood has started");
                int floodEnd = FLOOD_END_TIME;
                while(time>0 && floodEnd>0 ){
                    while (!end){
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        double probability = Double.parseDouble(decimalFormat.format(Math.random()));
                        if(probability == FLOOD_CASUALTY_PROB){
                            casualtyCount++;
                            floodLogger.info("Flood : Casualty");
                        } else if (probability == FLOOD_DAMAGE_PROB) {
                            damageCount++;
                            floodLogger.info("Flood : Damage");
                        }
                        pollResult = responderComm.poll();
                        if(!pollResult.isEmpty()){
                            if(pollResult.contains("flood + "+location) || pollResult.contains("flood - "+location)){
                                System.out.println(pollResult);
                                if(pollResult.contains("flood + "+location)){
                                    floodLogger.info("Flood  : Responders Arrived");
                                }else if (pollResult.contains("flood - "+location)){
                                    floodLogger.info("Flood  : Responders Left");
                                }
                            }
                        }
                        tempString = pollResult.toString();
                        end = checkEnd(tempString);
                        Thread.sleep(1000);
                        time--;
                        floodEnd--;

                    }
                }
                responderComm.send("flood end "+location);
                floodLogger.info("Flood Emergency has ended");
                responderComm.send("flood casualty "+casualtyCount+" "+location);
                System.out.println();
                responderComm.send("flood damage "+damageCount+" "+location);
                System.out.println();
                pollResult = responderComm.poll();
                System.out.println(pollResult);
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

    @Override
    public void printBorder() {
        System.out.println("__________________________________________________");
    }
    private boolean checkEnd(String pollResult){
        return pollResult.contains("end");
    }
}
