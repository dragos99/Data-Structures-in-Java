public class ArrayStack<E> implements Stack<E> {

	private Object[] stk;
	private int capacity;
	private int size;

	public static void main(String[] args) {
		ArrayStack<Integer> s = new ArrayStack<Integer>(128);
		s.push(1);
		s.push(2);
		s.push(3);
		System.out.println(s.top());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.size());
	}

	public ArrayStack(int capacity) {
		stk = new Object[capacity];
		this.capacity = capacity;
	}

	private boolean isFull() {
		return size == capacity;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public void push(E e) {
		if (isFull()) throw new IllegalStateException("Stack is full");
		stk[size++] = e;
	}

	@Override
	public E top() {
		if (isEmpty()) return null;
		return (E) stk[size - 1];
	}

	@Override
	public E pop() {
		if (isEmpty()) return null;

		size--;
		E e = (E) stk[size];
		stk[size] = null;

		return e;
	}

}
