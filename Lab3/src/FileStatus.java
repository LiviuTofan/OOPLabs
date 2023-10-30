import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileStatus {
    // A map to store the change status of files (whether they have been changed or not)
    private final Map<String, Boolean> fileChangeStatus = new HashMap<>();
    private final String folderPath;

    // Constructor that initializes the fileChangeStatus based on files in the given folder
    public FileStatus(String folderPath) {
        this.folderPath = folderPath;
        initializeFileChangeStatus(folderPath);
    }

    // Initializes the fileChangeStatus map based on files in the folder
    private void initializeFileChangeStatus(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    fileChangeStatus.put(fileName, false); // Initially, set all files as not changed
                }
            }
        }
    }

    // Reset the change status of all files to "not changed"
    public void resetAllFileStatus() {
        fileChangeStatus.replaceAll((n, v) -> false);
    }

    // Marks a file as changed in the fileChangeStatus map
    public void fileChanged(String fileName) {
        if (fileChangeStatus.containsKey(fileName)) {
            fileChangeStatus.put(fileName, true);
        }
    }

    // Checks for new files and missing files in the folder and updates their status
    // Checks for new files and missing files in the folder and updates their status
    // Checks for new files and missing files in the folder and updates their status
    public void checkForNewFilesAndMissingFiles() {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        // Create a copy of the current file status map
        Map<String, Boolean> currentFileStatus = new HashMap<>(fileChangeStatus);

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    // Update existing files' status if they are marked as changed
                    if (fileChangeStatus.containsKey(fileName) && fileChangeStatus.get(fileName)) {
                        currentFileStatus.put(fileName, true);
                    } else {
                        // Set status for files not in the map to "not changed"
                        currentFileStatus.put(fileName, false);
                    }
                }
            }
        }

        // Iterate through the current status and remove missing files
        currentFileStatus.keySet().removeIf(fileName -> !new File(folderPath, fileName).exists());

        // Replace the file status map with the updated status
        fileChangeStatus.clear();
        fileChangeStatus.putAll(currentFileStatus);
    }


    // Displays the file status for all files in the folder
    public void showFileStatus() {
        for (Map.Entry<String, Boolean> entry : fileChangeStatus.entrySet()) {
            String fileName = entry.getKey();
            String status = entry.getValue() ? "changed" : "not changed";
            System.out.println(fileName + " - " + status);
        }
    }
}