package Classes;

public class Player {
    StickHero current_hero;
    int points;
    String name;
    public void player(){}

    public StickHero getCurrent_hero() {
        return current_hero;
    }

    public void setCurrent_hero(StickHero current_hero) {
        this.current_hero = current_hero;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
