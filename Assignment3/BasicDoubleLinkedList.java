/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 3 - Double Linked List with an iterator
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {
	
	protected Node<T> head;
	protected Node<T> tail;
	protected int size;

	@Override
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}
	
	public int getSize() {
		return size;
	}
	
	public void addToEnd(T data) {
		Node<T> newNode = new Node<>(data);
		if(tail != null) {
		tail.next = newNode;
		newNode.prev = tail;
		tail = newNode;
		}
		else head = tail = newNode;
		size++;
		
	}
	
	public void addToFront(T data) {
		Node<T> newNode = new Node<>(data);
		if(head != null) {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		else head = tail = newNode;
		size++;
		
	}
	
	public T getFirst() {
		if(head == null && tail == null) {
			return null;
		}
		return head.data;
	}
	
	public T getLast() {
		if(head == null && tail == null) {
			return null;
		}
		return tail.data;
	}
	
	public Node<T> remove(T targetData, Comparator<T> comparator){
		Node<T> current = head;
		while(current != null){
			if(comparator.compare(current.data, targetData) == 0) {
				if(current == head) {
					head = head.next;
					if(head != null) head.prev = null;
					else tail = null;
				}
				else if(current == tail) {
					tail = tail.prev;
					if(tail != null) tail.next = null;
					else head = null;
				}
				else {
					current.prev.next = current.next;
					current.next.prev = current.prev;
				}
				size--;
				return current;
			}
			current = current.next;
		}
		return null;
	}
	
	public T retrieveFirstElement() {
		if(head == null && tail == null) {
			return null;
		}
		Node<T> current = head;
		head = head.next;
		if(head != null) head.prev = null;
		else {
			tail = null;
		}
		size--;
		return current.data;
	}
	
	public T retrieveLastElement() {
		if(head == null && tail == null) {
			return null;
		}
		Node<T> current = tail;
		tail = tail.prev;
		if(tail != null) tail.next = null;
		else {
			head = null;
		}
		size--;
		return current.data;
	}
	
	public ArrayList<T> toArrayList(){
		ArrayList<T> arr = new ArrayList<>();
		Node<T> temp = head;
		while(temp != null) {
			arr.add(temp.data);
			temp = temp.next;
		}
		return arr;
	}

	private class DoubleLinkedListIterator implements ListIterator<T>{
		private Node<T> current;
		
		public DoubleLinkedListIterator() {
			this.current = head;
		}
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T data = current.data;
			current = current.next;
			return data;
		}
		@Override
		public boolean hasPrevious() {
			return current != null && current.prev != null;
		}
		@Override
		public T previous() {
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}
			current = current.prev;
			return current.data;
		}
		@Override
		public int nextIndex() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}
		@Override
		public int previousIndex() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}
		@Override
		public void remove() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
			
		}
		@Override
		public void set(T e) throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
			
		}
		@Override
		public void add(T e) throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}
		
	}
	
	class Node<T>{
		T data;
		Node<T> prev;
		Node<T> next;
		
		Node(T dataNode) {
			data = dataNode;
		}
	}

}
