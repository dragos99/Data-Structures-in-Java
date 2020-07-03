public class ArrayQueue<E> implements Queue<E> {
    private int size;
    private int capacity;
    private Object[] arr;
    private int head;
    private int tail;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.arr = new Object[capacity];

        head = 0;
        tail = -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public void enqueue(E e) {
        if (isFull()) throw new IllegalStateException("Queue is full");
        tail++;
        if (tail == capacity) tail = 0;

        arr[tail] = e;
		size++;
    }

    @Override
    public E first() {
        if (isEmpty()) return null;
        return (E) arr[head];
    }

    @Override
    public E dequeue() {
        if (isEmpty()) return null;
        E e = (E) arr[head];
        head++;
        if (head == capacity) head = 0;
        size--;
        return e;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> q = new ArrayQueue<Integer>(3);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        q.enqueue(4);
        System.out.println(q.size());
        System.out.println(q.first());

		q.enqueue(5);
		System.out.println(q.dequeue());
		q.enqueue(6);
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		q.enqueue(7);
		System.out.println(q.dequeue());
		q.enqueue(7);
		q.enqueue(7);
		q.enqueue(7);
		q.enqueue(7);

        /**
         * Expected output:
		 * 1
		 * 2
		 * 3
		 * 1
		 * 4
		 * 4
		 * 5
		 * 6
		 * null
		 * 7
		 * queue is full exception
         */

    }


}
