public class LinkedListQueue<T> implements Queues<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;
    private static final int MAX_SIZE = 5;

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    @Override
    public void enqueue(T item) {
        if (isFull()) {
            System.out.println("Queue is full, cannot enqueue.");
        } else {
            Node<T> newNode = new Node<>(item);
            if (isEmpty()) {
                front = newNode;
                rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
            size++;
        }
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty, cannot dequeue.");
            return null;
        } else {
            T data = front.data;
            front = front.next;
            size--;
            return data;
        }
    }

    @Override
    public T peek() {
        return isEmpty() ? null : front.data;
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
            Node<T> current = front;
            while (current != null) {
                System.out.print(current.data + ", ");
                current = current.next;
            }
            System.out.println();
        }
    }
}