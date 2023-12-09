package javafx.game.stick_hero_project.Classes;

public class Cherries implements Collectible {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public void Points(Collectible a) {

    }

    @Override
    public void store(Collectible a) {

    }

    public boolean canRevive(){
        return true ;
    }
}
