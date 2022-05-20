package edu.curtin.app.model;

//import java.io.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {
    public static void readFile(Scanner fileScanner){
        //Scanner fileScanner = new Scanner(new File(filename));
        int time = 0;
        String type, town;
        while (fileScanner.hasNext()){
            String line = fileScanner.nextLine();
            String splitBy[] = line.split(" ");
            if(splitBy.length==3){
                time = Integer.parseInt(splitBy[0]);
                type = splitBy[1];
                town = splitBy[2];
                System.out.println(time+" "+type+" "+town);
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
                System.out.println(time+" "+type+" "+town);

            }
            //System.out.println(splitBy.length);
        }

    }
}
