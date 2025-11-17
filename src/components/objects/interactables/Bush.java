package components.objects.interactables;

import components.entities.Player;
import components.objects.Interactable;
import components.world.rooms.Room;

import core.ui.GameData;
import core.ui.GameText;
import utilities.Images;

import java.awt.*;
import java.util.ArrayList;

// TODO ADD PARTICLES WHEN BUSH IS DESTROYED
public class Bush extends Interactable {

    private final GameText textWithoutSword;
    private final GameText textWithSword;

    public Bush(int x, int y, ArrayList<String[]> text, Room room) {

        this.x = x;
        this.y = y;

        this.hasCollision = true;
        this.hasTextDialogue = true;
        this.inTextDialogue = false;

        textWithoutSword = new GameText(this, text.get(0), 0, false);
        textWithSword = new GameText(this, text.get(1), 0, false);

        this.image = Images.WorldObjects.BUSH;
        this.room = room;
    }

    @Override
    public void update() {}

    @Override
    public boolean action(Player player) {
        // IF THE BUSH WAS ATTACKED BY THE PLAYER
        if(player.getState().equals("ATTACK")) {

            this.hasCollision = false;
            this.image = null;
        }

        // IF THE BUSH WAS INTERACTED BY THE PLAYER
        if(this.hasCollision) {
            inTextDialogue = !inTextDialogue;
        }

        return false;
    }

    @Override
    public void draw(Graphics2D g2) {

        g2.drawImage(image, x, y, width, height, null);

        if(inTextDialogue) {

            if(GameData.swordLevel == 0) textWithoutSword.draw(g2);
            if(GameData.swordLevel > 0) textWithSword.draw(g2);
        }
    }
}
