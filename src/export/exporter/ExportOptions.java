package export.exporter;

import export.exporter.format.DataFormatter;
import export.exporter.format.DefaultFormatter;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Jul 12, 2014, 9:17:41 PM 
 */
public class ExportOptions {
	
	private String delimiter = "\t";
	
	private DataFormatter x = new DefaultFormatter();
	
	private DataFormatter y = new DefaultFormatter();

	/**
	 * @return the delimiter
	 */
	public String getDelimiter() {
		return delimiter;
	}

	/**
	 * @param delimiter the delimiter to set
	 */
	public void setDelimiter( String delimiter ) {
		this.delimiter = delimiter;
	}

	/**
	 * @return the x
	 */
	public DataFormatter getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX( DataFormatter x ) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public DataFormatter getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY( DataFormatter y ) {
		this.y = y;
	}

}
