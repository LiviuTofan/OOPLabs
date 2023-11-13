public interface LimitedStackQueue<T> {
    void push(T element);
    T pop();
    T element();
    boolean isEmpty();
    boolean isFull();
}
