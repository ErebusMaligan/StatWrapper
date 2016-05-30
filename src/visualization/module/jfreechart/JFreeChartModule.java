package visualization.module.jfreechart;

import javax.swing.JComponent;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import visualization.module.VisualizationModule;
import data.ChartData;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Feb 2, 2014, 3:41:17 AM 
 */
public class JFreeChartModule implements VisualizationModule {

	public JComponent getLineChart( ChartData data ) {
		JFreeChart chart = ChartFactory.createXYLineChart( data.getName(), data.getXName(), data.getYName(), DataConversionUtils.toXYSeriesCollection( data, data.getDynamic() ) );
		if ( data.getDynamic() ) {
			ChartUtils.setFixedAutoRange( chart, data );
		}
		return new ChartPanel( chart );
	}

	@Override
	public JComponent getBarChart( ChartData data ) {
		return new ChartPanel( ChartFactory.createBarChart( data.getName(), data.getXName(), data.getYName(), DataConversionUtils.toCategoryDataset( data, data.getDynamic() ) ) );
	}

	@Override
	public JComponent getPieChart( ChartData data ) {
		return new ChartPanel( ChartFactory.createPieChart( data.getName(), DataConversionUtils.toPieDataset( data ) ) );
	}

	@Override
	public JComponent getCategoryLineChart( ChartData data ) {
		return new ChartPanel( ChartFactory.createLineChart( data.getName(), data.getXName(), data.getYName(), DataConversionUtils.toCategoryDataset( data, data.getDynamic() ) ) );
	}

	@Override
	public JComponent getScatterPlot( ChartData data ) {
		JFreeChart chart = ChartFactory.createScatterPlot( data.getName(), data.getXName(), data.getYName(), DataConversionUtils.toXYSeriesCollection( data, data.getDynamic() ) );
		return new ChartPanel( chart );
	}

	@Override
	public JComponent getTimeSeriesLineChart( ChartData data ) {
		JFreeChart chart = ChartFactory.createTimeSeriesChart( data.getName(), data.getXName(), data.getYName(), DataConversionUtils.toTimeSeriesCollection( data, data.getDynamic() ) );
		return new ChartPanel( chart );
	}
}