package GraphMaker;


import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.collections.FXCollections;
/** 
 * DataTable creates a JavaFX TableView, and manages its associated data. 
 * */
public class DataTable {
	TableView table;
	DataHandler data;
	
	
	public DataTable() {
		this.data = new DataHandler();
		data.setProperty(0, "test0", "t0");
		data.setProperty(1, "test1", "t1");
		data.setProperty(2, "test2", "t2");
		data.setProperty(3, "test3", "t3");
		data.setProperty(4, "test4", "t4");
		data.setProperty(5, "test5", "t5");
		
		
		
		
		this.table = new TableView();
		
		
		
		
		
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
		this.table =  table;
	}
	
	public void setData(DataHandler data) {
		this.data = data;
	}
	
	
	
	
}		

