package components.objects.interactables;

import components.entities.Player;
import components.objects.Interactable;
import components.world.rooms.Room;
import core.ui.GameData;
import core.ui.GameText;
import utilities.Animation;
import utilities.Images;

import java.awt.*;
import java.util.ArrayList;

public class CandleBush extends Interactable {

    private final Animation bushBreak;
    private final GameText textWithoutCandle;

    public CandleBush(int x, int y, ArrayList<String[]> text, Room room) {

        this.x = x;
        this.y = y;

        this.hasCollision = true;
        this.hasTextDialogue = true;
        this.inTextDialogue = false;

        assert Images.Effects.BUSH_BREAK != null;
        bushBreak = new Animation(4, false, Images.Effects.BUSH_BREAK, width, height);

        textWithoutCandle = new GameText(this, text.get(0), 0, false);

        this.image = Images.WorldObjects.CANDLE_BUSH;
        this.room = room;
    }

    @Override
    public void update() {
        // BUSH BREAK EFFECT AFTER IT HAS BEEN INTERACTED WITH CANDLE
        if(!this.hasCollision) bushBreak.update();
    }

    @Override
    public boolean action(Player player) {

        // IF THE PLAYER HAS THE CANDLE, BREAK THE BUSH
        if(GameData.hasCandle) {

            this.hasCollision = false;
            this.image = null;
        }

        // IF IT HAS NOT BEEN BROKEN YET, SHOW TEXT DIALOGUE
        if(this.hasCollision) {

            if(inTextDialogue) textWithoutCandle.update();
            inTextDialogue = true;

            if(textWithoutCandle.hasEnded()) {

                inTextDialogue = false;
                textWithoutCandle.reset();
            }
        }

        return false;
    }

    @Override
    public void draw(Graphics2D g2) {

        if(this.hasCollision) g2.drawImage(image, x, y, width, height, null);
        else bushBreak.draw(g2, x, y, width, height);

        if(!GameData.hasCandle && inTextDialogue) textWithoutCandle.draw(g2);
    }
}
