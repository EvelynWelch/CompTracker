package GraphMaker;

import java.util.HashMap;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DummyData {
	public static String[] keys = {"key1", "key2", "key3", "key4", "key5", "key6", "key7"};
	public static Random rand = new Random();
	
	public static HashMap<String, String> makeData(String[] keys){
		HashMap<String, String> d = new HashMap<>();
		for(String k : keys) {
			d.put(k, String.valueOf(rand.nextInt(51)));
		}
		return d;
	}
	
	public static ObservableList<HashMap<String, String>> makeData(int c){
		ObservableList<HashMap<String, String>> r = FXCollections.observableArrayList();
		for(int i = 0; i < c; i++) {
			r.add(makeData(keys));
		}
		return r;
	}
	
}
