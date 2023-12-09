package javafx.game.stick_hero_project.Classes;

import java.io.*;
import java.util.ArrayList;

public class Player implements Serializable {
    private static Player instance = null;

    public Player(int cherries, int high_score,int score) {
        this.cherries = cherries;
        this.high_score = high_score;
        this.score=score;
    }

    public static Player getInstance(int cherries,int high_score ,int score){
        if(instance == null){
            instance = new Player(cherries,high_score,score);
        }
        return instance;
    }
    private int cherries=0;
    private int high_score=0;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private ArrayList<String> colors=new ArrayList<>();
    public int getCherries() {
        return cherries;
    }

    public void setCherries(int cherries) {
        this.cherries = cherries;
    }

    public int getHigh_score() {
        return high_score;
    }

    public void setHigh_score(int high_score) {
        this.high_score = high_score;
    }
    public ArrayList<String> getColors() {
        return colors;
    }
    public void addColor(String color){
        colors.add(color);
    }

    public void serialize(){
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("player.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public void deserialize(){
        File file = new File("player.ser");
        if(file.exists() && !file.isDirectory()) {
            try {
                FileInputStream fileIn = new FileInputStream("player.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                instance = (Player) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("Player class not found");
                c.printStackTrace();
            }
        } else {
            System.out.println("File does not exist");
        }
    }
}
