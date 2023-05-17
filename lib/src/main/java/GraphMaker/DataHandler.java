package GraphMaker;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.File; // Import the File class
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors

/**
 * Manages the data to be displayed in the table.
 * 
 * If constructed with a list, the item at index 0 is used to generate keyNames
 * 
 */
public class DataHandler {
	ObservableList<HashMap<String, String>> data;
	ObservableList<String> keyNames;;

	// they key in this.data that holds the metadata about the graph.
	private static String metaKey = "metadata";

	/** Assigns this.data to and empty ObersvableList */
	public DataHandler() {
//		data = FXCollections.observableArrayList();
		data = populateEmptyData();
//		populateEmptyData();
		keyNames = FXCollections.observableArrayList();
		// add a metadata key to all maps
//		keyNames.add(this.metaKey);
	}

	private ObservableList<HashMap<String, String>> populateEmptyData() {
		ObservableList<HashMap<String, String>> d = FXCollections.observableArrayList();
		for (int i = 0; i < 25; i++) {
			d.add(i, new HashMap<String, String>());
		}
		return d;
	}

	/** Creates an ObservableList out the passed ArrayList */
	public DataHandler(ArrayList<HashMap<String, String>> data) {
		this.data = FXCollections.observableArrayList(data);
		this.keyNames = generateKeyNames();
	}

	public DataHandler(ObservableList<HashMap<String, String>> data) {
		this.data = data;
		this.keyNames = generateKeyNames();
	}

	/** @return the HashMap at this.data */
	public ObservableList<HashMap<String, String>> getData() {
		return this.data;
	}

	/** @param data - an observable list of string string hash maps */
	public void setData(ObservableList<HashMap<String, String>> data) {
		this.data = data;
	}

	/** the amount of keys each Map in Data has */
	public int getKeyCount() {
		return this.keyNames.size();
	}

	/** A list of all of the keys that the maps in this.data have */
	public ObservableList<String> getKeyNames() {
		return this.keyNames;
	}

	/**
	 * gets the HashMap in this.data[index]
	 */
	public HashMap<String, String> getRow(int index) {
		return this.data.get(index);
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

	/** creates keyNames from the first element in data */
	private ObservableList<String> generateKeyNames() {
		return FXCollections.observableArrayList(this.data.get(0).keySet());
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
		try {
			HashMap<String, String> map;
			// check to see if the map exists, if not create a new one and add it to
			// DataHandler
			if (this.data.size() <= index) {
				map = new HashMap<String, String>();
				this.data.add(index, map);
			}
			map = this.data.get(index);
			// If the key isn't found in the map create a new SimpleStringProperty set it.
			if (!map.containsKey(key)) {
				map.put(key, value);
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
	 */
	public int addKeyName(String key) {
		int status = 0;
		if (keyNames.contains(key)) {
			status = -1;
		} else {
			keyNames.add(key);
			status = 1;
		}
		for (HashMap<String, String> m : this.data) {
			if (!m.containsKey(key)) {
				m.put(key, "");
			}
		}
		return status;
	}

	/** Creates a new map with all of the keys with "" as the string */
	public void addRow() {
		HashMap<String, String> map = new HashMap<>();
		for (String s : keyNames) {
			// TODO: remove the 1 for production
			map.put(s, "0");
		}
		data.add(map);
	}

	/**
	 * removes key from keyNames, and iterates through this.data and removes key
	 * from all maps
	 * 
	 * @param key - the value to be removed
	 */
	public void removeKey(String key) {
		keyNames.remove(key);
		for (HashMap<String, String> map : this.data) {
			if (map.containsKey(key)) {
				map.remove(key);
			}

		}
	}

	/**
	 * this method converts the string from a Map's metaKey to a String[] the
	 * metadata is stored as a string with the data separated by "#"s this function
	 * splits the string and returns a string array example: #prop1 #prop2 #etc
	 * returns ["prop1", "prop2", "etc"]
	 * 
	 * @param metaDataString - a string with metadata separated by '#'s
	 * @return
	 * 
	 * @return a String[] of the metadata
	 */
	public String[] extractMetaData(String metaDataString) {
		return metaDataString.split("#");
	}

	/**
	 * @param map - takes a Map and extracts the metadata store in metaKey
	 * @return String[] of the extracted metadata
	 */
	public String[] getMapMetaData(HashMap<String, String> map) {
		if (!map.containsKey(this.metaKey)) {
			map.put(metaKey, "");
		}
		return this.extractMetaData(map.get(this.metaKey));
	}

	/**
	 * Assigns metadata to the Map
	 * 
	 * @param map      - The Map to add metadata to
	 * @param metadata - the metadata String
	 */
	public void setMapMetaData(HashMap<String, String> map, String metadata) {
		map.put(this.metaKey, metadata);
	}

	/**
	 * Adds tag to map's metadata NOTE: this function adds the "#"
	 * 
	 * @param map - map to add metadata to
	 * @param tag - metadata tag to be added
	 */
	public static void addMetaTag(HashMap<String, String> map, String tag) {
		String currentMetaData = map.get(metaKey);
		if (!currentMetaData.contains(tag)) {
			currentMetaData += "#" + tag;
		}
		map.put(metaKey, currentMetaData);
	}

	/** outputs the table data into a csv file */
	public void writeToCSVFile(String filePath) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					_writeToCSVFile(filePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/**
	 * imports data froma csv file
	 * 
	 * @throws IOException
	 */
	private void _writeToCSVFile(String filePath) throws IOException {

		FileWriter writer = new FileWriter(filePath, false);
		StringBuilder out = new StringBuilder();
		for (String key : this.keyNames) {
//			key.strip();
			key.replace(" ", "_");
			key.replace("'", "");
			out.append(key + ",");
		}
		out.append("\n");

		for (HashMap<String, String> map : this.data) {
			for (String key : this.keyNames) {
				if (map.containsKey(key)) {
					out.append(map.get(key) + ",");
				} else {
					out.append(",");
				}
			}
			out.append("\n");
		}

		writer.write(out.toString());
		writer.close();
	}

	public void readFromCSVFile(String filePath) {
	
				try {
					
					this.data = FXCollections.observableArrayList(_readFromCSVFile(filePath));
				} catch (IOException e) {
					e.printStackTrace();
				}

	}

	private ArrayList<HashMap<String, String>> _readFromCSVFile(String filePath) throws IOException {
//		this.data = FXCollections.observableArrayList();
		ArrayList<HashMap<String, String>> dataMaps = new ArrayList<>();		
		File f = new File(filePath);
		StringBuffer contents = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(f));
		boolean firstLine = true;
		// get key names
		String nextLine = reader.readLine();
		while( nextLine != null && nextLine.length()!=0) {
			if (firstLine) {
				String[] dataKeys = nextLine.split(",");
				this.keyNames = FXCollections.observableArrayList(dataKeys);
				firstLine = false;
			} else {
				HashMap<String, String> da = new HashMap<>();
				System.out.println(nextLine);
				char[] chars = nextLine.toCharArray();
				StringBuilder nextInput = new StringBuilder();
				int counter = 0;
				for(char c : chars) {
					if(c != ',') {
						nextInput.append(c);
						
					} else {
						da.put(this.keyNames.get(counter), nextInput.toString());
						nextInput = new StringBuilder();
						counter++;
					}
				}

			
				dataMaps.add(da);
			}
			nextLine = reader.readLine();
		}
		reader.close();
		return dataMaps;
		
	}
}
