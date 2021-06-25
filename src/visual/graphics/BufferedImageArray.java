package visual.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BufferedImageArray {

    private static BufferedImage imageUp;

    static {
        try {
            imageUp = ImageIO.read(new File("./res/up.png"));
            imageDown = ImageIO.read(new File("./res/down.png"));
            imageRight = ImageIO.read(new File("./res/right.png"));
            imageLeft = ImageIO.read(new File("./res/left.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    } ;
    private static BufferedImage imageDown;
    private static BufferedImage imageLeft;
    private static BufferedImage imageRight;
    private static BufferedImage[] bufferedImages = new BufferedImage[]{imageUp, imageRight, imageDown, imageLeft};;

    public static BufferedImage getBufferedImage(int i){
        return bufferedImages[i];
    }
}
