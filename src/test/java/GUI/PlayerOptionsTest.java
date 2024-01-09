package GUI;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.Assert.assertTrue;
import static org.testfx.util.NodeQueryUtils.isVisible;
import static org.testfx.api.FxAssert.verifyThat;

public class PlayerOptionsTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        new FirstFrame().start(stage);
    }

    @Test
    public void testTwoPlayersButton() {
        clickOn("#newGameButton");
        clickOn("#twoPlayersButton");
        verifyThat("#sizeSelectionRoot", isVisible());
    }

    @Test
    public void testBotButton() {
        clickOn("#newGameButton");
        assertTrue(true);
        // TODO: test obsługi przycisku "GAME WITH BOT"
        // clickOn("#botButton");
        // sverifyThat("#sizeSelectionRoot", isVisible());
    }
}
