package GraphMaker;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/** 
 * Manages creating charts
 * 
 * The general idea is to be able to pass columns as x and y values.
 * This means that despite everything being saved as a string everything needs to be an Integer value
 * 
 * All imported data should be timestamped. and have the ability to change the time. A lot of the competative data should be
 * viewed as information over time. though there are other things you can look at like kills over deaths and stuff.
 * 
 * 
 * 
 * */
public class ChartHandler {
	
	public static LineChart createLineChart() {
		
		final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        
        lineChart.getData().add(series);
		return lineChart;
	}
	
	public static CategoryAxis createCategoryAxis() {
		return null;
	}
	
	public static NumberAxis createNumberAxis() {
		return null;
		
	}
	
	public static XYChart createXYChart() {
		return null;
	}
	
}
