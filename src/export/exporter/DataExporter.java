package export.exporter;

import export.Exportable;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Jul 12, 2014, 5:14:24 PM 
 */
public interface DataExporter {
	public void export( Exportable ex, ExportOptions opt );
}