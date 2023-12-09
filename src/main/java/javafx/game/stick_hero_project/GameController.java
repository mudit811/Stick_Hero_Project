package javafx.game.stick_hero_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public static GameLogic gameLogic;
    public ImageView Player;
//    private Scene root;
    public Rectangle second_pillar;
    double rotated = 0;
    @FXML
    private Rectangle Starting_pillar;
    @FXML
    private Rectangle stick;
    private double pillar_height;
    private boolean canextend = true;
    boolean canfall=true;
    private double next_pillar_width;
    private double next_pillar_xcoord;

    public Rectangle getStick() {
        return stick;
    }

    public void extend() {
        if (canextend) {
            stick.setLayoutY(stick.getLayoutY() - 5);
            stick.setHeight(stick.getHeight() + 5);
        }
    }

    public void initial_pillar_setup() {
        //width +x< 335
        // y layout = 404 , height = 214
        //20<width<140
        Random random = new Random();
        double width = random.nextDouble(10, 140);
        double xcoord = random.nextDouble(70, 320 - width);
        second_pillar.setLayoutX(xcoord);
        second_pillar.setWidth(width);
        next_pillar_width=width;
        next_pillar_xcoord=xcoord;

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
        if (canfall){
            stick.setLayoutY(stick.getLayoutY() - 1);

            rotated = 0;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameLogic = new GameLogic(this);
    }
    public void fall(double endpoint){


    }
    public void Player_move_forward(){
        Player.setLayoutX(Player.getLayoutX()+1);
    }
    public void safe_run(){}
    public void player_fate(){
        double stick_length= stick.getHeight();
        System.out.println(stick_length);
        double stick_endpoint= 47+stick_length;
        System.out.println(stick_endpoint+ " "+next_pillar_xcoord+" "+next_pillar_width);
        if (stick_endpoint<next_pillar_xcoord || stick_endpoint > next_pillar_xcoord+next_pillar_width){

            System.out.println("not safe");
//            TranslateTransition transition= new TranslateTransition();
//            transition.setNode(Player);
//            transition.setDuration(Duration.millis(1000));
//            transition.setToX(stick_endpoint);

            Player.setLayoutX(stick_endpoint);
//            for (int i = 0; i < (int)(stick_endpoint); i++) {
//                Timeline timeline=new Timeline();
//                KeyFrame keyFrame = new KeyFrame(Duration.millis(i * Duration.millis(10).toMillis()), sevent -> {
//                    // Call your function here
//                    Player_move_forward();});
//                timeline.getKeyFrames().add(keyFrame);
//            }
        }
        else {
            System.out.println("safe");
//            TranslateTransition transition= new TranslateTransition();
//            transition.setNode(Player);
//            transition.setDuration(Duration.millis(1000));
//            transition.setToX(next_pillar_width+next_pillar_xcoord-Player.getFitWidth());
            Player.setLayoutX(next_pillar_width+next_pillar_xcoord-Player.getFitWidth());
//            safe_run();
        }

    }
}
