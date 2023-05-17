package GraphMaker;

import java.util.Map;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TablePosition;

import javafx.scene.control.TableCell;

/**
 * DataTable creates a JavaFX TableView, and manages its associated data with
 * DataHandler.
 */
public class DataTable {
	TableView table;
	DataHandler data;
	GridPane graphContainer;
	ObservableList<Number> graphedData;
	XYChart.Series series;
	ObservableList<TableColumn> columns;
	VBox tableContainer;
	LineChart chart;
	private boolean willAdd = true;

	public DataTable() {
		this.chart = new LineChart(new NumberAxis(), new NumberAxis());
//		this.data = new DataHandler(DummyData.makeData(10));
		this.data = new DataHandler();
		this.table = new TableView<>(this.data.getData());
		this.table.setEditable(true);

		// Add right click menu
		this.chart.setAnimated(false);
		this.graphContainer = new GridPane();
		HBox graphButtons = new HBox();
		Button btClearGraph = new Button("Clear");
		graphButtons.getChildren().add(btClearGraph);
		btClearGraph.setOnAction(e -> {
			this.clearGraph();
		});
		this.graphContainer.add(chart, 0, 0);
		this.graphContainer.add(btClearGraph, 0, 1);
		this.table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		this.table.getSelectionModel().setCellSelectionEnabled(true);

		tableColumnFactory();

		// Add a new column functionality
		TextField taAddColumn = new TextField();
		Button btAddColumn = new Button("add column");
		btAddColumn.setOnAction(e -> {
			String columnName = taAddColumn.getText();
			String trimmed = columnName.trim();
			if (!trimmed.equals("")) {
				this.addColumn(trimmed);

				System.out.println(this.data.getKeyNames());
			}
			taAddColumn.setText("");
		});
		HBox addColumnHBox = new HBox();
		addColumnHBox.getChildren().addAll(taAddColumn, btAddColumn);

		// Delete column functionality
		Button deleteColumnBt = new Button("delete col");
		deleteColumnBt.setOnAction(e -> {
			// get the String from the text area
			TablePosition tp = table.getFocusModel().getFocusedCell();
			System.out.println("tableColumn: " + tp.getTableColumn().getText());
			String toDelete = tp.getTableColumn().getText();
			TableColumn<Map, String> td = null;
			ObservableList<TableColumn> columns = this.table.getColumns();
			// loop through the columns looking for one thats text matches toDelete
			for (TableColumn c : columns) {
				if (c.getText().equals(toDelete)) {
					td = c;
					break;
				}
			}
			// remove the column from columns
			columns.remove(td);
			this.data.removeKey(toDelete);
			// update graph.
			Platform.runLater(() -> {
				for (Object x : this.chart.getData()) {
					String n = ((Series) x).getName();
					if (n.equals(toDelete)) {
						this.chart.getData().remove(x);
					}
				}
			});

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

		this.setTableContextMenu();
		
		
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
		newColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		newColumn.setCellValueFactory(new MapValueFactory<String>(key));

		newColumn.setOnEditCommit((CellEditEvent<Map, String> t) -> {
			Map<String, String> map = ((Map<String, String>) t.getTableView().getItems().get(t.getTablePosition().getRow())); 
			String initialValue = map.get(key);		
			try {
				int updatedCellInt = Integer.valueOf(t.getNewValue());
				String updatedCellString = String.valueOf(updatedCellInt);
				map.put(key, updatedCellString);
			} catch(Exception e) {

				t.getTableView().getColumns().get(0).setVisible(false);
				t.getTableView().getColumns().get(0).setVisible(true);
				map.put(key, initialValue);
			}
			Platform.runLater(() -> {
//				System.out.println("running later");
				map.put(key, map.get(key));
			});
			
			// check if the row is graphed and if it is update it
			int r = t.getTablePosition().getRow();
			for (Object x : this.chart.getData()) {
				String n = ((Series) x).getName();
				if (n.equals(key)) {
					this.chart.getData().remove(x);
					this.chart.getData().add(ChartHandler.makeSeries(this.data.getData(), key));

				}
			}

		});
		// this is a hacky fix because the foreach can't access method variables. so it
		// is declared as a class variable
		this.willAdd = true;
		this.table.getColumns().forEach(e -> {
			String text = ((TableColumn) e).getText();
//			System.out.println(text);
			if (text.equals(key)) {
				this.willAdd = false;
			}
		});
		if (this.willAdd) {
			this.table.getColumns().add(newColumn);
		}
		this.willAdd = true;
	}

	/**
	 * runs addColumn on all DataHandler.keyNames
	 */
	public void tableColumnFactory() {
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

	private void clearGraph() {
		this.chart.getData().removeAll(this.chart.getData());
	}
	
	public void setTableContextMenu() {
		ContextMenu rightClickMenu = new ContextMenu();
		MenuItem selectColumn = new MenuItem("Graph column");
		selectColumn.setOnAction(e -> {
			TablePosition tp = table.getFocusModel().getFocusedCell();
			System.out.println("tableColumn: " + tp.getTableColumn().getText());
			String tableKey = tp.getTableColumn().getText();

			int col = tp.getColumn();
			System.out.println("column: " + this.data.getKeyNames().get(col));

			this.chart.getData().add(ChartHandler.makeSeries(this.data.getData(), tableKey));
		});
		rightClickMenu.getItems().addAll(selectColumn);
		this.table.setContextMenu(rightClickMenu);
	}
}
