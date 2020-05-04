package util;

import exception.EmptyListException;

public class PriorityQueue<E> implements List<E>{

	//Constants
	public enum Priority{
		MIN, MAX;
	}
	
	//Attributes
	private Priority type; 
	private int size;
	private PriorityNode<E> first;
	private PriorityNode<E> last;
	
	//Constructor
	public PriorityQueue(Priority type) {
		this.type=type;
		size = 0;
		first = null;
		last = null;
	}
	
	//Methods
	public void enqueue(E element, int priority){
		PriorityNode<E> node = new PriorityNode<E>(element, priority);
		
		PriorityNode<E> prev = null;
		PriorityNode<E> actual = first;
		
		boolean found = false;
		while((actual != null) && (!found)){
			found = ( (type.equals(Priority.MAX)) && (node.getPriority() >= actual.getPriority()) ) || 	//MAX
					( (type.equals(Priority.MIN)) && (node.getPriority() <= actual.getPriority()) );	//MIN
			
			if(found){
				node.setNext(actual);
				if(prev == null){
					first = node;
				}
				else{
					prev.setNext(node);
				}
			}
		}
		
		if(!found){
			last.setNext(node);
			last = node;
		}
		
		size++;
	}
	
	public void decreaseKey(E element, int priority) {
		PriorityNode<E> actual = first;
		boolean run = true;
		while((actual != null) && (run)) {
			if(actual.getElement() == element){
				actual.setPriority(priority);
			}
			else{
				actual = actual.getNext();
			}
		}
	}
	
	public E dequeue() {
		if(isEmpty())
			throw new EmptyListException();
		E element = first.getElement();
		first = first.getNext();
		if(isEmpty())
			last = null;
		size--;
		return element;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public E peek() {
		if(isEmpty())
			throw new EmptyListException();
		return first.getElement();
	}
	
}
