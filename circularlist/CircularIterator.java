package circularlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularIterator<E> implements Iterator<E> {
	private CircularList<E> cl;
	private int logCount;

	public CircularIterator(CircularList<E> cl) {
		this.cl = cl;
		this.logCount = 0;
	}

	/**
	 * Determines whether the CircularList has any more elements to iterate
	 * through.
	 * 
	 * @return true if getting the element at the next index returns a value
	 *         other than null
	 */
	public boolean hasNext() {
		return !(cl.isEmpty());
	}

	/**
	 * Returns the next element in the CircularList.
	 * @return the value gotten from the get method at index logCount
	 */
	public E next() {
		if (hasNext()) {
			return cl.get(logCount++);
		} else {
			throw new NoSuchElementException("There are no more elements.");
		}
	}

	/**
	 * Not implemented.
	 */
	public void remove() { }
}