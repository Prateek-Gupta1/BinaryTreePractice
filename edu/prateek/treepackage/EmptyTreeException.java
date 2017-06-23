package edu.prateek.treepackage;

public class EmptyTreeException extends Exception {

	@Override
	public String getMessage() {
		return "Trying to access data in an empty tree";
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		// TODO Auto-generated method stub
		return super.getStackTrace();
	}

	
}
