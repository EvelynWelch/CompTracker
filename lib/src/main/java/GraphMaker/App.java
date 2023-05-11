package GraphMaker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author Evelyn Welch
 * 
 *         This is the main entry point for the program
 */

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Table View Sample");
		stage.setWidth(300);
		stage.setHeight(500);

		final Label label = new Label("GraphMaker");
		label.setFont(new Font("Arial", 20));

		DataTable table = new DataTable();

		final VBox vbox = new VBox();

		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table.getTable());

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);

		stage.show();
	}

}
