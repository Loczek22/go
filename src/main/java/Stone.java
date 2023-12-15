public class Stone {
    private StoneColor color;
    private int x, y; // współrzędne kamienia na planszy
    private boolean isAlive;

    public Stone(StoneColor color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.isAlive = true;
    }
    public StoneColor getColor() {
        return color;
    }
    // sprawdzenie czy kamień ma oddechy
    public boolean hasBreaths() {
        return true;
    }
    // ustawienie śmierci kamienia
    public void setDead() {
        isAlive = false;
    }
    // sprawdzenie czy kamień jest żywy
    public boolean isAlive() {
        return isAlive;
    }
}