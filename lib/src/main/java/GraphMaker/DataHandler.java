package GraphMaker;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
/** 
 * TODO: figure out what to say here
 * TODO: Make it so if you change the name of a key value, it will go through all of the maps and update them.
 * 
 * */
public class DataHandler {
	ObservableList<HashMap<String, SimpleStringProperty>> data;
	
	/**
	 * Assigns this.data to and empty ObersvableList
	 * */
	public DataHandler() {
		data = FXCollections.observableArrayList();
	}
	/**
	 * Creates an ObservableList out the passed ArrayList
	 * */
	public DataHandler(ArrayList<HashMap<String, SimpleStringProperty>> data) {
		this.data = FXCollections.observableArrayList(data);
	}
	
	public ObservableList<HashMap<String, SimpleStringProperty>> getData() {
		return this.data;
	}
	
	public void setData(ObservableList<HashMap<String, SimpleStringProperty>> data) {
		this.data = data;
	}
	
	/** 
	 * @param index - the target index of the ObservableList data
	 * @param key - the key value on the HashMap
	 * 
	 * @return returns the key value at index
	 * */
	public SimpleStringProperty getProperty(int index, String key) {
		return this.data.get(index).get(key);
	}
	
	/** 
	 * assigns value to key at index
	 * 
	 * @param index - the target index of the ObservableList data
	 * @param key - the key value on the HashMap 
	 * @param value - The String to set the key to
	 * */
	public void setProperty(int index, String key, String value) {		
		HashMap<String, SimpleStringProperty> map = this.data.get(index);
		// If the key isn't found in the map create a new SimpleStringProperty set it.
		if(!map.containsKey(key)) {
			SimpleStringProperty v = new SimpleStringProperty();
			v.set(value);
			map.put(key, v);
		} else { 
			// update the observable strings value
			map.get(key).set(value);
		}
	}

}
