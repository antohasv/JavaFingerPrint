import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager extends JPanel {
    private BufferedImage mImage;
    private Dimension size = new Dimension();
    private int mMatrix[][];

    public ImageManager(String linkToImg) {
        initImage(linkToImg);
    }

    public ImageManager(BufferedImage image) {
        this.mImage = image;
        initImageSize();
    }

    private ImageManager(int[][] binMatrix) {
        mMatrix = binMatrix;
        mImage = new BufferedImage(binMatrix.length, binMatrix[0].length, BufferedImage.TYPE_INT_BGR);
        for (int i = 0; i < binMatrix[0].length; i++) {
            for (int j = 0; j < binMatrix.length; j++) {
                mImage.setRGB(j, i, binMatrix[j][i]);
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

    public static ImageManager newInstance(int[][] binMatrix) {
        return new ImageManager(binMatrix);
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
        changeColor(1, Color.RED.getRGB());
        ImageIcon icon = new ImageIcon(mImage);
        JLabel label = new JLabel(icon, JLabel.CENTER);
        JOptionPane.showMessageDialog(null, label, "FingerPrint", -1);
        changeColor(Color.RED.getRGB(), 1);
    }

    public void changeColor(int colorForChange, int newColor) {
        int width = mImage.getWidth();
        int height = mImage.getHeight();

        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mImage.getRGB(j, i) == colorForChange) {
                    mImage.setRGB(j, i, newColor);
                } else {
                    mImage.setRGB(j, i, Color.GREEN.getRGB());
                }
            }
        }
    }

    public BufferedImage getImage() {
        return mImage;
    }
}