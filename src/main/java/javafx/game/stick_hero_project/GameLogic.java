package javafx.game.stick_hero_project;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

//import static com.sun.javafx.animation.TickCalculation.toMillis;

public class GameLogic {
    GameController gameController;
    Timeline timeline;

    public GameLogic(GameController gameController, Timeline t) {
        this.gameController = gameController;
        this.timeline = t;
    }

    public void keyboard_mapper(KeyEvent event) {
        System.out.println(event.getCode());
        if (event.getCode() == KeyCode.SPACE) {
            gameController.extend();
//            extend();
        }
    }

    public void keyboard_release(KeyEvent event) {
        System.out.println(event.getCode());
        if (event.getCode() == KeyCode.SPACE) {


            for (int i = 0; i < 91; i++) {
                KeyFrame keyFrame = new KeyFrame(Duration.millis(i * Duration.millis(10).toMillis()), sevent -> {
                    // Call your function here
                    gameController.stick_fall();
                });
                timeline.getKeyFrames().add(keyFrame);
            }

            timeline.setCycleCount(1); // Set to 1 for one-time execution
            timeline.play();
            gameController.adjust();
            Timeline run_timeline = new Timeline();
            boolean fate = gameController.player_fate();
            if (!fate) {
                //Player falls
                run_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(750), new KeyValue(gameController.Player.layoutXProperty(), gameController.stick_endpoint)));
                timeline.setOnFinished(event1 -> {
                    run_timeline.play();
                });
                Timeline fall_timeline = new Timeline();
                fall_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new KeyValue(gameController.Player.rotateProperty(), 300)));
                fall_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new KeyValue(gameController.Player.layoutYProperty(), 600)));
                run_timeline.setOnFinished(event1 -> {
                    fall_timeline.play();
                });
            }
            else{
                run_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), new KeyValue (gameController.Player.layoutXProperty(), gameController.pillar_end)));
                timeline.setOnFinished(event1->{run_timeline.play();});


            }


//            extend();
        }
    }

    public void pillar_setup() {
        gameController.initial_pillar_setup();

    }

}
