public class Main {
    public static void main(String[] args) {
        LimitedStackQueue<String> arrayStack = new ArrayLimitedStack<>(5);
        LimitedStackQueue<Integer> linkedStack = new LinkedLimitedStack<>(5);

        // Test the stack operations
        arrayStack.push("One");
        arrayStack.push("Two");
        arrayStack.push("Three");

        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);

        System.out.println("Array Stack Top: " + arrayStack.element());
        System.out.println("Linked Stack Top: " + linkedStack.element());

        arrayStack.pop();
        linkedStack.pop();

        System.out.println("Array Stack Top after pop: " + arrayStack.element());
        System.out.println("Linked Stack Top after pop: " + linkedStack.element());
    }
}
