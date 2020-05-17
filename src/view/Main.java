package view;
	
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Window.fxml"));//FXML
			BorderPane root = (BorderPane) loader.load();
			root.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());//CSS
			//Gets the screen dimensions
			Rectangle2D screen = Screen.getPrimary().getVisualBounds();
			Scene scene = new Scene(root, screen.getWidth(), screen.getHeight());
			primaryStage.setMaximized(true);
			primaryStage.setMinHeight(screen.getHeight() + 50);
			primaryStage.setMinWidth(screen.getWidth() + 20);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Yoshi's Island: Johan's Quest");
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}