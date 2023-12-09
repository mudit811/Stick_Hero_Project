package javafx.game.stick_hero_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.game.stick_hero_project.Classes.Player;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Player player= Player.deserialize();
    @Override

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 335, 600);
        stage.setTitle("Stick Hero");
        stage.setScene(scene);
        stage.setOnCloseRequest(e->{
            player.serialize();
            stage.close();
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}