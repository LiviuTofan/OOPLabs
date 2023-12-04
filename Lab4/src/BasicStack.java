import java.util.Stack;

public class BasicStack<T> implements Stacks<T> {
    private static final int MAX_SIZE = 5;
    private Stack<T> stack = new Stack<>();

    @Override
    public void push(T item) {
        if (isFull()) {
            System.out.println("Stack is full, cannot push.");
        } else {
            stack.push(item);
        }
    }

    @Override
    public T pop() {
        return stack.isEmpty() ? null : stack.pop();
    }

    @Override
    public T peek() {
        return stack.isEmpty() ? null : stack.peek();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public boolean isFull() {
        return stack.size() == MAX_SIZE;
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            System.out.print("Stack elements: ");
            for (int i = size() - 1; i >= 0; i--) {
                System.out.print(stack.elementAt(i) + ", ");
            }
            System.out.println();
        }
    }
}
