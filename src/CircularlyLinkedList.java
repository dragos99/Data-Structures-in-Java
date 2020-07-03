import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private Node head = null;
    private int size = 0;

    private Node getNode(int i) {
		if (i < 0 || i >= size) return null;

		Node curr = head;
		while (i-- != 0) curr = curr.next;

		return curr;
	}

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E get(int i) {
        if (i < 0 || i >= size) return null;

        Node curr = head;
        while (i-- != 0) curr = curr.next;

        return (E) curr.data;
    }

    public void add(int i, E e) {
        if (i < 0 || i > size) throw new IndexOutOfBoundsException();

        if (i == 0) {
            addFirst(e);
        } else if (i == size) {
            addLast(e);
        } else {
            Node prev = head;
            while (i-- != 1) prev = prev.next;

            prev.next = new Node(e, prev.next);
            size++;
        }
    }

    public E remove(int i) {
		if (i < 0 || i > size) return null;

		if (i == 0) {
			return removeFirst();
		} else if (i == size - 1) {
			return removeLast();
		} else {
			Node prev = head;
			while (i-- != 1) prev = prev.next;
			prev.next = prev.next.next;

			size--;
			return (E) prev.next.data;
		}
    }

    public E removeFirst() {
        if (isEmpty()) return null;

        E data = (E) head.data;
        Node tail = getNode(size - 1);

        assert tail != null;
        tail.next = head.next;
		head = head.next;

        size--;
        return data;
    }

    public E removeLast() {
        if (isEmpty()) return null;
        if (size == 1) return removeFirst();

        Node preTail = getNode(size - 2);
		Node tail = preTail.next;
        preTail.next = head;

        size--;
        return (E) tail.data;
    }

    public void addFirst(E e) {
        head = new Node(e, head);
        size++;
    }

    public void addLast(E e) {
        if (isEmpty()) {
            addFirst(e);
        } else {
            Node newNode = new Node(e, head);
            Node tail = getNode(size - 1);

            assert tail != null;
            tail.next = newNode;
            size++;
        }
    }

    public void rotate() {
    	if (size <= 1) return;
		head = head.next;
    }

	public Iterator<E> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<E> {
		Node curr;
		boolean hasNext = true;

		public ListIterator() {
			curr = head;
		}
		public boolean hasNext() {
			return hasNext;
		}
		@Override
		public E next() {
			E res = (E) curr.data;
			curr = curr.next;
			if (curr == head) hasNext = false;
			return res;
		}
	}

	@Override
	public String toString() {
		String s = "";
		Node node = head;

		for (int i = 0; i < size; ++i) {
			s += node.data.toString() + " ";
			node = node.next;
		}

		return s;
	}

    private static class Node<E> {
        E data = null;
        Node next = null;

        Node(E d, Node n) {
            data = d;
            next = n;
        }
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
		System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
		System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        ll.add(2, -1);
		System.out.println(ll);

		ll.remove(2);
		System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }


}
