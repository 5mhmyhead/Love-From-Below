package core;

import state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    // GLOBAL SCREEN SETTINGS
    public final static int MAX_SCREEN_COL = 16;                        // 16 TILES FROM LEFT TO RIGHT
    public final static int MAX_SCREEN_ROW = 12;                        // 12 TILES FROM TOP TO BOTTOM
    public final static int TILE_SIZE = 48;                             // 48 x 48 PER TILE

    public final static int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;  // 768 PIXELS
    public final static int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576 PIXELS

    // THREAD AND FPS SETTINGS
    public static final double FPS = 60.0;

    private Thread gameThread;
    private boolean running;

    // CLASS THAT HANDLES THE CHANGING OF STATES IN THE GAME
    private StateManager stateManager;

    public GamePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        init();
    }

    public void addNotify() {

        super.addNotify();

        if(gameThread == null) {

            gameThread = new Thread(this);
            this.addKeyListener(this);
            gameThread.start();
        }
    }

    public void init() {

        stateManager = new StateManager();
        running = true;
    }

    @Override
    public void run() {

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
        stateManager.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        stateManager.draw(g2);
        g2.dispose();
    }

    @Override
    public void keyPressed(KeyEvent e) { if(e != null) stateManager.keyPressed(e.getKeyCode()); }

    @Override
    public void keyReleased(KeyEvent e) { if(e != null) stateManager.keyReleased(e.getKeyCode()); }

    @Override
    public void keyTyped(KeyEvent e) {}
}
