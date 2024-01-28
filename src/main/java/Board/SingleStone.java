package Board;

public class SingleStone {
    private StoneColor color;
    private int x, y; // współrzędne kamienia na planszy
    private StonesGroup group;


    public SingleStone(StoneColor color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        group = new StonesGroup(color);
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
    public StonesGroup getGroup(){
        return group;
    }
    public void setGroup(StonesGroup group){
        this.group = group;
    }
    public void destroy(){
    }

}