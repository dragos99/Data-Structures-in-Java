/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {

	private CircularlyLinkedList<E> queue = new CircularlyLinkedList<E>();

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		queue.addLast(e);
	}

	@Override
	public E first() {
		return queue.get(0);
	}

	@Override
	public E dequeue() {
		return queue.removeFirst();
	}

	public void rotate() {
		queue.rotate();
	}

	public static void main(String[] args) {
		LinkedCircularQueue<Integer> q = new  LinkedCircularQueue<Integer>();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		q.enqueue(4);
		System.out.println(q.size());
		System.out.println(q.first());

		while (!q.isEmpty()) q.dequeue();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.rotate();
		while (!q.isEmpty()) System.out.println(q.dequeue());

		/**
		 * Expected output:
		 * 1 2 3 1 4
		 * 2 3 4 1
		 */
	}

}
