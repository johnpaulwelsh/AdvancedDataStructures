/**
 * Interface for a simple Binary Search Tree
 * 
 * @author Benjamin Carle
 */
public interface BinarySearchTree<T extends Comparable<? super T>> {

	/**
	 * Method to check if the BST contains the given key.
	 * 
	 * @param key
	 *            the key to search for
	 * @return true if the BST contains key, false otherwise
	 */
	public boolean contains(T key);

	/**
	 * Method to find the kth item in sorted order from the BST. i.e., the item
	 * with index k when listed in sorted order. So, the first item is item 0,
	 * the second is item 1, etc.
	 * 
	 * @param k
	 *            the index of the item to find.
	 * @return the kth item
	 */
	public T findKth(int k);
}