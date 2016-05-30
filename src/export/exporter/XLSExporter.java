package export.exporter;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import export.Exportable;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Aug 10, 2014, 7:59:07 PM 
 */
public class XLSExporter implements DataExporter {

	@Override
	public void export( Exportable ex, ExportOptions opt ) {
		if ( opt == null ) {
			opt = new ExportOptions();
		}
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
		FileNameExtensionFilter filter = new FileNameExtensionFilter( "Excel Spreadsheet", "xls" );
		fc.addChoosableFileFilter( filter );
		fc.setFileFilter( filter );
		fc.setMultiSelectionEnabled( false );
		if ( JFileChooser.APPROVE_OPTION == fc.showOpenDialog( null ) ) {
			String path = fc.getSelectedFile().getAbsolutePath();
			if ( !path.endsWith( ".xls" ) ) {
				path += ".xls";
			}
			spreadsheet.export.XLSExporter.exportSpreadSheet( null, ex.getSpreadSheetExportObject( opt ), new File( path ) );
		}
	}
}
