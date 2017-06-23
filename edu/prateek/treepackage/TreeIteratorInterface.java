package edu.prateek.treepackage;

import java.util.Iterator;

public interface TreeIteratorInterface<T> {

	public Iterator<T> getPreOrderIterator();
	public Iterator<T> getInorderIterator();
	public Iterator<T> getPostOrderIterator();
}
