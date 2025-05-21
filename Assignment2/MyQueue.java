import java.util.ArrayList;
/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 2 - Notation Assignment
 */
public class MyQueue<T> implements QueueInterface<T> {
	
	private T[] queue;
	private int front, rear;
	private int size, capacity;
	private static final int DEFAULT = 50;
	/**
	 * Constructor that takes an int as the size of the queue
	 **/
	@SuppressWarnings("unchecked")
	public MyQueue(int capacity) {
		this.capacity = capacity;
		queue = (T[])new Object[capacity];
		front = 0;
		rear = -1;
		size = 0;
	}
	/*
	 * Default constructor
	 */
	public MyQueue() {
		this(DEFAULT);
	}
	/*
	 * Determines if queue is empty
	 * @return true if queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	/*
	 * Determines if queue is full
	 * @return true if queue is full
	 */
	@Override
	public boolean isFull() {
		return size() == queue.length;
	}
	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()) {
			throw new QueueUnderflowException();
		}
		T data = queue[front];
		queue[front] = null;
		front = (front+ 1) % capacity;
		size--;
		return data;
	}
	/**
	 * Returns number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if(isFull()) {
			throw new QueueOverflowException();
		}
		rear = (rear+ 1) % capacity;
		queue[rear] = e;
		size++;
		return true;
	}
	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	@Override
	public String toString(String delimiter) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < size; i ++) {
			sb.append(queue[(front + i) % queue.length]);
			if(i < size - 1) {
				sb.append(delimiter);
			}
		}
		return sb.toString();
	}
	/**
	 * Fills the queue with elements of the ArrayList
	 * @param list elements to be added to the Queue
	 * @throws QueueOverflowException if queue is full
	 */
	@Override
	public void fill(ArrayList<T> list) {
		ArrayList<T> listClone = new ArrayList<>(list);
		for(T element: listClone) {
			try {
				enqueue(element);
			} catch (QueueOverflowException e) {
				break;
			}
		}
	}
	
	@Override
	public String toString() {
		return toString("");
	}

}
