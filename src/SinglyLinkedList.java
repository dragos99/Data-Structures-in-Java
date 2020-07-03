import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {
    public static class Node<E> {
        E data;
        Node next;

        Node(E data) {
            this.data = data;
        }

        Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;


    public E get(int i) {
        if (i < 0 || i >= size) return null;

        Node node = head;
        while (i-- > 0) node = node.next;

        return (E) node.data;
    }

    public E first() {
        return get(0);
    }

    public E last() {
        return get(size - 1);
    }

    public void add(int i, E e) {
        if (i < 0 || i > size) throw new IllegalArgumentException("Index out of bounds.");

        if (i == 0) {
            addFirst(e);
        } else if (i == size) {
            addLast(e);
        } else {
            // add somewhere in the middle
            Node prev = head;

            // find the node after which to add the new one
            while (--i > 0) prev = prev.next;

            prev.next = new Node(e, prev.next);

            size++;
        }
    }

    public E remove(int i) {
        // input check
        if (size == 0) throw new RuntimeException("Can't remove from empty list");
        if (i >= size || i < 0) throw new IllegalArgumentException("Index out of bounds");

        if (i == 0) return removeFirst();
        if (i == size - 1) return removeLast();

        // it's somewhere in between
        Node target = head;
        Node preTarget = null;

        while (i-- > 0) {
            preTarget = target;
            target = target.next;
        }

        // skip it in the list and let the GC obliterate it
        preTarget.next = target.next;

        size--;
        return (E) target.data;
    }

    public Iterator<E> iterator() {
        return new ListIterator();
    }

    public int size() {
        return size;
    }

    public E removeFirst() {
        if (size == 0) throw new RuntimeException("Cant remove from empty list.");
        E removedData = (E) head.data;

        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
        }

        size--;
        return removedData;
    }

    public E removeLast() {
        if (size == 0) throw new RuntimeException("Cant remove from empty list.");
        if (size == 1) return removeFirst();

        E removedData = (E) tail.data;

        // find the second to last node
        Node preTail = head;
        while (preTail.next != tail) preTail = preTail.next;

        // update tail and remove last element
        tail = preTail;
        tail.next = null;

        size--;
        return removedData;
    }

    public void addFirst(E e) {
        head = new Node(e, head);
        size++;

        // also update tail if the list was empty before
        if (tail == null) {
            tail = head;
        }
    }

    public void addLast(E e) {
        if (size == 0) {
            addFirst(e);
        } else {
            tail.next = new Node(e);
            tail = tail.next;
            size++;
        }
    }

    @Override
    public String toString() {
        String s = "[";
        Node node = head;

        while (node != null) {
            s += node.data.toString();
            node = node.next;
            if (node != null) s += ", ";
        }

        s += "]";

        return s;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    private class ListIterator implements Iterator<E> {
        Node curr;
        public ListIterator() {
            curr = head;
        }
        public boolean hasNext() {
            return curr != null;
        }
        @Override
		public E next() {
            E res = (E) curr.data;
            curr = curr.next;
            return res;
        }
    }


    public static void main(String[] args) {
		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

		SinglyLinkedList<String> sll = new SinglyLinkedList<String>();

		for (String s : alphabet) {
			sll.addFirst(s);
			sll.addLast(s);
		}
		System.out.println(sll.toString());

		sll.removeFirst();
		System.out.println(sll.toString());

		sll.removeLast();
		System.out.println(sll.toString());

		sll.remove(2);
		System.out.println(sll.toString());

		sll.add(sll.size() - 2, "hi");
		System.out.println(sll);

		for (String s : sll) {
			System.out.print(s + ", ");
		}
    }
}
