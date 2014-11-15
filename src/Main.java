import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {

    public static final String LINK_TO_IMG = "img/img1.png";


    public static void main(String[] args) {
        ImageManager imageManager = new ImageManager(LINK_TO_IMG);

        try {
            Skeletization sk = new Skeletization(getBinaryImage(bufferedImage));
            //showImage(sk.getBinImg());

            FindMinutia findMinutia = new FindMinutia(sk.getBinImg());
            findMinutia.perform();

            System.out.print("Branch:" + findMinutia.getMinutiaBranch().size() + " End:" + findMinutia.getMinutiaEnd().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static BufferedImage getBinaryImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        Color c = null;
        int p;
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++){
                c = new Color(image.getRGB(j, i));
                p = (int) (c.getRed() * 0.3 + c.getGreen() * 0.59 + c.getBlue() * 0.11);
                if (p > 128) {
                    p = 1;
                } else {
                    p = 0;
                }
                image.setRGB(j, i, p);
            }
        }
        return image;
    }

    public static void showImage(BufferedImage img) {
        img = changeColorPic(img);
        ImageManager test = new ImageManager(img);
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new JScrollPane(test));
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }

}
