package GraphMaker;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Manages creating charts
 * 
 * The general idea is to be able to pass columns as x and y values. This means
 * that despite everything being saved as a string everything needs to be an
 * Integer value
 * 
 * All imported data should be timestamped. and have the ability to change the
 * time. A lot of the competative data should be viewed as information over
 * time. though there are other things you can look at like kills over deaths
 * and stuff.
 */
public class ChartHandler {

	/**
	 * Creates a LineChart from data in key position on x axis is determined by the
	 * position in data
	 * 
	 * @param data - the data displayed in DataTable
	 * @param key  - the key to be displayed
	 */
//	public static LineChart createLineChart(ObservableList<HashMap<String, String>> data, String key) {
//		final NumberAxis xAxis = new NumberAxis();
//		final NumberAxis yAxis = new NumberAxis();
//		xAxis.setLabel("");
//		yAxis.setLabel(key);
//		// creating the chart
//		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
//
//		// defining a series
//		XYChart.Series series = new XYChart.Series();
//
//		int c = 1;
//		for (HashMap<String, String> map : data) {
//			System.out.println(map.get(key));
//			series.getData().add(new XYChart.Data(c, Integer.valueOf(map.get(key))));
//			c++;
//		}
//		lineChart.getData().add(series);
//		return lineChart;
//	}

	public static XYChart.Series makeSeries(ObservableList<HashMap<String, String>> data, String key) {
		XYChart.Series series = new XYChart.Series();
		series.setName(key);

		int c = 1;
		for (HashMap<String, String> map : data) {
			System.out.println(map.get(key));
			if (!map.get(key).equals("")) {
				series.getData().add(new XYChart.Data(c, Integer.valueOf(map.get(key))));
			}
			c++;
		}
		return series;
	}

	public static ObservableList<Number> makeNumberList(ObservableList<HashMap<String, String>> data, String key) {
		ObservableList<Number> r = FXCollections.observableArrayList();
		for (HashMap<String, String> map : data) {
			if (!map.get(key).equals("")) {
				r.add(Integer.valueOf(map.get(key)));
			}
		}
		return r;

	}

	public static XYChart.Series<Number, Number> makeSeriesData(ObservableList<HashMap<String, String>> data,
			String key) {
		XYChart.Series series = new XYChart.Series();
		series.setName(key);
		series.getData().addAll(makeNumberList(data, key));
		return series;
	}

}
