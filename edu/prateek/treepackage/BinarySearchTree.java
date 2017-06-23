package edu.prateek.treepackage;

public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> implements SearchTreeInterface<T> {

	public BinarySearchTree(){
		super();
	}
	public BinarySearchTree(T rootData) {
		super();
		setRootNode(new BinaryNode<T>(rootData));
	}

	@Override
	public T getRootData() throws EmptyTreeException {
		return super.getRootData();
	}

	@Override
	public boolean contains(T entry) {
		return getEntry(entry) != null;
	}

	@Override
	public T getEntry(T entry) {
		return findEntry(getRootNode(), entry);
	}
	
	private T findEntry(BinaryNode<T> rootNode, T entry){
		T result = null;
		
		if(rootNode != null){
			T rootEntry = rootNode.getData();
			if(entry.equals(rootEntry)){
				result = rootEntry;
			}else if(entry.compareTo(rootEntry) < 0){
				result = findEntry(rootNode.getLeftChild(), entry);
			}else{
				result = findEntry(rootNode.getRightChild(), entry);
			}
		}
		return result;
	}

	@Override
	public T add(T newEntry) {
		BinaryNode<T> currentNode = getRootNode();
		T result = null;
		boolean found = false;
		
		while(!found){
			
			T currentEntry = currentNode.getData();
			int comparison = newEntry.compareTo(currentEntry);
			
			if(comparison == 0){
				found = true;
				result = currentEntry;
				currentNode.setData(newEntry);
			}else if(comparison < 0){
				if(currentNode.hasleftChild()){
					currentNode = currentNode.getLeftChild();
				}else{
					found = true;
					currentNode.setLeftChild(new BinaryNode<T>(newEntry));
				}// end if
			}else{
				if(currentNode.hasRightChild()){
					currentNode = currentNode.getRightChild();
				}else{
					found = true;
					currentNode.setRightChild(new BinaryNode<T>(newEntry));
				}// end if
			}// end if
		}//end while
		return result;
	}

	@Override
	public T remove(T entry) {
		BinaryNode<T> deletedRoot = removeEntry(getRootNode(),entry);
		return deletedRoot.getData();
	}
	
	private BinaryNode<T> removeEntry(BinaryNode<T> root, T entry){
		
		if(root == null) return root;
		
		int comparison = root.getData().compareTo(entry);
		
		if(comparison < 0){
			root.setLeftChild(removeEntry(root.getLeftChild(),entry));
		}else if(comparison > 0){
			root.setRightChild(removeEntry(root.getRightChild(), entry));
		}else{
			if(!root.hasleftChild()) return root.getRightChild();
			else if(!root.hasRightChild()) return root.getLeftChild();
			else{
				root.setData(minValue(root.getRightChild()));
				root.setRightChild(removeEntry(root.getRightChild(), entry));
			}
		}
		return root;
	}
	
	private T minValue(BinaryNode<T> node){
		T minVal = node.getData();
		while(node.hasleftChild()){
			minVal = node.getLeftChild().getData();
			node = node.getLeftChild();
		}
		return minVal;
	}
	
	@Override
	public void setTree(T rootData) {
	}
	@Override
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
	}
}
