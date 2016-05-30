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
 * Created: Feb 1, 2014, 5:17:46 AM 
 */
public class DualLineTestApp {
	
	public static void main( String args[] ) {
		//Generate a data set
		DataSet<Integer, Double> ds = new DataSet<Integer, Double>( "test" );
		DataSet<Integer, Double> ds2 = new DataSet<Integer, Double>( "test2" );
		for ( int i = 0; i < 20; i++ ) {
			double val = (double)( Math.random() * 100 );
			DataPair<Integer, Double> p = new DataPair<Integer, Double>( i, val );
			DataPair<Integer, Double> p2 = new DataPair<Integer, Double>( i, (double)( val * -1 ) );
			ds.add( p );
			ds2.add( p2 );
		}
		ArrayList<DataSet<?, ?>> l = new ArrayList<DataSet<?, ?>>();
		l.add( ds );
		l.add( ds2 );
		
		//create a chart from the data set
		Chart chart = new Chart( new ChartData( l, "Some Chart", "Time (Seconds)", "Amount" ), Chart.ChartType.LINE_CHART );
		
		//create a standard frame to display chart
		JFrame f = new JFrame();
		f.setSize( 400, 600 );
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		f.add( chart.getVisualization() );
		f.setVisible( true );
	}
}