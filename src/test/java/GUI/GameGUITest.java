package GUI;

import Server.Client;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameGUITest extends ApplicationTest {

    Client client = new Client();
    @Override
    public void start(Stage stage) {
        new FirstFrame().start(stage);
    }

    @Test
    public void testInitGame9() {
        Platform.runLater(() -> {
            //Client client = new Client();
            GameGUI gameGUI = new GameGUI(9, client);
            gameGUI.initGame();
            assertNotNull(gameGUI.boardGUI);
        });
    }

    @Test
    public void testInitGame13() {
        Platform.runLater(() -> {
            //Client client = new Client();
            GameGUI gameGUI = new GameGUI(13, client);
            gameGUI.initGame();
            assertNotNull(gameGUI.boardGUI);
        });
    }

    @Test
    public void testInitGame19() {
        Platform.runLater(() -> {
            //Client client = new Client();
            GameGUI gameGUI = new GameGUI(19,client);
            gameGUI.initGame();
            assertNotNull(gameGUI.boardGUI);
        });
    }

    @Test
    public void testConfirmButton() {
        Platform.runLater(() -> {
            GameGUI gameGUI = new GameGUI(9, client);
            gameGUI.initGame();
            clickOn("#confirmButton");
            // TODO: zmiana gracza na 2 po dodaniu logiki zmiany gracza
            assertEquals(1, BoardGUI.getCurrentPlayer());
        });
    }

    @Test
    public void testDoNotMoveButton() {
        Platform.runLater(() -> {
            GameGUI gameGUI = new GameGUI(9, client);
            gameGUI.initGame();
            clickOn("#doNotMoveButton");
            // TODO: test obsługi przycisku "DON'T MOVE"
        });
    }
}