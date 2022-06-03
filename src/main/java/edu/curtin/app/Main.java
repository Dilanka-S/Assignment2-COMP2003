/*
    Name        : Dilanka Vishad Seneviratne
    CURTIN ID   : 20529624
    SLIIT ID    : IT21120916
    Assessment  : COMP2003 - Assignment 2
 */
package edu.curtin.app;

import edu.curtin.app.userinterface.Banners;
import edu.curtin.app.userinterface.MainMenu;



public class Main
{
    public static void main(String[] args)
    {
        Banners.welcomeBanner();
        MainMenu.mainMenu();
        Banners.exitBanner();
    }
}
