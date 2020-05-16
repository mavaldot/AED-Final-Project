package model;

import java.util.ArrayList;
import java.util.Collections;

public class World {

	//Attributes
	private String name;
	private int stars;
	private ArrayList<Level> levels;
	
	//Constructor
	public World(String name, int stars) {
		this.name = name;
		this.stars = stars;
		this.levels = new ArrayList<Level>();
	}
	
	//Methods
	//Add
	public void addLevel(Level level) {
		if(searchLevel(level.getName()) == null) {
			levels.add(level);
			Collections.sort(levels);
		}
	}
	
	//Get
	public int getStars() {
		return stars;
	}
	
	//Search
	public Level searchLevel(String name) {
		Level level = null;
		
		boolean run = true;
		for(int i = 0; (i < levels.size()) && run; i++) {
			if(name.equals(levels.get(i).getName())){
				
				level = levels.get(i);
				run = false;
				
			}
		}
		
		return level;
	}
	
}
