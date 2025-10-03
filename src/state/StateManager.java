package state;

import state.states.GameState;
import state.states.TitleState;

import java.awt.*;

public class StateManager {

    // GAME STATES
    private static final int NUMBER_OF_STATES = 3;

    public static final int TITLE_STATE = 0;
    public static final int GAME_STATE = 1;
    public static final int PAUSE_STATE = 2;

    private final State[] states; // array holding the different states
    private int currentState;

    // CONSTRUCTOR
    public StateManager() {

        states = new State[NUMBER_OF_STATES];
        currentState = GAME_STATE;
        loadState(currentState);
    }

    // UPDATES THE CURRENT STATE
    public void update() {

        if(states[currentState] != null) {
            states[currentState].update();
        }
    }

    // DRAWS THE CURRENT STATE
    public void draw(Graphics2D g2) {

        if(states[currentState] != null) {
            states[currentState].draw(g2);
        }
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
        if(state == GAME_STATE) states[state] = new GameState(this);
    }

    // SENDS THE KEY PRESS TO THE CURRENT STATE
    public void keyPressed(int key) { states[currentState].keyPressed(key); }
    public void keyReleased(int key) { states[currentState].keyReleased(key); }
}
