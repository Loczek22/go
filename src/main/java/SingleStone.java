public class SingleStone {
    private StoneColor color;
    private int x, y; // współrzędne kamienia na planszy

    public SingleStone(StoneColor color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public StoneColor getColor() {
        return color;
    }
    // sprawdzenie czy kamień ma oddechy
    public boolean hasBreaths() {
        return true;
    }
}