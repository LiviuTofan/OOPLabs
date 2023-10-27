public class MenuManager {
    public static void main(String[] args) {
        MenuManager menuManager = new MenuManager();

        // Example usage of the MenuManager with options
        menuManager.processOption("commit");
        menuManager.processOption("info myfile.txt");
        menuManager.processOption("status");
    }

    public void processOption(String option) {
        String[] parts = option.split(" ");

        switch (parts[0]) {
            case "commit":
                commit();
                break;
            case "info":
                if (parts.length == 2) {
                    info(parts[1]);
                } else {
                    System.out.println("Usage: info <filename>");
                }
                break;
            case "status":
                status();
                break;
            default:
                System.out.println("Invalid option: " + option);
        }
    }

    public void commit() {
        System.out.println("Committing changes...");
    }

    public void info(String filename) {
        System.out.println("Getting info for file: " + filename);
    }

    public void status() {
        System.out.println("Checking status...");
    }
}
