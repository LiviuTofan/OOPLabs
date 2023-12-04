public class ArrayQueue<T> implements Queues<T> {
    private Object[] array;
    private int front;
    private int rear;
    private static final int MAX_SIZE = 5;
    private int size;

    public ArrayQueue() {
        this.array = new Object[MAX_SIZE];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    @Override
    public void enqueue(T item) {
        if (isFull()) {
            System.out.println("Queue is full, cannot enqueue.");
        } else {
            rear = (rear + 1) % MAX_SIZE;
            array[rear] = item;
            size++;
        }
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty, cannot dequeue.");
            return null;
        } else {
            T data = (T) array[front];
            front = (front + 1) % MAX_SIZE;
            size--;
            return data;
        }
    }

    @Override
    public T peek() {
        return isEmpty() ? null : (T) array[front];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == MAX_SIZE;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.print("Queue elements: ");
            int current = front;
            for (int i = 0; i < size; i++) {
                System.out.print(array[current] + ", ");
                current = (current + 1) % MAX_SIZE;
            }
            System.out.println();
        }
    }
}