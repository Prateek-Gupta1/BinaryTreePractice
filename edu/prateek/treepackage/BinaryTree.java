package edu.prateek.treepackage;

import java.util.Iterator;
import java.util.Stack;

public class BinaryTree<T> implements BinaryTreeInterface<T> {

	private BinaryNode<T> root;

	public BinaryTree(){
		
	}
	
	public BinaryTree(T rootData) {
		root = new BinaryNode<>(rootData);
	}

	public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		setTreePrivate(rootData, leftTree, rightTree);
	}

	@Override
	public T getRootData() throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException();
		else
			return root.getData();
	}

	@Override
	public int getHeight() {
		return root.getHeight();
	}

	@Override
	public int getNumberOfNodes() {
		return root.getNumberOfNodes();
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public Iterator<T> getPreOrderIterator() {
		return new PreOrderIterator();
	}

	@Override
	public Iterator<T> getInorderIterator() {
		return new InorderIterator();
	}

	@Override
	public Iterator<T> getPostOrderIterator() {
		return new PostOrderIterator();
	}

	@Override
	public void setTree(T rootData) {
		root = new BinaryNode<T>(rootData);
	}

	@Override
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		setTreePrivate(rootData, (BinaryTree<T>) leftTree, (BinaryTree<T>) rightTree);
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	private void setTreePrivate(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {

		root = new BinaryNode<T>(data);
		if ((leftTree != null) && !leftTree.isEmpty()) {
			root.setLeftChild(leftTree.root);
		}

		if (rightTree != null && !rightTree.isEmpty()) {

			if (rightTree != leftTree) {
				root.setRightChild(rightTree.root);
			} else {
				root.setRightChild(leftTree.root.copy());
			}
		}

		if (leftTree != null && leftTree != this)
			leftTree.clear();
		if (rightTree != null && rightTree != this)
			rightTree.clear();
	}

	protected void setRootData(T rootData) {
		root.setData(rootData);
	}

	protected void setRootNode(BinaryNode<T> root) {
		this.root = root;
	}

	protected BinaryNode<T> getRootNode() {
		return root;
	}

	private class InorderIterator implements Iterator<T> {

		private Stack<BinaryNode<T>> nodeStack;
		private BinaryNode<T> currentNode;

		public InorderIterator() {
			nodeStack = new Stack<>();
			currentNode = root;
		}

		@Override
		public boolean hasNext() {
			return !nodeStack.isEmpty() || currentNode != null;
		}

		@Override
		public T next() {
			BinaryNode<T> nextNode = null;
			while (currentNode != null) {
				nodeStack.push(currentNode);
				currentNode = currentNode.getLeftChild();
			}

			if (!nodeStack.isEmpty()) {
				nextNode = nodeStack.pop();
				currentNode = nextNode.getRightChild();
			}
			return nextNode.getData();
		}

	}

	private class PreOrderIterator implements Iterator<T> {

		private Stack<BinaryNode<T>> nodeStack;

		public PreOrderIterator() {
			nodeStack = new Stack<>();
			if(root != null){
				nodeStack.push(root);
			}
		}

		@Override
		public boolean hasNext() {
			return !nodeStack.isEmpty();
		}

		@Override
		public T next() {
			BinaryNode<T> nextNode = null;

			if (!nodeStack.isEmpty()) {
				nextNode = nodeStack.pop();
				if (nextNode != null && nextNode.hasRightChild())
					nodeStack.push(nextNode.getRightChild());
				if (nextNode != null && nextNode.hasleftChild())
					nodeStack.push(nextNode.getLeftChild());
			}

			return nextNode.getData();
		}
	}

	private class PostOrderIterator implements Iterator<T> {

		private Stack<BinaryNode<T>> nodeStack;
		private BinaryNode<T> currentNode;

		public PostOrderIterator() {
			nodeStack = new Stack<>();
			currentNode = root;
			while (currentNode != null) {
				nodeStack.push(currentNode);
				currentNode = currentNode.getLeftChild();
			}
		}

		@Override
		public boolean hasNext() {
			return !nodeStack.isEmpty();
		}

		@Override
		public T next() {
			BinaryNode<T> nextNode = null;
			
			nextNode = nodeStack.pop();
			
			if (!nodeStack.isEmpty()) {
				currentNode = nodeStack.peek();
				if (currentNode.hasleftChild() && currentNode.getLeftChild().getData() == nextNode.getData()) {
					currentNode = currentNode.getRightChild();
					while (currentNode != null) {
						nodeStack.push(currentNode);
						
						if (currentNode.hasleftChild()) {
							currentNode = currentNode.getLeftChild();
						} else {
							currentNode = currentNode.getRightChild();
						}
					}
				}
			}

			return nextNode.getData();
		}
	}
}
