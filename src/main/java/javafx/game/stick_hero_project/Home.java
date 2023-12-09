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

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class Home {

    @FXML
    private Rectangle stick;


    public void loadgame(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        Scene scene;
        writeIntegersToFile("revive.txt",0,15);

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        GameController.gameLogic.pillar_setup();
        GameController.gameLogic.start_player();
        GameController.gameLogic.set_cherries();
        scene.setOnKeyPressed(GameController.gameLogic::keyboard_mapper);
        scene.setOnKeyReleased(GameController.gameLogic::keyboard_release);
        stage.show();
    }
    private static void writeIntegersToFile(String fileName, int num1, int num2) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            dos.writeInt(num1);
            dos.writeInt(num2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadShop(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        Scene scene;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Shop.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void loadAbout(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        Scene scene;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("About.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void loadColors(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        Scene scene;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Colors.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}