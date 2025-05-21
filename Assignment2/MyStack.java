import java.util.ArrayList;
/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 2 - Notation Assignment
 */
public class MyStack<T> implements StackInterface<T> {
	
	private T[] stack;
	private int capacity;
	private int top;
	private static final int DEFAULT = 50;
	/**
	 * Constructor - takes in an int as the size of the stack
	 * @param size
	 */
	@SuppressWarnings("unchecked")
	public MyStack(int size) {
		stack = (T[])new Object[size];
		top = -1;
		capacity = size;
	}
	/**
	 * Default constructor
	 */
	public MyStack() {
		this(DEFAULT);
	}
	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return top == -1;
	}
	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
	 */
	@Override
	public boolean isFull() {
		return top == capacity - 1;
	}
	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		T data = stack[top];
		stack[top--] = null;
		return data;
	}
	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	@Override
	public T top() throws StackUnderflowException {
		if(isEmpty()) {
			throw new StackUnderflowException();
		}
		T data = stack[top];
		return data;
	}
	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	@Override
	public int size() {
		return top + 1;
	}
	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
	@Override
	public boolean push(T e) throws StackOverflowException {
		if(isFull()) {
			throw new StackOverflowException();
		}
		stack[++top] = e;
		return true;
	}
	/**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i <= top; i++) {
			sb.append(stack[i].toString());
			if(i < top) {
				sb.append(delimiter);
			}
		}
		return sb.toString();
		
	}
	/**
	 * Fills the stack with the elements of the ArrayList
	 * @param list elements to be added
	 * @throws StackOverflowException if stack gets full
	 */
	@Override
	public void fill(ArrayList<T> list) {
		ArrayList<T> listClone = new ArrayList<>(list);
		for(T element : listClone) {
			try {
				push(element);
			} catch (StackOverflowException e) {
				break;
			}
		}
	}
}
