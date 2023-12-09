package javafx.game.stick_hero_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.Random;

//import static com.sun.javafx.animation.TickCalculation.toMillis;

public class GameLogic {
    GameController gameController;
    public GameLogic(GameController gameController) {
        this.gameController=gameController;
    }

    public  void keyboard_mapper(KeyEvent event){
        System.out.println(event.getCode());
        if(event.getCode()== KeyCode.SPACE){
            gameController.extend();
//            extend();
        }
    }
    public  void  keyboard_release(KeyEvent event)  {
        System.out.println(event.getCode());
        if(event.getCode()== KeyCode.SPACE){
            Timeline timeline = new Timeline();

            for (int i = 0; i < 91; i++) {
                KeyFrame keyFrame = new KeyFrame(Duration.millis(i * Duration.millis(10).toMillis()), sevent -> {
                    // Call your function here
                    gameController.stick_fall();});
                timeline.getKeyFrames().add(keyFrame);
            }

            timeline.setCycleCount(1); // Set to 1 for one-time execution
            timeline.play();
            gameController.adjust();
            gameController.player_fate();


//            extend();
        }
    }

    public void pillar_setup(){
       gameController.initial_pillar_setup();

    }

}
