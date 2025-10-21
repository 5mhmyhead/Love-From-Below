package core.ui;

import components.entities.Entity;
import core.GamePanel;
import utilities.Animation;
import utilities.FontHandler;
import utilities.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameDialogue {

    private final int portraitWidth = 144;
    private final int portraitHeight = 144;

    private final Entity entity;
    private final Animation portrait;

    private int index;                  // INDEX OF THE CURRENT DIALOGUE
    private final String[] text;        // ARRAY OF STRING OF TEXT FROM THE ENTITY
    private final boolean repeat;       // IF THE DIALOGUE SHOULD REPEAT OR NOT

    public GameDialogue(Entity entity, BufferedImage image, String[] text, int startingIndex, boolean repeat) {

        this.entity = entity;
        this.portrait = new Animation(0, false, image, portraitWidth, portraitHeight);

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

        g2.setColor(new Color(224, 248, 207));
        g2.setFont(FontHandler.maruMonica);

        // SPLITS BETWEEN THE PORTRAIT NUMBER AND THE TEXT ITSELF
        String[] parts = text[index].split(":");

        // GETS THE CORRESPONDING PORTRAIT WITH THE NUMBER GIVE
        String numberOnly = parts[0].replaceAll("[^0-9]", "");
        int portraitNum = Integer.parseInt(numberOnly);

        // CHECKS THE Y POSITION OF THE ENTITY
        // IF THE ENTITY IS AT THE TOP HALF OF THE SCREEN, DRAW THE DIALOGUE AT THE BOTTOM AND VICE VERSA
        if(entity.getY() <= GamePanel.SCREEN_HEIGHT / 2) {

            g2.drawImage(Images.UI.DIALOGUE_BOX_BOTTOM, 0, 0, null);
            portrait.drawSpecific(g2, portraitNum, 72, 360, portraitWidth, portraitHeight);

            for(String line : parts[1].split("\\|")) {
                g2.drawString(line, 240, bottomY);
                bottomY += 30;
            }
        }
        else {

            g2.drawImage(Images.UI.DIALOGUE_BOX_TOP, 0, 0, null);
            portrait.drawSpecific(g2, portraitNum, 72, 72, portraitWidth, portraitHeight);

            for(String line : parts[1].split("\\|")) {
                g2.drawString(line, 240, topY);
                topY += 30;
            }
        }
    }

    public void reset() { index = 0; }
    public void resetTo(int index) { this.index = index; }

    public boolean hasEnded() { return index == -1; }
}
