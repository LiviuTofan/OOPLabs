import java.util.NoSuchElementException;
public class ArrayLimitedStack<T> implements LimitedStackQueue<T> {
    private final int capacity;
    private final Object[] array;
    private int size;

    public ArrayLimitedStack(int capacity) {
        this.capacity = capacity;
        this.array = new Object[capacity];
        this.size = 0;
    }

    @Override
    public void push(T element) {
        if (isFull()) {
            throw new IllegalStateException("Stack is full. Cannot push more elements.");
        }
        array[size++] = element;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot pop elements.");
        }
        @SuppressWarnings("unchecked")
        T element = (T) array[--size];
        return element;
    }

    @Override
    public T element() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty. No element to return.");
        }
        @SuppressWarnings("unchecked")
        T element = (T) array[size - 1];
        return element;
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
