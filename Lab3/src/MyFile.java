import java.io.File;

public abstract class MyFile {
    String path = "C:\\Users\\liviu\\IdeaProjects\\OOPLabs\\Lab3\\Changes";
    protected void printInfo() {
        File[] files = readFolder();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    // Get the file name and extension
                    String fileName = file.getName();
                    int lastDotIndex = fileName.lastIndexOf('.');
                    String extension = (lastDotIndex == -1) ? "No extension" : fileName.substring(lastDotIndex + 1);

                    System.out.println("File: " + fileName + ", Extension: " + extension);
                }
            }
        }
    }

    protected File[] readFolder() {
        File folder = new File(path);

        if (folder.exists() && folder.isDirectory()) {
            return folder.listFiles();
        }
        return null;
    }

    protected void analyze(String filePath){
        File file = new File(filePath);
    }
}
