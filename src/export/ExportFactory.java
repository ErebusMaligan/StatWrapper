package export;

import java.util.HashMap;

import export.Exportable.ExportType;
import export.exporter.DataExporter;
import export.exporter.TextExporter;
import export.exporter.XLSExporter;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Jul 12, 2014, 5:12:27 PM 
 */
public class ExportFactory extends HashMap<ExportType, DataExporter> {

	private static final long serialVersionUID = 1L;

	private static ExportFactory instance;
	
	private ExportFactory() {
		super();
		this.put( ExportType.TEXT, new TextExporter() );
		this.put( ExportType.XLS, new XLSExporter() );
	}
	
	public static ExportFactory getInstance() {
		if ( instance == null ) {
			instance = new ExportFactory();
		}
		return instance;
	}

}