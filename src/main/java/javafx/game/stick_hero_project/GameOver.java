package javafx.game.stick_hero_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.game.stick_hero_project.Classes.Player;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameOver implements Initializable {
    public Button homeButton;
    public Button aboutButton;
    public Button reviveButton;
    public Button soundButton;
    public  Label scoreLabel;
    public  Label highscoreLabel;

    public int cherries;

    public void loadHome(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        Scene scene;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
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
    private static int[] readIntegersFromFile(String fileName) {
        int[] numbers = new int[3];

        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            numbers[0] = dis.readInt();
            numbers[1] = dis.readInt();
            numbers[2] =dis.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numbers;
    }
    private static void writeIntegersToFile(String fileName, int num1, int num2) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            dos.writeInt(num1);
            dos.writeInt(num2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void update_scores(Player p){
        scoreLabel.setText(p.getScore()+"");
        highscoreLabel.setText(p.getHigh_score()+"");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int[] readNumbers = readIntegersFromFile("data.txt");
        scoreLabel.setText(""+readNumbers[0]);
        highscoreLabel.setText(""+readNumbers[1]);
        cherries=readNumbers[2];

    }

    public void revive(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        Scene scene;
        writeIntegersToFile("revive.txt",Integer.parseInt(scoreLabel.getText()),cherries);
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        GameController.gameLogic.pillar_setup();
        GameController.gameLogic.start_player();
        scene.setOnKeyPressed(GameController.gameLogic::keyboard_mapper);
        scene.setOnKeyReleased(GameController.gameLogic::keyboard_release);
        stage.setScene(scene);
        stage.show();
    }
}
