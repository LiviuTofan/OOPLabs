import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileStatus {
    private final String folderPath;
    private final Map<String, String> fileChangeStatus = new HashMap<>();
    private boolean committed = false;

    public FileStatus(String folderPath) {
        this.folderPath = folderPath;
        initializeFileChangeStatus(folderPath);
    }

    private void initializeFileChangeStatus(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    fileChangeStatus.put(fileName, "not changed"); // Initially, set all files as not changed
                }
            }
        }
    }
    public void commit() {
        committed = true;
    }

    public boolean isCommitted() {
        return committed;
    }
    public void fileChanged(String fileName) {
        fileChangeStatus.put(fileName, "changed");
    }

    public void fileAdded(String fileName) {
        fileChangeStatus.put(fileName, "added");
    }

    public void fileDeleted(String fileName) {
        fileChangeStatus.put(fileName, "deleted");
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void checkForNewFilesAndMissingFiles() {
        File folder = new File(getFolderPath());
        File[] files = folder.listFiles();

        if (files != null) {
            List<String> keysToRemove = new ArrayList<>();

            for (String fileName : fileChangeStatus.keySet()) {
                if (!fileExistsInFolder(fileName, files)) {
                    keysToRemove.add(fileName);
                }
            }

            for (String fileName : keysToRemove) {
                fileDeleted(fileName);
            }

            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (!fileChangeStatus.containsKey(fileName)) {
                        fileAdded(fileName);
                    }
                }
            }
        }
    }

    private boolean fileExistsInFolder(String fileName, File[] files) {
        for (File file : files) {
            if (file.isFile() && file.getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    public void resetAllFileStatus() {
        for (String fileName : new ArrayList<>(fileChangeStatus.keySet())) {
            if (!fileChangeStatus.get(fileName).equals("deleted")) {
                fileChangeStatus.put(fileName, "not changed");
            } else {
                fileChangeStatus.remove(fileName);
            }
        }
    }

    public void showFileStatus() {
        for (Map.Entry<String, String> entry : fileChangeStatus.entrySet()) {
            String fileName = entry.getKey();
            String status = entry.getValue();

            if (status != null) {
                System.out.println(fileName + " - " + status);
            }
        }
    }
}
