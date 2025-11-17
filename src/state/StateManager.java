package state;

import core.GamePanel;
import state.states.GameState;
import state.states.SceneState;
import state.states.TitleState;

import java.awt.*;

// HANDLES SWITCHING BETWEEN AND MOVING THROUGH DIFFERENT STATES DURING GAMEPLAY
public class StateManager {

    // GAME STATES
    private static final int NUMBER_OF_STATES = 3;

    public static final int TITLE_STATE = 0;
    public static final int SCENE_STATE = 1;
    public static final int GAME_STATE = 2;
    public static final int LOAD_STATE = 3;

    private final State[] states;           // ARRAY HOLDING THE STATES
    private int currentState;               // ID REPRESENTING THE CURRENT STATE

    public StateManager() {

        states = new State[NUMBER_OF_STATES];
        currentState = GAME_STATE;          // FIXME CURRENT STATE SHOULD BE TITLE STATE AFTER TESTING
        loadState(currentState);
    }

    // UPDATES THE CURRENT STATE
    public void update() {

        if(states[currentState] != null)
            states[currentState].update();
    }

    // DRAWS THE CURRENT STATE
    public void draw(Graphics2D g2) {

        if(states[currentState] != null)
            states[currentState].draw(g2);
    }

    // SET STATE DEPENDING ON INDEX GIVEN
    public void setState(int state) {

        unloadState(currentState);
        currentState = state;
        loadState(currentState);
    }

    // DEALLOCATES THE REMOVED STATE
    private void unloadState(int state) {
        states[state] = null;
    }

    // LOADS STATE DEPENDING ON INDEX
    private void loadState(int state) {

        if(state == TITLE_STATE) states[state] = new TitleState(this);
        if(state == SCENE_STATE) states[state] = new SceneState(this);
        if(state == GAME_STATE) states[state] = new GameState(this);
        if(state == LOAD_STATE) states[state] = new GameState(this); //FIXME SHOULD MAKE NEW LOAD STATE CLASS
    }

    // SENDS THE KEY PRESS TO THE CURRENT STATE
    public void keyPressed(int key) { states[currentState].keyPressed(key); }
    public void keyReleased(int key) { states[currentState].keyReleased(key); }
}
