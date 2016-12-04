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
 * Created: Feb 5, 2014, 10:56:08 PM 
 */
public class PieChartTest {

	public static void main( String args[] ) {		
		//Generate a data set
		ArrayList<DataSet<?, ?>> data = new ArrayList<DataSet<?, ?>>();
		ChartData cd = new ChartData( data, "Publishing Trials", "Trial Runs", "Objects Sent" );
		String[] cat = new String[] { "Publisher 1", "Publisher 2", "Publisher 3" };
		String[] series = new String[] { "Trail 1" };
		for ( String s : series ) {
			DataSet<String, Integer> ds = new DataSet<String, Integer>( s );
			for ( String c : cat ) {
				ds.add( new DataPair<String, Integer>( c, (int)( Math.random() * 50 ) ) );
			}
			data.add( ds );
		}
		
		//create a chart from the data set
		Chart chart = new Chart( cd, Chart.ChartType.PIE_CHART );
		
		//create a standard frame to display chart
		JFrame f = new JFrame();
		f.setSize( 400, 600 );
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		f.add( chart.getVisualization() );
		f.setVisible( true );
	}	
}