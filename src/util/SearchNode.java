package util;

public class SearchNode<T> {
	
	//Attributes
	private T object;
	private int color;
	private int distance;
	private Tuple<Integer, Integer> timestamps;
	private SearchNode<T> ancestor;
	
	//Constants
	public static final int WHITE = 0;
	public static final int GRAY = 1;
	public static final int BLACK = 2;
	
	//Constructor
	public SearchNode(T object){
		this.object = object;
		this.color = 0;
		this.distance = Integer.MAX_VALUE;
		this.timestamps = new Tuple<>(-1,-1);
		this.ancestor = null;
		
	}
	
	public SearchNode(T object, int color, int distance, SearchNode<T> ancestor){
		this.object = object;
		this.color = color;
		this.distance = distance;
		this.timestamps = new Tuple<>(-1,-1);
		this.ancestor = ancestor;
	}

	//Methods
	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public SearchNode<T> getAncestor() {
		return ancestor;
	}

	public void setAncestor(SearchNode<T> ancestor) {
		this.ancestor = ancestor;
	}
	
	public Tuple<Integer, Integer> getTimestamps() {
		return timestamps;
	}
	
	public void setDTimestamps(Integer d) {
		timestamps.setVal1(d);
	}
	
	public void setFTimestamps(Integer f) {
		timestamps.setVal2(f);
	}
	
}
