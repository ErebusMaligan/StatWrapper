package utils;

import java.util.ArrayList;
import java.util.List;

import data.ChartData;
import data.DataSet;
import data.TimeData;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Jul 15, 2014, 7:22:29 PM 
 */
public class DataCreationUtils {
	
	public static ChartData createSingleTimeSeriesData( String legendName, String name, String xName, String yName, int viewablePoints ) {
		ArrayList<DataSet<?, ?>> l = new ArrayList<DataSet<?, ?>>();
		l.add( new DataSet<TimeData, Number>( legendName, viewablePoints ) );
		ChartData cd = new ChartData( l, name, xName, yName );
		cd.setViewableRange( viewablePoints );
		cd.setDynamic( true );
		return cd;
	}
	
	public static List<DataSet<?, ?>> createNumericDataSet( String name ) {
		ArrayList<DataSet<?, ?>> l = new ArrayList<DataSet<?, ?>>();
		l.add( new DataSet<Number, Number>( name ) );
		return l;
	}
	
	public static ChartData createAggregateTimeSeriesData( String legendName, String name, String xName, String yName, int viewablePoints ) {
		ArrayList<DataSet<?, ?>> l = new ArrayList<DataSet<?, ?>>();
		l.add( new DataSet<Number, Number>( legendName, viewablePoints ) );
		ChartData cd = new ChartData( l, name, xName, yName );
		cd.setViewableRange( viewablePoints );
		cd.setDynamic( true );
		return cd;
	}
}