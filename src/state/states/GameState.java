package state.states;

import entities.Player;
import state.State;
import state.StateManager;

import java.awt.*;

public class GameState extends State {

    private Player player;

    // CONSTRUCTOR
    public GameState(StateManager stateManager) {

        this.stateManager = stateManager;
        init();
    }

    @Override
    public void init() {
        player = new Player();
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        player.draw(g2);
    }

    @Override
    public void keyPressed(int key) {

        // TELLS THE PLAYER WHICH KEYS ARE PRESSED
        player.setInput(key, true);
    }

    @Override
    public void keyReleased(int key) {

        // TELLS THE PLAYER WHICH KEYS ARE RELEASED
        player.setInput(key, false);
    }
}
