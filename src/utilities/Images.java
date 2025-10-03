package utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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

        public static final BufferedImage TITLE_SCREEN = loadSingleImage("/assets/titleScreen/titleScreen.png");
    }

    // ALL TILE ASSETS
    public static class Blocks {

        // ALL ORANGE TILES
        static class Orange {

            static final BufferedImage GROUND = loadSingleImage("/assets/tiles/orange/ground.png");
            static final BufferedImage GRASS = loadSingleImage("/assets/tiles/orange/grass.png");
            static final BufferedImage FLOWERS = loadSingleImage("/assets/tiles/orange/flowers.png");
            static final BufferedImage ROCK = loadSingleImage("/assets/tiles/orange/rock.png");
            static final BufferedImage BRICK = loadSingleImage("/assets/tiles/orange/brick.png");
        }

        static class Tree {
            static final BufferedImage SMALL_TREE = loadSingleImage("/assets/tiles/trees/smallTree.png");
        }
    }
}


