public class LinkedDeque<E> implements Deque<E> {

	SinglyLinkedList<E> deque = new SinglyLinkedList<E>();

	@Override
	public int size() {
		return deque.size();
	}

	@Override
	public boolean isEmpty() {
		return deque.isEmpty();
	}

	@Override
	public E first() {
		return deque.get(0);
	}

	@Override
	public E last() {
		return deque.get(deque.size() - 1);
	}

	@Override
	public void addFirst(E e) {
		deque.addFirst(e);
	}

	@Override
	public void addLast(E e) {
		deque.addLast(e);
	}

	@Override
	public E removeFirst() {
		return deque.removeFirst();
	}

	@Override
	public E removeLast() {
		return deque.removeLast();
	}

	public static void main(String[] args) {
		LinkedDeque<Integer> d = new LinkedDeque<Integer>();
		d.addFirst(1);
		d.addLast(2);
		d.addLast(3);
		d.addFirst(0);
		System.out.println(d.last());
		System.out.println(d.first());
		while (!d.isEmpty()) System.out.println(d.removeFirst());

		/**
		 * Expected output:
		 * 3 0 0 1 2 3
		 */
	}
}
