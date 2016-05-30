package data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Feb 8, 2014, 2:55:31 PM 
 */
public class TimeData implements Comparable<TimeData> {

	public static DateFormat formatter;
	
	private long millis;
	
	public TimeData( long millis ) {
		this.millis = millis;
	}
	
	public long getMillis() {
		return millis;
	}
	
	@Override
	public String toString() {
		Date date = new Date( millis );
		if ( formatter == null ) {
			formatter = new SimpleDateFormat("HH:mm:ss:SSS");
		}
		return formatter.format(date);
	}
	
	@Override
	public int compareTo( TimeData other ) {
		return (int)( millis - other.getMillis() );
	}
}