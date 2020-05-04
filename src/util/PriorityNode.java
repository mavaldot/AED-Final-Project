package util;

public class PriorityNode<E>{

	//Attributes
	private E element;
	private PriorityNode<E> next;
	private int priority;
	
	//Constructor
	public PriorityNode(E element, int priority) {
		this.element=element;
		this.priority=new Integer(priority);
	}

	//Methods	
	public void setNext(PriorityNode<E> next) {
		this.next = next;
	}
	
	public PriorityNode<E> getNext(){
		return next;
	}
	
	public E getElement(){
		return element;
	}
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
}
