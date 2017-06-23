package edu.prateek.treepackage;

import javax.naming.ldap.InitialLdapContext;

public class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T> {
	
	private T[] heap;
	private int lastIndex;
	private boolean initialized;
	private static final int DEFAULT_CAPACITY = 30;
	private static final int MAX_CAPACITY = 10000;
	
	public MaxHeap() {
		this(DEFAULT_CAPACITY);
	}
	
	public MaxHeap(T[] entries){
		this(entries.length);
		assert initialized = true;
		for(int idx = 0 ; idx < entries.length; idx++){
			heap[idx] = entries[idx];
		}
		for(int rootIndex = lastIndex/2; rootIndex > 0; rootIndex--)
			reheap(rootIndex);
		
		
	}
	
	public MaxHeap(int initCapacity){
		if(initCapacity < DEFAULT_CAPACITY){
			initCapacity = DEFAULT_CAPACITY;
		}else if(initCapacity > MAX_CAPACITY){
			initCapacity = MAX_CAPACITY;
		}
		
		T[] tempHeap = (T[]) new Comparable[initCapacity];
		heap = tempHeap;
		lastIndex = 0;
		initialized = true;
	}
	
	@Override
	public void add(T newEntry) {
		int newIndex = lastIndex + 1;
		if(newIndex > MAX_CAPACITY) 
			return;
		else 
			ensureCapacity();
		int parentIndex = newIndex/2;
		while((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0){
			heap[newIndex] = heap[parentIndex];
			newIndex = parentIndex;	
			parentIndex = newIndex/2;
		}
		heap[newIndex] = newEntry;
		lastIndex++;
	}

	private void ensureCapacity(){
		if(heap.length == lastIndex){
			@SuppressWarnings("unchecked")
			T[] tempHeap = (T[])new Comparable[heap.length*2 < MAX_CAPACITY ? heap.length*2 : MAX_CAPACITY];
			for(int i=0; i<heap.length; i++){
				tempHeap[i] = heap[i];
			}
			heap = tempHeap;
		}
	}
	
	@Override
	public T removeMax() {
		T root = null;
		if(!isEmpty()){
			root = heap[1];
			heap[1] = heap[lastIndex];
			lastIndex--;
			reheap(1);
		}
		return root;
	}
	
	private void reheap(int rootIndex){
		
		boolean done = false;
		T orphan = heap[rootIndex];
		int leftChildIndex = rootIndex*2;
		
		while(!done && leftChildIndex <= lastIndex){
			int largerChildIndex = leftChildIndex;
			int rightChildIndex = leftChildIndex+1;
			if( (rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[leftChildIndex]) > 0){
				largerChildIndex = rightChildIndex;
			}
			
			if(orphan.compareTo(heap[largerChildIndex]) < 0){
				heap[rootIndex] = heap[largerChildIndex];
				rootIndex = largerChildIndex;
				leftChildIndex = 2*rootIndex;
			}else
				done = true;
		}
		heap[rootIndex] = orphan;
	}

	@Override
	public T getMax() {
		T root = null;
		if(initialized && !isEmpty()){
			root = heap[1];
		}
		return root;
	}

	@Override
	public boolean isEmpty() {
		return lastIndex < 1;
	}

	@Override
	public int getSize() {
		return lastIndex;
	}

	@Override
	public void clear() {
		if(initialized && !isEmpty()){
			while(lastIndex > -1){
				heap[lastIndex] = null;
				lastIndex--;
			}
			lastIndex = 0;
		}
	}

}
