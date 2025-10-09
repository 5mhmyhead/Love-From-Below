package utilities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private int delay;                      // LENGTH BETWEEN EACH FRAME

    private int timer;                      // TIMER TO CHECK BETWEEN EACH FRAME
    private int index;                      // INDEX OF THE CURRENT SPRITE IN THE SPRITESHEET

    private final BufferedImage[] images;   // ARRAY OF IMAGES TAKEN FROM THE SPRITESHEET

    private final boolean repeat;           // IF ANIMATION SHOULD LOOP OR NOT

    // CONSTRUCTOR THAT ASKS A LIST OF ALL FRAMES
    public Animation(int delay, boolean repeat, BufferedImage... frames) {

        this.delay = delay;
        this.images = frames;
        this.repeat = repeat;

        timer = 0;
        index = 0;
    }

    // CONSTRUCTION THAT TAKES A SPRITESHEET
    // ALL FRAMES MUST BE IN ONE ROW
    public Animation(int delay, boolean repeat, BufferedImage spritesheet, int spriteWidth, int spriteHeight) {

        this.delay = delay;
        this.repeat = repeat;

        images = new BufferedImage[spritesheet.getWidth() / spriteWidth];
        // LOOP THAT CUTS APART THE SPRITESHEET
        for(int i = 0; i < images.length; i++) {
            images[i] = spritesheet.getSubimage(i * spriteWidth, 0, spriteWidth, spriteHeight);
        }

        timer = 0;
        index = 0;
    }

    // UPDATES TIMER AND RESETS DEPENDING ON THE DELAY
    public void update() {

        timer++;

        if(timer > delay) {

            timer = 0;
            nextFrame();
        }
    }

    // MOVES THE INDEX TO THE NEXT FRAME
    public void nextFrame() {
        // IF THE INDEX IS -1, THEN THE ANIMATION STOPS UPDATING
        if(index != -1) {

            index++;
            // IF THE LOOP IS FINISHED, CHECK IF THE ANIMATION SHOULD BE REPEATING OR NOT
            if(index >= images.length) {

                if(repeat) index = 0;
                else index = -1;
            }
        }
    }

    public void draw(Graphics2D g2, int x, int y, int width, int height) {
        if(index != -1) g2.drawImage(images[index], x, y, width, height, null);
    }

    // DRAW FUNCTION THAT ONLY DRAWS THE FIRST FRAME
    public void drawFirst(Graphics2D g2, int x, int y, int width, int height) {
        g2.drawImage(images[0], x, y, width, height, null);
    }

    public void reset() { index = 0; }                               // RESET ANIMATION
    public boolean hasEnded() { return index == -1; }                // CHECKS IF ANIMATION HAS ENDED

    public int getWidth() { return images[index].getWidth(); }
    public int getHeight() { return images[index].getHeight(); }

    public void setDelay(int delay) {

        this.delay = delay;
        if(timer > delay) timer = delay;
    }

    public int getDelay() { return delay; }
}

