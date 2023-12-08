package javafx.game.stick_hero_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 325, 600);

//        scene.setOnMousePressed(event->{
//            if(event.getButton()== MouseButton.PRIMARY){
//                gameController.on_mouse_hold();
//            }
//        });
        stage.setTitle("Stick Hero");
        stage.setScene(scene);
        scene.setOnKeyPressed(GameController.gameLogic::keyboard_mapper);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}