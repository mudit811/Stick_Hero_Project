package javafx.game.stick_hero_project;

import Classes.Player;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

//import static com.sun.javafx.animation.TickCalculation.toMillis;

public class GameLogic {
    GameController gameController;
    Timeline timeline;

    public GameLogic(GameController gameController) {

        this.gameController = gameController;

    }

    public void keyboard_mapper(KeyEvent event) {
//        System.out.println(event.getCode());

        if (event.getCode() == KeyCode.SPACE) {
            gameController.extend();
//            extend();
        }
    }

    public void keyboard_release(KeyEvent event) {
//        System.out.println(event.getCode());
        if (event.getCode() == KeyCode.SPACE) {
            gameController.canextend = false;
            if (gameController.movement) {
                gameController.movement = false;
                Timeline timeline= new Timeline();
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
//                timeline.setOnFinished(event2 -> {
//                    gameController.will_not_fall();
//                });


                Timeline run_timeline = new Timeline();

                boolean fate = gameController.player_fate();
                if (!fate) {
                    //Player falls
                    gameController.canflip=true;
                    int walk_distance=(int)gameController.stick_endpoint-(int)gameController.Player.getLayoutX();
                    for (int i = 0; i <walk_distance; i++) {
                        KeyFrame keyFrame = new KeyFrame(Duration.millis(i * Duration.millis(5).toMillis()), sevent -> {
                            // Call your function here
                            gameController.Player_move_forward();
                            if(gameController.Player.getLayoutX()>gameController.getNext_pillar_xcoord() || gameController.Player.getLayoutX()<50){
                                gameController.canflip=false;
                            }
                            else{
                                gameController.canflip=true;
                            }
                            if(gameController.flipped && gameController.Player.getLayoutX()+15>=gameController.getNext_pillar_xcoord()){
                                System.out.println("Game Over");
                                run_timeline.stop();
                                Timeline fall_timeline = new Timeline();
                                fall_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), new KeyValue(gameController.Player.rotateProperty(), 300)));
                                fall_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), new KeyValue(gameController.Player.layoutYProperty(), 600)));
                                fall_timeline.play();
                                fall_timeline.setOnFinished(event1 -> {
                                    try {
                                        gameController.game_over(gameController.anchorPane);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                });


                            }
                            if(gameController.ischerrypresent){
                                if(gameController.flipped && gameController.Player.getLayoutX()>=gameController.cherry.getLayoutX() && gameController.Player.getLayoutX()<= gameController.cherry.getLayoutX()+gameController.cherry.getFitWidth()){
                                    System.out.println("cherry collected");
                                    gameController.collect_cherry();
                                }
                            }

                        });
                        run_timeline.getKeyFrames().add(keyFrame);

                    }

