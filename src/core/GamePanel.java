package core;

import controller.KeyHandler;
import entities.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    private final int originalTileSize = 16; // 16 x 16 per tile
    private final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48 x 48 per tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // THREAD SETTINGS
    private Thread gameThread;
    private boolean running;

    // HANDLERS AND MANAGERS
    private final KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);

    // FPS SETTINGS
    public static final double FPS = 60.0;

    // DEFAULT CONSTRUCTOR
    public GamePanel() {

        super();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    public void addNotify() {

        super.addNotify();

        if(gameThread == null) {

            gameThread = new Thread(this);
            this.addKeyListener(keyHandler);
            gameThread.start();
        }
    }

    public void init() {

        running = true;
    }

    @Override
    public void run() {

        init();

        double drawInterval = (double) 1_000_000_000 / FPS;
        double delta = 0;

        long lastUpdate = System.nanoTime();
        long currentTime;

        while(running) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastUpdate) / drawInterval;
            lastUpdate = currentTime;

            if(delta >= 1) {

                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);

        g2.dispose();
    }
}
