package javafx.game.stick_hero_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.game.stick_hero_project.Classes.Player;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 335, 600);
        stage.setTitle("Stick Hero");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {
            Player player = Player.getInstance(0,0,0);
            writePlayerDataToFile(player);
        });
    }


    public static void main(String[] args) {
        launch();
    }
    public static void writePlayerDataToFile(Player player) {
        try (PrintWriter writer = new PrintWriter(new File("player.txt"))) {
            writer.println(player.getScore());
            writer.println(player.getHigh_score());
            writer.println(player.getCherries());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void readPlayerDataFromFile() {

    }
}