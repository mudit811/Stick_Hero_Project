package Classes;

import java.io.*;
import java.util.ArrayList;

public class Player implements Serializable {
    private static Player instance = null;
    public static Player getInstance(){
        if(instance == null){
            instance = new Player();
        }
        return instance;
    }
    private int cherries;
    private int high_score;
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
