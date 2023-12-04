import java.util.Scanner;

public class MenuManagerStack {

    public <T> void showStackMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose Implementation:");
            System.out.println("1. Array");
            System.out.println("2. LinkedList");
            System.out.println("3. BasicStack");
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
                        // Instantiate and use ArrayStack
                        Stacks<T> arrayStack = new ArrayStack<>();
                        performStackActions(arrayStack);
                        break;
                    case 2:
                        // Instantiate and use LinkedListStack
                        Stacks<T> linkedListStack = new LinkedListStack<>();
                        performStackActions(linkedListStack);
                        break;
                    case 3:
                        // Instantiate and use BasicStack
                        Stacks<T> basicStack = new BasicStack<>();
                        performStackActions(basicStack);
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

    private <T> void performStackActions(Stacks<T> stack) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printStackMethods();
            String action = scanner.next();

            if (action.equals("b")) {
                break; // Go back to the previous menu
            }

            try {
                System.out.println();
                switch (Integer.parseInt(action)) {
                    case 1:
                        System.out.print("Enter element to push:");
                        T element = stackType(scanner);
                        stack.push(element);
                        break;
                    case 2:
                        T poppedElement = stack.pop();
                        System.out.println("----------------------");
                        System.out.println("Popped element: " + poppedElement);
                        System.out.println("----------------------");
                        break;
                    case 3:
                        T peekedElement = stack.peek();
                        System.out.println("----------------------");
                        System.out.println("Peeked element: " + peekedElement);
                        System.out.println("----------------------");
                        break;
                    case 4:
                        boolean isEmpty = stack.isEmpty();
                        System.out.println("----------------------");
                        System.out.println("Stack is empty: " + isEmpty);
                        System.out.println("----------------------");
                        break;
                    case 5:
                        boolean isFull = stack.isFull();
                        System.out.println("----------------------");
                        System.out.println("Stack is full: " + isFull);
                        System.out.println("----------------------");
                        break;
                    case 6:
                        int stackSize = stack.size();
                        System.out.println("----------------------");
                        System.out.println("Stack size: " + stackSize);
                        System.out.println("----------------------");
                        break;
                    case 7:
                        System.out.println("----------------------");
                        stack.printStack();
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

    private void printStackMethods() {
        System.out.println("Choose one from the following methods:");
        System.out.println("1. Push");
        System.out.println("2. Pop");
        System.out.println("3. Peek");
        System.out.println("4. Check if Stack is empty");
        System.out.println("5. Check if Stack is full");
        System.out.println("6. Print Stack size");
        System.out.println("7. Print Stack elements");
        System.out.println("b - go back");
        System.out.print("Your option: ");
    }

    private <T> T stackType(Scanner scanner) {
        if (scanner.hasNextInt()) {
            return (T) Integer.valueOf(scanner.nextInt());
        } else if (scanner.hasNext()) {
            return (T) scanner.next();
        } else {
            return null;
        }
    }
}
