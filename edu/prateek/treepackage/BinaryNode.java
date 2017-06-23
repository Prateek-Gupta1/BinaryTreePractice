package edu.prateek.treepackage;

/**
 * @author Prateek Gupta
 * Class representing Node object for a Binary tree.
 * @param <T>
 */
class BinaryNode<T> {

	private T data;
	private BinaryNode<T> leftChild;
	private BinaryNode<T> rightChild;
	
	BinaryNode(){
		this(null);
	}
	
	BinaryNode(T data){
		this(data, null, null);
	}
	
	BinaryNode(T data, BinaryNode<T> leftChild, BinaryNode<T> rightChild){
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public T getData(){
		return data;
	}
	
	public void setData(T data){
		this.data = data;
	}

	public BinaryNode<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinaryNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	public boolean hasleftChild(){
		return leftChild != null;
	}
	
	public BinaryNode<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinaryNode<T> rightChild) {
		this.rightChild = rightChild;
	}
	
	public boolean hasRightChild(){
		return rightChild != null;
	}
	
	public boolean isLeafNode(){
		return (leftChild == null) && (rightChild == null);
	}
	
	/**
	 * Counts the number of node in the subtree rooted at this node.
	 * @return number of nodes in the subtree.
	 */
	public int getNumberOfNodes(){
		return  1 
				+ (leftChild != null ? leftChild.getNumberOfNodes() : 0 ) 
				+ (rightChild != null ? rightChild.getNumberOfNodes() : 0 );
	}
	
	/**
	 * Computes height of the subtree rooted at this node.
	 * @return height of subtree.
	 */
	public int getHeight(){
		int height = 1;
		height += Math.max(leftChild != null ? leftChild.getHeight() : 0,
						   rightChild != null ? rightChild.getHeight() : 0);
		return height;
	}
	
	public BinaryNode<T> copy(){
		BinaryNode<T> newRoot = new BinaryNode<>(data);
		if(leftChild!=null) newRoot.setLeftChild(leftChild.copy());
		if(rightChild != null) newRoot.setRightChild(rightChild.copy());
		return newRoot;
	}
	
}
