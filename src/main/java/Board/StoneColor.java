package Board;

public enum StoneColor {
    BLACK('B'),
    WHITE('W');

    private final char symbol;
    StoneColor(char symbol) {
        this.symbol = symbol;
    }
    public char getSymbol() {
        return symbol;
    }
}