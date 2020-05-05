package util;

public class Node<E> {

	private E element;
	private Node<E> next;
	
	public Node(E element) {
		
		this.element = element;
		next = null;
	}
	
	public void setNext(Node<E> next) {
		this.next = next;
	}
	
	public Node<E> getNext(){
		return next;
	}
	
	public E getElement(){
		return element;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node<E> other = (Node<E>) obj;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		return true;
	}
}