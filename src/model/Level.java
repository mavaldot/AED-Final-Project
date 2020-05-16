package model;

import util.MatrixGraph;

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
			this.graph = graph;
		}
		else {
			//Error
		}
		
		this.movements = minMovements();
		this.stars = stars;
		
	}
	
	//Methods
	public boolean move(int position){
		boolean possible = false;
		
		if(graph.getEdge(player, position).size() != 0) {
			possible = true;
			
			this.player = position;
			this.movements -= graph.getEdge(player, position).get(0);
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
	
	public int getStars() {
		return stars;
	}
	
}
