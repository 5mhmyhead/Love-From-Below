package state.states;

import core.GamePanel;
import state.State;
import state.StateManager;
import utilities.Animation;
import utilities.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

// TODO SAVE AND INTRO CUTSCENE MUST BE IMPLEMENTED ALONG WITH THE TITLE STATE

public class TitleState extends State {

    private boolean pressedEnter;

    private int timer;  // AMOUNT OF TIME BEFORE SCREEN GOES BLACK
    private int fade;   // ALPHA LEVEL

    private Animation titleScreenAnimation;

    public TitleState(StateManager stateManager) {

        this.stateManager = stateManager;
        init();
    }

    @Override
    public void init() {

        timer = 0;
        fade = 0;

        // CREATE ANIMATION FOR TITLE SCREEN
        assert Images.TitleScreenAssets.TITLE_SCREEN != null;
        titleScreenAnimation = new Animation(10, true, Images.TitleScreenAssets.TITLE_SCREEN,
                GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT);
    }

    @Override
    public void update() {
        // IF THE PLAYER PRESSED ENTER, THEN FADE INTO NEXT STATE
        if(pressedEnter) {

            timer++;

            if(fade < 255) fade += 4;
            // IF THE PLAYER PRESSED ENTER AFTER 2 SECONDS, SWITCH STATE
            else if(timer >= (2 * GamePanel.FPS)) {
                stateManager.setState(StateManager.GAME_STATE);
            }
            // CONSTRAIN FADE
            if(fade < 0) fade = 0;
            if(fade > 255) fade = 255;
        }

        titleScreenAnimation.update();
    }

    @Override
    public void draw(Graphics2D g2) {

        titleScreenAnimation.draw(g2, 0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT);

        // DRAW FADE INTO GAME WHEN ENTER IS PRESSED
        g2.setColor(new Color(0, 0, 0, fade));
        g2.fillRect(0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT);
    }

    @Override
    public void keyPressed(int key) {

        if(key == KeyEvent.VK_ENTER)
            pressedEnter = true;
    }

    @Override
    public void keyReleased(int key) {}
}
