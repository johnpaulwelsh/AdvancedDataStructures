package circularlist;

public class CircularListArrayBased<E> implements CircularList<E> {
	private E[] arr;
	private int numElements;

	/**
	 * Default constructor for CircularListArrayBased with a default size of 0
	 * elements.
	 */
	@SuppressWarnings("unchecked")
	public CircularListArrayBased() {
		arr = (E[]) new Object[0];
		numElements = 0;
	}

	/**
	 * Constructor for CircularListArrayBased with a user-entered size.
	 * 
	 * @param physicalSize
	 *            the initial size of the array arr
	 */
	@SuppressWarnings("unchecked")
	public CircularListArrayBased(int physicalSize) {
		arr = (E[]) new Object[physicalSize];
		numElements = 0;
	}

	/**
	 * Determines whether a list is empty.
	 * 
	 * @return true if the list is empty, otherwise false
	 */
	@Override
	public boolean isEmpty() {

		if (size() == 0) {
			return true;
		}
		
		return (arr[0] == null);
	}

	/**
	 * Determines the length of a list.
	 * 
	 * @return the number of elements in the list without wrapping
	 */
	@Override
	public int size() {
		return arr.length;
	}
	
	/**
	 * Removes all elements from the list.
	 */
	@Override
	public void clear() {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = null;
		}
	}

	/**
	 * Adds a new item to the end of the list.
	 * 
	 * @param item
	 *            the new item to add
	 * @return true if the list was modified
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean add(E item) {
		// the list is not empty
		if (!isEmpty()) {

			// the list is not empty, and it's also full (# elements = # spaces)
			if (numElements == arr.length) {
				expandAndAdd(item);
				return true;
				
			// the list is not empty, but it's not full yet either
			} else {
				// numElements counts the number of elements, so it is also a
				// digit representing the next unfilled element
				arr[numElements] = item;
				return true;
			}

		// the list is empty
		} else {

			// the list is empty and it also has no elements at all
			if (numElements == 0) {
				// remake the array to have one element
				arr = (E[]) new Object[1];
			}
			
			// assign the item to the new index
			arr[numElements] = item;
			return true;
		}
	}

	/**
	 * Adds a new item to the list at position index. Other items are shifted,
	 * not overwritten.
	 * 
	 * @param index
	 *            where to add the new item
	 * @param item
	 *            the new item to add
	 * @throws IndexOutOfBoundsException
	 *             if index is negative
	 */
	@Override
	public void add(int index, E item) throws IndexOutOfBoundsException {
		
		if (index < 0) {
			throw new IndexOutOfBoundsException("Cannot be a negative index.");
		}

		if (index >= arr.length) {
			index %= arr.length;
		}
		
		if (!isEmpty() && (numElements == arr.length)) {
			expandAndAdd(item, index);
			numElements++;
		}
	}

	/**
	 * Remove and return the item at the given index.
	 * 
	 * @param index
	 *            the position of the item to remove
	 * @return the item that was removed
	 * @throws IndexOutOfBoundsException
	 *             if index is negative
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Cannot be a negative index.");
		}

		if (index >= arr.length) {
			index %= arr.length;
		}
		return expandAndRemove(index);		
	}

	/**
	 * Retrieve the item at the given index without altering the list.
	 * 
	 * @param index
	 *            the position of the item to retrieve
	 * @return the item at position index
	 * @throws IndexOutOfBoundsException
	 *             if index is negative or list is empty
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Cannot be a negative index.");
		}

		if (this.isEmpty()) {
			throw new IndexOutOfBoundsException("List cannot be empty.");
		}

		return arr[index % this.size()];
	}

	/**
	 * Generate an iterator for the list. The iterator should visit the items in
	 * a circular pattern. As long as the list is not empty, it should never
	 * stop.
	 */
	@Override
	public CircularIterator<E> iterator() {
		return new CircularIterator<E>(this);
	}
	
	@SuppressWarnings("unchecked")
	private void expandAndAdd(E item) {
		// make a temporary array with one extra element
		E[] copy = (E[]) new Object[arr.length + 1];

		// copy over the elements that were there before
		int i;
		for (i = 0; i < arr.length - 1; i++) {
			copy[i] = arr[i];
		}

		// move to the next element (the first new one) and add in the
		// new item
		i++;
		copy[i] = item;
		numElements++;

		// finally, assign the edited array to the original reference
		arr = copy;
	}
	
	@SuppressWarnings("unchecked")
	private void expandAndAdd(E item, int index) {
		// make a temporary array with one extra element
		E[] copy = (E[]) new Object[arr.length + 1];

		// copy over the elements prior to the one you're inserting at	
		for (int i = 0; i < index; i++) {
				copy[i] = arr[i];
		}

		// drop in the new element at index
		copy[index] = item;

		// start again after the new element, and copy everything else in
		// (shifted over one to the right to make room for the new element)
		for (int j = index + 1; j < arr.length + 1; j++) {
			copy[j] = arr[j - 1];
		}

		// finally, assign the edited array to the original reference
		arr = copy;
	}

	@SuppressWarnings("unchecked")
	private E expandAndRemove(int index) {
		E[] copy = (E[]) new Object[arr.length];

		// copy over the elements prior to the one you're removing from
		for (int i = 0; i < index; i++) {
			copy[i] = arr[i];
		}

		// remove the element
		E removed = arr[index];

		// start again after the new element, and copy everything else in
		// (shifted over one to the left since the removed element is gone)
		for (int j = index + 1; j < arr.length - 1; j++) {
			copy[j] = arr[j + 1];
		}

		// finally, assign the edited array to the original reference
		arr = copy;
		
		numElements--;
		
		return removed;
	}
}