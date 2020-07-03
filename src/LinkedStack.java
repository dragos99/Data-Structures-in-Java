public class LinkedStack<E> implements Stack<E> {

	private SinglyLinkedList<E> stk = new SinglyLinkedList<E>();

	public static void main(String[] args) {
		LinkedStack<Integer> s = new LinkedStack<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		System.out.println(s.top());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.size());
	}

	@Override
	public int size() {
		return stk.size();
	}

	@Override
	public boolean isEmpty() {
		return stk.isEmpty();
	}

	@Override
	public void push(E e) {
		stk.addFirst(e);
	}

	@Override
	public E top() {
		if (isEmpty()) return null;
		return stk.get(0);
	}

	@Override
	public E pop() {
		if (isEmpty()) return null;
		return stk.removeFirst();
	}

	public String toString() {
		return stk.toString();
	}

}
