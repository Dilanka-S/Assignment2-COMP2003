package edu.curtin.app.datacontroller.emergencies;

import edu.curtin.app.model.exceptions.IncorrectEmergencyType;
import edu.curtin.app.model.states.EmergencyState;
import edu.curtin.app.model.responders.ResponderComm;
import edu.curtin.app.model.responders.ResponderCommImpl;
import edu.curtin.app.userinterface.MainMenu;

import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Logger;

public class ChemicalEmergency extends EmergencySimulator {
    private EmergencyState emergencyState;
//    private final int CHEM_CLEANUP_TIME = 30;
    private final double CHEM_CASUALTY_PROB = 0.3;
    private final double CHEM_CONTAM_PROB = 0.1;
    private int time;
    private String type,location;

    private static Logger chemicalLogger = Logger.getLogger(MainMenu.class.getName());


    public ChemicalEmergency(int time, String type, String location){
        //System.out.println("Chemical Method is working");
        this.time = time;
        this.type = type;
        this.location = location;
        chemicalLogger.info("New Chemical Emergency created with : "+time+"\t"+type+"\t"+location);
        initialize();
    }

    @Override
    public void initialize(){
//        if(type.equals("chemical")){
        try{
            if(!type.equals("chemical")){
                throw new IncorrectEmergencyType("Incorrect Emergency Type has been passed to the Chemical Emergency State");
            }else{
                boolean end = false;
                int casualtyCount=0, contaminationCount=0;
                ResponderComm responderComm = new ResponderCommImpl();
                List<String> pollResult;
                responderComm.send("chemical start "+location);
                chemicalLogger.info("Chemical Emergency has occurred");
                while (time>0){
                    while (!end){
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        double probability = Double.parseDouble(decimalFormat.format(Math.random()));
                        if (probability == CHEM_CASUALTY_PROB){
                            casualtyCount++;
                            chemicalLogger.info("Chemical Emergency : Casualty ");
                            //responderComm.send("chemical casualty 1 "+location);
                            //System.out.println("CHEMICAL CASUALTY PROB");
                        } else if (probability == CHEM_CONTAM_PROB) {
                            contaminationCount++;
                            chemicalLogger.info("Chemical Emergency : Contamination ");
                            //responderComm.send("chemical damage 1 "+location);
                            //System.out.println("CHEMICAL CONTAMINATION PROB");
                        }
                        pollResult = responderComm.poll();
                        String tempString = pollResult.toString();
                        //System.out.println("tempString : "+tempString);
                        end = checkEnd(tempString);
                        if(!pollResult.isEmpty()){
                            if(pollResult.contains("chemical + "+location) || pollResult.contains("chemical - "+location)){
                                System.out.println(pollResult);
                            }
                        }
                        //System.out.println(pollResult);
                        Thread.sleep(1000);
                    }
                    time--;
                }
                responderComm.send("chemical end "+location);
                responderComm.send("chemical casualty "+casualtyCount+" "+location);
                System.out.println();
                responderComm.send("chemical damage "+contaminationCount+" "+location);
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

    public Boolean checkEnd(String pollResult){
        return pollResult.contains("end");
    }
}
