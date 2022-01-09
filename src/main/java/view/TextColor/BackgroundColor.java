package view.TextColor;

public enum BackgroundColor {
    RESET("\u001B[0m"),
    BLACK("\u001B[40m"),
    RED("\u001B[41m"),
    GREEN("\u001B[42m"),
    YELLOW("\u001B[43m"),
    BLUE("\u001B[45m"),
    CYAN("\u001B[46m"),
    WHITE("\u001B[47m");

    private String color;

    BackgroundColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}
