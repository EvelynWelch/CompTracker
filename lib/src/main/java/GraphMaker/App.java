package GraphMaker;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * @author Evelyn Welch
 * 
 * This is the main entry point for the program
 *         
 * TODO: add the ability to write data to file.
 * TODO: add data import and export
 * TODO: make metadata column always appear last
 * TODO: make it resize on window change
 * TODO: make it so when a csv is loaded, you can add columns
 */

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Table View Sample");
		stage.setWidth(1500);
		stage.setHeight(750);

		final Label label = new Label("GraphMaker");
		label.setFont(new Font("Arial", 20));
		
		DataTable table = new DataTable();

		HBox display = new HBox();
		
		display.getChildren().add(table.getTableContainer());
		display.getChildren().addAll(table.graphContainer);
		HBox csvButtons = new HBox();
		Button btWriteToFile = new Button("write csv");
		btWriteToFile.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save CSV");
			String file = fileChooser.showSaveDialog(stage).toString();
			table.data.writeToCSVFile(file);
		});
		Button btReadFromFile = new Button("read csv");
		btReadFromFile.setOnAction(e -> {		
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Read from CSV");
			File f = fileChooser.showOpenDialog(stage);
			if(f != null) {
				table.data.readFromCSVFile(f.getAbsolutePath());
				table.tableContainer.getChildren().remove(table.table);
				table.table = new TableView<>(table.data.getData());
				table.table.setEditable(true);
				table.tableContainer.getChildren().add(0, table.table);
				table.setTableContextMenu();
				table.table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				table.table.getSelectionModel().setCellSelectionEnabled(true);
				Platform.runLater(() -> {
					table.tableColumnFactory();
//					System.out.println("datahandler.data: " + table.data.data.get(0).get("key1"));
				});
			}			
		});
		table.graphContainer.add(csvButtons, 0, 2);
		csvButtons.getChildren().addAll(btWriteToFile, btReadFromFile);
		

		((Group) scene.getRoot()).getChildren().addAll(display);

		stage.setScene(scene);

		stage.show();
	}

}
