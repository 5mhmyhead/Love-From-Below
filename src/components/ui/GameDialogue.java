package components.ui;

import utilities.FontHandler;

import java.awt.*;

public class GameDialogue {

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
        if(index != -1) drawWindow(g2, 0, 100, 300, 300);
    }

    public void drawWindow(Graphics2D g2, int x, int y, int width, int height) {

        g2.setColor(Color.white);
        g2.setFont(FontHandler.comicoro);
        g2.drawString(text[index], x, y);
    }
}
