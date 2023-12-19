package niepotrzebne;

import niepotrzebne.SingleStone;

import java.util.ArrayList;
import java.util.List;

public class StonesGroup {
    private List<SingleStone> stones;

    // konstruktor inicjalizujący listę kamieni
    public StonesGroup(List<SingleStone> stones) {
        this.stones = stones;
    }

    public List<SingleStone> getNeighbors(int x, int y, String color) {
        List<SingleStone> neighbors = new ArrayList<>();

        for (SingleStone stone : stones) {
            if (isNeighbor(stone, x, y) && stone.getColor().equals(color)) {
                neighbors.add(stone);
            }
        }

        return neighbors;
    }

    public boolean hasNeighbors(int x, int y, String color) {
        for (SingleStone stone : stones) {
            if (isNeighbor(stone, x, y) && stone.getColor().equals(color)) {
                return true;
            }
        }
        return false;
    }

    // metoda pomocnicza do sprawdzania, czy kamień jest sąsiadem
    private boolean isNeighbor(SingleStone stone, int x, int y) {
        int stoneX = stone.getX();
        int stoneY = stone.getY();

        // sprawdzenie, czy kamień znajduje się na sąsiednich współrzędnych
        return Math.abs(stoneX - x) <= 1 && Math.abs(stoneY - y) <= 1 && (stoneX != x || stoneY != y);
    }
}