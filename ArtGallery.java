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
//Online Sources:  Piazza, zyBooks
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class models the Artwork Gallery implemented as a binary search tree.
 * The search criteria include the year of creation of the artwork, the name of
 * the artwork and its cost.
 * 
 * @author Kolby Boesel
 *
 */
public class ArtGallery {

	private BSTNode<Artwork> root; // root node of the artwork catalog BST
	private int size; // size of the artwork catalog tree

	/**
	 * Checks whether this binary search tree (BST) is empty
	 * 
	 * @return true if this ArtworkGallery is empty, false otherwise
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false; // Default return statement added to resolve compiler errors
	}

	/**
	 * Returns the number of artwork pieces stored in this BST.
	 * 
	 * @return the size of this ArtworkGallery
	 */
	public int size() {
		return size; // Default return statement added to resolve compiler errors
	}

	/**
	 * Checks whether this ArtworkGallery contains a Artwork given its name, year,
	 * and cost.
	 * 
	 * @param name name of the Artwork to search
	 * @param year year of creation of the Artwork to search
	 * @param cost cost of the Artwork to search
	 * @return true if there is a match with this Artwork in this BST, and false
	 *         otherwise
	 */
	public boolean lookup(String name, int year, double cost) {
		if (root == null) {
			return false;
		}
		Artwork tempArt = new Artwork(name, year, cost);

		return lookupHelper(tempArt, root); // Default return statement added to resolve compiler errors

	}

	/**
	 * Recursive helper method to search whether there is a match with a given
	 * Artwork in the subtree rooted at current
	 * 
	 * @param target  a reference to a Artwork we are searching for a match in the
	 *                BST rooted at current.
	 * @param current "root" of the subtree we are checking whether it contains a
	 *                match to target.
	 * @return true if match found and false otherwise
	 */
	protected static boolean lookupHelper(Artwork target, BSTNode<Artwork> current) {
		if (target.equals(current.getData())) {
			return true;
		}
		if (target.compareTo(current.getData()) < 0) {
			if (current.getLeft() == null) {
				return false;
			}
			return lookupHelper(target, current.getLeft());
		} else {
			if (current.getRight() == null) {
				return false;
			}
			return lookupHelper(target, current.getRight());
		}

	}

	/**
	 * Adds a new artwork piece to this ArtworkGallery
	 * 
	 * @param newArtwork a new Artwork to add to this BST (gallery of artworks).
	 * @return true if the newArtwork was successfully added to this gallery, and
	 *         returns false if there is a match with this Artwork already stored in
	 *         gallery.
	 * @throws NullPointerException if newArtwork is null
	 */
	public boolean addArtwork(Artwork newArtwork) {
		if (newArtwork == null) {
			throw new NullPointerException();
		}

		if (lookup(newArtwork.getName(), newArtwork.getYear(), newArtwork.getCost()) == true) {
			return false;
		}

		if (isEmpty() == true) {
			root = new BSTNode<Artwork>(newArtwork);
			size += 1;
			return true;
		}
		size += 1;
		return addArtworkHelper(newArtwork, root); // Default return statement added to resolve compiler errors
	}

	/**
	 * Recursive helper method to add a new Artwork to an ArtworkGallery rooted at
	 * current.
	 * 
	 * @param current    The "root" of the subtree we are inserting new Artwork
	 *                   into.
	 * @param newArtwork The Artwork to be added to a BST rooted at current.
	 * @return true if the newArtwork was successfully added to this ArtworkGallery,
	 *         false if a match with newArtwork is already present in the subtree
	 *         rooted at current.
	 */
	protected static boolean addArtworkHelper(Artwork newArtwork, BSTNode<Artwork> current) {
		BSTNode<Artwork> toAdd = new BSTNode<Artwork>(newArtwork);
		if (newArtwork.compareTo(current.getData()) < 0) {
			if (current.getLeft() == null) {
				current.setLeft(toAdd);
				return true;
			}
			addArtworkHelper(newArtwork, current.getLeft());
			return true;
		} else {
			if (current.getRight() == null) {
				current.setRight(toAdd);
				return true;
			}
			addArtworkHelper(newArtwork, current.getRight());
			return true;

		}

	}

