package Classes;

public class Stick  implements Gameobject, Appl {
    int length;


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    public void extendstick(event e ){
        System.out.println();


    }
    public void rotatestick(){}
    public void checkimpact(Tower t){}
    private boolean checkmiddle(){return true;}
}
