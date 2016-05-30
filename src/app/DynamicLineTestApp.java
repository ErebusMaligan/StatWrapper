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
 * Created: Feb 5, 2014, 12:48:21 PM 
 */
public class DynamicLineTestApp {

	public static void main( String args[] ) {
		
		//Generate a data set
		final DataSet<Integer, Double> ds = new DataSet<Integer, Double>( "test" );
		for ( int i = 0; i < 20; i++ ) {
			DataPair<Integer, Double> p = new DataPair<Integer, Double>( i, (double)Math.random() * 100 );
			ds.add( p );
		}
		ArrayList<DataSet<?, ?>> l = new ArrayList<DataSet<?, ?>>();
		l.add( ds );
		
		//create a chart from the data set
		ChartData cd = new ChartData( l, "Some Chart", "Time (Seconds)", "Amount" );
		cd.setViewableRange( 70 );
		cd.setDynamic( true );
		Chart chart = new Chart( cd, Chart.ChartType.LINE_CHART );
		
		//create a standard frame to display chart
		JFrame f = new JFrame();
		f.setSize( 400, 600 );
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		f.add( chart.getVisualization() );
		f.setVisible( true );
		
		new Thread() {
			public void run() {
				int i = 20;
				while ( true ) {
					try {
						Thread.sleep( 1000 );
					} catch ( InterruptedException e ) {
						e.printStackTrace();
					}
					ds.add( new DataPair<Integer, Double>( i++, (double)Math.random() * 100 ) );					
				}
			}
		}.start();
	}
	
}
