package edu.curtin.app.datacontroller.tester;

import edu.curtin.app.datacontroller.emergencies.ChemicalEmergency;
import edu.curtin.app.datacontroller.emergencies.EmergencySimulator;
import edu.curtin.app.datacontroller.emergencies.FireEmergency;
import edu.curtin.app.datacontroller.emergencies.FloodEmergency;
import edu.curtin.app.model.Emergency;
import edu.curtin.app.model.EmergencyList;

import java.util.List;

public class Simulator {
    List<Emergency> emergencyList;
    EmergencySimulator emergencySimulator;
    public Simulator(List<Emergency> emergencyList) {
        EmergencySimulator emergencySimulator;
        this.emergencyList = emergencyList;
        //this.emergencySimulator = emergencySimulator;
    }

    public void simulation(EmergencyList emergencyList, EmergencySimulator emergencySimulator){
        //emergencySimulator.testCase();

    }

    public void testing(List<Emergency> emergencies) throws InterruptedException {
        //System.out.println("Testing worksss");
        int time;
        String type, location;
        for (int i = 0; i < emergencies.size(); i++) {
            //System.out.println("Time : "+emergencies.get(i).getTime()+"\tType : "+emergencies.get(i).getEmergencyType()+
                   // "\tLocation : "+emergencies.get(i).getLocation());
            time = emergencies.get(i).getTime();
            type = emergencies.get(i).getEmergencyType();
            location = emergencies.get(i).getLocation();
            distributor(time,type,location);
        }

    }
    public static void distributor(Integer time, String type, String location) throws InterruptedException {
        EmergencySimulator emergencySimulator;
        //System.out.println("The type is :"+type);
        switch (type){
            case "fire" :
                emergencySimulator = new FireEmergency();
                emergencySimulator.initialize(time,type,location);
                //System.out.println("Switch Fire");
                break;
            case "flood" :
                emergencySimulator = new FloodEmergency();
                break;
            case "chemical" :
                emergencySimulator = new ChemicalEmergency();
                break;
            default:
                break;
        }
        //return emergencySimulator;
    }

}
