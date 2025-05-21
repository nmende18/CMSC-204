/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 3 - Double Linked List with an iterator
 */
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{
	
	private Comparator<T> compareableObject;

	public SortedDoubleLinkedList(Comparator<T> compareableObject) {
		this.compareableObject = compareableObject;
	}
	
	public void add(T data) {
		Node<T> newNode = new Node<T>(data);
		if(head == null) {
			head = newNode;
			tail = newNode;
		}
		else {
			Node<T> current = head;
			
			while(current != null && compareableObject.compare(data, current.data) > 0) {
				current = current.next;
			}
			
			if(current == head) {
				newNode.next = head;
				head.prev = newNode;
				head = newNode;
			}
			else if(current == null) {
				tail.next = newNode;
				newNode.prev = tail;
				tail = newNode;
			}
			else {
				newNode.prev = current.prev;
				newNode.next = current;
				current.prev.next = newNode;
				current.prev = newNode;
			}
		}
		size++;
	}
	
	public void addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
	
	public void addToFront(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	
	public ListIterator<T> iterator(){
		return super.iterator();
		
	}
	@Override
	public Node<T> remove(T data, Comparator<T> comparator){
		return super.remove(data, comparator);
	}

}
