package javafx.game.stick_hero_project;

import Classes.Stick;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public  class GameController implements Initializable {
//    private Scene root;


    @FXML private Rectangle Starting_pillar ;
    @FXML private Rectangle stick;



    private AnchorPane anchorPane;
    public static GameLogic gameLogic;

    private double pillar_height;
    private boolean canextend=true;
    public  void extend(){
        if(canextend){
            stick.setLayoutY(stick.getLayoutY()-5);
            stick.setHeight(stick.getHeight()+5);
        }
    }

    public void stop(){
        canextend=false;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            gameLogic=new GameLogic();
    }
}
