package data;

import java.util.List;
import java.util.Vector;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Feb 4, 2014, 3:21:59 PM 
 */
public class DataSetListenerManager {
	
	private List<DataSetListener> listeners = new Vector<DataSetListener>();
	
	public void addListener( DataSetListener l ) {
		listeners.add( l );
	}
	
	public void removeListener( DataSetListener l ) {
		listeners.remove( l );
	}
	
	public void notifyListeners( String name, DataPair<?, ?> data ) {
		for ( DataSetListener l : listeners ) {
			l.dataAdded( name, data );
		}
	}
}