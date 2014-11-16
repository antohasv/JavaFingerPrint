import java.awt.*;
import java.awt.image.BufferedImage;

public class Binarization {
    public static int[][] getBinaryMatrix(BufferedImage image) {
        int result[][] = new int[image.getWidth()][image.getHeight()];

        Color color = null;
        int pixel = 0;
        for(int i = 0; i < image.getHeight(); i++) {
            for(int j = 0; j < image.getWidth(); j++){
                color = new Color(image.getRGB(j, i));
                pixel = (int) (color.getRed() * 0.3 + color.getGreen() * 0.59 + color.getBlue() * 0.11);
                if (pixel > 128) {
                    pixel = 1;
                } else {
                    pixel = 0;
                }
                result[j][i] = pixel;
            }
        }
        return result;
    }
}
