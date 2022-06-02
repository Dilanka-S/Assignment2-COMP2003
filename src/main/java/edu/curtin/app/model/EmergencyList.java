package edu.curtin.app.model;


import edu.curtin.app.datacontroller.tester.Simulator;

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

    public void passEmergencies() throws InterruptedException {
//        int time;
 //       String location, type;
        //display();
        System.out.println("\n\n");
        Simulator simulator = new Simulator(emergencies);
//        for (int i = 0; i < emergencies.size() ; i++) {
//            time = emergencies.get(i).getTime();
//            location = emergencies.get(i).getLocation();
//            type = emergencies.get(i).getEmergencyType();
//            //EmergencySimulator.distributor(time,type,location);
//        }
        simulator.testing(emergencies);
    }

    public void display(){
        for (Emergency emergency : emergencies) {
            System.out.println(emergency.getEmergencyType());
            System.out.println(emergency.getLocation());
            System.out.println(emergency.getTime());
            System.out.println("");
        }
    }


}
