package view;

import model.ModifyRamClasses;
import model.pizza.Drinks.Drinks;
import model.pizza.Pizza;
import view.TextColor.LetterColors;
import view.TextColor.PrintWithColors;

import java.util.List;
import java.util.Scanner;

public class View {
    private Scanner scanner;
    public View() {
        scanner = new Scanner(System.in);
    }

    public void showWelcomeText () {
        PrintWithColors.printColorText(LetterColors.CYAN,"*************************************************");
        PrintWithColors.printColorText(LetterColors.CYAN,"------------Welcome to PizziSalle----------------");
        PrintWithColors.printColorText(LetterColors.CYAN,"*************************************************");
        // ask for username
    }

    public String askForUsername () {
        PrintWithColors.printColorText(LetterColors.RED,"*************************************************");
        String username = printAndScanColor("           Do you have username?                 ", LetterColors.RED);
        PrintWithColors.printColorText(LetterColors.RED,"*************************************************");
        return username;
    }

    public void createCustomerMenu() {
        PrintWithColors.printColorText(LetterColors.CYAN,"\nWhat do you want to do?\n" +
                "\t[1] Make order\n" +
                "\t[2] See cart\n" +
                "\t[3] Update credentials\n" +
                "\t[4] Update delegation\n" +
                "\t[5] Exit\n" +
                "Option: ");
    }
    public void createDoYouHaveUsername() {
        PrintWithColors.printColorText(LetterColors.CYAN,"\nAre you a returning buyer or a new one?\n" +
                "\t[1] Returning buyer\n" +
                "\t[2] New buyer\n");
    }

    public void showOrderOptions() {
        PrintWithColors.printColorText(LetterColors.CYAN,"\nOrder items:\n" +
                "\t 1. Add Pizza\n" +
                "\t 2. Add Drink\n" +
                "\t 3. Finish\n");
    }

    /**
     * All classes implements ModifyRamClasses, therefore, we can take advantage of this to call the getName method to obtain the
     * information to list
     * @param list : the list of objects to print
     * @param itemName: The item name to print to the screen
     */
    public void printObjectList(List<? extends ModifyRamClasses> list, String itemName) {
        System.out.println();
        PrintWithColors.printColorText(LetterColors.CYAN,"---- Select the available "+itemName+" ----");
        int j = 0;
        for (ModifyRamClasses classes : list) {
            if(j%2 ==0)System.out.print("\t[" + (++j) + "]. " + classes.getName());
            else System.out.println("\t[" + (++j) + "]. " + classes.getName());
        }
        System.out.println();
        PrintWithColors.printColorText(LetterColors.CYAN,"--------------------");
    }

    public void delegationMenu() {
        PrintWithColors.printColorText(LetterColors.CYAN,  "\nChoose your delegation:\n" +
                "\t[1]. Barcelona\n" +
                "\t[2]. Lleida\n" +
                "\t[3]. Tarragona\n" +
                "\t[4]. Girona\n" +
                "Delegacio: ");
    }

    public void errorMessageMenu(int limit){
        PrintWithColors.printColorText(LetterColors.RED,"*************************************************");
        PrintWithColors.printColorText( LetterColors.RED, "ERROR!! You can only select options from 1 to "+limit);
        PrintWithColors.printColorText(LetterColors.RED,"*************************************************");
    }
    public void printToScreen(String message){
        System.out.println(message);
    }
    public void printToScreenColor(String message, LetterColors letterColor){
        PrintWithColors.printColorText(letterColor,message);
    }
    public String printAndScan(String message){
        System.out.println(message);
        return scanner.nextLine();
    }
    public String printAndScanColor(String message, LetterColors letterColor){
        PrintWithColors.printColorText(letterColor,message);
        return scanner.nextLine();
    }

    public Scanner getUserInput() {
        return scanner;
    }
}
