package edu.curtin.app.datacontroller.tester;

import edu.curtin.app.datacontroller.emergencies.EmergencySimulator;
import edu.curtin.app.model.Emergency;
import edu.curtin.app.model.EmergencyList;

import java.util.List;

public class Simulator {
    List<Emergency> emergencyList;
    EmergencySimulator emergencySimulator;
    public Simulator(List<Emergency> emergencyList, EmergencySimulator emergencySimulator) {
        this.emergencyList = emergencyList;
        this.emergencySimulator = emergencySimulator;
    }

    public void simulation(EmergencyList emergencyList, EmergencySimulator emergencySimulator){
        emergencySimulator.testCase();

    }

    public void testing(List<Emergency> emergencies) throws InterruptedException {
        System.out.println("Testing fkn worksss");
        int time;
        String type, location;
        for (int i = 0; i < emergencies.size(); i++) {
            System.out.println("Time : "+emergencies.get(i).getTime()+"\tType : "+emergencies.get(i).getEmergencyType()+
                    "Location : "+emergencies.get(i).getLocation());
            time = emergencies.get(i).getTime();
            type = emergencies.get(i).getEmergencyType();
            location = emergencies.get(i).getLocation();

            emergencySimulator.callDistributor(time,type,location);
        }

    }
}
