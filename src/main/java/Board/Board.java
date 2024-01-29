package Board;

import Server.ServerPlayer;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int boardSize; //19, 13, 9

    public SingleStone[][] getStones() {
        return stones;
    }

    public final SingleStone[][] stones;
    private final KoPlace koPlacement = new KoPlace();
    public static StoneColor turn = StoneColor.BLACK;;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.stones = new SingleStone[boardSize][boardSize];
        resetAll();
    }


    // resetowanie planszy
    public void resetAll() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                stones[i][j] = null;
            }
        }
    }

    public void updateBreaths(){
        List<StonesGroup> updatedGroups = new ArrayList<>();
        for(int x = 0; x < boardSize; x++){
            for(int y = 0; y < boardSize; y++){
                if(stones[x][y] != null) {
                    StonesGroup currentGroup = stones[x][y].getGroup();
                    if(!updatedGroups.contains(currentGroup)){
                        int breaths = calculateBreaths(currentGroup);
                        currentGroup.setBreaths(breaths);
                        updatedGroups.add(currentGroup);
                    }

                }
            }
        }
    }
    public int calculateBreaths(StonesGroup group){
        int[][] fields = {{0,-1},{0,1},{-1,0},{1,0}};
        int breaths = 0;
        List<String> neighboringFields = new ArrayList<>();
        for(SingleStone stone : group.getStones()){
            for(int[] dir: fields) {
                int currX = stone.getX() + dir[0];
                int currY = stone.getY() + dir[1];
                String field = currX+" "+currY;
                if(isEmpty(currX, currY) && !neighboringFields.contains(field)){
                    breaths += 1;
                    neighboringFields.add(field);
                }
            }
        }
        return breaths;
    }
    public void updateGroups(SingleStone stone){
        int[][] fields = {{0,-1},{0,1},{-1,0},{1,0}};
        StonesGroup newGroup = stone.getGroup();
        for(int[] dir: fields) {
            int x = stone.getX() + dir[0];
            int y = stone.getY() + dir[1];
            if(isValidCoordinate(x, y) && !isEmpty(x, y)){
                StonesGroup group = stones[x][y].getGroup();
                StoneColor color = stones[x][y].getColor();
                if(group.getBreaths() == 1 && color != turn){
                    if(checkKo(stone.getX(), stone.getY(), x, y)){
                        koPlacement.setKo(x, y);
                    }
                    System.out.println("removing "+ x+" "+y+" group");
                    removeGroup(group);
                } else if (color == turn && group != newGroup) {
                    System.out.println("merging" + x+" "+y+" group into "+stone.getX()+" "+stone.getY());
                    newGroup.mergeGroups(group);
                }
            }
        }
    }
    public boolean checkKo(int x, int y, int delX, int delY){
        int[][] fields = {{0,-1},{0,1},{-1,0},{1,0}};
        int checkNew = 0, checkDel = 0;
        for(int[] dir : fields){
            int newX = x + dir[0];
            int newDelX = delX + dir[0];
            int newY = y + dir[1];
            int newDelY = delY + dir[1];
            if(stones[newX][newY].getColor() != turn || (newX == newDelX && newY == newDelY)){
                checkNew += 1;
            }
            if(stones[newDelX][newDelY].getColor() == turn || (newX == newDelX && newY == newDelY)){
                checkDel += 1;
            }
        }
        if(checkNew == 4 && checkDel == 4){
            return true;
        }
        return false;
    }

    public void removeGroup(StonesGroup group) {
        for(SingleStone stone : group.getStones()){
            stone.setGroup(null);
            stones[stone.getX()][stone.getY()] = null;
        }
        group.killGroup();
    }

    public boolean placeStone(SingleStone stone) {
        int x = stone.getX();
        int y = stone.getY();

        if (isEmpty(x, y) && !willDie(x, y) && !isKoHere(x, y)) {
            stones[x][y] = stone;
            StonesGroup newGroup = new StonesGroup(turn);
            newGroup.addStone(stone);
            if(koPlacement.getIsKo()){
                koPlacement.setKoFalse();
            }
            return true;
        }else{
            System.out.println("Nieprawidłowy ruch");
            return false;
        }
    }


    public boolean willDie(int x, int y){
        return checkBreathsForNewStone(x, y) == 0 && !willKill(x, y);
    }

    private boolean willKill(int x, int y) {
        int[][] fields = {{0,-1},{0,1},{-1,0},{1,0}};
        for(int[] dir: fields){
            int newX = x + dir[0];
            int newY = y + dir[1];
            if(isValidCoordinate(newX, newY) && stones[newX][newY].getColor() != turn && stones[newX][newY].getGroup().getBreaths() == 1){
                return true;
            }
        }
        return false;
    }



    public int checkBreathsForNewStone(int x, int y) {
        int breaths = 0;
        int[][] fields = {{0,-1},{0,1},{-1,0},{1,0}};
        List<StonesGroup> groups = new ArrayList<>();
        for(int[] dir: fields){
            int newX = x + dir[0];
            int newY = y + dir[1];
            if(isValidCoordinate(newX, newY)){
                if(stones[newX][newY] == null) {
                    breaths += 1;
                } else if (stones[newX][newY].getColor() == turn && !groups.contains(stones[newX][newY].getGroup())) {
                    StonesGroup currentGroup = stones[newX][newY].getGroup();
                    breaths += currentGroup.getBreaths() - 1;
                    groups.add(currentGroup);
                }
            }
        }
        return breaths;
    }

    // obsługa wyjścia poza planszę
    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < boardSize && y >= 0 && y < boardSize;
    }

    public boolean isKoHere(int x, int y) {
        if(koPlacement.getX() == x && koPlacement.getY() == y && koPlacement.getIsKo()){
            return true;
        }
        return false;
    }

    public boolean isEmpty(int x, int y) {
        return isValidCoordinate(x, y) && stones[x][y] == null;
    }
    public void printBoard(){
        if(stones[1][1] != null){System.out.println(stones[1][1].getGroup().getBreaths());}
        System.out.println("   0 1 2 3 4 5 6 7 8");
        for(int i = 0; i<9;i++){
            String row = "";
            for(int j = 0; j<9; j++){
                if(stones[i][j] == null){
                    row += "0 ";
                }else {
                    row += String.valueOf(stones[i][j].getColor().getSymbol()) + " ";
                }
            }
            System.out.println(i+"  "+ row);
        }
    }

    public String endGame() {

        if(countPoints(StoneColor.BLACK) > countPoints(StoneColor.WHITE)){
            return "BLACK WINS";
        } else if (countPoints(StoneColor.BLACK) < countPoints(StoneColor.WHITE)) {
            return "WHITE WINS";
        }else{
            return "DRAW";
        }

    }
    private int countPoints(StoneColor color){
        int points = 0;
        List<List<int[]>> checkedTerritories = new ArrayList<>();
        for(int x = 0; x < boardSize; x++){
            for(int y = 0; y < boardSize; y++){
                if(isEmpty(x, y)){
                    List<int[]> group = createTerritoryGroup(x, y, new ArrayList<>());
                    if(checkTerritory(color, group)){
                        points += countPointsOfGroup(group);
                    }
                }
            }
        }
        return points;
    }

    public int countPointsOfGroup(List<int[]> group){
        return group.size();
    }
    public boolean checkTerritory(StoneColor color, List<int[]> group){
        for(int[] field : group){
            int[][] fields = {{0,-1},{0,1},{-1,0},{1,0}};
            for(int[] dir : fields){
                int x = field[0] + dir[0];
                int y = field[1] + dir[1];
                if(isEmpty(x, y) || (isValidCoordinate(x, y) && stones[x][y].getColor() != color)){
                    return false;
                }
            }
        }
        return true;
    }

    public List<int[]> createTerritoryGroup(int x, int y, List<int[]> group){
        if (!group.contains(new int[]{x, y})) {
            group.add(new int[]{x, y});
        }
        if(isEmpty(x,y-1) && !group.contains(new int[]{x, y-1})){
            group.add(new int[]{x, y-1});
            createTerritoryGroup(x, y-1, group);
        }
        if(isEmpty(x,y+1) && !group.contains(new int[]{x,y+1})){
            group.add(new int[]{x,y+1});
            createTerritoryGroup(x, y+1, group);
        }if(isEmpty(x-1,y) && !group.contains(new int[]{x-1,y})){
            group.add(new int[]{x-1,y});
            createTerritoryGroup(x-1,y, group);
        }
        if(isEmpty(x+1,y) && !group.contains(new int[]{x+1,y})){
            group.add(new int[]{x+1,y});
            createTerritoryGroup(x+1,y, group);
        }
        return group;

    }

}