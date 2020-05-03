package util;

import java.util.ArrayList;
import exception.EmptyListException;

public class Queue<E> implements List<E> {
	
	private int size;
	private Node<E> first;
	private Node<E> last;
	
	public Queue() {
		
		size = 0;
		first = null;
		last = null;
	}
	
	public void enqueue(E element) {
		
		Node<E> aux = last;
		last = new Node<>(element);
		if(isEmpty()) {
			first = last;
		}
		else {
			aux.setNext(last);
		}
		size++;
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
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public E peek() {
		
		if(isEmpty())
			throw new EmptyListException();
		return first.getElement();
	}
	
	public ArrayList<E> toArrayList(){
		ArrayList<E> list=new ArrayList<E>();
		
		Node<E> actual=first;
		while(actual!=null){
			list.add(actual.getElement());
			actual=actual.getNext();
		}
		
		return list;
	}
	
	@Override
	public boolean isEmpty() {
		return first == null;
	}
}