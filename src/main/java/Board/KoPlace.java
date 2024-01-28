package Board;

public class KoPlace {
    private  int x = -1, y = -1;
    private boolean isKo = false;

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public void setKoFalse() {
        isKo = false;
    }
    public boolean getIsKo(){
        return isKo;
    }

    public void setKo(int x, int y){
        this.x = x;
        this.y = y;
        this.isKo = true;
    }
}
