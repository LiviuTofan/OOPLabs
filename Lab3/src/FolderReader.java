                    import java.io.File;

                    public class FolderReader {
                        public static void listFilesInFolder(String folderPath) {
                            File folder = new File(folderPath);

                            // Check if the specified path exists and is a directory
                            if (folder.exists() && folder.isDirectory()) {
                                File[] files = folder.listFiles();

                                // List all files in the folder
                                if (files != null) {
                                    for (File file : files) {
                                        if (file.isFile()) {
                                            System.out.println(file.getName());
                                        }
                                    }
                                } else {
                                    System.out.println("The folder is empty.");
                                }
                            } else {
                                System.out.println("Invalid folder path or the folder doesn't exist.");
                            }
                        }
                    }
