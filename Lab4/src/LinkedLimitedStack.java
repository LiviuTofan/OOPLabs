import java.util.NoSuchElementException;
public class LinkedLimitedStack<T> implements LimitedStackQueue<T> {
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private final int capacity;
    private Node<T> top;
    private int size;

    public LinkedLimitedStack(int capacity) {
        this.capacity = capacity;
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(T element) {
        if (isFull()) {
            throw new IllegalStateException("Stack is full. Cannot push more elements.");
        }
        Node<T> newNode = new Node<>(element);
        newNode.next = top;
        top = newNode;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot pop elements.");
        }
        T element = top.data;
        top = top.next;
        size--;
        return element;
    }

    @Override
    public T element() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty. No element to return.");
        }
        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }
}
