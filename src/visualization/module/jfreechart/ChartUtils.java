package visualization.module.jfreechart;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;

import data.ChartData;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Feb 5, 2014, 2:01:26 PM 
 */
public class ChartUtils {
	public static void setFixedAutoRange( JFreeChart chart, ChartData data ) {
		XYPlot plot = chart.getXYPlot();
		ValueAxis axis = plot.getDomainAxis();
		axis.setAutoRange( true );
		axis.setFixedAutoRange( data.getViewableRange() );
	}
}