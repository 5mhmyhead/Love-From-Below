package core;

import utilities.Images;

import javax.swing.*;
import java.awt.*;

public class Game {

    public static JFrame window;

    public static void main(String[] args) { new Game().startGame(); }

    public void startGame() {

        window = new JFrame("LOVE FROM BELOW");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setIconImage(Images.Misc.ICON);
        window.setResizable(false);

        window.add(new GamePanel());
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
