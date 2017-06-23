package edu.prateek.treepackage;

import java.util.Iterator;

public class TreeTest {

	public static void main(String[] args) {
		BinaryTree<String> lefttree = new BinaryTree<>("b",new BinaryTree<String>("d"), new BinaryTree<String>("e"));
		BinaryTree<String> rightTree = new BinaryTree<>("c", 
						new BinaryTree<String>("f",
								new BinaryTree<String>("h",
										new BinaryTree<String>("i"),null), 
								new BinaryTree<String>("g")), 
						null);
		BinaryTree<String> tree = new BinaryTree<>("a",lefttree,rightTree);
		System.out.println("Height of tree = " + tree.getHeight());
		System.out.println("Number of nodes = " + tree.getNumberOfNodes());
		Iterator<String> it = tree.getPostOrderIterator();
		System.out.println("PostOrder traversal");
		while(it.hasNext()){
			System.out.println("Node = " + it.next());
		}
		it = tree.getPreOrderIterator();
		System.out.println("PreOrder traversal");
		while(it.hasNext()){
			System.out.println("Node = " + it.next());
		}
		it = tree.getInorderIterator();
		System.out.println("InOrder traversal");
		while(it.hasNext()){
			System.out.println("Node = " + it.next());
		}
	}
}
