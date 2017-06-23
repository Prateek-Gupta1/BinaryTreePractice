package edu.prateek.treepackage;

public interface SearchTreeInterface<T extends Comparable<? super T>> 
	   extends TreeInterface<T> {

	public boolean contains(T entry);
	public T getEntry(T entry);
	public T add(T newEntry);
	public T remove(T entry);
}
