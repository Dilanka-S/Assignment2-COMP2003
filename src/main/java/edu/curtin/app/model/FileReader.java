package edu.curtin.app.model;

//import java.io.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader {
    public static void readFile(Scanner fileScanner) throws InterruptedException {
        //Scanner fileScanner = new Scanner(new File(filename));
        int time;
        String type, town;
        int size=0;
        ArrayList<String> arrayList = new ArrayList<>();
        EmergencyList emergencyList = new EmergencyList();
        while (fileScanner.hasNext()){
            String line = fileScanner.nextLine();
            String[] splitBy = line.split(" ");
            if(splitBy.length==3){
                time = Integer.parseInt(splitBy[0]);
                type = splitBy[1];
                town = splitBy[2];
                //System.out.println(time+" "+type+" "+town);
                arrayList.add(0, String.valueOf(time));
                arrayList.add(1,type);
                arrayList.add(2,town);
                emergencyList.addEmergency(new Emergency(type,town,time));
                size++;
            }else{
                StringBuilder strBuilder = new StringBuilder();
                int count = 2;
                time = Integer.parseInt(splitBy[0]);
                type = splitBy[1];
                while (count<splitBy.length){
                    strBuilder.append(splitBy[count]).append(" ");
                    //System.out.println(splitBy[count]);
                    count++;
                }
                town = strBuilder.toString();
                arrayList.add(0, String.valueOf(time));
                arrayList.add(1,type);
                arrayList.add(2,town);
               // System.out.println(time+" "+type+" "+town);
                emergencyList.addEmergency(new Emergency(type,town,time));
                size++;
            }
            //System.out.println(splitBy.length);
        }
        //System.out.println("\n"+Arrays.toString(arrayList.toArray())+"\n\n");
        //emergencyList.display();
        emergencyList.passEmergencies();
    }
}
