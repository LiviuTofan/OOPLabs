public class Main {
    public static void main(String[] args) {
        // Replace this with the path to your folder
        String folderPath = "C:\\Users\\liviu\\IdeaProjects\\OOPLabs\\Lab3\\Changes";

        FolderReader.listFilesInFolder(folderPath);

        TxtFiles.analyzeTextFilesInDirectory(folderPath);

        ImageSize.printImageSizesInFolder(folderPath);

    }
}
