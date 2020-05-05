package util;
import java.util.HashMap;

public class DisjointSet<E> {
	
	HashMap<Node<E>, Node<E>> representatives;
	
	public DisjointSet() {
		representatives = new HashMap<>();
	}
	
	public void makeSet(E v) {
		representatives.put(new Node<E>(v), new Node<E>(v));
	}
	
	public Node<E> find(E v) {
		return representatives.get(new Node<E>(v));
	}
	
	public boolean union(E a, E b) {
		
		Node<E> nA = find(a);
		Node<E> nB = find(b);
		boolean joined = false;
		
		if(!nA.equals(nB)) {
			for(HashMap.Entry<Node<E>, Node<E>> entry : representatives.entrySet()) {
				if(entry.getValue().equals(nB)) {
					representatives.put(entry.getKey(), nA);
				}
			}
			
			joined = true;
		}
		return joined;
	}
	
	public void print() {
		for(HashMap.Entry<Node<E>, Node<E>> entry : representatives.entrySet()) {
			System.out.println(entry.getKey().getElement() + " : " + entry.getValue().getElement());
		}
	}
}
