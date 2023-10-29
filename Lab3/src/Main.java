public class Main {
    public static void main(String[] args) {
        // Define the folder path you want to monitor for changes
        String folderPath = "C:\\Users\\liviu\\IdeaProjects\\OOPLabs\\Lab3\\Changes";

        // Create a FileStatus object to track file change status
        FileStatus fileStatus = new FileStatus(folderPath);

        // Create a thread for DetectChanges and pass the FileStatus object
        Thread detectChangesThread = new Thread(() -> {
            // Initialize DetectChanges with the folder path and FileStatus object
            DetectChanges detector = new DetectChanges(folderPath, fileStatus);
            // Start monitoring for file changes
            detector.startMonitoring();
        });

        // Start the DetectChanges thread to monitor file changes
        detectChangesThread.start();

        // Create MenuManager with the folder path and the FileStatus object
        MenuManager menuManager = new MenuManager(folderPath, fileStatus);

        // Example usage of the MenuManager with options
        menuManager.processOptions();
    }
}
