package Classes;

import java.util.List;

public class Game {
    Player currplayer;
    List<Gameobject> gameobjects;
    public void playgame(){}
    public void exit(){}

    public Player getCurrplayer() {
        return currplayer;
    }

    public void setCurrplayer(Player currplayer) {
        this.currplayer = currplayer;
    }

    public List<Gameobject> getGameobjects() {
        return gameobjects;
    }

    public void setGameobjects(List<Gameobject> gameobjects) {
        this.gameobjects = gameobjects;
    }

    public void save(){}

    public void resume(){}

}
