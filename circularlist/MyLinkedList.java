package circularlist;

public class MyLinkedList<E> {
	private Node<E> head;
	private int numElements;

	/**
	 * Constructor for MyLinkedList.
	 */
	public MyLinkedList() {
		head = null;
		numElements = 0;
	}

	/**
	 * Determines whether a list is empty.
	 * 
	 * @return true if the list is empty, otherwise false
	 */
	public boolean isEmpty() {
		return (head == null);
	}

	/**
	 * Determines the length of a list.
	 * 
	 * @return the number of elements in the list
	 */
	public int size() {
		return numElements;
	}

	/**
	 * Removes all elements from the list.
	 */
	public void clear() {
		head = null;
		numElements = 0;
	}

	/**
	 * Adds a new item to the end of the list.
	 * 
	 * @param item
	 *            the new item to add
	 */
	public void add(E item) {
		// if the list is empty, simply add a new node with item as its data
		if (isEmpty()) {
			head = new Node<E>(item, null);
		} else {
			Node<E> current = head;

			// eventually sets current to be the last node
			while (current.getLink() != null) {
				current = current.getLink();
			}
			
			// adds a new node after current with item as its data content
			current.addNodeAfter(item);
		}
		numElements++;
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
	public void add(int index, E item) throws IndexOutOfBoundsException {
		int tempCount = 0;
		Node<E> current = head;

		if (index < 0) {
			throw new IndexOutOfBoundsException("Cannot be a negative index.");
		}

		if (index <= numElements) {
			index %= numElements;
		}

		// eventually sets current to be the node before the node at index
		while (tempCount < index - 1) {
			current = current.getLink();
			tempCount++;
		}

		// since we only looped until index - 1, we can use addNodeAfter and
		// insert the new Node at the correct spot
		current.addNodeAfter(item);
		numElements++;
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
	public E remove(int index) throws IndexOutOfBoundsException {
		E removed = null;
		Node<E> current;
		Node<E> prev = head;
		int tempCount = 0;

		if (index < 0) {
			throw new IndexOutOfBoundsException("Cannot be a negative index.");
		}
		
		if (index <= numElements) {
			index %= numElements;
		}

		// eventually sets prev to be the node before the one at index
		while (tempCount < index - 1) {
			prev = prev.getLink();
			tempCount++;
		}

		// sets current to be the node after prev
		current = prev.getLink();

		// sets the value of current to the variable removed
		removed = current.getData();

		// this bypasses the current link by setting prev's link to the node
		// after current. Current will be removed with the garbage collector.
		prev.setLink(current.getLink());

		numElements--;
		return removed;
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
	public E get(int index) throws IndexOutOfBoundsException {
		Node<E> current = head;
		int tempCount = 0;

		if (index <= numElements) {
			index %= numElements;
		}
		
		if (index < 0) {
			throw new IndexOutOfBoundsException("Cannot be a negative index.");
		}

		if (isEmpty()) {
			throw new IndexOutOfBoundsException("The list is empty.");
		}

		// eventually sets current to be the node at index
		while (tempCount < index) {
			current = current.getLink();
			tempCount++;
		}
		
		return current.getData();
	}
}