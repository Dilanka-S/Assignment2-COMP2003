package edu.curtin.app.datacontroller;

import edu.curtin.app.model.Emergency;
import edu.curtin.app.model.EmergencyList;

import java.time.LocalDateTime;
import java.util.List;

public class EmergencyController {
    private EmergencyList list;

    public EmergencyController(EmergencyList list){
        this.list = list;
    }

    public void addEmergency(LocalDateTime time, String type, String location){
        list.addEmergency(new Emergency(type,location,time));
    }
    public void removeEmergency(int index){
        list.removeEmergency(index);
    }
    public List<Emergency> getEmergencies(){
        return list.getEmergencies();
    }

}
