package edu.curtin.app.datacontroller.tester;

import edu.curtin.app.datacontroller.emergencies.EmergencySimulator;
import edu.curtin.app.model.Emergency;
import edu.curtin.app.model.EmergencyList;

import java.util.List;

public class TestCase {
    List<Emergency> emergencyList;
    EmergencySimulator emergencySimulator;
    public TestCase(List<Emergency> emergencyList, EmergencySimulator emergencySimulator) {
        this.emergencyList = emergencyList;
        this.emergencySimulator = emergencySimulator;
    }

    public void testing(EmergencyList emergencyList, EmergencySimulator emergencySimulator){
        emergencySimulator.testCase();

    }

    public void testing(List<Emergency> emergencies) {
        System.out.println("Testing fkn worksss");
    }
}
