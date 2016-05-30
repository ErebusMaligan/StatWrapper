package visualization.module;

import javax.swing.JComponent;

import data.ChartData;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Feb 2, 2014, 1:27:11 AM 
 */
public interface VisualizationModule {
	
	public JComponent getLineChart( ChartData data );

	public JComponent getBarChart( ChartData data );
	
	public JComponent getPieChart( ChartData data );
	
	public JComponent getCategoryLineChart( ChartData data );
	
	public JComponent getScatterPlot( ChartData data );
	
	public JComponent getTimeSeriesLineChart( ChartData data );
	
}