//                    run_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1500), new KeyValue(gameController.Player.layoutXProperty(), gameController.stick_endpoint)));
                    timeline.setOnFinished(event1 -> {
                        run_timeline.play();
//                        gameController.Player.setLayoutX(gameController.stick_endpoint);
                    });
                    Timeline fall_timeline = new Timeline();
                    fall_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), new KeyValue(gameController.Player.rotateProperty(), 300)));
                    fall_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), new KeyValue(gameController.Player.layoutYProperty(), 600)));
                    run_timeline.setOnFinished(event1 -> {
                        fall_timeline.play();
                    });
                    fall_timeline.setOnFinished(event1 -> {
                        try {
                            gameController.game_over(gameController.anchorPane);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } else {
                    gameController.canflip=true;
                    //player survives
//                    run_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1500), new KeyValue(gameController.Player.layoutXProperty(), gameController.pillar_end)));
                    int walk_distance=(int)gameController.pillar_end-(int)gameController.Player.getLayoutX();
                    boolean b=false;

                    for (int i = 0; i <walk_distance; i++) {
                        KeyFrame keyFrame = new KeyFrame(Duration.millis(i * Duration.millis(5).toMillis()), sevent -> {
                            // Call your function here
                            gameController.Player_move_forward();
                            if(gameController.Player.getLayoutX()>gameController.getNext_pillar_xcoord() || gameController.Player.getLayoutX()<50){
                                gameController.canflip=false;
                            }
                            else{
                                gameController.canflip=true;
                            }
                            if(gameController.flipped && gameController.Player.getLayoutX()+15>=gameController.getNext_pillar_xcoord()){
                                System.out.println("Game Over");
                                run_timeline.stop();
                                Timeline fall_timeline = new Timeline();
                                fall_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), new KeyValue(gameController.Player.rotateProperty(), 300)));
                                fall_timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), new KeyValue(gameController.Player.layoutYProperty(), 600)));
                                fall_timeline.play();
                                fall_timeline.setOnFinished(event1 -> {
                                    try {
                                        gameController.game_over(gameController.anchorPane);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                });
                            }
                            if(gameController.ischerrypresent){
                                if(gameController.flipped && gameController.Player.getLayoutX()>=gameController.cherry.getLayoutX() && gameController.Player.getLayoutX()<= gameController.cherry.getLayoutX()+gameController.cherry.getFitWidth()){
                                    System.out.println("cherry collected");
                                    gameController.collect_cherry();
                                }
                            }


                        });

                        if(b){
                            break;
                        }
                        run_timeline.getKeyFrames().add(keyFrame);

                    }
                    timeline.setOnFinished(event1 -> {
                        run_timeline.play();
//                        gameController.Player.setLayoutX(gameController.pillar_end);
                    });
                    Timeline loop_timeline_1 = new Timeline();
                    int loopnumber = (int) gameController.pillar_end - 25;
                    for (int i = 0; i <50; i++) {
                        KeyFrame keyFrame = new KeyFrame(Duration.millis(i * Duration.millis(10).toMillis()), sevent -> {
                            // Call your function here
                            gameController.loop();

                        });
                        loop_timeline_1.getKeyFrames().add(keyFrame);

                    }

                    loop_timeline_1.setCycleCount(1); // Set to 1 for one-time execution
                    run_timeline.setOnFinished(event1 -> {
                        gameController.scoreUpdate();
                        loop_timeline_1.play();
                    });



                    Timeline loop_timeline_2 = new Timeline();
                    for (int i = 0; i <loopnumber- 50; i++) {
                        KeyFrame keyFrame = new KeyFrame(Duration.millis(i * Duration.millis(10).toMillis()), sevent -> {
                            // Call your function here
                            gameController.loop();

                        });
                        loop_timeline_2.getKeyFrames().add(keyFrame);

                    }

                    loop_timeline_2.setCycleCount(1); // Set to 1 for one-time execution
                    loop_timeline_1.setOnFinished(event1 -> {
                        Rectangle temp=new Rectangle();
//                        System.out.println(gameController.first_pillar.getLayoutX());
//                        System.out.println(gameController.second_pillar.getLayoutX());
                        temp=gameController.first_pillar;
                        gameController.first_pillar=gameController.second_pillar;
                        gameController.second_pillar=temp;
                        gameController.cherry.setLayoutX(106);
                        gameController.cherry.setLayoutY(139);
                        gameController.cherry.setOpacity(0);
//                        System.out.println(gameController.first_pillar.getLayoutX());

                        gameController.second_pillar=gameController.initial_pillar_setup(gameController.second_pillar);
                        gameController.second_pillar.setLayoutX(gameController.second_pillar.getLayoutX()+loopnumber-25);
//                        System.out.println(gameController.second_pillar.getLayoutX());
//                        gameController.anchorPane.getChildren().add(gameController.second_pillar);
                        loop_timeline_2.play();
                    });
//
                    loop_timeline_2.setOnFinished(event1 -> {
                        gameController.stick.setHeight(1);
                        gameController.stick.setLayoutY(405);
                        gameController.stick.setLayoutX(46);
                        gameController.stick.setRotate(0);
                        gameController.spawncherry();
                        if(gameController.ischerrypresent){
                            Random random=new Random();
                                double cherry_coord = random.nextDouble(70, gameController.second_pillar.getLayoutX()-gameController.cherry.getFitWidth());
                                gameController.cherry.setLayoutX(cherry_coord);
                                gameController.cherry.setLayoutY(406);
                                gameController.cherry.setOpacity(1);
                                System.out.println("cherry present");
                            }
                            System.out.println(gameController.cherry.getLayoutX());
                        gameController.movement = true;
                        gameController.canextend = true;
                        gameController.canfall = true;
                        gameController.setNext_pillar_xcoord(gameController.second_pillar.getLayoutX());

                    });


                }
            }
        }
        if (event.getCode()==KeyCode.F){
            if (gameController.canflip){
                gameController.Player.setScaleY(-1);
                if(!gameController.flipped){
                    gameController.Player.setLayoutY(gameController.Player.getLayoutY()+25);
                }
                else{
                    gameController.Player.setLayoutY(gameController.Player.getLayoutY()-25);

                }
                gameController.flipped=!gameController.flipped;



            }

        }
    }

    public void pillar_setup() {
        gameController.second_pillar= gameController.initial_pillar_setup(gameController.second_pillar);

    }

}
