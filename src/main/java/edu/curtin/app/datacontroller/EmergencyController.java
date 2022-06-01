package edu.curtin.app.datacontroller;

import edu.curtin.app.model.Emergency;
import edu.curtin.app.model.EmergencyList;

import java.util.List;

public class EmergencyController {
    private final EmergencyList list;

    public EmergencyController(EmergencyList list){
        this.list = list;
    }

    public void addEmergency(Integer time, String type, String location){
        System.out.println(time+type+location);
        list.addEmergency(new Emergency(type,location,time));
    }
    public void removeEmergency(int index){
        list.removeEmergency(index);
    }
    public List<Emergency> getEmergencies(){
        return list.getEmergencies();
    }

}
