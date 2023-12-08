package Classes;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public  class Stick  implements Gameobject {

    private double x_stick=46;
    private Rectangle stick;
    private double y_stick=405;
    private double stick_height=0;
    private Paint color= Paint.valueOf("#00020d");
    public void create_stick(Scene root){
        stick= new Rectangle(x_stick,y_stick);
        stick.setHeight(stick_height);
        stick.setId("stick");
        stick.setFill(color );
        ((StackPane) stick.getParent()).getChildren().add(stick);

    }


    public void extendstick(){
        if (stick!=null){
            stick.setLayoutY(stick.getLayoutY()-1);
            stick.setHeight(stick.getHeight()+1);
        }

    }
    public void rotatestick(){}
    public void checkimpact(Tower t){}
    private boolean checkmiddle(){return true;}
}
