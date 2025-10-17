package core.ui;

import components.entity.Entity;
import core.GamePanel;
import utilities.FontHandler;
import utilities.Images;

import java.awt.*;
import java.util.Arrays;

public class GameDialogue {

    private final Entity entity;
    private int index;                  // INDEX OF THE CURRENT DIALOGUE

    private final String[] text;        // ARRAY OF STRING OF TEXT FROM THE ENTITY
    private final boolean repeat;       // IF THE DIALOGUE SHOULD REPEAT OR NOT

    public GameDialogue(Entity entity, String[] text, int startingIndex, boolean repeat) {

        this.entity = entity;

        this.text = text;
        this.repeat = repeat;
        this.index  = startingIndex;
    }

    public void update() {
        // IF THE INDEX IS -1, THEN THE DIALOGUE STOPS UPDATING
        if(index != -1) {

            index++;
            // IF THE LOOP IS FINISHED, CHECK IF THE DIALOGUE SHOULD BE REPEATING OR NOT
            if(index >= text.length) {

                if(repeat) index = 0;
                else index = -1;
            }
        }
    }

    public void draw(Graphics2D g2) { if(index != -1) drawWindow(g2); }

    public void drawWindow(Graphics2D g2) {

        int topY = 100;
        int bottomY = GamePanel.SCREEN_HEIGHT / 2 + 100;

        g2.setColor(Color.white);
        g2.setFont(FontHandler.comicoro);

        if(entity.getY() <= GamePanel.SCREEN_HEIGHT / 2) {

            g2.drawImage(Images.UI.DIALOGUE_BOX_BOTTOM, 0, 0, null);

            for(String line : text[index].split("\\|")) {
                g2.drawString(line, 70, bottomY);
                bottomY += 30;
            }
        }
        else {

            g2.drawImage(Images.UI.DIALOGUE_BOX_TOP, 0, 0, null);

            for(String line : text[index].split("\\|")) {
                g2.drawString(line, 70, topY);
                topY += 30;
            }
        }
    }

    public void reset() { index = 0; }
    public void resetTo(int index) { this.index = index; }

    public boolean hasEnded() { return index == -1; }
}