	/**
	 * Gets the recent best Artwork in this BST (meaning the largest artwork in this
	 * gallery)
	 * 
	 * @return the best (largest) Artwork (the most recent, highest cost artwork) in
	 *         this ArtworkGallery, and null if this tree is empty.
	 */
	public Artwork getBestArtwork() {
		Artwork bestArt = null;
		BSTNode<Artwork> current = root;
		while (current.getRight() != null) {
			current = current.getRight();
		}

		bestArt = current.getData();

		return bestArt; // Default return statement added to resolve compiler errors
	}

	/**
	 * Returns a String representation of all the artwork stored within this BST in
	 * the increasing order of year, separated by a newline "\n". For instance
	 * 
	 * "[(Name: Stars, Artist1) (Year: 1988) (Cost: 300)]" + "\n" + "[(Name: Sky,
	 * Artist1) (Year: 2003) (Cost: 550)]" + "\n"
	 * 
	 * @return a String representation of all the artwork stored within this BST
	 *         sorted in an increasing order with respect to the result of
	 *         Artwork.compareTo() method (year, cost, name). Returns an empty
	 *         string "" if this BST is empty.
	 */
	@Override
	public String toString() {
		return toStringHelper(root);
	}

	/**
	 * Recursive helper method which returns a String representation of the BST
	 * rooted at current. An example of the String representation of the contents of
	 * a ArtworkGallery is provided in the description of the above toString()
	 * method.
	 * 
	 * @param current reference to the current Artwork within this BST (root of a
	 *                subtree)
	 * @return a String representation of all the artworks stored in the sub-tree
	 *         rooted at current in increasing order with respect to the result of
	 *         Artwork.compareTo() method (year, cost, name). Returns an empty
	 *         String "" if current is null.
	 */
	protected static String toStringHelper(BSTNode<Artwork> current) {
		String toString = "";
		if (current == null) {
			return "";
		}

		toString += toStringHelper(current.getLeft());
		toString += current.getData() + "\n";
		toString += toStringHelper(current.getRight());

		return toString; // Default return statement added to resolve compiler errors
	}

	/**
	 * Computes and returns the height of this BST, counting the number of NODES
	 * from root to the deepest leaf.
	 * 
	 * @return the height of this Binary Search Tree
	 */
	public int height() {
		if (root == null)
			return 0;

		return heightHelper(root); // Default return statement added to resolve compiler errors
	}

	/**
	 * Recursive helper method that computes the height of the subtree rooted at
	 * current counting the number of nodes and NOT the number of edges from current
	 * to the deepest leaf
	 * 
	 * @param current pointer to the current BSTNode within a ArtworkGallery (root
	 *                of a subtree)
	 * @return height of the subtree rooted at current
	 */
	protected static int heightHelper(BSTNode<Artwork> current) {
		int leftHeight = 0;
		int rightHeight = 0;

		if (current.getLeft() != null) {
			leftHeight = heightHelper(current.getLeft());
		}
		if (current.getRight() != null) {
			leftHeight = heightHelper(current.getLeft());
		}

		return 1 + Math.max(leftHeight, rightHeight);
	}

	/**
	 * Search for all artwork objects created on a given year and have a maximum
	 * cost value.
	 * 
	 * @param year creation year of artwork
	 * @param cost the maximum cost we would like to search for a artwork
	 * @return a list of all the artwork objects whose year equals our lookup year
	 *         key and maximum cost. If no artwork satisfies the lookup query, this
	 *         method returns an empty arraylist
	 */
	public ArrayList<Artwork> lookupAll(int year, double cost) {

		return lookupAllHelper(year, cost, root); // Default return statement added to resolve compiler errors
	}

