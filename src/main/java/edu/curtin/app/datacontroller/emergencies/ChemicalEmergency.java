package edu.curtin.app.datacontroller.emergencies;

import edu.curtin.app.model.exceptions.IncorrectEmergencyType;
import edu.curtin.app.model.states.EmergencyState;
import edu.curtin.app.model.responders.ResponderComm;
import edu.curtin.app.model.responders.ResponderCommImpl;

import java.text.DecimalFormat;
import java.util.List;

public class ChemicalEmergency extends EmergencySimulator {
    private EmergencyState emergencyState;
    public final int CHEM_CLEANUP_TIME = 30;
    public final double CHEM_CASUALTY_PROB = 0.3;
    public final double CHEM_CONTAM_PROB = 0.1;
    public int time;
    public String type,location;

    public ChemicalEmergency(int time, String type, String location){
        //System.out.println("Chemical Method is working");
        this.time = time;
        this.type = type;
        this.location = location;
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
                while (time>0){
                    while (!end){
                        DecimalFormat decimalFormat = new DecimalFormat("#.#");
                        double probability = Double.parseDouble(decimalFormat.format(Math.random()));
                        if (probability == CHEM_CASUALTY_PROB){
                            casualtyCount++;
                            //responderComm.send("chemical casualty 1 "+location);
                            //System.out.println("CHEMICAL CASUALTY PROB");
                        } else if (probability == CHEM_CONTAM_PROB) {
                            contaminationCount++;
                            //responderComm.send("chemical damage 1 "+location);
                            //System.out.println("CHEMICAL CONTAMINATION PROB");
                        }
                        pollResult = responderComm.poll();
                        String tempString = pollResult.toString();
                        //System.out.println("tempString : "+tempString);
                        end = checkEnd(tempString);
                        System.out.println(pollResult);
                        Thread.sleep(1000);

                    }
                    time--;
                }
                responderComm.send("chemical casualty "+casualtyCount+" "+location);
                System.out.println();
                responderComm.send("chemical damage "+contaminationCount+" "+location);
                System.out.println();
                responderComm.send("chemical end "+location);
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
    public Boolean checkEnd(String pollResult){
        return pollResult.contains("end");
    }
}
