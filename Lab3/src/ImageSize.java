import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageSize extends MyFile {
    private String currentFileName;
    private int width;
    private int height;
    private String targetFileName;
    public void setTargetFileName(String targetFileName) {
        this.targetFileName = targetFileName;
    }
    @Override
    protected void analyze(String filePath) {
        try {
            File imageFile = new File(filePath);
            BufferedImage image = ImageIO.read(imageFile);

            // Reset values before analyzing a new image
            width = -1;
            height = -1;
            currentFileName = imageFile.getName();

            // Check image
            if (image != null && currentFileName.equalsIgnoreCase(targetFileName)) {
                width = image.getWidth();
                height = image.getHeight();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printInfo() {
        File[] files = readFolder();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && isImage(file) && file.getName().equalsIgnoreCase(targetFileName)) {
                    analyze(file.getAbsolutePath());
                    System.out.println("-------------");
                    System.out.println("File: " + currentFileName);
                    System.out.println("Image Size: " + getSizeString());
                    System.out.println("-------------");
                    break;
                }
            }
        }
    }

    private boolean isImage(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".png");
    }

    private String getSizeString() {
        if (width == -1 || height == -1) {
            return "Image not found";
        } else {
            return width + "x" + height;
        }
    }
}
