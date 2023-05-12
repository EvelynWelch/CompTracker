package GraphMaker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author Evelyn Welch
 * 
 * This is the main entry point for the program
 *         
 * TODO: Figure out how to make deleting a column more elegant
 * TODO: make it so you can add and delete elements from table
 * TODO: Make it display a graph
 * TODO: figure out what to say here 
 * TODO: Make it so if you change the name of a key value, it will go through all of the maps and update them. 
 * TODO: Make keyCount and keyNames update correctly.
 * TODO: add the ability to write data to file.
 * TODO: Make it correctly handle keyNames so they stay in sync.
 * TODO: add data import and export
 * TODO: make the table editable.
 * TODO: make it so you have rightclick option
 *  	- select current column
 *      - select current row
 * 
 */

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Table View Sample");
		stage.setWidth(750);
		stage.setHeight(750);

		final Label label = new Label("GraphMaker");
		label.setFont(new Font("Arial", 20));

		DataTable table = new DataTable();

		HBox display = new HBox();
		display.getChildren().addAll(table.getTableContainer(), ChartHandler.createLineChart());
		

		((Group) scene.getRoot()).getChildren().addAll(display);

		stage.setScene(scene);

		stage.show();
	}

}
