package Database;

public class AddMoveHandle implements BdHandler {
    private final DbSaveGame dbSaveGame;

    public AddMoveHandle(DbSaveGame dbSaveGame) {
        this.dbSaveGame = dbSaveGame;
    }
    @Override
    public void handle(Move move) {
        dbSaveGame.addMove(move);
    }
}
