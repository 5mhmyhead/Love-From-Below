package state.states;

import core.GamePanel;
import state.State;
import state.StateManager;
import utilities.Animation;
import utilities.Images;
import utilities.SoundManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TitleState extends State {

    private boolean pressedEnter;

    private int cursorIndex;
    private int timer;  // AMOUNT OF TIME BEFORE SCREEN GOES BLACK
    private int fade;   // ALPHA LEVEL

    private Animation titleScreenAnimation;
    private Animation cursor;

    private Animation startEntered;
    private Animation loadEntered;
    private Animation quitEntered;

    public TitleState(StateManager stateManager) {

        this.stateManager = stateManager;
        init();
    }

    @Override
    public void init() {

        cursorIndex = 0;
        timer = 0;
        fade = 0;

        // CREATE ANIMATION FOR TITLE SCREEN
        assert Images.TitleScreenAssets.TITLE_SCREEN != null;
        titleScreenAnimation = new Animation(10, true, Images.TitleScreenAssets.TITLE_SCREEN,
                GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT);

        // CREATE ANIMATION FOR THE MENU
        assert Images.TitleScreenAssets.START_ENTERED != null;
        startEntered = new Animation(5, true, Images.TitleScreenAssets.START_ENTERED,
            GamePanel.TILE_SIZE * 3, GamePanel.TILE_SIZE);

        assert Images.TitleScreenAssets.LOAD_ENTERED != null;
        loadEntered = new Animation(5, true, Images.TitleScreenAssets.LOAD_ENTERED,
                GamePanel.TILE_SIZE * 3, GamePanel.TILE_SIZE);

        assert Images.TitleScreenAssets.QUIT_ENTERED != null;
        quitEntered = new Animation(5, true, Images.TitleScreenAssets.QUIT_ENTERED,
                GamePanel.TILE_SIZE * 3, GamePanel.TILE_SIZE);

        assert Images.TitleScreenAssets.TITLE_CURSOR != null;
        cursor = new Animation(5, true, Images.TitleScreenAssets.TITLE_CURSOR,
                GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);

        if(!SoundManager.INTRO.isPlaying()) SoundManager.INTRO.play();
        SoundManager.INTRO.setVolume(-10);
    }

    @Override
    public void update() {
        // IF THE PLAYER PRESSED ENTER, THEN FADE INTO NEXT STATE
        if(pressedEnter) {

            timer++;

            if(fade < 255) fade += 4;
            // IF THE PLAYER PRESSED ENTER AFTER 80 FRAMES, SWITCH STATE
            else if(timer >= 80) {

                switch(cursorIndex) {

                    case 0: stateManager.setState(StateManager.GAME_STATE); break;
                    case 1: stateManager.setState(StateManager.LOAD_STATE); break;
                    case 2: System.exit(0); break;
                }
            }
            // CONSTRAIN FADE
            if(fade < 0) fade = 0;
            if(fade > 255) fade = 255;

            startEntered.update();
            loadEntered.update();
            quitEntered.update();
        }

        titleScreenAnimation.update();
        cursor.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        // DRAW THE TITLE SCREEN
        titleScreenAnimation.draw(g2, 0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT);

        int centeredX = 312;
        int startY = 415;
        int loadY = 455;
        int quitY = 495;

        // DRAW THE MENU (START, LOAD, QUIT)
        switch(cursorIndex) {

            case 0:
                // INDEX 0, START IS PICKED
                if(pressedEnter) startEntered.draw(g2, centeredX, startY, GamePanel.TILE_SIZE * 3, GamePanel.TILE_SIZE);
                else g2.drawImage(Images.TitleScreenAssets.START_PICKED, centeredX, startY, null);

                g2.drawImage(Images.TitleScreenAssets.LOAD_UNPICKED, centeredX, loadY, null);
                g2.drawImage(Images.TitleScreenAssets.QUIT_UNPICKED, centeredX, quitY, null);

                cursor.draw(g2, centeredX - 50, startY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
                break;
            case 1:
                // INDEX 1, LOAD IS PICKED
                if(pressedEnter) loadEntered.draw(g2, centeredX, loadY, GamePanel.TILE_SIZE * 3, GamePanel.TILE_SIZE);
                else g2.drawImage(Images.TitleScreenAssets.LOAD_PICKED, centeredX, loadY, null);

                g2.drawImage(Images.TitleScreenAssets.START_UNPICKED, centeredX, startY, null);
                g2.drawImage(Images.TitleScreenAssets.QUIT_UNPICKED, centeredX, quitY, null);

                cursor.draw(g2, centeredX - 40, loadY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
                break;
            case 2:
                // INDEX 2, QUIT IS PICKED
                if(pressedEnter) quitEntered.draw(g2, centeredX, quitY, GamePanel.TILE_SIZE * 3, GamePanel.TILE_SIZE);
                else g2.drawImage(Images.TitleScreenAssets.QUIT_PICKED, centeredX, quitY, null);

                g2.drawImage(Images.TitleScreenAssets.START_UNPICKED, centeredX, startY, null);
                g2.drawImage(Images.TitleScreenAssets.LOAD_UNPICKED, centeredX, loadY, null);

                cursor.draw(g2, centeredX - 40, quitY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
                break;

            default:
                System.out.println(cursorIndex);
                break;
        }

        // DRAW FADE INTO GAME WHEN ENTER IS PRESSED
        g2.setColor(new Color(0, 0, 0, fade));
        g2.fillRect(0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT);
    }

    @Override
    public void keyPressed(int key) {

        if(key == KeyEvent.VK_ENTER) {

            pressedEnter = true;
            SoundManager.INTRO.stop();
        }

        if(key == KeyEvent.VK_W) {

            cursorIndex--;
            if(cursorIndex < 0) cursorIndex = 2;
        }

        if(key == KeyEvent.VK_S) {

            cursorIndex++;
            if(cursorIndex > 2) cursorIndex = 0;
        }
     }

    @Override
    public void keyReleased(int key) {}
}
