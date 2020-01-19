package visualization.module.jfreechart;

import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.ShapeUtils;

import data.ChartData;
import data.ChartData.TimeGranularity;
import export.exporter.ExportOptions;
import export.exporter.format.NanoTimeFormatter;
import utils.FormattingUtils;
import visualization.chart.Chart;

/**
 * Way to encapsulate some standard chart visual adjustment methods
 * 
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Feb 12, 2014, 12:52:27 PM 
 */
public class JFreeChartEnhancer {
	
	/**
	 * The additional settings that should be performed on all time series charts to make them look better and more consistent throughout the program
	 * 
	 * @param cp ChartPanel containing a time series category chart - if a non category chart panel is used this will throw errors
	 */
	public static void enhanceStandardTimeSeriesChart( ChartPanel cp ) {
		CategoryPlot plot = cp.getChart().getCategoryPlot();
		plot.getRenderer().setDefaultItemLabelsVisible( true );
		plot.getRenderer().setSeriesShape( 0, ShapeUtils.createDiamond( 3 ) );
		if ( plot.getRenderer() instanceof LineAndShapeRenderer ) {
			( (LineAndShapeRenderer)plot.getRenderer() ).setSeriesShapesVisible( 0, true );
		}
		plot.getDomainAxis().setCategoryLabelPositions( CategoryLabelPositions.UP_45 );
		plot.getDomainAxis().setLowerMargin( 0 );
		plot.getDomainAxis().setUpperMargin( 0 );
	}
	
