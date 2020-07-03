import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {
    private class Node<E> {
        E data;
        Node prev;
        Node next;

        Node() {
            data = null;
            prev = null;
            next = null;
        }

        Node(E data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node header;
    private Node trailer;
    private int size;

    public DoublyLinkedList() {
        header = new Node();
        trailer = new Node();

        header.next = trailer;
        trailer.prev = header;
    }

    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node newNode = new Node(e, predecessor, successor);
        predecessor.next = newNode;
        successor.prev = newNode;
        size++;
    }

    private Node getNode(int i) {
        if (i < 0 || i >= size)
            return null;

        Node node = header.next;
        while (i-- != 0)
            node = node.next;

        return node;
    }

    public E first() {
        return get(0);
    }

    public E last() {
        return get(size - 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if (i < 0 || i >= size) return null;
        return (E) getNode(i).data;
    }

    @Override
    public void add(int i, E e) {
        if (i < 0 || i > size)
            throw new IndexOutOfBoundsException();

        if (i == 0) {
            addFirst(e);
        } else if (i == size) {
            addLast(e);
        } else {
            // find node at position i - 1
            Node predecessor = getNode(i - 1);
            addBetween(e, predecessor, predecessor.next);
        }
    }

    @Override
    public E remove(int i) {
        Node node = getNode(i);
        node.prev.next = node.next;
        node.next.prev = node.prev;

        size--;
        return (E) node.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public void addFirst(E e) {
        addBetween(e, header, header.next);
    }

    @Override
    public void addLast(E e) {
        addBetween(e, trailer.prev, trailer);
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder("[");

        Node node = header.next;
        while (node != trailer) {
            strBuilder.append(node.data.toString());
            if (node != trailer.prev) {
                strBuilder.append(", ");
            }
            node = node.next;
        }

        strBuilder.append("]");
        return strBuilder.toString();
    }

    private class ListIterator implements Iterator<E> {
        Node curr;

        public ListIterator() {
            curr = header.next;
        }

        @Override
        public boolean hasNext() {
            return curr != trailer;
        }

        @Override
        public E next() {
            E res = (E) curr.data;
            curr = curr.next;
            return res;
        }

        public boolean hasPrevious() {
            return curr.prev != header;
        }

        public E previous() {
            E res = (E) curr.data;
            curr = curr.prev;
            return res;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);
        
        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }

}
