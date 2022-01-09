package view.TextColor;

public class PrintWithColors {
    public static void printBackground(BackgroundColor backgroundColor, String text){
        System.out.println(backgroundColor.toString()+text+BackgroundColor.RESET);
    }
    public static void printColorText(LetterColors letterColors, String text){
        System.out.println(letterColors.toString()+text+LetterColors.RESET);
    }
    public static void printBackgroundColorText(LetterColors letterColors, BackgroundColor backgroundColor, String text) {
        System.out.println(backgroundColor.toString()+letterColors.toString()+text+LetterColors.RESET+LetterColors.RESET);
    }
}
