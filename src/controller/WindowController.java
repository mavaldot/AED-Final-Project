package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import javafx.stage.DirectoryChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Level;
import model.Manager;
import model.Node;

public class WindowController implements Initializable {
	
	//Constants
	public final static double RADIUS = 50;
	
	public final static String LOSE_IMAGE = "/../../med/message/lose.png";
	public final static String STAR_IMAGE_PATH = "/../../med/stars/starImage.png";
	public final static String STAR_IMAGE2_PATH = "/../../med/stars/starImage2.png";
	public final static String LOCKED_STAR_IMAGE_PATH = "/../../med/stars/locked.png";
	public final static String EGGS_IMAGE_PATH = "/../../med/eggs/";
	public final static String EGGS_IMAGE_EXTENSION = ".png";
	public final static String LOCKED_EGG = "/../../med/eggs/LOCKED.png";
	public final static String YOSHIS_IMAGE_PATH = "/../../med/yoshis/";
	public final static String YOSHIS_IMAGE_EXTENSION = ".png";
	public final static String SHYGUYS_IMAGE_PATH = "/../../med/shyguys/";
	public final static String SHYGUY_IMAGE_EXTENSION = ".png";
	public final static String WORLDS_PATH = "worlds/";
	
	private @FXML BorderPane pane;
	private @FXML MenuBar menuBar;
	private @FXML ImageView starImage;
	private @FXML Label worldName;
	private @FXML Label starNumber;
	private @FXML VBox vBox;
	private @FXML HBox hBox;
	private @FXML ListView<HBox> listView;
	private Canvas canvas;
	private Stage stage;
	
	private Manager game;
	private Level level;
	
	private Rectangle2D screen;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Gets the screen dimension.
		screen = Screen.getPrimary().getVisualBounds();
		game = new Manager();
		
