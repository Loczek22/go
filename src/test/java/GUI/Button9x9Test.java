package GUI;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.isVisible;

public class Button9x9Test extends ApplicationTest {
    @Override
    public void start(Stage stage) {
        new FirstFrame().start(stage);
    }

    @Test
    public void test9x9Button() {
        clickOn("#newGameButton");
        clickOn("#twoPlayersButton");
        clickOn("#size9Button");
        verifyThat("#gameBoardRoot", isVisible());
    }
}