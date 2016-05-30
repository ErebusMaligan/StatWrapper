package data;

import java.util.List;
import java.util.Vector;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Feb 2, 2014, 4:25:22 AM 
 */
public class ChartData implements DataSetListener {

	public enum TimeGranularity { YEAR, MONTH, DAY, HOUR, MINUTE, SECOND, MILLISECOND };
	
	protected List<DataSet<?, ?>> data;

	protected String name;

	protected String xName;

	protected String yName;

	protected int viewableRange = 20; //default in case nobody ever sets it

	protected boolean dynamic = false;

	protected TimeGranularity time = TimeGranularity.MILLISECOND;
		
	private List<DataSetListener> listeners = new Vector<DataSetListener>();
	
	public ChartData( String name, String xName, String yName ) {
		this( null, name, xName, yName );
	}

	public ChartData( List<DataSet<?, ?>> data, String name, String xName, String yName ) {
		this.data = data;
		this.name = name;
		this.xName = xName;
		this.yName = yName;
	}
	
	public void setTimeGranularity( TimeGranularity time ) {
		this.time = time;
	}
	
	public TimeGranularity getTimeGranularity() {
		return time;
	}

	public synchronized void safeClear() {
		for ( DataSet<?, ?> ds : data ) {
			ds.safeClear();
		}
	}

	public synchronized void addDataSet( DataSet<?, ?> set ) {
		data.add( set );
		set.setChartData( this );
		for ( DataSetListener l : listeners ) {
			l.dataSetAdded( set );
		}
	}

	public List<DataSet<?, ?>> getData() {
		return data;
	}

	public String getName() {
		return name;
	}

	public String getXName() {
		return xName;
	}

	public String getYName() {
		return yName;
	}

	public int getViewableRange() {
		return viewableRange;
	}

	public void setViewableRange( int viewableRange ) {
		this.viewableRange = viewableRange;
	}

	public void setDynamic( Boolean dynamic ) {
		this.dynamic = dynamic;
	}

	public boolean getDynamic() {
		return dynamic;
	}

	public void addListener( DataSetListener l ) {
		listeners.add( l );
	}

	public void removeListener( DataSetListener l ) {
		listeners.remove( l );
	}

	public synchronized DataSet<?, ?> getSetByName( String name ) {
		DataSet<?, ?> ret = null;
		for ( DataSet<?, ?> ds : data ) {
			if ( ds.getName().equals( name ) ) {
				ret = ds;
				break;
			}
		}
		return ret;
	}


	@Override
	public void dataAdded( String name, DataPair<?, ?> data ) {
		for ( DataSetListener l : listeners ) {
			l.dataAdded( name, data );
		}
	}

	@Override
	public void dataCleared( String name ) {
		for ( DataSetListener l : listeners ) {
			l.dataCleared( name );
		}
	}

	@Override
	public void dataSetRemoved( String name ) {
		for ( DataSetListener l : listeners ) {
			l.dataSetRemoved( name );
		}
	}

	@Override
	public void dataSetAdded( DataSet<?, ?> set ) {
		for ( DataSetListener l : listeners ) {
			l.dataSetAdded( set );
		}
	}
}