package visualization.module.jfreechart.conversion;

import java.util.List;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import data.ChartData;
import data.DataPair;
import data.DataSet;
import data.DataSetListener;
import data.TimeData;

/**
 * @author Daniel J. Rivers
 *         2015
 *
 * Created: Apr 26, 2015, 6:09:00 PM 
 */
public class XYSeriesConversionUtils {

	public static XYSeriesCollection toXYSeriesCollection( ChartData cd, boolean dynamic ) {
		XYSeriesCollection col = new XYSeriesCollection();
		List<DataSet<?, ?>> data = cd.getData();
		for ( DataSet<?, ?> ds : data ) {
			col.addSeries( toXYSeries( col, ds, dynamic, cd.getViewableRange() ) );
		}
		cd.addListener( getXYListener( col, cd.getViewableRange() ) );
		return col;
	}
	
	public static XYSeries toXYSeries( XYSeriesCollection col, DataSet<?, ?> ds, boolean dynamic, int count ) {
		final XYSeries s = new XYSeries( ds.getName() );
		s.setMaximumItemCount( count );
		for ( DataPair<?, ?> p : ds ) {
			addXYData( s, p );
		}
		return s;
	}
	
	private static DataSetListener getXYListener( final XYSeriesCollection col, final int count ) {
		return new DataSetListener() {
			@Override
			public void dataAdded( String name, DataPair<?, ?> data ) {
				addXYData( col.getSeries( name ), data );
			}

			@Override
			public void dataCleared( String name ) {
				col.getSeries( name ).clear();
			}

			@Override
			public void dataSetAdded( DataSet<?, ?> set ) {
				col.addSeries( toXYSeries( col, set, true, count ) );
			}

			@Override
			public void dataSetRemoved( String name ) {
				col.removeSeries( col.getSeries( name ) );			
			}
		};
	}
	
	private static void addXYData( XYSeries s, DataPair<?, ?> p ) {
		s.add( p.getFirst() instanceof TimeData ? ( (TimeData)p.getFirst() ).getMillis() : (Number)p.getFirst(), 
				p.getSecond() instanceof TimeData ? ( (TimeData)p.getSecond() ).getMillis() : (Number)p.getSecond() );
	}
	
}
