

/*
 */

import java.util.ArrayList;
import java.util.Comparator;

/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    private ArrayList<PQEntry<K, V>> heap = new ArrayList<PQEntry<K, V>>();

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */
    public HeapPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the priority queue
     */
    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Creates a priority queue initialized with the respective
     * key-value pairs.  The two arrays given will be paired
     * element-by-element. They are presumed to have the same
     * length. (If not, entries will be created only up to the length of
     * the shorter of the arrays)
     *
     * @param keys   an array of the initial keys for the priority queue
     * @param values an array of the initial values for the priority queue
     */
    public HeapPriorityQueue(K[] keys, V[] values) {
        for (int i = 0; i < keys.length; ++i) {
            heap.add(new PQEntry<K, V>(keys[i], values[i]));
        }

        heapify();
    }

    // protected utilities
    protected int parent(int j) {
        if (j == 0) return 0;
        return (j - 1) / 2;
    }

    protected int left(int j) {
        return j * 2 + 1;
    }

    protected int right(int j) {
        return j * 2 + 2;
    }

    protected boolean hasLeft(int j) {
        return left(j) < size();
    }

    protected boolean hasRight(int j) {
        return right(j) < size();
    }

    /**
     * Exchanges the entries at indices i and j of the array list.
     */
    protected void swap(int i, int j) {
        PQEntry<K, V> tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    /**
     * Moves the entry at index j higher, if necessary, to restore the heap property.
     */
    protected void upheap(int i) {
        while (compare(heap.get(i), heap.get(parent(i))) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property.
     */
    protected void downheap(int i) {
        if (!hasLeft(i) && !hasRight(i)) return;
        int smallerChild = left(i);

        if (hasRight(i)) {
            // has both left and right
            int cmp = compare(heap.get(left(i)), heap.get(right(i)));
            smallerChild = cmp < 0 ? left(i) : right(i);
        }

        if (compare(heap.get(i), heap.get(smallerChild)) > 0) {
            swap(i, smallerChild);
            downheap(smallerChild);
        }
    }

    /**
     * Performs a bottom-up construction of the heap in linear time.
     */
    protected void heapify() {
        for (int i = parent(heap.size() - 1); i >= 0; --i) {
            downheap(i);
        }
    }

    // public methods

    /**
     * Returns the number of items in the priority queue.
     *
     * @return number of items
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     *
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry<K, V> min() {
        return (Entry<K, V>) heap.get(0);
    }

    /**
     * Inserts a key-value pair and return the entry created.
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        PQEntry<K, V> e = new PQEntry<K, V>(key, value);
        heap.add(e);
        upheap(heap.size() - 1);
        return e;
    }

    /**
     * Removes and returns an entry with minimal key.
     *
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry<K, V> removeMin() {
        Entry<K, V> e = min();
        heap.set(0, null);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
        return e;
    }

    /**
     * Used for debugging purposes only
     */
    private void sanityCheck() {
        for (int j = 0; j < heap.size(); j++) {
            int left = left(j);
            int right = right(j);
            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0)
                System.out.println("Invalid left child relationship");
            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0)
                System.out.println("Invalid right child relationship");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (Entry<K, V> e : heap) {
            sb.append(e.getValue().toString());
            if (e != heap.get(heap.size() - 1)) sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }
}

