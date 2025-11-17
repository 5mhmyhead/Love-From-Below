package core.ui;

import components.entities.Entity;
import components.objects.WorldObject;
import core.GamePanel;
import utilities.Animation;
import utilities.FontHandler;
import utilities.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameText {

    private final WorldObject object;

    private int index;                  // INDEX OF THE CURRENT DIALOGUE
    private final String[] text;        // ARRAY OF STRING OF TEXT FROM THE ENTITY
    private final boolean repeat;       // IF THE DIALOGUE SHOULD REPEAT OR NOT

    public GameText(WorldObject object, String[] text, int startingIndex, boolean repeat) {

        this.object = object;
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

        int tileSize = GamePanel.TILE_SIZE;
        int dialogueWidth = tileSize * 14;
        int dialogueHeight = tileSize * 4;

        // SPLITS THE TEXT APART FROM THE WHITE SPACE
        String[] parts = text[index].split(":");

        // SET THE COLOR FOR THE BACKGROUND OF THE TEXT BOX AND FONT
        g2.setColor(new Color(7, 22, 33, 220));
        g2.setFont(FontHandler.maruMonica);

        // CHECKS THE Y POSITION OF THE OBJECT
        // IF THE OBJECT IS AT THE TOP HALF OF THE SCREEN, DRAW THE TEXT AT THE BOTTOM AND VICE VERSA
        if(object.getY() <= GamePanel.SCREEN_HEIGHT / 2) {

            g2.fillRect(tileSize, tileSize * 7, dialogueWidth, dialogueHeight);
            g2.drawImage(Images.UI.TEXT_BOX_BOTTOM, 0, 0, null);

            g2.setColor(new Color(224, 248, 207));
            for(String line : parts[1].split("\\|")) {

                g2.drawString(line, 100, bottomY);
                bottomY += 30;
            }
        }
        else {

            g2.fillRect(tileSize, tileSize, dialogueWidth, dialogueHeight);
            g2.drawImage(Images.UI.TEXT_BOX_TOP, 0, 0, null);

            g2.setColor(new Color(224, 248, 207));
            for(String line : parts[1].split("\\|")) {

                g2.drawString(line, 100, topY);
                topY += 30;
            }
        }
    }

    public void reset() { index = 0; }
    public void resetTo(int index) { this.index = index; }

    public boolean hasEnded() { return index == -1; }
}
