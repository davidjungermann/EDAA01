package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
	int size;
	private boolean b;

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		this.root = null;
		size = 0;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		root = insert(root, x);
		return b;
	}

	private BinaryNode<E> insert(BinaryNode<E> node, E x) {

		if (node == null) { // om trädet är tomt returnera en ny nod
			size++; // boolean = true, eftersom elementet sattes in, size på trädet sätts till 1
			b = true;
			return new BinaryNode<E>(x);
		}

		if (x.compareTo(node.element) < 0) { // om roten är mindre än x, gå vänster rekursivt
			node.left = insert(node.left, x);
			return node;
		} else if (x.compareTo(node.element) > 0) { // om roten är större än x, gå höger rekursivt
			node.right = insert(node.right, x);
			return node;
		} else { // om ingen sådan nod finns i trädet, returnerna noden
			b = false;
			return node;
		}
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return getHeight(root);
	}

	private int getHeight(BinaryNode<E> node) {
		if (node == null) {
			return 0;
		} else {
			int leftDepth = getHeight(node.left);
			int rightDepth = getHeight(node.right);

			return Math.max(leftDepth, rightDepth) + 1;
		}
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		inorder(root);
	}

	private void inorder(BinaryNode<E> node) {
		if (node != null) {
			inorder(node.left);
			System.out.println(node.element);
			inorder(node.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	@SuppressWarnings("unchecked")
	public void rebuild() {
		E[] a = (E[]) new Comparable[size];
		toArray(root, a, 0);
		root = buildTree(a, 0, a.length - 1);

	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index]. Returns the index of the last inserted element + 1 (the
	 * first empty position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {

		if (n != null) {
			index = toArray(n.left, a, index);
			a[index++] = n.element;
			index = toArray(n.right, a, index);
		}
		return index;
	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last]. Elements in the
	 * array a are assumed to be in ascending order. Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {

		if (first > last) {
			return null;
		}

		int root = first + ((last - first) / 2);
		BinaryNode<E> node = new BinaryNode<E>(a[root]);

		node.left = buildTree(a, first, root - 1);
		node.right = buildTree(a, root + 1, last);
		return node;
	}

	public static void main(String[] args) {

		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		BSTVisualizer vis = new BSTVisualizer("Binary tree", 300, 300);

		int[] v = { 20, 10, 30, 47, 42 };

		for (int i = 0; i < v.length; i++) {
			bst.add(v[i]);
		}
		
		bst.rebuild();
		bst.printTree();
		vis.drawTree(bst);

	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}

	}
}
