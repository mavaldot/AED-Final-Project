package model;

import util.MatrixGraph;

public class Level {
	
	//Constants
	public enum Color{
		GREEN, RED, BLUE, YELLOW;
	}
	
	//Attributes
	private String name;
	private int stars;
	private Color color;
	
	private int start;
	private int end;
	private int player;
	private MatrixGraph<Node> graph;
	
	private int movements;
	
	//Constructor
	public Level(String name, int stars, Color color, int start, int end, int player, MatrixGraph<Node> graph) {
		this.name = name;
		this.stars = stars;
		this.color = color;
		
		this.start = start;
		this.end = end;
		this.player = player;
		if(!graph.getMultipleEdges()){
			this.graph = graph;
		}
		else {
			//Error
		}
		
		this.movements = minMovements();
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
		int stars = 0;
		
		if((movements >= 0) && (movements <=2)){
			stars = movements+1;
		}
		
		return stars;
	}
	
	public int minMovements() {
		return graph.dijkstra(graph.getVertex(start)).get(end).getDistance();
	}
	
	public int maxMovements(){//Change
		return minMovements() + 2;
	}
	
	//Get
	public String getName() {
		return name;
	}
	
	public int getStars() {
		return stars;
	}
	
	public Color getColor() {
		return color;
	}
	
}
