package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.XYPlot;

import visualization.chart.Chart;
import data.ChartData;
import data.DataPair;
import data.DataSet;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Feb 5, 2014, 2:27:26 PM 
 */
public class DualChartTest {
	
	public static void main( String args[] ) {
			
			//Generate a data set
			final DataSet<Integer, Double> ds = new DataSet<Integer, Double>( "test" );
			for ( int i = 0; i < 20; i++ ) {
				DataPair<Integer, Double> p = new DataPair<Integer, Double>( i, (double)Math.random() * 100 );
				ds.add( p );
			}
			ArrayList<DataSet<?, ?>> l = new ArrayList<DataSet<?, ?>>();
			l.add( ds );

			
			final DataSet<Integer, Double> ds2 = new DataSet<Integer, Double>( "test" );
			for ( int i = 0; i < 20; i++ ) {
				DataPair<Integer, Double> p = new DataPair<Integer, Double>( i, (double)Math.random() * 100 );
				ds2.add( p );
			}
			ArrayList<DataSet<?, ?>> l2 = new ArrayList<DataSet<?, ?>>();
			l2.add( ds2 );
			
			//create a chart from the data set
			ChartData cd = new ChartData( l, "Some Chart", "Time (Seconds)", "Amount" );
			cd.setViewableRange( 30 );
			cd.setDynamic( true );
			Chart chart = new Chart( cd, Chart.ChartType.LINE_CHART );
			
			ChartData cd2 = new ChartData( l2, "Some Other Chart", "Time(Seconds)", "Amount" );
			cd2.setViewableRange( 30 );
			cd.setDynamic( true );
			Chart chart2 = new Chart( cd2, Chart.ChartType.LINE_CHART );
			
			
			//create a standard frame to display chart
			JFrame f = new JFrame();
			f.setSize( 400, 600 );
			f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			f.setLayout( new BoxLayout( f.getContentPane(), BoxLayout.Y_AXIS ) );
			
			JComponent ch1 = chart.getVisualization();
//			ch1.setMaximumSize( new Dimension( 9000, 300 ) );
			f.add( ch1, BorderLayout.NORTH );
			
			JComponent ch2 = chart2.getVisualization();
			XYPlot p = (XYPlot)( (ChartPanel)ch2 ).getChart().getPlot();
			p.getRenderer().setSeriesPaint( 0, Color.BLUE );
//			ch2.setMaximumSize( new Dimension( 9000, 300 ) );
			f.add( ch2, BorderLayout.SOUTH );
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
						ds.add( new DataPair<Integer, Double>( i, (double)Math.random() * 100 ) );
						ds2.add( new DataPair<Integer, Double>( i++, (double)Math.random() * 100 ) );
					}
				}
			}.start();
	}

}
