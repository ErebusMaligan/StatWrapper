package export.exporter.format;

import utils.FormattingUtils;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Jul 12, 2014, 9:41:05 PM 
 */
public class NanoTimeFormatter implements DataFormatter {

	@Override
	public String format( Object value ) {
		return FormattingUtils.formatNanoTime( (Double)value );
	}

}
