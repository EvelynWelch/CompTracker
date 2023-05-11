package GraphMaker;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;

/**
 * DataTable creates a JavaFX TableView, and manages its associated data with DataHandler.
 */
public class DataTable {
	TableView table;
	DataHandler data;
	ObservableList<TableColumn> columns;

	public DataTable() {
		this.data = new DataHandler();
		data.setProperty(0, "test0", "t0");
		data.setProperty(0, "test1", "t1");
		data.setProperty(0, "test2", "t2");
		data.setProperty(0, "test3", "t3");
		data.setProperty(0, "test4", "t4");
		data.setProperty(0, "test5", "t5");

		this.table = new TableView<>(this.data.data);
		this.table.setEditable(true);

		this.columns = tableColumnFactory();
		System.out.println("columns size(): " + this.columns.size());
		this.table.getColumns().addAll(columns);

		System.out.println("table.getColumns().size(): " + this.table.getColumns().size());

	}

	public DataTable(TableView table, DataHandler data) {
		this.table = table;
		this.data = data;
	}

	public TableView getTable() {
		return this.table;
	}

	public DataHandler getData() {
		return this.data;
	}

	public void setTable(TableView table) {
		this.table = table;
	}

	public void setData(DataHandler data) {
		this.data = data;
	}

	/**
	 * Creates an array list of TableColumns based the data key length of the stored
	 * data in DataHandler
	 * 
	 * @return ArrayList<TableColumn>
	 */
	private ObservableList<TableColumn> tableColumnFactory() {

		ObservableList<TableColumn> tableColumns = FXCollections.observableArrayList();
		// gets the keyNames from DataHandler
		ObservableList<String> keyNames = this.data.getKeyNames();

		// loop through KeyNames and make all of the columns
		for (int i = 0; i < keyNames.size(); i++) {
			TableColumn<Map, String> column = new TableColumn<>(keyNames.get(i));
//			column.setCellValueFactory(new MapValueFactory<String>(keyNames.get(i)));
			column.setCellValueFactory(new MapValueFactory<String>(keyNames.get(i)));

			tableColumns.add(column);
		}

		return tableColumns;
	}
}
