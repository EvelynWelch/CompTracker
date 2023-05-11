package GraphMaker;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Manages the data to be displayed in the table.
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
	
	/** 
	 * @param key - the column name / Map key
	 * 
	 * @return -1 if the key already exists, 1 if it adds the new key
	 * */
	public int addKeyName(String key) {
		int status = 0;
		if(keyNames.contains(key)) {
			status = -1;
		} else {
			keyNames.add(key);
			status = 1;
		}
		return status;
	}
	/** Creates a new map with all of the keys with "" as the string*/
	public void addRow() {
		HashMap<String, String> map = new HashMap<>();
		for(String s : keyNames) {
			// TODO: remove the 1 for production
			map.put(s, "1");
		}
		data.add(map);
	}
	/**
	 * removes key from keyNames, and iterates through this.data and removes key from all maps
	 * 
	 * @param key - the value to be removed
	 *  */
	public void removeKey(String key) {
		keyNames.remove(key);
		for(HashMap<String, String> map : this.data) {
			if(map.containsKey(key)) {
				map.remove(key);
			}
			
		}
	}

}
