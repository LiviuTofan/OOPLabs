public interface Queues<T> {
    void enqueue(T item);
    T dequeue();
    T peek();
    boolean isEmpty();
    boolean isFull();
    int size();
    void printQueue();
}
