package utils;

import data.TimeData;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Jul 12, 2014, 9:23:48 PM 
 */
public class FormattingUtils {
	
	public static String formatNanoTime( Double value ) {
		return new TimeData( (long)( value * 1000l ) ).toString();		
	}
	
	public static String formatMillisTime( Long value ) {
		return new TimeData( value ).toString();
	}
}