import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class FileStatus {
    // A map to store the change status of files (whether they have been changed or not)
    private final Map<String, Boolean> fileChangeStatus = new HashMap<>();

    // Constructor that initializes the fileChangeStatus based on files in the given folder
    public FileStatus(String folderPath) {
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

    // Marks a file as changed in the fileChangeStatus map
    public void fileChanged(String fileName) {
        if (fileChangeStatus.containsKey(fileName)) {
            fileChangeStatus.put(fileName, true);
        }
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
