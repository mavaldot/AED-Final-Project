package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Manager;

public class WindowController implements Initializable {
	
	//Constants
	public final String STAR_IMAGE_PATH = "/../../med/startImage.png";
	public final String WORLDS_PATH = "worlds/";
	
	private @FXML BorderPane pane;
	private @FXML MenuBar menuBar;
	private @FXML ImageView starImage;
	private @FXML Label levelName;
	private @FXML Label starNumber;
	private Stage stage;
	
	private Manager game;
	
	private Rectangle2D screen;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Gets the screen dimension.
		screen = Screen.getPrimary().getVisualBounds();
		
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
		}
		catch(NullPointerException e) {
			showAlert("Please select a folder");
		}
		catch(IOException e) {
			showAlert("A problem occurred loading the files, please try again");
		}
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
		
		//Adjust the components to the screen
		starImage.setTranslateX(screen.getWidth() - 320);
		starNumber.setTranslateX(screen.getWidth() - 320);
	}

	//Supporters
	public void showAlert(String message) {
		ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
		Alert alert = new Alert(AlertType.NONE, message, ok);
		alert.setHeaderText(null);
		alert.setTitle(null);
		alert.initStyle(StageStyle.UNDECORATED);
		
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/view/Style.css").toExternalForm());
		Stage stage = (Stage) dialogPane.getScene().getWindow();
		stage.getIcons().add(new Image("file:../../med/Logo.png"));
		
		alert.showAndWait();
	}
}