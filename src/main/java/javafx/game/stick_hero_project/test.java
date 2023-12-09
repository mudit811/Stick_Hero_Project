package javafx.game.stick_hero_project;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) {

    }
    private static void writeIntegersToFile(String fileName, int num1, int num2) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            dos.writeInt(num1);
            dos.writeInt(num2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
