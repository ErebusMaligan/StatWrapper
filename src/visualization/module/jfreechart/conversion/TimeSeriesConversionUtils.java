package visualization.module.jfreechart.conversion;

import java.sql.Date;
import java.util.List;

import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import data.ChartData;
import data.ChartData.TimeGranularity;
import data.DataPair;
import data.DataSet;
import data.DataSetListener;
import data.TimeData;

/**
 * @author Daniel J. Rivers
 *         2015
 *
 * Created: Apr 26, 2015, 6:08:38 PM 
 */
public class TimeSeriesConversionUtils {

	public static TimeSeriesCollection toTimeSeriesCollection( ChartData cd, boolean dynamic ) {
		TimeSeriesCollection col = new TimeSeriesCollection();
		List<DataSet<?, ?>> data = cd.getData();
		for ( DataSet<?, ?> ds : data ) {
			col.addSeries( toTimeSeries( col, ds, dynamic, cd.getViewableRange(), cd.getTimeGranularity() ) );
		}
		cd.addListener( getTimeSeriesListener( col, cd.getViewableRange(), cd.getTimeGranularity() ) );
		return col;
	}
	
	public static TimeSeries toTimeSeries( TimeSeriesCollection col, DataSet<?, ?> ds, boolean dynamic, int count, TimeGranularity time ) {
		final TimeSeries s = new TimeSeries( ds.getName() );
		s.setMaximumItemCount( count );
		for ( DataPair<?, ?> p : ds ) {
			addTimeData( s, p, time );
		}
		return s;
	}
	
	private static void addTimeData( TimeSeries s, DataPair<?, ?> p, TimeGranularity time ) {
		s.add(  p.getFirst() instanceof TimeData ? getTimePeriod( new Date( ( (TimeData)p.getFirst() ).getMillis() ), time ) : 
			getTimePeriod( new Date( ( (Number)p.getFirst() ).longValue() ), time ),
				p.getSecond() instanceof TimeData ? ( (TimeData)p.getSecond() ).getMillis() : (Number)p.getSecond() );

				
	}
	
	private static RegularTimePeriod getTimePeriod( Date in, TimeGranularity time ) {
		RegularTimePeriod ret = null;
		switch ( time ) {
			case YEAR: ret = new Year( in ); break;
			case MONTH: ret = new Month( in ); break;
			case DAY: ret = new Day( in ); break;
			case HOUR: ret = new Hour( in ); break;
			case MINUTE: ret = new Minute( in ); break;
			case SECOND:  ret = new Second( in ); break;
			case MILLISECOND: ret = new Millisecond( in ); break;
		}
		return ret;
	}
	
	private static DataSetListener getTimeSeriesListener( final TimeSeriesCollection col, final int count, final TimeGranularity time ) {
		return new DataSetListener() {
			
			private final int c = count;
			
			private TimeGranularity t = time;
			
			@Override
			public void dataAdded( String name, DataPair<?, ?> data ) {
				addTimeData( col.getSeries( name ), data, time );
			}

			@Override
			public void dataCleared( String name ) {
				col.getSeries( name ).clear();
			}

			@Override
			public void dataSetAdded( DataSet<?, ?> set ) {
				col.addSeries( toTimeSeries( col, set, true, c, t ) );
			}

			@Override
			public void dataSetRemoved( String name ) {
				col.removeSeries( col.getSeries( name ) );			
			}
		};
	}
	
}
