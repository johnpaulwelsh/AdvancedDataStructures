/**
 * @author Benjamin Carle
 */

public class TestBSTAB {

	public static void main(String[] args) {
		String[] sa = { "M", "J", "R", "C", "K", "P" };
		BinarySearchTree<String> bst = new BinarySearchTreeArrayBased<String>(sa);
		
		System.out.println("contains(P) = " + bst.contains("P")); // true
		System.out.println("contains(O) = " + bst.contains("O")); // false
		
		for (int i = 0; i < sa.length; i++)
			System.out.println("findKth(" + i + ") = " + bst.findKth(i));
		// Should output C J K M P R
	}
}