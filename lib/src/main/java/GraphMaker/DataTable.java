package GraphMaker;

import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TablePosition;

/**
 * DataTable creates a JavaFX TableView, and manages its associated data with
 * DataHandler.
 */
public class DataTable {
	TableView table;
	DataHandler data;
	ObservableList<TableColumn> columns;
	VBox tableContainer;

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
		
		// Add right click menu
		ContextMenu rightClickMenu = new ContextMenu();
		MenuItem selectRow = new MenuItem("Select row");
		selectRow.setOnAction(e -> {
			
		});
		
		MenuItem selectColumn = new MenuItem("Select column");
		selectColumn.setOnAction(e -> {
			TablePosition tp = table.getFocusModel().getFocusedCell();
			System.out.println("tableColumn: " + tp.getTableColumn().getText());
			String tableKey = tp.getTableColumn().getText();
			
			int col = tp.getColumn();
			System.out.println("column: " + this.data.getKeyNames().get(col));
		});
		rightClickMenu.getItems().addAll(selectRow, selectColumn);
		this.table.setContextMenu(rightClickMenu);
		
		
		
		
		table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		table.getSelectionModel().setCellSelectionEnabled(true);
//		table.setOnMouseClicked(event -> {
//			// if right click
//			if(event.getButton() == MouseButton.SECONDARY) {
//				
//			}
//			
//		});
		// creates columns from DataHandler.keyNames
		tableColumnFactory();

		// Add a new column functionality
		TextArea taAddColumn = new TextArea();
		Button btAddColumn = new Button("add column");
		btAddColumn.setOnAction(e -> {
			String columnName = taAddColumn.getText();
			if (columnName != "") {
				this.addColumn(columnName);
				taAddColumn.setText("");
				System.out.println(this.data.getKeyNames());
			}
		});
		HBox addColumnHBox = new HBox();
		addColumnHBox.getChildren().addAll(taAddColumn, btAddColumn);
		
		// Delete column functionality
		// NOTE: this needs to be changed so you can select a column and delete it, not put the name in and hit the delete button
//		TextArea deleteColumnTa = new TextArea("test0");
		Button deleteColumnBt = new Button("delete col");
		deleteColumnBt.setOnAction(e -> {
			// get the String from the text area
			
			TablePosition tp = table.getFocusModel().getFocusedCell();
			System.out.println("tableColumn: " + tp.getTableColumn().getText());
			String toDelete = tp.getTableColumn().getText();
			TableColumn<Map, String> td = null;
			ObservableList<TableColumn> columns = this.table.getColumns();
			// loop through the columns looking for one thats text matches toDelete
			for(TableColumn c :  columns) {
				if(c.getText().equals(toDelete)) {
					td = c;
					break;
				}
			}
			// remove the column from columns
			columns.remove(td);
			
		});
		HBox deleteColumnHBox = new HBox();
		deleteColumnHBox.getChildren().addAll(deleteColumnBt);
		
		
		// Add row functionality
		Button addRowBt = new Button("Add row");
		addRowBt.setOnAction(e -> {
			this.addRow();
		});
		HBox addRowHBox = new HBox();
		
		// delete row functionality
		Button deleteRowBt = new Button("delete row");
		deleteRowBt.setOnAction(e -> {
			this.data.data.remove(this.table.getSelectionModel().getSelectedItem());
		});
		addRowHBox.getChildren().addAll(addRowBt, deleteRowBt);
		
		
		tableContainer = new VBox();
		tableContainer.setSpacing(5);
		tableContainer.setPadding(new Insets(10, 0, 0, 10));
		tableContainer.getChildren().addAll(this.getTable(), addColumnHBox, deleteColumnHBox, addRowHBox);
	}

	public DataTable(TableView table, DataHandler data) {
		this.table = table;
		this.data = data;
	}

	public VBox getTableContainer() {
		return this.tableContainer;
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
	 * Creates a new column with the name of string
	 * 
	 * @param key - the name of the column / the value it is stored in the map
	 */
	public void addColumn(String key) {
		this.data.addKeyName(key);
		TableColumn<Map, String> newColumn = new TableColumn<>(key);
		newColumn.setCellValueFactory(new MapValueFactory<String>(key));

		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				System.out.println("Hello World");
			}
		};

		newColumn.addEventHandler(MouseEvent.MOUSE_MOVED, eventHandler);
		this.table.getColumns().add(newColumn);
	}

	/**
	 * runs addColumn on all DataHandler.keyNames
	 */
	private void tableColumnFactory() {
		// gets the keyNames from DataHandler
		ObservableList<String> keyNames = this.data.getKeyNames();
		// loop through keyNames and make all of the columns
		for (int i = 0; i < keyNames.size(); i++) {
			addColumn(keyNames.get(i));
		}
	}

	/** Adds a new Map to DataHandler.data */
	private void addRow() {
		this.data.addRow();
	}
	
	/**
	 * Manages the right click utility.
	 *  */
	private void clickEventHandler(MouseEvent event) {
		
	}
}
