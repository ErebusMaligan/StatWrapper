package visualization.module.jfreechart;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeriesCollection;

import visualization.module.jfreechart.conversion.CategoryConversionUtils;
import visualization.module.jfreechart.conversion.PieConversionUtils;
import visualization.module.jfreechart.conversion.TimeSeriesConversionUtils;
import visualization.module.jfreechart.conversion.XYSeriesConversionUtils;
import data.ChartData;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Feb 2, 2014, 3:46:34 AM 
 */
public class DataConversionUtils {

	public static TimeSeriesCollection toTimeSeriesCollection( ChartData cd, boolean dynamic ) {
		return TimeSeriesConversionUtils.toTimeSeriesCollection( cd, dynamic );
	}
	
	public static XYSeriesCollection toXYSeriesCollection( ChartData cd, boolean dynamic ) {
		return XYSeriesConversionUtils.toXYSeriesCollection( cd, dynamic );
	}
	
	public static CategoryDataset toCategoryDataset( ChartData cd, boolean dynamic ) {
		return CategoryConversionUtils.toCategoryDataset( cd, dynamic );
	}
	
	public static PieDataset toPieDataset( ChartData cd ) {
		return PieConversionUtils.toPieDataset( cd );
	}
}