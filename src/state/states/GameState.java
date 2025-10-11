package state.states;

import components.entity.Player;
import components.ui.GameDialogue;
import components.ui.GameMenu;
import components.world.World;
import state.State;
import state.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameState extends State {

    private Player player;
    private World world;
    private GameMenu menu;

    private String state;                // CHECK THE DIFFERENT STATES: GAME, INVENTORY, MENU OR TRANSITION

    // CONSTRUCTOR
    public GameState(StateManager stateManager) {

        this.stateManager = stateManager;
        init();
    }

    @Override
    public void init() {

        state = "GAME";

        /* STARTING ROOM REFERS TO THE ROOM ID
         * THE ID IS REPRESENTED BY TWO NUMBERS, THE FIRST FOR COLUMN AND SECOND FOR ROW
         * STARTING ROOM 11 REFERS TO THE ROOM AT THE VERY TOP LEFT
         */
        world = new World(13, "/tileMaps/Caves.txt", "/tileMaps/Metadata.xml",48, 36);

        menu = new GameMenu(world);
        player = world.getPlayer();
    }

    @Override
    public void update() {

        switch(state) {

            case "GAME":
                world.update();
                break;

            case "MENU":
                menu.update();
                break;

            case "TRANSITION":
                if(!world.isTransitioning()) state = "GAME";
                break;

            default: break;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        // DRAWS THE WORLD
        g2.getTransform();
        world.draw(g2);

        // DRAW THE MENU WHEN IN MENU STATE
        if(state.equals("MENU")) menu.draw(g2);
    }

    @Override
    public void keyPressed(int key) {

        if(key == KeyEvent.VK_ESCAPE) {

            switch(state) {

                case "GAME": state = "MENU"; break;
                case "MENU": state = "GAME"; break;

                default: break;
            }
        }
        // TELLS THE PLAYER WHICH KEYS ARE PRESSED
        player.setInput(key, true);
    }

    @Override
    public void keyReleased(int key) {
        // TELLS THE PLAYER WHICH KEYS ARE RELEASED
        player.setInput(key, false);
    }
}
