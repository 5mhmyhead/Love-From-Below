package components.entities;

import java.awt.*;

// TODO ADD NPC SPECIFIC CODE HERE
public abstract class NPC extends Entity {

    protected boolean inDialogue;

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {

    }

    public boolean isInDialogue() { return inDialogue; }
}
