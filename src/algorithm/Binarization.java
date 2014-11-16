package algorithm;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Binarization extends Algorithm<boolean[][]> {

    BufferedImage mImage;

    public Binarization(BufferedImage image) {
        this.mImage = image;
    }

    @Override
    public boolean[][] execute() {
        boolean result[][] = new boolean[mImage.getWidth()][mImage.getHeight()];

        Color color = null;
        boolean pixel;
        for(int i = 0; i < mImage.getHeight(); i++) {
            for(int j = 0; j < mImage.getWidth(); j++){
                color = new Color(mImage.getRGB(j, i));
                if ((color.getRed() * 0.3 + color.getGreen() * 0.59 + color.getBlue() * 0.11) > 128) {
                    pixel = true;//1
                } else {
                    pixel = false;//0
                }
                result[j][i] = pixel;
            }
        }
        return result;
    }
}
