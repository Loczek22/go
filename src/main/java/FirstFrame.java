import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class FirstFrame extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GO GAME");

        VBox root = new VBox(10);
        Button newGameButton = new Button("NEW GAME");
        Button loadGameButton = new Button("LOAD GAME");

        newGameButton.setOnAction(e -> {
            PlayerOptions playerOptions = new PlayerOptions(primaryStage);
            playerOptions.showPlayerOptions();
        });

        // TODO: obsługa przycisku "LOAD GAME" (bazy danych)

        root.getChildren().addAll(newGameButton, loadGameButton);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}