	/**
	 * Recursive helper method to lookup the list of artworks given their year of
	 * creation and a maximum value of cost
	 * 
	 * @param year    the year we would like to search for a artwork
	 * @param cost    the maximum cost we would like to search for a artwork
	 * @param current "root" of the subtree we are looking for a match to find
	 *                within it.
	 * @return a list of all the artwork objects whose year equals our lookup year
	 *         key and maximum cost stored in the subtree rooted at current. If no
	 *         artwork satisfies the lookup query, this method returns an empty
	 *         arraylist
	 */

	protected static ArrayList<Artwork> lookupAllHelper(int year, double cost, BSTNode<Artwork> current) {
		ArrayList<Artwork> artwork = new ArrayList<Artwork>();

		if (current == null) {
			return artwork;
		}

		artwork.addAll(lookupAllHelper(year, cost, current.getLeft()));
		if (year == current.getData().getYear() && cost == current.getData().getCost()) {
			artwork.add(current.getData());
		}
		artwork.addAll(lookupAllHelper(year, cost, current.getRight()));

		return artwork; // Default return statement added to resolve compiler errors
	}

	/**
	 * Buy an artwork with the specified name, year and cost. In terms of BST
	 * operation, this is equivalent to finding the specific node and deleting it
	 * from the tree
	 * 
	 * @param name name of the artwork, artist
	 * @param year creation year of artwork
	 * @throws a NoSuchElementException with a descriptive error message if there is
	 *           no Artwork found with the buying criteria
	 */

	public void buyArtwork(String name, int year, double cost) {
		Artwork artwork = new Artwork(name, year, cost);
		root = buyArtworkHelper(artwork, root);
		size--;
	}

	/**
	 * Recursive helper method to buy artwork given the name, year and cost. In
	 * terms of BST operation, this is equivalent to finding the specific node and
	 * deleting it from the tree
	 * 
	 * @param target  a reference to a Artwork we are searching to remove in the BST
	 *                rooted at current.
	 * @param current "root" of the sub3tree we are checking whether it contains a
	 *                match to target.
	 * @return the new "root" of the subtree we are checking after trying to remove
	 *         target
	 * @throws a NoSuchElementException with a descriptive error message if there is
	 *           no Artwork found with the buying criteria in the BST rooted at
	 *           current
	 */
	protected static BSTNode<Artwork> buyArtworkHelper(Artwork target, BSTNode<Artwork> current) {
		if (current == null) {
			throw new NoSuchElementException("Error: No artwork found with the buying criteria");
		}
		if (target.compareTo(current.getData()) < 0) {
			current.setLeft(buyArtworkHelper(target, current.getLeft()));
		} else if (target.compareTo(current.getData()) > 0) {
			current.setRight(buyArtworkHelper(target, current.getRight()));

		}

		else if (target.compareTo(current.getData()) == 0) {

			if (current.getLeft() == null && current.getRight() == null) {
				return null;
			}

			else if (current.getLeft() != null && current.getRight() == null) {
				return current.getLeft();
			}

			else if (current.getLeft() == null && current.getRight() != null) {
				return current.getRight();

			}

			else {
				BSTNode<Artwork> successor = new BSTNode<Artwork>(getSuccessor(current));
				successor.setLeft(current.getLeft());
				successor.setRight(current.getRight());
				return successor;

			}

		}

		return current;

	}

	/**
	 * Helper method to find the successor of a node while performing a delete
	 * operation (buyArtwork) The successor is defined as the smallest key in the
	 * right subtree. We assume by default that node is not null
	 * 
	 * @param node node whose successor is to be found in the tree
	 * @return return the key of the successor node
	 */

	protected static Artwork getSuccessor(BSTNode<Artwork> node) {
		Artwork successor = null;
		if (node.getRight() != null) {
			BSTNode<Artwork> temp = node.getRight();

			while (temp.getLeft() != null) {
				temp = temp.getLeft();
			}

			successor = temp.getData();
			buyArtworkHelper(temp.getData(), node);
		}

		return successor; // Default return statement added to resolve compiler errors
	}
}