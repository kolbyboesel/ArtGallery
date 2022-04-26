////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title:    P09 Art Gallery
//Course:   CS 300 Spring 2022
//
//Author:   Kolby Boesel
//Email:    kboesel@wisc.edu
//Lecturer: Mouna Kacem
//

//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//Persons:   None
//Online Sources:  Piazza
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class implements the tester methods for the ArtworkGallery BST
 *
 */
import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * This class checks the correctness of the implementation of the methods
 * defined in the class ArtworkGallery.
 * 
 * @author Kolby Boesel
 *
 */

public class ArtGalleryTester {

	/**
	 * Checks the correctness of the implementation of both compareTo() and equals()
	 * methods defined in the Artwork class.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testArtworkCompareToEquals() {
		Artwork art1 = new Artwork("Mona Lisa", 1994, 100.0);
		Artwork art2 = new Artwork("Mona Lisa", 1994, 100.0);
		Artwork art3 = new Artwork("Mona", 1996, 110);

		boolean checkTrue = false;
		boolean checkFalse = false;

		if (art1.compareTo(art3) == -1) {
			checkFalse = true;
		}
		if (art1.compareTo(art2) == 0) {
			checkTrue = true;
		}

		if (checkTrue == true && checkFalse == true && art1.equals(art2) == true && art1.equals(art3) == false) {
			return true;
		}
		return false; // Default return statement added to resolve compiler errors
	}

	/**
	 * Checks the correctness of the implementation of both addArtwork() and
	 * toString() methods implemented in the ArtworkGallery class. This unit test
	 * considers at least the following scenarios. (1) Create a new empty
	 * ArtworkGallery, and check that its size is 0, it is empty, and that its
	 * string representation is an empty string "". (2) try adding one artwork and
	 * then check that the addArtwork() method call returns true, the tree is not
	 * empty, its size is 1, and the .toString() called on the tree returns the
	 * expected output. (3) Try adding another artwork which is smaller that the
	 * artwork at the root, (4) Try adding a third artwork which is greater than the
	 * one at the root, (5) Try adding at least two further artwork such that one
	 * must be added at the left subtree, and the other at the right subtree. For
	 * all the above scenarios, and more, double check each time that size() method
	 * returns the expected value, the add method call returns true, and that the
	 * .toString() method returns the expected string representation of the contents
	 * of the binary search tree in an increasing order from the smallest to the
	 * greatest artwork with respect to year, cost, and then name. (6) Try adding a
	 * artwork already stored in the tree. Make sure that the addArtwork() method
	 * call returned false, and that the size of the tree did not change.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testAddArtworkToStringSize() {
		Artwork art1 = new Artwork("Mona Lisa 3", 1994, 100.0);
		Artwork art3 = new Artwork("Mona Lisa 2", 1992, 90.0);
		Artwork art2 = new Artwork("Mona Lisa 4", 1996, 110.0);
		Artwork art4 = new Artwork("Mona Lisa 1", 1990, 85.0);
		Artwork art5 = new Artwork("Mona Lisa 5", 1999, 115.0);
		ArtGallery testGal = new ArtGallery();
		boolean check1 = false;
		boolean check2 = false;
		boolean check3 = false;
		boolean check4 = false;
		boolean check5 = false;
		boolean check6 = false;

		if (testGal.size() == 0 && testGal.toString().trim().equals("")) {
			check1 = true;
		}

		if (testGal.addArtwork(art1) == true && testGal.size() == 1
				&& testGal.toString().trim().equals("[(Name: Mona Lisa 3) (Year: 1994) (Cost: $100.0)]")) {

			check2 = true;
		}

		if (testGal.addArtwork(art3) == true && testGal.size() == 2
				&& testGal.toString().trim().equals("[(Name: Mona Lisa 2) (Year: 1992) (Cost: $90.0)]" + "\n"
						+ "[(Name: Mona Lisa 3) (Year: 1994) (Cost: $100.0)]")) {
			check3 = true;
		}

		if (testGal.addArtwork(art2) == true && testGal.size() == 3
				&& testGal.toString().trim()
				.equals("[(Name: Mona Lisa 2) (Year: 1992) (Cost: $90.0)]" + "\n"
						+ "[(Name: Mona Lisa 3) (Year: 1994) (Cost: $100.0)]" + "\n"
						+ "[(Name: Mona Lisa 4) (Year: 1996) (Cost: $110.0)]")) {
			check4 = true;
		}

		if (testGal.addArtwork(art4) == true && testGal.addArtwork(art5) == true && testGal.size() == 5
				&& testGal.toString().trim()
				.equals("[(Name: Mona Lisa 1) (Year: 1990) (Cost: $85.0)]" + "\n"
						+ "[(Name: Mona Lisa 2) (Year: 1992) (Cost: $90.0)]" + "\n"
						+ "[(Name: Mona Lisa 3) (Year: 1994) (Cost: $100.0)]" + "\n"
						+ "[(Name: Mona Lisa 4) (Year: 1996) (Cost: $110.0)]" + "\n"
						+ "[(Name: Mona Lisa 5) (Year: 1999) (Cost: $115.0)]")) {
			check5 = true;
		}

		if (testGal.addArtwork(art1) == false) {
			check6 = true;
		}

		if (check1 == true && check2 == true && check3 == true && check4 == true && check5 == true && check6 == true) {
			return true;
		}

		return false; // Default return statement added to resolve compiler errors
	}

	/**
	 * This method checks mainly for the correctness of the ArtworkGallery.lookup()
	 * method. It must consider at least the following test scenarios. (1) Create a
	 * new ArtworkGallery. Then, check that calling the lookup() method on an empty
	 * ArtworkGallery returns false. (2) Consider a ArtworkGallery of height 3 which
	 * lookup at least 5 artwork. Then, try to call lookup() method to search for
	 * the artwork having a match at the root of the tree. (3) Then, search for a
	 * artwork at the right and left subtrees at different levels considering
	 * successful and unsuccessful search operations. Make sure that the lookup()
	 * method returns the expected output for every method call.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testLookup() {
		ArtGallery testGal = new ArtGallery();
		boolean check1 = false;
		boolean check2 = false;
		boolean check3 = false;
		Artwork art1 = new Artwork("Mona Lisa 3", 1994, 100.0);
		Artwork art3 = new Artwork("Mona Lisa 2", 1992, 90.0);
		Artwork art2 = new Artwork("Mona Lisa 4", 1996, 110.0);
		Artwork art4 = new Artwork("Mona Lisa 1", 1990, 85.0);
		Artwork art5 = new Artwork("Mona Lisa 5", 1999, 115.0);

		if (testGal.lookup("Mona Lisa", 1996, 100.0) == false) {
			check1 = true;
		}

		testGal.addArtwork(art1);
		testGal.addArtwork(art2);
		testGal.addArtwork(art3);
		testGal.addArtwork(art4);
		testGal.addArtwork(art5);

		if (testGal.lookup("Mona Lisa 3", 1994, 100.0)) {
			check2 = true;
		}

		if (testGal.lookup("Mona Lisa 1", 1990, 85.0) == true && testGal.lookup("Mona Lisa 5", 1999, 115.0) == true
				&& testGal.lookup("Mona Lisa 4", 1996, 110.0) == true
				&& testGal.lookup("Mona Lisa 6", 2001, 150.0) == false) {
			check3 = true;
		}

		if (check1 == true && check2 == true && check3 == true) {
			return true;
		}
		return false; // Default return statement added to resolve compiler errors
	}

	/**
	 * Checks for the correctness of ArtworkGallery.height() method. This test must
	 * consider several scenarios such as, (1) ensures that the height of an empty
	 * artwork tree is zero. (2) ensures that the height of a tree which consists of
	 * only one node is 1. (3) ensures that the height of a ArtworkGallery with the
	 * following structure for instance, is 4.
	 * 
	 * (*) / \ (*) (*) \ / \ (*) (*) (*) / (*)
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testHeight() {
		ArtGallery testGal = new ArtGallery();
		boolean check1 = false;
		boolean check2 = false;
		boolean check3 = false;
		Artwork art1 = new Artwork("Mona Lisa 3", 1994, 100.0);
		Artwork art3 = new Artwork("Mona Lisa 2", 1992, 90.0);
		Artwork art2 = new Artwork("Mona Lisa 4", 1996, 110.0);
		Artwork art4 = new Artwork("Mona Lisa 1", 1990, 85.0);
		Artwork art5 = new Artwork("Mona Lisa 5", 1999, 115.0);
		if (testGal.height() == 0) {
			check1 = true;
		}

		testGal.addArtwork(art1);

		if (testGal.height() == 1) {
			check2 = true;
		}

		testGal.addArtwork(art2);
		testGal.addArtwork(art3);
		testGal.addArtwork(art4);
		testGal.addArtwork(art5);
		if (testGal.height() == 3) {
			check3 = true;
		}
		if (check1 == true && check2 == true && check3 == true) {
			return true;
		}

		return false; // Default return statement added to resolve compiler errors
	}

	/**
	 * Checks for the correctness of ArtworkGallery.getBestArtwork() method.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testGetBestArtwork() {
		ArtGallery testGal = new ArtGallery();

		Artwork art1 = new Artwork("Mona Lisa 3", 1994, 100.0);
		Artwork art3 = new Artwork("Mona Lisa 2", 1992, 90.0);
		Artwork art2 = new Artwork("Mona Lisa 4", 1996, 110.0);
		Artwork art4 = new Artwork("Mona Lisa 1", 1990, 85.0);
		Artwork art5 = new Artwork("Mona Lisa 5", 1999, 115.0);
		testGal.addArtwork(art1);
		testGal.addArtwork(art2);
		testGal.addArtwork(art3);
		testGal.addArtwork(art4);
		testGal.addArtwork(art5);

		if (testGal.getBestArtwork() == art5) {
			return true;
		}

		return false; // Default return statement added to resolve compiler errors
	}

	/**
	 * Checks for the correctness of ArtworkGallery.lookupAll() method. This test
	 * must consider at least 3 test scenarios. (1) Ensures that the
	 * ArtworkGallery.lookupAll() method returns an empty arraylist when called on
	 * an empty tree. (2) Ensures that the ArtworkGallery.lookupAll() method returns
	 * an array list which contains all the artwork satisfying the search criteria
	 * of year and cost, when called on a non empty artwork tree with one match, and
	 * two matches and more. Vary your search criteria such that the lookupAll()
	 * method must check in left and right subtrees. (3) Ensures that the
	 * ArtworkGallery.lookupAll() method returns an empty arraylist when called on a
	 * non-empty artwork tree with no search results found.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testLookupAll() {

		ArtGallery testGal = new ArtGallery();
		boolean check1 = false;
		boolean check2 = false;
		boolean check3 = false;
		boolean check4 = false;

		Artwork art1 = new Artwork("Mona Lisa 3", 1994, 100.0);
		Artwork art2 = new Artwork("Mona Lisa 4", 1994, 100.0);
		ArrayList<Artwork> expected = new ArrayList<Artwork>();

		if (testGal.lookupAll(1990, 100.0).equals(expected)) {
			check1 = true;
		}

		testGal.addArtwork(art1);
		expected.add(art1);

		if (testGal.lookupAll(1994, 100.0).equals(expected)) {
			check2 = true;
		}

		testGal.addArtwork(art2);
		expected.add(art2);

		if (testGal.lookupAll(1994, 100.0).equals(expected)) {
			check3 = true;
		}

		expected.clear();
		if (testGal.lookupAll(1995, 200.0).equals(expected)) {
			check4 = true;
		}

		if (check1 == true && check2 == true && check3 == true && check4 == true) {
			return true;
		}

		return false; // Default return statement added to resolve compiler errors
	}

	/**
	 * Checks for the correctness of ArtworkGallery.buyArtwork() method. This test
	 * must consider at least 3 test scenarios. (1) Buying artwork that is at leaf
	 * node (2) Buying artwork at non-leaf node (3) ensures that the
	 * ArtworkGallery.buyArtwork() method throws a NoSuchElementException when
	 * called on an artwork that is not present in the BST
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testBuyArtwork() {
		ArtGallery testGal = new ArtGallery();
		boolean check1 = false;
		boolean check2 = false;
		boolean check3 = false;
		boolean check4 = false;

		Artwork art1 = new Artwork("Mona Lisa 3", 1994, 100.0);
		Artwork art3 = new Artwork("Mona Lisa 2", 1992, 90.0);
		Artwork art2 = new Artwork("Mona Lisa 4", 1996, 110.0);
		Artwork art4 = new Artwork("Mona Lisa 1", 1990, 85.0);
		Artwork art5 = new Artwork("Mona Lisa 5", 1999, 115.0);
		testGal.addArtwork(art1);
		testGal.addArtwork(art2);
		testGal.addArtwork(art3);
		testGal.addArtwork(art4);
		testGal.addArtwork(art5);

		testGal.buyArtwork("Mona Lisa 1", 1990, 85.0);

		if (testGal.toString().trim()
				.equals("[(Name: Mona Lisa 2) (Year: 1992) (Cost: $90.0)]" + "\n"
						+ "[(Name: Mona Lisa 3) (Year: 1994) (Cost: $100.0)]" + "\n"
						+ "[(Name: Mona Lisa 4) (Year: 1996) (Cost: $110.0)]" + "\n"
						+ "[(Name: Mona Lisa 5) (Year: 1999) (Cost: $115.0)]")) {
			check1 = true;
		}

		testGal.buyArtwork("Mona Lisa 4", 1996, 110.0);

		if (testGal.toString().trim()
				.equals("[(Name: Mona Lisa 2) (Year: 1992) (Cost: $90.0)]" + "\n"
						+ "[(Name: Mona Lisa 3) (Year: 1994) (Cost: $100.0)]" + "\n"
						+ "[(Name: Mona Lisa 5) (Year: 1999) (Cost: $115.0)]")) {
			check2 = true;
		}

		testGal.buyArtwork("Mona Lisa 3", 1994, 100.0);
		if (testGal.toString().trim().equals("[(Name: Mona Lisa 2) (Year: 1992) (Cost: $90.0)]" + "\n"
				+ "[(Name: Mona Lisa 5) (Year: 1999) (Cost: $115.0)]")) {
			check3 = true;
		}

		try {
			testGal.buyArtwork("Mona Lisa 4", 1996, 110.0);
		} catch (NoSuchElementException e) {
			check4 = true;
		}

		if (check1 == true && check2 == true && check3 == true && check4 == true) {
			return true;
		}

		return false; // Default return statement added to resolve compiler errors
	}

	/**
	 * Returns false if any of the tester methods defined in this tester class
	 * fails.
	 * 
	 * @return false if any of the tester methods defined in this tester class
	 *         fails, and true if all tests pass
	 */
	public static boolean runAllTests() {
		if (testArtworkCompareToEquals() == true && testAddArtworkToStringSize() == true && testLookup() == true
				&& testHeight() == true && testGetBestArtwork() == true && testLookupAll() == true
				&& testBuyArtwork() == true) {
			return true;
		}
		return false; // Default return statement added to resolve compiler errors
	}

	/**
	 * Calls the test methods
	 * 
	 * @param args input arguments if any
	 */
	public static void main(String[] args) {
		System.out.println("testArworkCompareToEquals(): " + testArtworkCompareToEquals());
		System.out.println("testAddArtworkToStringSize(): " + testAddArtworkToStringSize());
		System.out.println("testLookup(): " + testLookup());
		System.out.println("testHeight(): " + testHeight());
		System.out.println("testGetBestArtwork(): " + testGetBestArtwork());
		System.out.println("testLookupAll(): " + testLookupAll());
		System.out.println("testBuyArtwork(): " + testBuyArtwork());
		System.out.println("runAllTests(): " + runAllTests());
	}

}