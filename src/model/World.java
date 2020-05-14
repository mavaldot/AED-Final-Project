package model;

import java.util.ArrayList;

public class World {

	//Attributes
	private int stars;
	private ArrayList<Level> levels;
	
	//Constructor
	public World() {
		this.stars = 0;
		this.levels = new ArrayList<Level>();
	}
	
}
