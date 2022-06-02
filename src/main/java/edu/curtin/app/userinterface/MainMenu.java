package edu.curtin.app.userinterface;

import edu.curtin.app.model.FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Logger;

public class MainMenu {
    private static Logger menuLogger = Logger.getLogger(MainMenu.class.getName());
    public static void mainMenu(){
        try(Scanner sc = new Scanner(System.in)) {
            String filename;
            boolean exit=false;
            while(!exit){
                Banners.filePrompt();
                System.out.print("Your selection : ");
                filename = sc.next();
                menuLogger.info("User has entered the filename : "+filename);
                if(filename.equals("EXIT")){
                    menuLogger.info("User has decided to EXIT the program");
                    exit=true;
                }else{
                    System.out.println("\nThe input file you entered is : "+filename+"\n");
                    //to check if the file is valid initially so that the while loop can remain active
                    try(Scanner tempScanner = new Scanner(new File(filename))){
                        menuLogger.info("Filename entered by the user has been passed to the file reader method ");
                        FileReader.readFile(tempScanner);
                    }catch (FileNotFoundException fileNotFoundException){
                        System.out.println("Please the check the filename provided since it cannot be found : "
                                +fileNotFoundException.getMessage());
                    }catch (InputMismatchException e) {
                        throw new InputMismatchException(e.getMessage());
                    }
                }
            }
        }catch (InputMismatchException inputMismatchException){
            System.out.println("Incorrect input values entered for the required field : "
                    +inputMismatchException.getMessage());
        }

    }
}
