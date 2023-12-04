import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

public class BasicQueue<T> implements Queues<T> {
    private static final int MAX_SIZE = 5;
    private Queue<T> queue = new LinkedList<>();

    @Override
    public void enqueue(T item) {
        if (isFull()) {
            System.out.println("Queue is full, cannot enqueue.");
        } else {
            queue.offer(item);
        }
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty, cannot dequeue.");
            return null;
        } else {
            return queue.poll();
        }
    }
    @Override
    public T peek() {
        return queue.peek();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean isFull() {
        return queue.size() == MAX_SIZE;
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.print("Queue elements: ");
            Iterator<T> iterator = queue.iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + ", ");
            }
            System.out.println();
        }
    }
}
