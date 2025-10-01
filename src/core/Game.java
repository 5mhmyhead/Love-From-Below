package core;

import javax.swing.*;

public class Game {

    public static void main(String[] args) {
        new Game().startGame();
    }

    public void startGame() {

        JFrame window = new JFrame("LOVE FROM BELOW");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.add(new GamePanel());
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
