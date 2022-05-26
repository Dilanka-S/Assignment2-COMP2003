package edu.curtin.app.model;

import edu.curtin.app.datacontroller.emergencies.EmergencySimulator;
import edu.curtin.app.datacontroller.tester.TestCase;

import java.util.*;

public class EmergencyList {
    private List<Emergency> emergencies;
    private Set<EmergencyModel> observers;

    public EmergencyList() {
        emergencies = new ArrayList<>();
        observers = new HashSet<>();
    }

    public void addEmergency(Emergency emergency){
        emergencies.add(emergency);
        notifyObservers();
        //add notify observers here
    }

    public void addEmergencies(List<Emergency> emergencyList){
        emergencies.addAll(emergencyList);
        notifyObservers();
        //add notify observers here
    }

    public void removeEmergency(int index){
        emergencies.remove(index);
        notifyObservers();
    }

    public List<Emergency> getEmergencies(){
        return Collections.unmodifiableList(emergencies);
    }

    public void addEmergencyModel(EmergencyModel tempObserver){
        observers.add(tempObserver);
    }

    public void removeEmergencyModel(EmergencyModel tempObserver){
        observers.remove(tempObserver);
    }

    public void notifyObservers(){
        for (EmergencyModel tempObserver : observers){
            tempObserver.observerChange(emergencies);
        }
    }

    public void passEmergencies(){
        int time;
        String location, type;
        for (int i = 0; i < emergencies.size() ; i++) {
            time = emergencies.get(i).getTime();
            location = emergencies.get(i).getLocation();
            type = emergencies.get(i).getEmergencyType();
            //EmergencySimulator.distributor(time,type,location);
            EmergencySimulator emergencySimulator = EmergencySimulator.distributor(time,type,location);
            TestCase testCase = new TestCase(emergencies, emergencySimulator);
            testCase.testing(emergencies);
        }
    }

    public void display(){
        for (int i = 0; i < emergencies.size() ; i++) {
            System.out.println(emergencies.get(i).getEmergencyType());
            System.out.println(emergencies.get(i).getLocation());
            System.out.println(emergencies.get(i).getTime());
            System.out.println("");
        }
    }


}
