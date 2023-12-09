package javafx.game.stick_hero_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Home {

    @FXML
    private Rectangle stick;


    public void loadgame(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        Scene scene;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        GameController.gameLogic.pillar_setup();

        scene.setOnKeyPressed(GameController.gameLogic::keyboard_mapper);
        scene.setOnKeyReleased(GameController.gameLogic::keyboard_release);
        stage.show();
    }

}