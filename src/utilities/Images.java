package utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.awt.*;

public class Images {

    // LOADS AN IMAGE FROM A PATH
    private static BufferedImage loadSingleImage(String path) {

        try {
            return ImageIO.read(Objects.requireNonNull(Images.class.getResourceAsStream(path)));
        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ALL ASSETS FOR TITLE SCREEN
    public static class TitleScreenAssets {

        public static final BufferedImage TITLE_SCREEN = loadSingleImage("/titleScreenAssets/titleScreen.png");
    }
}


