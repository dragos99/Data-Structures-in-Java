import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a map using an unsorted table.
 */

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
	public static void main(String[] args) {
		Map<String, Integer> map = new UnsortedTableMap<>();
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);

		System.out.println(map.get("one"));
		System.out.println(map.get("two"));
		System.out.println(map.get("three"));
		map.remove("two");
		System.out.println("Removed two");
		System.out.println(map.get("one"));
		System.out.println(map.get("two"));
		System.out.println(map.get("three"));
	}

	/** Underlying storage for the map of entries. */
	private ArrayList<MapEntry<K, V>> table;

	/** Constructs an initially empty map. */
	public UnsortedTableMap() {
		table = new ArrayList<>();
	}

	// private utility
	/** Returns the index of an entry with equal key, or -1 if none found. */
	private int findIndex(K key) {
		for (int i = 0; i < table.size(); ++i) {
			if (table.get(i).getKey().equals(key)) return i;
		}
		return -1;
	}

	// public methods
	/**
	 * Returns the number of entries in the map.
	 * 
	 * @return number of entries in the map
	 */
	@Override
	public int size() {
		return table.size();
	}

	/**
	 * Returns the value associated with the specified key, or null if no such entry
	 * exists.
	 * 
	 * @param key the key whose associated value is to be returned
	 * @return the associated value, or null if no such entry exists
	 */
	@Override
	public V get(K key) {
		int index = findIndex(key);

		if (index == -1) return null;
		return table.get(index).getValue();
	}

	/**
	 * Associates the given value with the given key. If an entry with the key was
	 * already in the map, this replaced the previous value with the new one and
	 * returns the old value. Otherwise, a new entry is added and null is returned.
	 * 
	 * @param key   key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the previous value associated with the key (or null, if no such
	 *         entry)
	 */
	@Override
	public V put(K key, V value) {
		V old = get(key);
		if (old != null) {
			table.get(findIndex(key)).setValue(value);
		} else {
			table.add(new MapEntry<>(key, value));
		}
		return old;
	}

	/**
	 * Removes the entry with the specified key, if present, and returns its value.
	 * Otherwise does nothing and returns null.
	 * 
	 * @param key the key whose entry is to be removed from the map
	 * @return the previous value associated with the removed key, or null if no
	 *         such entry exists
	 */
	@Override
	public V remove(K key) {
		int index = findIndex(key);

		if (index != -1) {
			MapEntry<K, V> e = table.remove(findIndex(key));
			return e.getValue();
		}

		return null;
	}

	// ---------------- nested EntryIterator class ----------------
	private class EntryIterator implements Iterator<Entry<K, V>> {
		private int j = 0;

		public boolean hasNext() {
			return j < table.size();
		}

		public Entry<K, V> next() {
			if (j == table.size())
				throw new NoSuchElementException("No further entries");
			return table.get(j++);
		}

		public void remove() {
			throw new UnsupportedOperationException("remove not supported");
		}
	} // ----------- end of nested EntryIterator class -----------

	// ---------------- nested EntryIterable class ----------------
	private class EntryIterable implements Iterable<Entry<K, V>> {
		public Iterator<Entry<K, V>> iterator() {
			return new EntryIterator();
		}
	} // ----------- end of nested EntryIterable class -----------

	/**
	 * Returns an iterable collection of all key-value entries of the map.
	 *
	 * @return iterable collection of the map's entries
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return new EntryIterable();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (Entry<K, V> e : table) {
			sb.append(e.getValue().toString());
			if (e != table.get(table.size() - 1)) sb.append(", ");
		}
		sb.append(']');
		return sb.toString();
	}
}
