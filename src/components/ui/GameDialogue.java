package components.ui;

import utilities.FontHandler;
import utilities.Images;

import java.awt.*;

public class GameDialogue {

    // TODO GET ENTITY POSITION, IF HIGH, DRAW THE DIALOGUE BOX LOW, AND VICE VERSA

    private int index;                  // INDEX OF THE CURRENT DIALOGUE

    private final String[] text;        // ARRAY OF STRING OF TEXT FROM THE ENTITY
    private final boolean repeat;       // IF THE DIALOGUE SHOULD REPEAT OR NOT

    public GameDialogue(String[] text, int startingIndex, boolean repeat) {

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

    public void draw(Graphics2D g2) {
        if(index != -1) drawWindow(g2, 0, 0, 0, 100);
    }

    public void drawWindow(Graphics2D g2, int x, int y, int textX, int textY) {

        g2.drawImage(Images.UserInterface.DIALOGUE_BOX, x, y, null);

        g2.setColor(Color.white);
        g2.setFont(FontHandler.comicoro);
        g2.drawString(text[index], textX, textY);
    }

    public void reset() { index = 0; }
    public void resetTo(int index) { this.index = index; }
    public boolean hasEnded() { return index == -1; }
}
