import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageManager extends JPanel {
    private BufferedImage mImage;
    private Dimension size = new Dimension();

    public ImageManager(String linkToImg) {
        initImage(linkToImg);
    }

    private void initImage(String linkToImg) {
        try {
            this.mImage = ImageIO.read(new File(linkToImg));
            size.setSize(mImage.getWidth(), mImage.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        ImageIcon icon = new ImageIcon(mImage);
        JLabel label = new JLabel(icon, JLabel.CENTER);
        JOptionPane.showMessageDialog(null, label, "icon", -1);
    }

    public static BufferedImage changeColorPic(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (image.getRGB(j, i) == 1) {
                    image.setRGB(j, i, Color.RED.getRGB());
                }
            }
        }
        return image;
    }
}