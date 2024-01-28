package Board;

import java.util.ArrayList;
import java.util.List;

public class StonesGroup {

    private List<SingleStone> stones;
    private StoneColor color;


    private int breaths;


    public StonesGroup(StoneColor color) {
        this.stones = new ArrayList<>();
        this.color = color;
    }

    public void addStone(SingleStone stone){
        if(!stones.contains(stone)) {
            stones.add(stone);
            stone.setGroup(this);
        }
    }

    public void mergeGroups(StonesGroup group){
        for(SingleStone stone: group.getStones()){
            this.addStone(stone);
        }
    }


    public void killGroup() {
        stones = new ArrayList<>();
    }

    public int getBreaths() {
        return breaths;
    }
    public void setBreaths(int breaths) {
        this.breaths = breaths;
    }
    public List<SingleStone> getStones() {
        return stones;
    }
    public void removeOneBreath(){
        breaths -=1;
    }
    public void printGroup(){
        for (SingleStone stone : stones) {
            System.out.println(stone.getX() + " " + stone.getY());
        }
    }
}