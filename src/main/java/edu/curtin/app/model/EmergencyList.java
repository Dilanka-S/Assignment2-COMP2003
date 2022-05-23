package edu.curtin.app.model;

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

}
