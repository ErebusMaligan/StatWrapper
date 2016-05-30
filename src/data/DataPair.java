package data;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Jan 30, 2014, 3:47:22 PM 
 */
public class DataPair<X, Y> {
	
	private X first;
	
	private Y second;
	
	public DataPair( X first, Y second ) {
		this.first = first;
		this.second = second;
	}
	
	public X getFirst() {
		return first;
	}
	
	public Y getSecond() {
		return second;
	}
	
	@Override
	public String toString() {
		return first.toString() + " : " + second.toString();
	}
	
	
	//TEST CODE
	public static void main( String[] args ) {
		DataPair<String, Integer> p = new DataPair<String, Integer>( "test", 1 );
		System.out.println( p );
		DataPair<Float, Double> p2 = new DataPair<Float, Double>( 22.2f, 33.3d );
		System.out.println( p2 );
		System.out.println( p2.getSecond() );
		System.out.println( p2.getFirst() );
	}

}
