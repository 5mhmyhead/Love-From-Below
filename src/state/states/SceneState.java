package state.states;

import state.State;
import state.StateManager;
import utilities.SoundManager;

import java.awt.*;
import java.awt.event.KeyEvent;

// TODO WORK ON THIS INTRO CUTSCENE
public class SceneState extends State {

    private boolean pressedEnter;
    private String state;

    public SceneState(StateManager stateManager) {

        this.stateManager = stateManager;
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {

        if(pressedEnter) {
            System.out.println("Skip dialogue");
        }
    }

    @Override
    public void draw(Graphics2D g2) {

    }

    @Override
    public void keyPressed(int key) {

        if(key == KeyEvent.VK_ENTER) {

            pressedEnter = true;
            SoundManager.INTRO.stop();
        }
    }

    @Override
    public void keyReleased(int key) {

    }
}
