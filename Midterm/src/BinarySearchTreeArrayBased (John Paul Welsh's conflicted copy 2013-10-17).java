import java.util.Arrays;

/**
 * Implementation for an array-based Binary Search Tree.
 * @author John Paul Welsh
 */
public class BinarySearchTreeArrayBased<T extends Comparable<? super T>>
		implements BinarySearchTree<T> {

	// the list of items from input, to be stored into an array according
	// to the rules of a Binary Search Tree
	protected T[] items;
	// the list of items to be searched through with findKth, sorted
	// according to an in-order traversal of the tree
	protected T[] sortedItems;

	/**
	 * Constructor for the BinarySearchTreeArrayBased, which takes in an array
	 * of type T[] that contains the values to be organized into a BST.
	 * @param items the array of values
	 */
	public BinarySearchTreeArrayBased(T[] items) {
		// input is copied to member variable
		this.items = items;
		// for now, unsorted member variable is copied to this variable
		this.sortedItems = this.items;
		// arrange the elements in items according to the rules of a BST
		fillBSTAB(items);
		// sort the elements in sortedItems according to an in-order traversal
		inOrderTraversalSort(0, 0);
	}

	/**
	 * Private method used to fill the array according to the
	 * rules of binary search trees. This assumes that the
	 * incoming values make up a complete tree and that there
	 * are no repeat values.
	 * @param items the array of items to take from when filling
	 */
	private void fillBSTAB(T[] items) {
		@SuppressWarnings("unchecked")
		T[] copy = (T[]) new Comparable[2 * items.length + 1];

		int indexItems = 0;
		int indexCopy = 0;
		// go through the elements in items
		while (indexItems < items.length) {

			/* first case: the element in copy you're looking at
			   has not been filled with a value from items yet */
			if (copy[indexCopy] == null) {
				copy[indexCopy] = items[indexItems];
			}

			/* second case: the current element in items is smaller
			than the current element in copy */
			else if (items[indexItems].compareTo(copy[indexCopy]) < 0) {
				// if the current element already has two children
				if (hasTwoChildren(copy, indexCopy)) {
					// we need to move to the left child of that element
					indexCopy *= 2;
					indexCopy += 1;
				}
				// put it in element 2i+1 where i is our current index
				copy[2 * indexCopy + 1] = items[indexItems];
			}

			/* third case: the current element in items is larger
			   than the current element in copy */
			else {
				// if the current element already has two children
				if (hasTwoChildren(copy, indexCopy)) {
					// we need to move to the right child of that element
					indexCopy *= 2;
					indexCopy += 2;
				}
				// put it in element 2i+2 where i is our current index
				copy[2 * indexCopy + 2] = items[indexItems];
			}
			// move to the next element in items
			indexItems++;
		}
		// let 'items' reference the newly filled array
		items = copy;
	}
	
	/**
	 * Private method used in fillBSTAB to determine whether the current node
	 * (root) has two children.
	 * @param copy the list of items currently being filled
	 * @param indexCopy the index that represents the root for the children
	 *        we are checking for
	 * @return true if the root has exactly two children, false otherwise
	 */
	private boolean hasTwoChildren(T[] copy, int indexCopy) {
		return (copy[2*indexCopy+1] != null && copy[2*indexCopy+2] != null);
	}

	/**
	 * Method to check if the BST contains the given key.
	 * @param key the key to search for
	 * @return true if the BST contains key, false otherwise
	 */
	public boolean contains(T key) {
		for (int i = 0; i < items.length; i++) {
			if (items[i].compareTo(key) == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to find the kth item in sorted order from the BST. i.e., the item
	 * with index k when listed in sorted order. So, the first item is item 0,
	 * the second is item 1, etc.
	 * @param k the index of the item to find
	 * @return the kth item
	 */
	public T findKth(int k) {
		for (int tempIndex = 0; tempIndex < sortedItems.length; tempIndex++) {
			if (tempIndex == k) {
				return sortedItems[tempIndex];
			}
		}
		return null; // if the index was not found/out of bounds
	}
	
	/**
	 * Method to perform an in-order traversal on items recursively, copying
	 * each element to the sortedItems array as we go, which will be the array
	 * we search through when we call findKth.
	 * @param currentRoot the element in items we are current treating as the
	 *        root, so we can find its children
	 * @param indexSorted the array index for our array sortedItems
	 */
	private void inOrderTraversalSort(int currentRoot, int indexSorted) {		
		// stops the recursion if we go out of bounds or reach a null element
		if (currentRoot > items.length - 1 || items[currentRoot] == null) {
			return;
		}
		
		// do left subtree
		inOrderTraversalSort(2*currentRoot+1);
		
		// do current root
		sortedItems[indexSorted] = items[currentRoot];
		indexSorted++;
		System.out.println(items[currentRoot]);
		
		// do right subtree
		inOrderTraversalSort(2*currentRoot+2);
	}
}