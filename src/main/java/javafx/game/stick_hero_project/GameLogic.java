package javafx.game.stick_hero_project;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameLogic {
    public  void keyboard_mapper(KeyEvent event){
        System.out.println("hello");
        if(event.getCode()== KeyCode.SPACE){
            System.out.println("TRY");
//            extend();
        }
    }
}
