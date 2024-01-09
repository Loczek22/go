package GUI;

import javafx.stage.Stage;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.isVisible;

public class FirstFrameTest extends ApplicationTest{

    @Override
    public void start(Stage stage) {
        new FirstFrame().start(stage);
    }

    @Test
    public void testNewGameButton() {
        clickOn("#newGameButton");
        verifyThat("#playerOptionsRoot", isVisible());
    }

    @Test
    public void testLoadGameButton() {
        clickOn("#loadGameButton");
        assertTrue(true);
        // TODO: test obsługi przycisku "LOAD GAME" (bazy danych)
    }
}
