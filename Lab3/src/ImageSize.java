import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageSize {
    public static void printImageSizesInFolder(String folderPath) {
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] imageFiles = folder.listFiles((dir, name) ->
                    name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg"));

            if (imageFiles != null) {
                for (File imageFile : imageFiles) {
                    try {
                        BufferedImage image = ImageIO.read(imageFile);
                        if (image != null) {
                            int width = image.getWidth();
                            int height = image.getHeight();
                            System.out.println("File: " + imageFile.getName());
                            System.out.println("Image Dimensions: " + width + "x" + height + " pixels");
                            System.out.println();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("No .png or .jpg files found in the directory.");
            }
        } else {
            System.out.println("Invalid directory path or the directory doesn't exist.");
        }
    }
}
