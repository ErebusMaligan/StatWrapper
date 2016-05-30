package export.exporter.format;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Jul 12, 2014, 9:56:19 PM 
 */
public class DefaultFormatter implements DataFormatter {

	@Override
	public Object format( Object value ) {
		Object ret = value;
		if ( value instanceof Integer ) {
			ret = Double.valueOf( (Integer)value );
		} else if ( value instanceof Float ) {
			ret = Double.valueOf( (Float)value );
		} else if ( value instanceof Double ) {
			ret = (Double)value;
		} else {
			ret = value.toString();
		}
		return ret;
	}
}