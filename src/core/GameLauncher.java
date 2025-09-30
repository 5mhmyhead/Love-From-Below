package core;

import javax.swing.*;

public class GameLauncher {

    public static void main(String[] args) {

        new GameLauncher().startGame();
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
