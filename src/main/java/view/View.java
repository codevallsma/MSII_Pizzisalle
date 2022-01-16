package view;

import model.Customer;
import model.Delegation.Delegation;
import model.ModifyRamClasses;
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

    public String askForCustomerId() {
        PrintWithColors.printColorText(LetterColors.CYAN,"*************************************************");
        String username = printAndScanColor("PLEASE PROVIDE YOUR CUSTOMER ID IN ORDER TO BE IDENTIFIED AS A REGISTERED USER: ", LetterColors.RED);
        PrintWithColors.printColorText(LetterColors.CYAN,"*************************************************");
        return username;
    }

    public void printUserInformation(Customer customer, String delegation) {
        PrintWithColors.printColorText(LetterColors.GREEN,"" +
                "----------------------------------------------------------------\n" +
                "CUSTOMER INFORMATION: "+
                "\nClient id: " + customer.getCustomerId() +
                "\nClient name: " + customer.getName() +
                "\nClient surname: " + customer.getSurname1() +
                "\nClient last name: " + customer.getSurname2() +
                "\nDelegation: " + delegation +
                "\n----------------------------------------------------------------");
    }

    public String askExtraIngredients() {
        return  "\nDo you want extra ingredients for this type of pizza?\n" +
                "\t[1] Yes\n" +
                "\t[2] No\n"+
                "Select option: ";
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
    public String createDoYouHaveUsername() {
        return  "\nAre you a returning buyer or a new one?\n" +
                "\t[1] Returning buyer\n" +
                "\t[2] New buyer\n"+
                "Select option: ";
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
    public Object printObjectList(List<? extends ModifyRamClasses> list, String itemName) {
        System.out.println();
        PrintWithColors.printColorText(LetterColors.CYAN,"---- Select the available "+itemName+"s ----");
        int j = 0;
        for (ModifyRamClasses classes : list) {
            if(j%2 ==0)System.out.print("\t[" + (++j) + "]. " + classes.getName());
            else System.out.println("\t[" + (++j) + "]. " + classes.getName());
        }
        int optionSelected = 1;
        int listSize = list.size();
        boolean secondOrMore = false;
        do {
            System.out.println();
            System.out.println();
            if(secondOrMore){
                errorMessageMenu(listSize);
            }
            System.out.print("Select the "+itemName+" to choose: ");
            try {
                optionSelected = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException exception){
                optionSelected+=listSize;
            }
            secondOrMore = true;
        } while(optionSelected< 1 || optionSelected > listSize);
        PrintWithColors.printColorText(LetterColors.CYAN,"--------------------");
        return list.get(optionSelected-1);
    }

    public int menuAskOption(String message, int limit){
        int option = 1;
        boolean loop = false;
        do {
            if(loop)errorMessageMenu(limit);
            try {
                option = Integer.parseInt(printAndScanColor(message, LetterColors.CYAN));
            } catch (NumberFormatException exception) {
                option += limit;
            }
            loop=true;
        }while(option<1 || option>limit);
        return option;
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
