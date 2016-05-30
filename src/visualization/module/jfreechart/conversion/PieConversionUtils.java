package visualization.module.jfreechart.conversion;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import data.ChartData;
import data.DataPair;
import data.DataSet;

/**
 * @author Daniel J. Rivers
 *         2015
 *
 * Created: Apr 26, 2015, 6:13:56 PM 
 */
public class PieConversionUtils {
	
	public static PieDataset toPieDataset( ChartData cd ) {
		DataSet<?,?> ds = cd.getData().get( 0 );
		DefaultPieDataset dataset = new DefaultPieDataset();
		for ( DataPair<?, ?> p : ds ) {
			dataset.setValue( (Comparable<?>)p.getFirst(), (Number)p.getSecond() );
		}
		return dataset;
	}	
}