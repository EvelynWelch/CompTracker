package GraphMaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.util.StringConverter;
import javafx.collections.FXCollections;

import javafx.util.Callback;

/**
 * DataTable creates a JavaFX TableView, and manages its associated data.
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
//		for(int i = 0; i < this.columns.size(); i++) {
//			this.table.getColumns().add(this.columns.get(i));
//		}
//		
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

		// Change the callback so it can take a Map.
//		Callback<TableColumn<Map, String>, TableCell<Map, String>> cellFactoryForMap = new Callback<TableColumn<Map, String>, TableCell<Map, String>>() {
//			@Override
//			public TableCell call(TableColumn p) {
//				return new TextFieldTableCell(new StringConverter() {
//					@Override
//					public String toString(Object t) {
//						return t.toString();
//					}
//
//					@Override
//					public Object fromString(String string) {
//						return string;
//					}
//				});
//			}
//		};

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
