package Database;

public class LoadMoveHandle {
    DbLoadGame dbLoadGame;

    public LoadMoveHandle(DbLoadGame dbLoadGame) {
        this.dbLoadGame = dbLoadGame;
    }

    public Move getMove(int gameId, int moveId) {
        return dbLoadGame.getMove(gameId, moveId);
    }
}
