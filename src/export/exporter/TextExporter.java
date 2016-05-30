package export.exporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import export.Exportable;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Jul 12, 2014, 5:17:11 PM 
 */
public class TextExporter implements DataExporter {

	@Override
	public void export( Exportable ex, ExportOptions opt ) {
		if ( opt == null ) {
			opt = new ExportOptions();
		}
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
		FileNameExtensionFilter filter = new FileNameExtensionFilter( "Text Files", "txt" );
		fc.addChoosableFileFilter( filter );
		fc.setFileFilter( filter );
		fc.setMultiSelectionEnabled( false );
		if ( JFileChooser.APPROVE_OPTION == fc.showOpenDialog( null ) ) {
			BufferedWriter out = null;
			try {
				String path = fc.getSelectedFile().getAbsolutePath();
				if ( !path.endsWith( ".txt" ) ) {
					path += ".txt";
				}
				out = new BufferedWriter( new FileWriter( path ) );
				out.write( ex.getTextExportObject( opt ).toString() );
				out.flush();
			} catch ( IOException e ) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch ( IOException e ) {
					e.printStackTrace();
				}
			}
		}
	}

}