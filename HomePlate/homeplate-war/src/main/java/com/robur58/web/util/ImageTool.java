package com.robur58.web.util;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.log4j.Logger;

/**
 *
 * @author godefrooije
 */
public class ImageTool {

    private static final Logger LOG = Logger.getLogger(ImageTool.class);

    public static Image createThumbnail(Image image) {
        BufferedImage bufferedImage = toBufferedImage(image);
        BufferedImage bufThumb = createThumbnail(bufferedImage);
        Image thumbImage = toImage(bufThumb);

        return thumbImage;
    }

    private static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();

        // Determine if the image has transparent pixels; for this method's
        // implementation, see e661 Determining If an Image Has Transparent
        // Pixels
        boolean hasAlpha = hasAlpha(image);

        // Create a buffered image with a format that's compatible with the
        // screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment
                .getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
            if (hasAlpha) {
                transparency = Transparency.BITMASK;
            }

            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image
                    .getHeight(null), transparency);
        } catch (HeadlessException e) {
            LOG.error("This system does not have a screen", e);
        }

        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            if (hasAlpha) {
                type = BufferedImage.TYPE_INT_ARGB;
            }
            bimage = new BufferedImage(image.getWidth(null), image
                    .getHeight(null), type);
        }

        // Copy image to buffered image
        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }

    private static boolean hasAlpha(Image image) {
        // If buffered image, the color model is readily available
        if (image instanceof BufferedImage) {
            BufferedImage bimage = (BufferedImage) image;
            return bimage.getColorModel().hasAlpha();
        }

        // Use a pixel grabber to retrieve the image's color model;
        // grabbing a single pixel is usually sufficient
        PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            LOG.error(e);
        }

        // Get the image's color model
        ColorModel cm = pg.getColorModel();
        if (cm != null) {
            return cm.hasAlpha();
        } else {
            return true;
        }
    }

    private static Image toImage(BufferedImage bufferedImage) {
        return Toolkit.getDefaultToolkit().createImage(
                bufferedImage.getSource());
    }

    private static BufferedImage createThumbnail(BufferedImage orig) {
        final int WIDTH = 75;
        final int HEIGHT = 75;
        AffineTransform at;

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        // g2.setPaint(bgColor);
        g2.fillRect(0, 0, WIDTH, HEIGHT);
        // scale to fit
        double xScale = (double) WIDTH / orig.getWidth();
        double yScale = (double) HEIGHT / orig.getHeight();
        double scale = Math.min(xScale, yScale);
        // center thumbnail image
        double x = (WIDTH - orig.getWidth() * scale) / 2;
        double y = (HEIGHT - orig.getHeight() * scale) / 2;
        at = AffineTransform.getTranslateInstance(x, y);
        at.scale(scale, scale);
        g2.drawRenderedImage(orig, at);
        g2.dispose();

        return image;
    }

    public static byte[] imageToByteArray(Image image) {

        try {
            MediaTracker tracker = new MediaTracker(new Container());
            tracker.addImage(image, 0);
            try {
                tracker.waitForAll();
            } catch (InterruptedException e) {
            }
            BufferedImage bufferedImage = new BufferedImage(image
                    .getWidth(null), image.getHeight(null), 1);
            Graphics gc = bufferedImage.createGraphics();
            gc.drawImage(image, 0, 0, null);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpeg", bos);

            return bos.toByteArray();
        } catch (IOException e) {
            LOG.error(e);
        }
        return null;
    }
}
