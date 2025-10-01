package state.states;

import state.State;
import state.StateManager;

import java.awt.*;

public class TitleState extends State {

    // CONSTRUCTOR
    public TitleState(StateManager stateManager) {

        this.stateManager = stateManager;
        init();
    }

    @Override
    public void init() {
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g2) {
    }

    @Override
    public void keyPressed(int key) {
    }

    @Override
    public void keyReleased(int key) {
    }
}
