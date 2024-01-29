package Database;

public class Move {
    private final int gameId;
    private final int moveId;
    private final int x;
    private final int y;
    private final String color;
    private final String moveType;

    public Move(Builder builder) {
        this.gameId = builder.gameId;
        this.moveId = builder.moveId;
        this.x = builder.x;
        this.y = builder.y;
        this.color = builder.color;
        this.moveType = builder.moveType;
    }

    public int getGameId() {
        return gameId;
    }

    public int getMoveId() {
        return moveId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor() {
        return color;
    }

    public String getMoveType() {
        return moveType;
    }

    public static class Builder {
        private final int gameId;
        private final int moveId;
        public String moveType;
        private int x;
        private int y;
        private String color;

        public Builder(int gameId, int moveId) {
            this.gameId = gameId;
            this.moveId = moveId;
        }

        public Builder x(int x) {
            this.x = x;
            return this;
        }

        public Builder y(int y) {
            this.y = y;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder moveType(String moveType) {
            this.moveType = moveType;
            return this;
        }

        public Move build() {
            return new Move(this);
        }
    }
}