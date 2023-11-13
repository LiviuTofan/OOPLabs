import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageSize {
    // A method to get the size of an image file
    public static String getImageSize(File imageFile) {
        try {
            // Read the image from the provided File object
            BufferedImage image = ImageIO.read(imageFile);

            // Check if the image was successfully loaded
            if (image != null) {
                // Get the width and height of the image
                int width = image.getWidth();
                int height = image.getHeight();
                // Return the image size in the format "width x height"
                return width + "x" + height;
            } else {
                // Return a message indicating that the image was not found or is not supported
                return "Image not found or not supported.";
            }
        } catch (Exception e) {
            // Handle exceptions and return an error message
            return "Error: " + e.getMessage();
        }
    }
}