package export;

import java.util.List;

import spreadsheet.data.SpreadSheetData;
import export.exporter.ExportOptions;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Jul 12, 2014, 4:31:38 PM 
 */
public interface Exportable {
	
	public static enum ExportType { TEXT, XLS };
	
	public ExportType[] getAvailableTypes();
	
	public StringBuffer getTextExportObject( ExportOptions opt );
	
	public List<SpreadSheetData> getSpreadSheetExportObject( ExportOptions opt );
}