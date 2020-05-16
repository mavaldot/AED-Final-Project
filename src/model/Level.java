package model;

import exception.ImpossibleException;
import exception.MultipleEdgesException;
import util.MatrixGraph;
import util.SearchNode;

public class Level implements Comparable<Level>{
	
	//Constants
	public enum Color{
		GREEN, RED, BLUE, YELLOW;
	}
	
	//Attributes
	private String name;
	private int unlock;
	private Color color;
	
	private int start;
	private int end;
	private int player;
	private MatrixGraph<Node> graph;
	
	private int movements;
	private int stars;
	
	//Constructor
	public Level(String name, int unlock, Color color, int start, int end, MatrixGraph<Node> graph, int stars) {
		this.name = name;
		this.unlock = unlock;
		this.color = color;
		
		this.start = start;
		this.end = end;
		this.player = start;
		if(!graph.getMultipleEdges()){
			if(checkPlayable(graph, start, end)){
				this.graph = graph;
			}
			else {
				throw new ImpossibleException();
			}
		}
		else {
			throw new MultipleEdgesException();
		}
		
		this.movements = maxMovements();
		this.stars = stars;
		
	}
	
	//Methods
	public boolean move(int position){
		boolean possible = false;
		
		if(graph.getEdge(player, position).size() != 0) {
			if(graph.getEdge(player, position).get(0) <= movements) {
				possible = true;
				
				this.movements -= graph.getEdge(player, position).get(0);
				this.player = position;
			}
		}
		
		return possible;
	}
	
	public boolean win() {
		return player == end;
	}
	
	public boolean lose() {
		boolean lose = true;
		
		for(int i = 0; (i < graph.getVertices().size()) && lose; i++){
			if((graph.getEdge(player, i).size() != 0) && (graph.getEdge(player, i).get(0) <= movements)){
				lose = false;
			}
		}
		
		return lose;
	}
	
	public int starsEarned(){//Change
		int starsE = 0;
		
		if((movements >= 0) && (movements <=2)){
			starsE = (movements+1) - stars;
			if(starsE < 0){
				starsE = 0;
			}
			else{
				this.stars += starsE;
			}
			
		}
		
		return starsE;
	}
	
	public int minMovements() {
		return graph.dijkstra(graph.getVertex(start)).get(end).getDistance();
	}
	
	public int maxMovements(){//Change
		return minMovements() + 2;
	}
	
	public static boolean checkPlayable(MatrixGraph<Node> graph, int start, int end) {
		return SearchNode.WHITE != graph.bfs(graph.getVertex(start)).getVertex(end).getColor();
	}
	
	//Compare
	public int compareTo(Level level) {
		return this.unlock - level.unlock;
	}
	
	//Get
	public String getName() {
		return name;
	}
	
	public int getUnlock() {
		return unlock;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public int getPlayer() {
		return  player;
	}
	
	public MatrixGraph<Node> getGraph() {
		return graph;
	}
	
	public int getMovements() {
		return movements;
	}
	
	public int getStars() {
		return stars;
	}
	
}
