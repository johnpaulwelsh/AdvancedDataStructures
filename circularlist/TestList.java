package circularlist;

/**
 * Class to test the circularlist package
 */
public class TestList {

	public static void main(String[] args) {
		// Create a CircularList of Strings
		CircularList<String> clstring = new CircularListArrayBased<String>();
		// Create a CircularList of integers
		CircularList<Double> cldouble = new CircularListReferenceBased<Double>();

		// ************************ BEGIN ARRAYBASED ***************************

		// Add elements for cls
		clstring.add("A");
		clstring.add("B");
		clstring.add("C");
		clstring.add("D");
		clstring.add("E");
		
		System.out.println(clstring.size());

		System.out.println("Our list of Strings looks like this: ");		
		System.out.print("{ ");
		int t = 1;
		for (String s : clstring) {
			System.out.print(s + " ");
			if (t++ > 25)
				break;
		}
		System.out.println("}");

		// ************************ END ARRAYBASED *****************************

		// ************************ BEGIN REFERENCEBASED ***********************

		// Add elements for cldouble
		cldouble.add(1.0);
		cldouble.add(3.0);
		cldouble.add(5.0);
		cldouble.add(7.0);
		cldouble.add(9.0);
		//      index, item
		//cli.add(1, 11.0);
		
		System.out.println(cldouble.size());
		System.out.println("Our list of doubles looks like this: ");
		System.out.print("{ ");
		int k = 1;
		for (double d : cldouble) {
			System.out.print(d + " ");
			if (k++ > 25)
				break;
		}
		System.out.println("}");
		
		
		// ************************ END REFERENCEBASED *************************
	}
}