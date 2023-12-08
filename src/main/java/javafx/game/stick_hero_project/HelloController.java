package javafx.game.stick_hero_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Rectangle stick;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");


    }

}