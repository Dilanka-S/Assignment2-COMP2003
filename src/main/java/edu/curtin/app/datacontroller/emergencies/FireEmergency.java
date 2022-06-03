package edu.curtin.app.datacontroller.emergencies;

import edu.curtin.app.model.exceptions.IncorrectEmergencyType;
import edu.curtin.app.model.states.EmergencyState;
import edu.curtin.app.model.responders.ResponderComm;
import edu.curtin.app.model.responders.ResponderCommImpl;
import edu.curtin.app.model.states.HighIntensityState;
import edu.curtin.app.model.states.IdleState;
import edu.curtin.app.model.states.LowIntensityState;
import edu.curtin.app.userinterface.MainMenu;

import java.lang.Thread;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Logger;

public class FireEmergency extends EmergencySimulator{
    private EmergencyState emergencyState;
    private EmergencySimulator emergencySimulator;
    private final int FIRE_LOW_TO_HIGH_TIME = 10;
    private final int FIRE_HIGH_TO_LOW_TIME = 20;
//    public final int FIRE_LOW_CLEANUP_TIME = 20;
    private final double FIRE_LOW_CASUALTY_PROB = 0.1;
    private final double FIRE_HIGH_CASUALTY_PROB = 0.7;
    private final double FIRE_LOW_DAMAGE_PROB = 0.2;
    private final double FIRE_HIGH_DAMAGE_PROB = 0.6;
    private int time;
    private String type,location;

    private static Logger fireLogger = Logger.getLogger(MainMenu.class.getName());

    // boolean for responders arrived or not

    public FireEmergency(Integer time, String type, String location) {
        //System.out.println("FIRE Method is working");
        this.time = time;
        this.type = type;
        this.location = location;
        fireLogger.info("New Fire Emergency created with : "+time+"\t"+type+"\t"+location);
        initialize();

    }

    public FireEmergency() {

    }

    @Override
    public void initialize() {
        EmergencyState state = new LowIntensityState(emergencySimulator);
        fireLogger.info("Fire Emergency set to : Low Intensity State");
        try{
            if(!type.equals("fire")){
                throw new IncorrectEmergencyType("Incorrect Emergency Type has been passed to the Fire Emergency State");
            }else{
                ResponderComm responderComm = new ResponderCommImpl();
                List<String> pollResult;
                fireLogger.info("Fire has started");
                responderComm.send("fire start "+location);
                int casualtyCount = 0;
                int damageCount = 0;
                boolean end = false;
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                double probability;
                String tempString;
                while (time>0){
                    /*
                    USE OF STATE PATTERN WITH instanceof
                     */
                    while(!end){
                        if(state instanceof LowIntensityState){
                            responderComm.send("fire low "+location);
                            //System.out.println("in low intensity condition");
                            int lowTime = FIRE_LOW_TO_HIGH_TIME;
                            while(lowTime > 0){
                                //System.out.println("in low intensity while loop");
                                probability = Double.parseDouble(decimalFormat.format(Math.random()));
                                if (probability == FIRE_LOW_CASUALTY_PROB){
                                    casualtyCount++;
                                    fireLogger.info("Fire Low Intensity : Casualty");
                                }
                                else if (probability == FIRE_LOW_DAMAGE_PROB) {
                                    damageCount++;
                                    fireLogger.info("Fire Low Intensity : Damage");
                                }
                                pollResult = responderComm.poll();
                                if(!pollResult.isEmpty()){
                                    if(pollResult.contains("fire + "+location) || pollResult.contains("fire - "+location)){
                                        System.out.println(pollResult);
                                        if(pollResult.contains("fire + "+location)){
                                            fireLogger.info("Fire Low Intensity : Responders Arrived");
                                        }else if (pollResult.contains("fire - "+location)){
                                            fireLogger.info("Fire Low Intensity : Responders Left");
                                        }
                                    }
                                }

                                tempString = pollResult.toString();
                                end = checkEnd(tempString);
                                Thread.sleep(1000);
                                lowTime--;
                            }
                            //Reducing pre-set constant from the total event time allocate from input file
                            time = time - FIRE_LOW_TO_HIGH_TIME;
                        }
                        state = new HighIntensityState(emergencySimulator);
                        fireLogger.info("Fire Emergency set to : High Intensity State");
                        if(state instanceof HighIntensityState){
                            responderComm.send("fire high "+location);
                            //System.out.println("in high intensity condition");
                            int highTime = FIRE_HIGH_TO_LOW_TIME;
                            while(highTime > 0){
                                //System.out.println("In high Intensity while loop");
                                probability = Double.parseDouble(decimalFormat.format(Math.random()));
                                if (probability == FIRE_HIGH_CASUALTY_PROB){
                                    casualtyCount++;
                                    fireLogger.info("Fire High Intensity : Casualty");
                                }
                                else if (probability == FIRE_HIGH_DAMAGE_PROB) {
                                    damageCount++;
                                    fireLogger.info("Fire High Intensity : Damage");
                                }
                                pollResult = responderComm.poll();
                                if(!pollResult.isEmpty()){
                                    if(pollResult.contains("fire + "+location) || pollResult.contains("fire - "+location)){
                                        System.out.println(pollResult);
                                        if(pollResult.contains("fire + "+location)){
                                            fireLogger.info("Fire High Intensity : Responders Arrived");
                                        }else if (pollResult.contains("fire - "+location)){
                                            fireLogger.info("Fire High Intensity : Responders Left");
                                        }
                                    }
                                }
                                tempString = pollResult.toString();
                                end = checkEnd(tempString);
                                Thread.sleep(1000);
                                highTime--;
                            }
                            time = time - FIRE_HIGH_TO_LOW_TIME;
                            state = new LowIntensityState(emergencySimulator);
                            fireLogger.info("Fire Emergency set to : Low Intensity State");
                        }
                    }
                    time--;
                }

                //setting the state to idle since the fire has dissipated by becoming low
                responderComm.send("fire end "+location);
                fireLogger.info("Fire Emergency has ended");
                state = new IdleState(emergencySimulator);
                fireLogger.info("Fire Emergency set to : Idle State");
                responderComm.send("fire casualty "+casualtyCount+" "+location);
                fireLogger.info("Fire Emergency Total Casualties : "+casualtyCount);
                System.out.println();
                responderComm.send("fire damage "+damageCount+" "+location);
                fireLogger.info("Fire Emergency Total Damages : "+damageCount);
                System.out.println();
                pollResult = responderComm.poll();
                System.out.println(pollResult);
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
