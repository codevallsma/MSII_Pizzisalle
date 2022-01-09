package view;

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
    private static String createGridCart(int cols, List<String> items) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            res.append(i + 1).append(". ").append(items.get(i)).append(" ");
            if (i > 0 && i % cols == 0) res.append("\n");
        }
        res.append("\n");
        return res.toString();
    }

    public Scanner getUserInput() {
        return scanner;
    }
}
