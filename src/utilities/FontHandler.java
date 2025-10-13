package utilities;

import core.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontHandler {
    // FONTS FOR THE GAME, INITIALIZED TO SIZE 30 AND 40 RESPECTIVELY
    public static Font comicoro;
    public static Font pixelBitAdvanced;

    public FontHandler() {

        try {
            // USES INPUT STREAM TO READ THE FONT FILES
            InputStream inputStream;

            inputStream = getClass().getResourceAsStream("/fonts/Comicoro.ttf");

            assert inputStream != null;
            comicoro = Font.createFont(Font.TRUETYPE_FONT, inputStream)
                    .deriveFont(Font.PLAIN, 30f);

            inputStream = getClass().getResourceAsStream("/fonts/PixelBitAdvanced.ttf");

            assert inputStream != null;
            pixelBitAdvanced = Font.createFont(Font.TRUETYPE_FONT, inputStream)
                    .deriveFont(Font.PLAIN, 40f);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    static { new FontHandler(); }

    //CENTERS A STRING TO CENTER
    public void drawCenteredString(String string, int y, Graphics2D g2) {

        for(String line : string.split("-")) {

            int stringWidth = (int) g2.getFontMetrics().getStringBounds(line, g2).getWidth();
            g2.drawString(line, (GamePanel.SCREEN_WIDTH - stringWidth) / 2,
                    (y += (g2.getFontMetrics().getHeight() + 2)));
        }
    }
}
