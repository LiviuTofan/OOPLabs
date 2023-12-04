import java.util.Scanner;

public class MenuManager {

    public <T> void showMainMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose Option:");
            System.out.println("1. Queue");
            System.out.println("e - exit");
            System.out.print("Your option: ");
            String option = scanner.next();
            System.out.println();

            if (option.equals("e")) {
                System.out.println("Exiting the program.");
                System.exit(0);
            }

            try {
                int selectedOption = Integer.parseInt(option);

                switch (selectedOption) {
                    case 1:
                        showQueueMenu();
                        break;
                    default:
                        System.out.println("Invalid input, please enter a valid option.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a valid option.");
            }
        }
    }

    private <T> void showQueueMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose Implementation:");
            System.out.println("1. Array");
            System.out.println("2. LinkedList");
            System.out.println("3. BasicQueue");
            System.out.println("b - go back");
            System.out.print("Your option: ");
            String implementationOption = scanner.next();
            System.out.println();

            if (implementationOption.equals("b")) {
                break; // Go back to the previous menu
            }

            try {
                int option = Integer.parseInt(implementationOption);

                switch (option) {
                    case 1:
                        // Instantiate and use ArrayQueue
                        Queues<T> arrayQueue = new ArrayQueue<>();
                        performQueueActions(arrayQueue);
                        break;
                    case 2:
                        // Instantiate and use LinkedListQueue
                        Queues<T> linkedListQueue = new LinkedListQueue<>();
                        performQueueActions(linkedListQueue);
                        break;
                    case 3:
                        // Instantiate and use BasicQueue
                        Queues<T> basicQueue = new BasicQueue<>();
                        performQueueActions(basicQueue);
                        break;
                    default:
                        System.out.println("Invalid input, please enter a valid option.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a valid option.");
            }
        }
    }

    private <T> void performQueueActions(Queues<T> queue) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printQueueMethods();
            String action = scanner.next();

            if (action.equals("b")) {
                break; // Go back to the previous menu
            }

            try {
                System.out.println();
                switch (Integer.parseInt(action)) {
                    case 1:
                        System.out.print("Enter element to enqueue:");
                        T element = queueType(scanner);
                        queue.enqueue(element);
                        break;
                    case 2:
                        T dequeuedElement = queue.dequeue();
                        System.out.println("----------------------");
                        System.out.println("Dequeued element: " + dequeuedElement);
                        System.out.println("----------------------");
                        break;
                    case 3:
                        T peekedElement = queue.peek();
                        System.out.println("----------------------");
                        System.out.println("Peeked element: " + peekedElement);
                        System.out.println("----------------------");
                        break;
                    case 4:
                        boolean isEmpty = queue.isEmpty();
                        System.out.println("----------------------");
                        System.out.println("Queue is empty: " + isEmpty);
                        System.out.println("----------------------");
                        break;
                    case 5:
                        boolean isFull = queue.isFull();
                        System.out.println("----------------------");
                        System.out.println("Queue is full: " + isFull);
                        System.out.println("----------------------");
                        break;
                    case 6:
                        int queueSize = queue.size();
                        System.out.println("----------------------");
                        System.out.println("Queue size: " + queueSize);
                        System.out.println("----------------------");
                        break;
                    case 7:
                        System.out.println("----------------------");
                        queue.printQueue();
                        System.out.println("----------------------");
                        break;
                    default:
                        System.out.println("Invalid action. Try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a valid option.");
            }
        }
    }

    private void printQueueMethods() {
        System.out.println("Choose one from the following methods:");
        System.out.println("1. Enqueue");
        System.out.println("2. Dequeue");
        System.out.println("3. Peek");
        System.out.println("4. Check if Queue is empty");
        System.out.println("5. Check if Queue is full");
        System.out.println("6. Print Queue size");
        System.out.println("7. Print Queue elements");
        System.out.println("b - go back");
        System.out.print("Your option: ");
    }

    private <T> T queueType(Scanner scanner) {
        if (scanner.hasNextInt()) {
            return (T) Integer.valueOf(scanner.nextInt());
        } else if (scanner.hasNext()) {
            return (T) scanner.next();
        } else {
            return null;
        }
    }
}
