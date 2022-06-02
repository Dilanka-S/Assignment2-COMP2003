package edu.curtin.app.model;

//import java.io.*;

import edu.curtin.app.model.exceptions.IncorrectTypeInput;


import java.util.Scanner;
import java.util.logging.Logger;

public class FileReader {
    private static Logger fileReaderLogger = Logger.getLogger(FileReader.class.getName());
    public static void readFile(Scanner fileScanner) {
        try{
            //Scanner fileScanner = new Scanner(new File(filename));
            int loggerCount = 0;
            int time;
            String type, town;
//            int size=0;
//            ArrayList<String> arrayList = new ArrayList<>();
            EmergencyList emergencyList = new EmergencyList();
            while (fileScanner.hasNext()){
                String line = fileScanner.nextLine();
                String[] splitBy = line.split(" ");
                if(splitBy.length==3){
                    time = Integer.parseInt(splitBy[0]);
                    type = splitBy[1];
                    town = splitBy[2];
                    {
                        fileReaderLogger.info("Data entered for Emergency(" + loggerCount + ")" +
                                "\n\tTime : " + time + " Type : " + type + " Location : " + town);
                        loggerCount++;
                    }
                    if(!(type.equals("fire")||type.equals("flood")||type.equals("chemical"))){
                        throw new IncorrectTypeInput("An incorrect emergency type has been entered to the system through " +
                                "the input file");
                    }
                    //System.out.println(time+" "+type+" "+town);
//                    arrayList.add(0, String.valueOf(time));
//                    arrayList.add(1,type);
//                    arrayList.add(2,town);
                    emergencyList.addEmergency(new Emergency(type,town,time));
 //                   size++;
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
                    {
                        fileReaderLogger.info("Data entered for Emergency(" + loggerCount + ")" +
                                "\n\tTime : " + time + " Type : " + type + " Location : " + town);
                        loggerCount++;
                    }
//                    arrayList.add(0, String.valueOf(time));
//                    arrayList.add(1,type);
//                    arrayList.add(2,town);
                    // System.out.println(time+" "+type+" "+town);
                    emergencyList.addEmergency(new Emergency(type,town,time));
//                    size++;
                }
                //System.out.println(splitBy.length);
            }
            //System.out.println("\n"+Arrays.toString(arrayList.toArray())+"\n\n");
            //emergencyList.display();
            emergencyList.passEmergencies();
        }catch (IncorrectTypeInput incorrectTypeInput){
            System.out.println("An incorrect input has been entered : "+incorrectTypeInput.getMessage());
        }catch (InterruptedException interruptedException){
            System.out.println("An interrupted exception has occurred : "+interruptedException.getMessage());
        }
    }
}
