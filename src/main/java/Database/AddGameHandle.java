package Database;

public class AddGameHandle implements BdGameHandler {
    private final DbAddGame dbAddGame;

    public AddGameHandle(DbAddGame dbAddGame) { this.dbAddGame = dbAddGame; }
    @Override
    public void handle() {
        dbAddGame.addGame();
    }
}