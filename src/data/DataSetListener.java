package data;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Feb 4, 2014, 3:12:54 PM 
 */
public interface DataSetListener {

	public void dataAdded( String name, DataPair<?, ?> data );
	
	public void dataCleared( String name );
	
	public void dataSetRemoved( String name );
	
	public void dataSetAdded( DataSet<?, ?> set );
}