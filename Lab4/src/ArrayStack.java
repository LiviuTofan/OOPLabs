public class ArrayStack<T> implements Stacks<T> {
    private Object[] array;
    private int top;
    private static final int MAX_SIZE = 5;

    public ArrayStack() {
        this.array = new Object[MAX_SIZE];
        this.top = -1;
    }

    @Override
    public void push(T item) {
        if (isFull()) {
            System.out.println("Stack is full. Cannot push.");
        } else {
            array[++top] = item;
        }
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return null;
        } else {
            T data = (T) array[top];
            array[top--] = null;
            return data;
        }
    }

    @Override
    public T peek() {
        return isEmpty() ? null : (T) array[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public boolean isFull() {
        return top == MAX_SIZE - 1;
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            System.out.print("Stack elements: ");
            for (int i = 0; i <= top; i++) {
                System.out.print(array[i] + ", ");
            }
            System.out.println();
        }
    }
}
