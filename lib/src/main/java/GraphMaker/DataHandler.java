package GraphMaker;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

/**
 * TODO: figure out what to say here TODO: Make it so if you change the name of
 * a key value, it will go through all of the maps and update them. TODO: Make
 * keyCount and keyNames update correctly.
 * 
 */
public class DataHandler {
	ObservableList<HashMap<String, String>> data;
	ObservableList<String> keyNames;

	private int keyCount;

	/**
	 * Assigns this.data to and empty ObersvableList
	 */
	public DataHandler() {
		data = FXCollections.observableArrayList();
		keyCount = 0;
		keyNames = FXCollections.observableArrayList();
	}

	/**
	 * Creates an ObservableList out the passed ArrayList
	 */
	public DataHandler(ArrayList<HashMap<String, String>> data) {
		this.data = FXCollections.observableArrayList(data);
	}

	public ObservableList<HashMap<String, String>> getData() {
		return this.data;
	}

	public void setData(ObservableList<HashMap<String, String>> data) {
		this.data = data;
	}

	public int getKeyCount() {
		return this.keyCount;
	}

	private void setKeyCount(int count) {
		this.keyCount = count;
	}

	public int keyNamesSize() {
		return this.keyNamesSize();
	}

	public ObservableList<String> getKeyNames() {
		return this.keyNames;
	}

	/**
	 * @param index - the target index of the ObservableList data
	 * @param key   - the key value on the HashMap
	 * 
	 * @return returns the key value at index
	 */
	public String getProperty(int index, String key) {
		return this.data.get(index).get(key);
	}

	/**
	 * assigns value to key at index
	 * 
	 * @param index - the target index of the ObservableList data
	 * @param key   - the key value on the HashMap
	 * @param value - The String to set the key to
	 * 
	 * @return - -1 = error, 0 = overwrote element, 1 = added new element;
	 */
	public int setProperty(int index, String key, String value) {
		int status = 0;
		try {
			HashMap<String, String> map;
			// check to see if the map exists, if not create a new one and add it to DataHandler
			if(this.data.size() <= index) {
				map = new HashMap<String, String>();
				this.data.add(index, map);
			}
			// Requery the map(?);
			map = this.data.get(index);
			// If the key isn't found in the map create a new SimpleStringProperty set it.
			if (!map.containsKey(key)) {

				map.put(key, value);
				// increment the key counter
				keyCount += 1;
				// add the name of the key
				keyNames.add(key);
				return 1;
			} else {
				// update the observable strings value
				map.put(key, value);
				return 0;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			return -1;
		}

	}

}
