public class Main {
    public static void main(String[] args) {
        // Define the folder path
        String folderPath = "C:\\Users\\liviu\\IdeaProjects\\OOPLabs\\Lab3\\Changes";
        FileStatus fileStatus = new FileStatus(folderPath);

        // Create a thread for DetectChanges and pass the FileStatus object
        Thread detectChangesThread = new Thread(() -> {
            DetectChanges detector = new DetectChanges(folderPath, fileStatus);
            detector.startMonitoring();
        });

        detectChangesThread.start();
        MenuManager menuManager = new MenuManager(folderPath, fileStatus);
        menuManager.processOptions();
    }
}
