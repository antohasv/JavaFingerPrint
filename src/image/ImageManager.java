package image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager extends JPanel {
    private BufferedImage mImage;
    private Dimension size = new Dimension();
    private boolean mMatrix[][];

    public ImageManager(String linkToImg) {
        initImage(linkToImg);
    }

    public ImageManager(BufferedImage image) {
        this.mImage = image;
        initImageSize();
    }

    private ImageManager(boolean[][] binMatrix) {
        mMatrix = binMatrix;
        mImage = new BufferedImage(binMatrix.length, binMatrix[0].length, BufferedImage.TYPE_4BYTE_ABGR);
        for (int i = 0; i < binMatrix[0].length; i++) {
            for (int j = 0; j < binMatrix.length; j++) {
                mImage.setRGB(j, i, binMatrix[j][i] ? 1 : 0);
            }
        }
        initImageSize();
    }

    private void initImage(String linkToImg) {
        try {
            this.mImage = ImageIO.read(new File(linkToImg));
            initImageSize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initImageSize() {
        size.setSize(mImage.getWidth(), mImage.getHeight());
    }

    public static ImageManager newInstance(boolean[][] binMatrix) {
        return new ImageManager(binMatrix);
    }

    public void save(String path) {
        changeColor(1, Color.WHITE.getRGB());
        File outputfile = new File(path);
        try {
            ImageIO.write(mImage, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        changeColor(Color.WHITE.getRGB(), 1);
    }

    /**
     * Drawing an image can allow for more
     * flexibility in processing/editing.
     */
    protected void paintComponent(Graphics g) {
        int x = (getWidth() - size.width) / 2;
        int y = (getHeight() - size.height) / 2;
        g.drawImage(mImage, x, y, this);
    }

    public Dimension getPreferredSize() { return size; }

    /**
     * Easy way to show an image: load it into a JLabel
     * and add the label to a container in your gui.
     */
    public void showImage() {
        changeColor(1, Color.WHITE.getRGB());
        ImageIcon icon = new ImageIcon(mImage);
        JLabel label = new JLabel(icon, JLabel.CENTER);
        JOptionPane.showMessageDialog(null, label, "algorithm.FingerPrint", -1);
        changeColor(Color.WHITE.getRGB(), 1);
    }

    public void changeColor(int colorForChange, int newColor) {
        int width = mImage.getWidth();
        int height = mImage.getHeight();

        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mImage.getRGB(j, i) == colorForChange) {
                    mImage.setRGB(j, i, newColor);
                } else if (mImage.getRGB(j, i) == 0) {
                    mImage.setRGB(j, i, Color.BLACK.getRGB());
                }
            }
        }
    }

    public BufferedImage getImage() {
        return mImage;
    }
}