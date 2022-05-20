package edu.curtin.app;

import edu.curtin.app.userinterface.Banners;
import edu.curtin.app.userinterface.MainMenu;


public class Simulator
{
    public static void main(String[] args)
    {
        try{
            Banners.welcomeBanner();
            MainMenu.mainMenu();
            Banners.exitBanner();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
