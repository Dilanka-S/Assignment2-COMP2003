package edu.curtin.app.userinterface;

public class Banners {
    public static void welcomeBanner(){
        System.out.println("\033[34m+ ---------------------------- +\033[m");
        System.out.println("\033[32m|          WELCOME TO          |");
        System.out.println("|              THE             |\033[m");
        System.out.println("|  \033[31mNATURAL DISASTER \033[31mSIMULATOR\033[m  |");
        System.out.println("\033[34m+ ---------------------------- +\033[m\n");
    }
    public static void exitBanner(){
        System.out.println("""
            \n
            \n
            \033[33mThanks for using the Natural Disaster Simulator!
                Created By\t:\tD.V.Seneviratne
                Student ID\t:\t20529624\s
                Institute\t:\tCurtin University/SLIIT International - Sri Lanka\033[m\s""");
    }
}
