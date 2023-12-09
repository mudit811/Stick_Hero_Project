package javafx.game.stick_hero_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 325, 600);
        stage.setTitle("Stick Hero");
        stage.setScene(scene);
//        scene.setOnKeyPressed(GameController.gameLogic::keyboard_mapper);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}