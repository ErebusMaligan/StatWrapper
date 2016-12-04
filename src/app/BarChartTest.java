package app;

import java.util.ArrayList;

import javax.swing.JFrame;

import visualization.chart.Chart;
import data.ChartData;
import data.DataPair;
import data.DataSet;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Feb 5, 2014, 4:02:11 PM 
 */
public class BarChartTest {
	
	public static void main( String args[] ) {		
		//Generate a data set
		ArrayList<DataSet<?, ?>> data = new ArrayList<DataSet<?, ?>>();
		ChartData cd = new ChartData( data, "Publishing Trials", "Trial Runs", "Objects Sent" );
		String[] series = new String[] { "Publisher 1 ", "Publisher 2", "Publisher 3" };
		String[] cat = new String[] { "Trial 1", "Trial 2", "Trial 3", "Trial 4" };
		for ( String s : series ) {
			DataSet<String, Integer> ds = new DataSet<String, Integer>( s );
			for ( String c : cat ) {
				ds.add( new DataPair<String, Integer>( c, (int)( Math.random() * 50 ) ) );
			}
			data.add( ds );
		}
		//create a chart from the data set
		Chart chart = new Chart( cd, Chart.ChartType.BAR_CHART );
		
		//create a standard frame to display chart
		JFrame f = new JFrame();
		f.setSize( 400, 600 );
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		f.add( chart.getVisualization() );
		f.setVisible( true );
	}
}