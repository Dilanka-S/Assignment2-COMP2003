package edu.curtin.app.userinterface;

import edu.curtin.app.model.FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class MainMenu {
    public static void mainMenu(){
        try {
            Scanner sc = new Scanner(System.in);
            String filename;
            boolean exit=false;
            while(!exit){
                //IntelliJ text block
                System.out.println("""
                    Please enter the name of the desired input file
                    \tOR
                    EXIT to quit the simulation
                    """);
                System.out.print("Your selection : ");
                filename = sc.next();
                if(filename.equals("EXIT")){
                    exit=true;
                }else{
                    System.out.println("\nThe input file you entered is : "+filename+"\n");
                    //to check if the file is valid initially so that the while loop can remain active
                    Scanner tempScanner = new Scanner(new File(filename));
                    FileReader.readFile(tempScanner);
                    tempScanner.close();
                }
            }
            sc.close();


        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println("Please the check the filename provided since it cannot be found : "
                    +fileNotFoundException.getMessage());
        }
    }
}
