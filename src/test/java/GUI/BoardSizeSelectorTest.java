package GUI;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardSizeSelectorTest {

    @Test
    public void testShowGameBoard() {

        Platform.startup(() -> {
            Stage primaryStage = new Stage();
            BoardSizeSelector boardSizeSelector = new BoardSizeSelector(primaryStage);
            boardSizeSelector.initSizeSelectionScreen();

            boardSizeSelector.showGameBoard(9);
            assertEquals(9, BoardSizeSelector.getSelectedBoardSize());

            boardSizeSelector.showGameBoard(13);
            assertEquals(13, BoardSizeSelector.getSelectedBoardSize());

            boardSizeSelector.showGameBoard(19);
            assertEquals(19, BoardSizeSelector.getSelectedBoardSize());
        });
        Platform.exit();
    }
}