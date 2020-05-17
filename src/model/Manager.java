package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import model.Level.Color;
import util.MatrixGraph;

public class Manager {
	
	//Constant
	public final static String LEVEL_EXTENSION = "lvl";
	public final static String WORLD_DATA = "world.wrd";
	
	//Attributes
	private World world;
	
	//Constructor
	public Manager() {
	}

	//Methods
	//Save
	public void saveWorld(String url) throws IOException {
		String[] worldData = readFile(url+WORLD_DATA).split("\n");
		worldData[1] = world.getStars() + "";
		writeFile(url+"/"+WORLD_DATA, worldData);
		
		File[] levelsFiles = new File(url).listFiles();
		for(File level: levelsFiles) {
			if(level.getName().substring(level.getName().lastIndexOf(".")).equals(LEVEL_EXTENSION)) {
				try{
					
					String[] levelData = readFile(level.getPath()).split("\n");
					levelData[8] = world.searchLevel(levelData[0]).getStars() + "";
					writeFile(level.getPath(), levelData);
					
				}
				catch(NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//Write
	private void writeFile(String url, String[] data) throws IOException{
		String text = data[0];
		for(int i = 1; i < data.length; i++){
			text += "\n"+data[i];
		}
		
		File file=new File(url);
		PrintWriter writer=new PrintWriter(file);
		writer.append(text);
		writer.close();
	}
	
	//Load
	public void importWorld(String url) throws IOException {
		String[] worldData = readFile(url+WORLD_DATA).split("\n");
		this.world = new World(worldData[0], Integer.parseInt(worldData[1]));
		
		File[] levelsFiles = new File(url).listFiles();
		for(File level: levelsFiles) {
			String ext = level.getName().substring(level.getName().length()-3, level.getName().length());
			if(ext.equals(LEVEL_EXTENSION)) {
				try{
					
					String[] levelData = readFile(level.getPath()).split("\n");
					
					String name = levelData[0];
					int unlock = Integer.parseInt(levelData[1]);
					Color color = Color.valueOf(levelData[2]);
					int start = Integer.parseInt(levelData[3]);
					int end = Integer.parseInt(levelData[4]);
					boolean directed = Boolean.parseBoolean(levelData[5]);
					int stars = Integer.parseInt(levelData[8]);
					
					MatrixGraph<Node> graph = new MatrixGraph<Node>(directed, false, false);
					
					String[] vertices = levelData[6].split(" ");
					for(String vertex : vertices) {
						String[] vertexData = vertex.split(",");
						
						int x = Integer.parseInt(vertexData[0]);
						int y = Integer.parseInt(vertexData[1]);
						
						graph.addVertex(new Node(x, y));
					}
					
					String[] edges = levelData[7].split(" ");
					for(String edge : edges){
						String[] edgeData = edge.split(",");
						
						int nodeF = Integer.parseInt(edgeData[0]);
						int nodeC = Integer.parseInt(edgeData[1]);
						int weight = Integer.parseInt(edgeData[2]);
						
						graph.addEdge(graph.getVertex(nodeF), graph.getVertex(nodeC), weight);
					}
					
					world.addLevel(new Level(name, unlock, color, start, end, graph, stars));
					
				}
				catch(NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//Read
	private String readFile(String url) throws IOException{//[File]
		String text="";
		
		File file=new File(url);
		if(file.exists()){
			file.createNewFile();
			FileReader fileReader=new FileReader(file);
			BufferedReader reader=new BufferedReader(fileReader);
			String actualLine;
			while((actualLine=reader.readLine())!=null){
				text+=actualLine+"\n";
			}
			reader.close();
		}
		else{
			text=null;
		}
		
		return text;
	}
	
	//Get
	public World getWorld() {
		return world;
	}	
}