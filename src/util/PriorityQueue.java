package util;

import java.util.ArrayList;

import exception.EmptyListException;

public class PriorityQueue<E> implements List<E>{
	
	//Constants
	public enum Priority{
		MIN, MAX;
	}
	
	//Attributes
	private Priority type;
	private ArrayList<E> nodes;
	private ArrayList<Integer> priorities;
	
	//Constructor
	public PriorityQueue(Priority type) {
		this.type = type;
		this.nodes = new ArrayList<E>();
		this.priorities = new ArrayList<Integer>();
	}
	
	//Methods
	public void enqueue(E element, int priority){
		this.nodes.add(element);
		this.priorities.add(priority);
	}
	
	public void decreaseKey(E element, int priority) {
		int i = nodes.indexOf(element);
		if(i != -1)
			priorities.set(i, priority);
	}
	
	public E dequeue() {
		int index = -1;
		Integer m = null;
		
		for(int i = 0; i < priorities.size(); i++){
			if(type.equals(Priority.MIN)){
				if((m == null) || (priorities.get(i).intValue() < m.intValue())){
					index = i;
					m = priorities.get(i);
				}
			}
			else{
				if((m == null) || (priorities.get(i).intValue() > m.intValue())){
					index = i;
					m = priorities.get(i);
				}
			}
			
		}
		
		E node = null;
		if(index != -1){
			node = nodes.get(index);
			
			nodes.remove(index);
			priorities.remove(index);
		}
		else{
			throw new EmptyListException();
		}
		
		return node;
	}
	
	public int peekPriority() {
		return priorities.get(nodes.indexOf(peek()));
	}
	
	public int size() {
		return nodes.size();
	}

	public boolean isEmpty() {
		return nodes.size() == 0;
	}

	public E peek() {
		int index = -1;
		Integer m = null;
		
		
		for(int i = 0; i < priorities.size(); i++){
			if(type.equals(Priority.MIN)){
				if((m == null) || (priorities.get(i).intValue() < m.intValue())){
					index = i;
					m = priorities.get(i);
				}
			}
			else{
				if((m == null) || (priorities.get(i).intValue() > m.intValue())){
					index = i;
					m = priorities.get(i);
				}
			}
			
		}
		
		E node = null;
		if(index != -1){
			node = nodes.get(index);
		}
		else{
			throw new EmptyListException();
		}
		
		return node;
	}
	
	public String toString(){
		return nodes.toString();
	}
	
}
