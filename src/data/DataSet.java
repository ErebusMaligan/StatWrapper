package data;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Feb 1, 2014, 2:31:33 AM 
 */
public class DataSet<X, Y> extends Vector<DataPair<? extends X, ? extends Y>> {

	private static final long serialVersionUID = 1L;

	private String name;
	
	protected int holdLast = 0;
	
	protected ChartData cd;
	
	public DataSet( String name ) {
		super();
		this.name = name;
	}
	
	public DataSet( String name, int holdLast ) {
		this( name );
		this.holdLast = holdLast;
	}
	
	public DataSet( String name, List<DataPair<? extends X, ? extends Y>> data ) {
		this( name );
		addAll( data );
	}
	
	public DataSet( String name, DataPair<? extends X, ? extends Y>[] data ) {
		this( name );
		addAll( Arrays.asList( data ) );
	}
	
	public void setChartData( ChartData cd ) {
		this.cd = cd;
	}
	
	public String getName() {
		return name;
	}
	
	public synchronized void safeClear() {
		this.clear();
		if ( cd != null ) {
			cd.dataSetRemoved( name );
		}
	}
	
	public synchronized void remove() {
		this.clear();
		if ( cd != null ) {
			 cd.dataSetRemoved( name );
		}
	}
	
	@Override
	public void addElement( DataPair<? extends X, ? extends Y> element ) {
		add( element );
	}
	
	@Override
	public synchronized boolean add( DataPair<? extends X, ? extends Y> element ) {
		boolean ret = super.add( element );
		if ( ret ) {
			if ( cd != null ) {
				cd.dataAdded( name, element );
			}
		}
//		if ( holdLast > 0 ) {
//			if ( size() > holdLast ) {
//				this.remove( 0 );
//			}
//		}
		return ret;
	}
	
	public void setHoldLast( int holdLast ) {
		this.holdLast = holdLast;
	}

	//TEST CODE
	public static void main( String[] args ) {
		DataSet<Integer, Double> ds = new DataSet<Integer, Double>( "test" );
		for ( int i = 0; i < 20; i++ ) {
			DataPair<Integer, Double> p = new DataPair<Integer, Double>( i, (double)i );
			ds.add( p );
		}
		for ( DataPair<? extends Integer, ? extends Double> p : ds ) {
			System.out.println( p );
		}
	}
	
}