		//Initialize the components.
		initializeMenuBar();
		initializeHeader();
	}
	
	public void loadWorld() {
		
		//Creates a file chooser
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setTitle("Select the world");
		File initial = new File(WORLDS_PATH);
		dirChooser.setInitialDirectory(initial);
		
		//sets the stage
		stage = (Stage) pane.getScene().getWindow();
		
		try {
			File selectedDir = dirChooser.showDialog(stage);
			game.importWorld(selectedDir.getPath());
			generateWorld();
		}
		catch(NullPointerException e) {
			showAlert("Please select a folder");
		}
		catch(IOException e) {
			showAlert("A problem occurred loading the files, please try again");
		}
	}
	
	private void displayLevels() {
		vBox.getChildren().clear();
		listView.getItems().clear();
		vBox.getChildren().add(listView);
		
		
		ArrayList<Level> levels = game.getWorld().getLevels();
		
		for(Level level : levels) {
			HBox itemBox = generateHBox(level.getColor(),level.getName(), level.getStars(), level);
			listView.getItems().add(itemBox);
		}
	}
	
	//Generators
	public void generateWorld() {
		worldName.setText(game.getWorld().getName());
		starNumber.setText(game.getWorld().getStars() + "");
		starImage.setVisible(true);
		displayLevels();
	}
	
	public void generateLevel() {
		canvas = new Canvas(vBox.getWidth(), vBox.getHeight());
		vBox.getChildren().clear();
		
		paintGraph();
		canvas.setOnMouseClicked(event -> selectNode(event));
		vBox.getChildren().add(canvas);
	}
	
	public HBox generateHBox(Level.Color yoshiColor, String levelName, int starsEarned, Level level) {
		
		//Creates a HBox.
		HBox itemBox = new HBox();
		itemBox.setSpacing(5);
		itemBox.setAlignment(Pos.CENTER);
		itemBox.getStyleClass().add("item-box");
		
		Label name = new Label(levelName);
		name.setId("level");
		
		if(level.getUnlock() > game.getWorld().getStars()) {
			generateLockedHbox(itemBox, name, level);
		}
		else {
			//Adds a image with the character.
			ImageView character = new ImageView();
	        File file = new File(EGGS_IMAGE_PATH + yoshiColor.name() + EGGS_IMAGE_EXTENSION);
			character.setImage(new Image(file.toURI().toString()));
			character.setFitWidth(50);
			character.setFitHeight(50);
			itemBox.getChildren().add(character);
			
			//Adds the name of the level to the HBox.
			itemBox.getChildren().add(name);
			
			int missing = 3 - starsEarned;
			
			while(starsEarned-- > 0) {
				
				//Creates a ImageView for the stars, sets the image and adds it in the HBox.
				ImageView star = new ImageView();
		        file = new File(STAR_IMAGE_PATH);
				star.setImage(new Image(file.toURI().toString()));
				star.setFitWidth(50);
				star.setFitHeight(50);
				itemBox.getChildren().add(star);
			}
			
			while(missing-- > 0) {
				
				//Creates a ImageView for the missing stars, sets the image and adds it in the HBox.
				ImageView star = new ImageView();
		        file = new File(STAR_IMAGE2_PATH);
				star.setImage(new Image(file.toURI().toString()));
				star.setFitWidth(50);
				star.setFitHeight(50);
				itemBox.getChildren().add(star);
			}
			
			//Sets on mouse clicked event to the HBox
			itemBox.setOnMouseClicked(event -> {
				if(event.getButton() == MouseButton.PRIMARY) {
					this.level = level;
					generateLevel();
				}
			});
		}
		
		return itemBox;
	}
	
	public void generateLockedHbox(HBox itemBox, Label levelName, Level level) {
		
		//Adds a image with the character.
		ImageView character = new ImageView();
        File file = new File(LOCKED_EGG);
		character.setImage(new Image(file.toURI().toString()));
		character.setFitWidth(50);
		character.setFitHeight(50);
		itemBox.getChildren().addAll(character, levelName);
		
		int stars = 3;
		
		while(stars-- > 0) {
			
			ImageView star = new ImageView();
	        file = new File(LOCKED_STAR_IMAGE_PATH);
			star.setImage(new Image(file.toURI().toString()));
			star.setFitWidth(50);
			star.setFitHeight(50);
			itemBox.getChildren().add(star);
		}
		
		Label requiredStars = new Label(level.getUnlock() + "");
		requiredStars.setId("endGameMessage");
		
		ImageView required = new ImageView();
        file = new File(STAR_IMAGE_PATH);
		required.setImage(new Image(file.toURI().toString()));
		required.setFitWidth(50);
		required.setFitHeight(50);
		
		itemBox.getChildren().addAll(requiredStars, required);	
		itemBox.setOnMouseClicked(event -> lockedLevel(level));
	}
	
	public void lockedLevel(Level level) {
		
		//Creates an alert.
		ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		Alert alert = new Alert(AlertType.NONE, "", ok);
		alert.setHeaderText(null);
		alert.setTitle(null);
		alert.initStyle(StageStyle.UNDECORATED);
		
		//Set the style of the alert.
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/view/Style.css").toExternalForm());
		Stage stage = (Stage) dialogPane.getScene().getWindow();
		stage.getIcons().add(new Image("file:../../med/logo.png"));
		
		HBox itemBox = new HBox();
        itemBox.setSpacing(10);
        itemBox.setAlignment(Pos.CENTER);
		
		Label text = new Label("You need");
		Label numberOfStars = new Label(level.getUnlock() - game.getWorld().getStars() + "");
		Label end = new Label("more to unlock this level");
		text.setId("endGameMessage");
		text.setTranslateX(3);
		numberOfStars.setId("endGameMessage");
		numberOfStars.setTranslateX(3);
		end.setId("endGameMessage");
		
		ImageView star = new ImageView();
        File file = new File(STAR_IMAGE_PATH);
		star.setImage(new Image(file.toURI().toString()));
		star.setFitWidth(50);
		star.setFitHeight(50);
		
		itemBox.getChildren().addAll(text, numberOfStars, star, end);
		
		dialogPane.setContent(itemBox);
		
		//Shows the alert.
		alert.showAndWait();
	}
	
	//Initializer methods.
	public void initializeMenuBar() {
		
		//Clears the menuBar.
		menuBar.getMenus().clear();
		
		//Add menus and menu items to the menu bar.
		Menu file = new Menu("File");
		MenuItem load = new MenuItem("Load World");
		file.getItems().add(load);
		menuBar.getMenus().add(file);
		load.setOnAction(event -> loadWorld());
	}
	
	public void initializeHeader() {
		
		//Sets the image.
        File file = new File(STAR_IMAGE_PATH);
		starImage.setImage(new Image(file.toURI().toString()));
		starImage.setVisible(false);
		
		//Adjust the components to the screen
		starImage.setTranslateX(screen.getWidth() - 300);
		starNumber.setTranslateX(screen.getWidth() - 300);
	}
	
	public void paintGraph() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		//Clear
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		double xc = canvas.getWidth()/2;
		double yc = canvas.getHeight()/2;
		 
		//Graph
			//Edge
		for(int i = 0; i < level.getGraph().getEdges().size(); i++) {
			for(int j = 0; j < level.getGraph().getEdges().get(i).size(); j++) {
				if(level.getGraph().getEdge(i, j).size() != 0){
					
					//Line
					Node node1 = level.getGraph().getVertices().get(i);
					double x1 = node1.getX() + xc;
					double y1 = node1.getY() + yc;
					
					Node node2 = level.getGraph().getVertices().get(j);
					double x2 = node2.getX() + xc;
					double y2 = node2.getY() + yc;
					
					gc.setFill(Color.BLACK);
					gc.strokeLine(x1, y1, x2, y2);
					//...
				}
			}
		}
			//...
			//Vertex
		for(int i = 0; i < level.getGraph().getVertices().size(); i++) {
			
			Node node = level.getGraph().getVertices().get(i);
			
			//Node
			if(level.getEnd() == i) {
				gc.setFill(Color.GREEN);
			}
			else{
				gc.setFill(Color.BROWN);
			}
			
			double d = RADIUS * 2;
			double x = node.getX() - RADIUS + xc;
			double y = node.getY() - RADIUS + yc;
			
			gc.fillOval(x, y, d, d);
			//...
			
			//Player
			if(level.getPlayer() == i) {
				File file = new File(YOSHIS_IMAGE_PATH + level.getColor().name() + YOSHIS_IMAGE_EXTENSION);
				Image img = new Image(file.toURI().toString());
				gc.drawImage(img, x, y, d, d);
			}
			//..
			
		}
			//...
			//Enemy
		for(int i = 0; i < level.getGraph().getEdges().get(level.getPlayer()).size(); i++) {
			Node node1 = level.getGraph().getVertices().get(level.getPlayer());
			double x1 = node1.getX() + xc, y1 = node1.getY() + yc;
			Node node2 = level.getGraph().getVertices().get(i);
			double x2 = node2.getX() + xc, y2 = node2.getY() + yc;
			double xm = (x1 + x2) / 2, ym = (y1 + y2) / 2;
			
			//Number
			if(level.getGraph().getEdge(level.getPlayer(), i).size() != 0){
				File file = new File(SHYGUYS_IMAGE_PATH + level.getGraph().getEdge(level.getPlayer(), i).get(0).toString() + SHYGUY_IMAGE_EXTENSION);
				Image img = new Image(file.toURI().toString());
				
				double r = 40;
				gc.drawImage(img, xm-r, ym-r, r*2, r*2);
				
				gc.setFill(Color.BLACK);
				gc.setFont(new Font("Impact", 40));
				gc.fillText(level.getGraph().getEdge(level.getPlayer(), i).get(0).toString(), xm + (r/2), ym - (r/2));
			}
			//...
			//Block
			else if(level.getGraph().getDirected() && (level.getGraph().getEdge(i, level.getPlayer()).size() != 0)){
				File file = new File(SHYGUYS_IMAGE_PATH + "BLOCK" + SHYGUY_IMAGE_EXTENSION);
				Image img = new Image(file.toURI().toString());
				
				double r = 60;
				gc.drawImage(img, xm-r, ym-r, r*2, r*2);
			}
			//...
		}
			//...
		
		
		
	}
	
	//Supporters
	public void selectNode(MouseEvent e){
		if(!level.win() && !level.lose()) {
			
			double x = e.getX();
			double y = e.getY();
			
			double xc = canvas.getWidth()/2;
			double yc = canvas.getHeight()/2;
			
			boolean run = true;
			for(int i = 0; run && (i < level.getGraph().getVertices().size()); i++) {
				double xv = level.getGraph().getVertex(i).getX() + xc;
				double yv = level.getGraph().getVertex(i).getY() + yc;
				
				double distance = Math.sqrt(Math.pow((x-xv), 2) + (Math.pow((y-yv), 2)));
				
				if(distance <= RADIUS){
					
					if(level.move(i)) {
						paintGraph();
					}
					
					//Win
					if(level.win()) {
						showWinAlert();
						
						game.getWorld().addStars(level.starsEarned());
						
						level.restart();
						this.level = null;
						save();
						generateWorld();
					}
					//...
					//Lose
					else if(level.lose()){
						showLoseAlert();
						
						level.restart();
						this.level = null;
						generateWorld();
					}
					//...
					
					run = false;
				}
				
			}
			
		}
	}
	
	public void showAlert(String message) {
		
		//Creates an alert.
		ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		Alert alert = new Alert(AlertType.NONE, message, ok);
		alert.setHeaderText(null);
		alert.setTitle(null);
		alert.initStyle(StageStyle.UNDECORATED);
		
		//Set the style of the alert.
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/view/Style.css").toExternalForm());
		Stage stage = (Stage) dialogPane.getScene().getWindow();
		stage.getIcons().add(new Image("file:../../med/logo.png"));
		
		//Shows the alert.
		alert.showAndWait();
	}
	
	public void save() {
		try {
			game.saveWorld();
		}
		catch (IOException e1) {
			showAlert("A problem occurred saving the files, please try again");
		}
	}
	
	public void showWinAlert() {
		
		//Creates an alert.
		ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		Alert alert = new Alert(AlertType.NONE, "", ok);
		alert.setHeaderText(null);
		alert.setTitle(null);
		alert.initStyle(StageStyle.UNDECORATED);
		
		//Set the style of the alert.
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/view/Style.css").toExternalForm());
		Stage stage = (Stage) dialogPane.getScene().getWindow();
		stage.getIcons().add(new Image("file:../../med/logo.png"));
		
		HBox itemBox = new HBox();
		itemBox.setSpacing(5);
		itemBox.setAlignment(Pos.CENTER);
		
		Label text = new Label("You Win!");
		text.setId("endGameMessage");
		itemBox.getChildren().add(text);
		
		int starsEarned = level.starsInGame();
		int missing = 3 - starsEarned;
		
		while(starsEarned-- > 0) {
			
			//Creates a ImageView for the stars, sets the image and adds it in the HBox.
			ImageView star = new ImageView();
	        File file = new File(STAR_IMAGE_PATH);
			star.setImage(new Image(file.toURI().toString()));
			star.setFitWidth(50);
			star.setFitHeight(50);
			itemBox.getChildren().add(star);
		}
		
		while(missing-- > 0) {
			
			//Creates a ImageView for the missing stars, sets the image and adds it in the HBox.
			ImageView star = new ImageView();
	        File file = new File(STAR_IMAGE2_PATH);
			star.setImage(new Image(file.toURI().toString()));
			star.setFitWidth(50);
			star.setFitHeight(50);
			itemBox.getChildren().add(star);
		}
		
		dialogPane.setContent(itemBox);
		
		//Shows the alert.
		alert.showAndWait();
	}
	
	public void showLoseAlert() {
		
		//Creates an alert.
		ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		Alert alert = new Alert(AlertType.NONE, "", ok);
		alert.setHeaderText(null);
		alert.setTitle(null);
		alert.initStyle(StageStyle.UNDECORATED);
		
		//Set the style of the alert.
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/view/Style.css").toExternalForm());
		Stage stage = (Stage) dialogPane.getScene().getWindow();
		stage.getIcons().add(new Image("file:../../med/logo.png"));
		
		HBox itemBox = new HBox();
        itemBox.setSpacing(10);
        itemBox.setAlignment(Pos.CENTER);
		
		Label text = new Label("You Lose!");
		text.setId("endGameMessage");
		itemBox.getChildren().add(text);
		
		ImageView lose = new ImageView();
        File file = new File(LOSE_IMAGE);
		lose.setImage(new Image(file.toURI().toString()));
		lose.setFitWidth(50);
		lose.setFitHeight(50);
		itemBox.getChildren().add(lose);
		
		dialogPane.setContent(itemBox);
		
		//Shows the alert.
		alert.showAndWait();
	}
}