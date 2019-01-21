package bst;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bst.BinarySearchTree;

public class BinarySearchTreeTest {

	private BinarySearchTree<Integer> intBST;

	@Before
	public void setUp() throws Exception {
		intBST = new BinarySearchTree<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		intBST = null;
	}

	@Test
	public void newTreeEmpty() {
		assertEquals("Size should be 0", 0, intBST.size());
		assertEquals("Height should be 0", 0, intBST.size());
	}

	@Test
	public void addToEmpty() {
		assertTrue("Should be possible to add to empty tree", intBST.add(1));
	}

	@Test
	public void addDuplicate() {
		intBST.add(1);
		assertFalse("Should not be able to add duplicates", intBST.add(1));
	}

	@Test
	public void testPrintTree() {
		for (int i = 0; i < 4; i++) {
			intBST.add(i);
		}
		intBST.printTree();
		System.out.println();
	}

	@Test
	public void testSize() {
		for (int i = 0; i < 4; i++) {
			intBST.add(i);
		}
		assertEquals("Should return the same value", 4, intBST.size());
	}
	
	@Test
	public void testHeight() {
		for(int i = 0; i < 4; i++) {
			intBST.add(i);
		}
		assertEquals("Should return the same value", 4, intBST.height());
	}
}
