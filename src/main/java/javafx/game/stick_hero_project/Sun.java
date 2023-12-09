package javafx.game.stick_hero_project;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Sun implements Runnable{
    private ImageView sunImage;

    @Override
    public void run() {
        Timeline timelineSun= new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(sunImage.rotateProperty(),0)),
                new KeyFrame(Duration.millis(12000),new KeyValue(sunImage.rotateProperty(),360))
        );
        timelineSun.setCycleCount(Timeline.INDEFINITE);
        timelineSun.play();

    }

    public Sun(ImageView sunImage){
        this.sunImage = sunImage;
    }
}
