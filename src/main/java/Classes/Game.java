package Classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Game {
    Player currplayer;
    List<Gameobject> gameobjects;
    public void playgame(){}
    public void exit(){}

    public Player getCurrplayer() {
        return currplayer;
    }

    public void setCurrplayer(Player currplayer) {
        this.currplayer = currplayer;
    }

    public List<Gameobject> getGameobjects() {
        return gameobjects;
    }

    public void setGameobjects(List<Gameobject> gameobjects) {
        this.gameobjects = gameobjects;
    }

    public void save(){}

    public void resume(){}

    public static class Main extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) throws IOException {
            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../resources/Home.fxml")));
            stage.setTitle("Stick Hero");
            stage.setScene(new Scene(root,335,600));
            stage.show();
        }
    }
}
