package Classes;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Stick  implements Gameobject {
    private int length;
    private Pane root;

    public Stick(Pane root) {
        this.root = root;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    public void extendstick( ){
        Rectangle stick = new Rectangle();
        System.out.println();


    }
    public void rotatestick(){}
    public void checkimpact(Tower t){}
    private boolean checkmiddle(){return true;}
}
