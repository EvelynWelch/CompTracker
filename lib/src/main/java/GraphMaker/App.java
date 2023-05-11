package GraphMaker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Evelyn Welch
 * 
 *         This is the main entry point for the program
 */
//public class App extends Application {
//
//	@Override
//	public void start(Stage primaryStage) {
//
//		StackPane root = new StackPane();
//		Scene scene = new Scene(root, 300, 250);
//
//		primaryStage.setTitle("Hello World!");
//		primaryStage.setScene(scene);
//		primaryStage.show();
//	}
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		System.out.println("Hello World");
//		launch(args);
//	}
//
//}

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class App extends Application {

	public static final String Column1MapKey = "A";
	public static final String Column2MapKey = "B";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Table View Sample");
		stage.setWidth(300);
		stage.setHeight(500);

		final Label label = new Label("Student IDs");
		label.setFont(new Font("Arial", 20));
		
		DataTable table = new DataTable();

		final VBox vbox = new VBox();

		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table.table);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);

		stage.show();
	}

	private ObservableList<Map> generateDataInMap() {
		int max = 10;
		ObservableList<Map> allData = FXCollections.observableArrayList();
		for (int i = 1; i < max; i++) {
			Map<String, String> dataRow = new HashMap<>();

			String value1 = "A" + i;
			String value2 = "B" + i;

			dataRow.put(Column1MapKey, value1);
			dataRow.put(Column2MapKey, value2);

			allData.add(dataRow);
		}
		return allData;
	}

}