	public static void enhanceXYTimeSeriesChart( ChartPanel cp, NumberTickUnit num ) {
		XYPlot plot = cp.getChart().getXYPlot();
		( (NumberAxis)plot.getDomainAxis() ).setTickUnit( num );
		plot.getDomainAxis().setVerticalTickLabels( true );
		plot.getRenderer().setDefaultItemLabelsVisible( true );
		if ( plot.getRenderer() instanceof XYLineAndShapeRenderer ) {
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)plot.getRenderer();
			renderer.setSeriesShape( 0, ShapeUtils.createDiamond( 3 ) );
			renderer.setSeriesShapesVisible( 0, true );
		}
		plot.getDomainAxis().setLowerMargin( 0 );
		plot.getDomainAxis().setUpperMargin( 0 );
	}
	
	public static void enhanceIndividualXYPlot( ChartPanel cp, int i ) {
		XYPlot plot = cp.getChart().getXYPlot();
		if ( plot.getRenderer() instanceof XYLineAndShapeRenderer ) {
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)plot.getRenderer();
			renderer.setSeriesShape( i, ShapeUtils.createDiamond( 3 ) );
			renderer.setSeriesShapesVisible( i, true );
			if ( i > 50 ) {
				renderer.setSeriesVisibleInLegend( i, false );
			}
		}
	}
	
	public static void setStandardChartColors( ChartPanel cp, Color text, Color background, Color chartBG, Color chartOutline ) {
		JFreeChart chart = cp.getChart();
		chart.getTitle().setBackgroundPaint( background );
		chart.getLegend().setBackgroundPaint( background );
		chart.setBackgroundPaint( background );
		chart.getPlot().setBackgroundPaint( background );
		chart.getTitle().setPaint( text );
		chart.getLegend().setItemPaint( text );
		chart.getPlot().setOutlinePaint( chartOutline );
	}
	
	/**
	 * If the plot is neither a CategoryPlot or XYPlot... the method is just a no-op
	 * 
	 * @param cp ChartPanel being acted on
	 * @param text Color to change the axis lines to
	 */
	public static void setPlotAxisColor( ChartPanel cp, Color text ) {  //yes, every one of these calls has to be duplicated in each block, they don't inherit common paint color calls
		try {
			CategoryPlot plot = cp.getChart().getCategoryPlot();
			plot.getDomainAxis().setLabelPaint( text );
			plot.getDomainAxis().setTickLabelPaint( text );
			plot.getRangeAxis().setLabelPaint( text );
			plot.getRangeAxis().setTickLabelPaint( text );
		} catch ( ClassCastException e ) {  //means it's an xy or some other plot
			try {
				XYPlot plot = cp.getChart().getXYPlot();
				plot.getDomainAxis().setLabelPaint( text );
				plot.getDomainAxis().setTickLabelPaint( text );
				plot.getRangeAxis().setLabelPaint( text );
				plot.getRangeAxis().setTickLabelPaint( text );
			} catch ( ClassCastException e2 ) {
				//do nothing
			}
		}
	}
	
	public static void setPlotShowShapes( ChartPanel cp, Shape shape ) {
		try {
			CategoryPlot plot = cp.getChart().getCategoryPlot();
			plot.getRenderer().setDefaultItemLabelsVisible( true );
			if ( plot.getDataset() != null ) {  //if dataset is empty (as in a dynamic chart that hasn't been filled) this will be null
				for ( int i = 0; i < plot.getDataset().getRowCount(); i++ ) {  //row count is number of labeled data plots... the participants in the legend
					plot.getRenderer().setSeriesShape( i, shape );
					if ( plot.getRenderer() instanceof LineAndShapeRenderer ) {
						( (LineAndShapeRenderer)plot.getRenderer() ).setSeriesShapesVisible( i, true );
					}
				}
			}
		} catch ( ClassCastException e ) {  //means it's an xy or some other plot
			try {
				XYPlot plot = cp.getChart().getXYPlot();
				plot.getRenderer().setDefaultItemLabelsVisible( true );
				if ( plot.getDataset() != null ) {
					for ( int i = 0; i < plot.getDataset().getSeriesCount(); i++ ) {
						plot.getRenderer().setSeriesShape( i, shape );
						if ( plot.getRenderer() instanceof XYLineAndShapeRenderer ) {
							( (XYLineAndShapeRenderer)plot.getRenderer() ).setSeriesShapesVisible( i, true );
						}
					}
				}
			} catch ( ClassCastException e2 ) {
				//do nothing
			}
		}
	}
	
	public static void setAntiAliasingOn( ChartPanel cp ) {
		cp.getChart().setRenderingHints( new RenderingHints( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON ) );
	}
	

	public static void angleDomainTickLabels( ChartPanel cp ) {
		try {
			CategoryPlot plot = cp.getChart().getCategoryPlot();
			plot.getDomainAxis().setCategoryLabelPositions( CategoryLabelPositions.UP_45 );
		} catch ( ClassCastException e ) {  //means it's an xy or some other plot
			try {
				XYPlot plot = cp.getChart().getXYPlot();
				plot.getDomainAxis().setVerticalTickLabels( true );
			} catch ( ClassCastException e2 ) {
				//do nothing
			}
		}
	}
	
	public static void setNanoTimeExportOptions( Chart ch ) {
		ExportOptions opt = new ExportOptions();
		opt.setX( new NanoTimeFormatter() );
		ch.setExportOptions( opt );
	}
	
	public static Shape getStandardShape() {
		return ShapeUtils.createDiamond( 3 );
	}
	
	public static NumberTickUnit getTimeNumberTick() {
		return new NumberTickUnit( 2.5 ) {
			private static final long serialVersionUID = 1L;
			@Override
			public String valueToString( double value ) {
				return FormattingUtils.formatNanoTime( value );
			}
		};
	}
	
	public static void setTimeSeriesDomainTickUnits( ChartPanel cp, final SimpleDateFormat f, ChartData.TimeGranularity time, int multiple ) {
		XYPlot plot = cp.getChart().getXYPlot();
		DateAxis axis = ( (DateAxis)plot.getDomainAxis() );
		axis.setTickUnit( new DateTickUnit( getTimePeriod( time ), multiple, f ) );
		axis.setAutoRange( true );
	}
	
	public static void setNumberRangeAxisTickUnits( ChartPanel cp, int num ) {
		ValueAxis axis = null;
		try {
			XYPlot plot = cp.getChart().getXYPlot();
			axis = plot.getRangeAxis();
		} catch ( ClassCastException e ) {
			try {
				CategoryPlot plot = cp.getChart().getCategoryPlot();
				axis = plot.getRangeAxis();
			} catch ( ClassCastException e2 ) {
			}
		}
		( (NumberAxis)axis ).setTickUnit( new NumberTickUnit( num ) );
	}
	
	private static DateTickUnitType getTimePeriod( TimeGranularity time ) {
		DateTickUnitType ret = null;
		switch ( time ) {
			case YEAR: ret = DateTickUnitType.YEAR; break;
			case MONTH: ret = DateTickUnitType.MONTH; break;
			case DAY: ret = DateTickUnitType.DAY; break;
			case HOUR: ret = DateTickUnitType.HOUR; break;
			case MINUTE: ret = DateTickUnitType.MINUTE; break;
			case SECOND: ret = DateTickUnitType.SECOND; break;
			case MILLISECOND: ret = DateTickUnitType.MILLISECOND; break;
		}
		return ret;
	}
}