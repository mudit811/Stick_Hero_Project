package javafx.game.stick_hero_project ;
import javafx.game.stick_hero_project.Classes.Player;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public static GameLogic gameLogic;

    public ImageView Player;
    public AnchorPane anchorPane;
    //    private Scene root;
    public Rectangle second_pillar;
    public boolean canextend = true;
    public Label score;
    public Label cherries;
    public ImageView sun;
    @FXML
    public Rectangle first_pillar;
    @FXML
    public Rectangle stick;
    public Player player;
    public ImageView cherry;
    public Rectangle default_stick;
    double rotated = 0;
    boolean canfall = true;
    boolean movement = true;
    double stick_endpoint;
    double pillar_end;
    boolean canflip;
    boolean flipped = false;
    boolean ischerrypresent = false;
    private double pillar_height;
    private double next_pillar_width;
    private double next_pillar_xcoord;
    private static void writeIntegersToFile(String fileName, int num1, int num2,int num3) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            dos.writeInt(num1);
            dos.writeInt(num2);
            dos.writeInt(num3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Update_player(Player p){
        p.setCherries(Integer.parseInt(cherries.getText()));
        p.setScore(Integer.parseInt(score.getText()));
        if(p.getHigh_score()<Integer.parseInt(score.getText()))
        {
            p.setHigh_score(Integer.parseInt(score.getText()));
        }
        writeIntegersToFile("data.txt", p.getScore(), p.getHigh_score(),p.getCherries());

    }

    public double getNext_pillar_width() {
        return next_pillar_width;
    }

    public void setNext_pillar_width(double next_pillar_width) {
        this.next_pillar_width = next_pillar_width;
    }

    public double getNext_pillar_xcoord() {
        return next_pillar_xcoord;
    }

    public void setNext_pillar_xcoord(double next_pillar_xcoord) {
        this.next_pillar_xcoord = next_pillar_xcoord;
    }

    public void spawncherry() {
        Random random = new Random();
        cherry.setLayoutX(106);
        cherry.setLayoutY(139);
        cherry.setOpacity(0);
        int a = random.nextInt(1, 10);
        ischerrypresent = a >= 6;
    }


    public Rectangle getStick() {
        return stick;
    }


    public void extend() {
        if (canextend) {
            stick.setLayoutY(stick.getLayoutY() - 5);
            stick.setHeight(stick.getHeight() + 5);
        }
    }

    public void collect_cherry() {
        cherry.setLayoutX(106);
        cherry.setLayoutY(139);
        cherry.setOpacity(0);
        ischerrypresent = false;
        cherryUpdate();
    }

    public Rectangle initial_pillar_setup(Rectangle pillar) {
        //width +x< 335
        // y layout = 404 , height = 214
        //20<width<140
        Random random = new Random();
        double width = random.nextDouble(10, 140);
        double xcoord = random.nextDouble(70, 320 - width);
        pillar.setLayoutX(xcoord);
        pillar.setWidth(width);
        next_pillar_width = width;
        next_pillar_xcoord = xcoord;
        return pillar;

    }

    public void stick_fall() {
        canextend = false;

        if (canfall) {
            double angle = 1;
            double height = stick.getHeight() / 2;
            stick.setRotate(rotated);
            double x_decrease = height * (Math.sin((rotated + angle) / 180 * Math.PI) - Math.sin((rotated) / 180 * Math.PI));
            double y_decrease = height * (Math.cos((rotated) / 180 * Math.PI) - Math.cos((rotated + angle) / 180 * Math.PI));
            stick.setLayoutX(stick.getLayoutX() + x_decrease);
            stick.setLayoutY(stick.getLayoutY() + y_decrease);
            rotated += angle;
        }
    }

    public void adjust() {
        if (canfall) {
            stick.setLayoutY(stick.getLayoutY() - 1);
            rotated = 0;
        }
    }
    private static int[] readIntegersFromFile(String fileName) {
        int[] numbers = new int[2];

        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            numbers[0] = dis.readInt();
            numbers[1] = dis.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numbers;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int[] readNumbers = readIntegersFromFile("revive.txt");
        gameLogic = new GameLogic(this);
        Sun sun = new Sun(this.sun);
        score.setText(""+readNumbers[0]);
        cherries.setText(""+(readNumbers[1]-5));
        Thread sunThread = new Thread(sun);
        sunThread.start();
    }


    public void Player_move_forward() {
        Player.setLayoutX(Player.getLayoutX() + 1);
    }

    public void loop() {
        Player.setLayoutX(Player.getLayoutX() - 1);
        stick.setLayoutX(stick.getLayoutX() - 1);
        first_pillar.setLayoutX(first_pillar.getLayoutX() - 1);
        second_pillar.setLayoutX(second_pillar.getLayoutX() - 1);
    }

    public boolean player_fate() {
        double stick_length = stick.getHeight();
//        System.out.println(stick_length);
        stick_endpoint = 47 + stick_length;
//        System.out.println(stick_endpoint + " " + next_pillar_xcoord + " " + next_pillar_width);
        if (stick_endpoint < next_pillar_xcoord || stick_endpoint > next_pillar_xcoord + next_pillar_width) {

//            System.out.println("not safe");
            return false;
//
        } else {
//            System.out.println("safe");
            pillar_end = next_pillar_width + next_pillar_xcoord - Player.getFitWidth() - 5;
            return true;
//
        }


    }

    public void scoreUpdate() {
        score.setText(String.valueOf(Integer.parseInt(score.getText()) + 1));
    }

    public void cherryUpdate() {
        cherries.setText(String.valueOf(Integer.parseInt(cherries.getText()) + 1));
    }

    public void game_over(AnchorPane pane) throws IOException {
        Update_player(player);

        Stage stage;
        Parent root;
        Scene scene;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameOver.fxml")));
        stage = (Stage) pane.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

}
