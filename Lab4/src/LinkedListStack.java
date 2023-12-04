import java.util.LinkedList;

public class LinkedListStack<T> implements Stacks<T> {
    private LinkedList<T> stackList = new LinkedList<>();
    private static final int MAX_SIZE = 5;

    @Override
    public void push(T item) {
        if (isFull()) {
            System.out.println("Stack is full, cannot push.");
        } else {
            stackList.push(item);
        }
    }

    @Override
    public T pop() {
        return stackList.isEmpty() ? null : stackList.pop();
    }

    @Override
    public T peek() {
        return stackList.isEmpty() ? null : stackList.peek();
    }

    @Override
    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    @Override
    public boolean isFull() {
        return stackList.size() == MAX_SIZE;
    }

    @Override
    public int size() {
        return stackList.size();
    }

    @Override
    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            System.out.print("Stack elements: ");
            for (T element : stackList) {
                System.out.print(element + ", ");
            }
            System.out.println();
        }
    }
}
