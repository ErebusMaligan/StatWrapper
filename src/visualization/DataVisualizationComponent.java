package visualization;

import java.util.Arrays;

import javax.swing.JComponent;

import data.ChartData;
import export.ExportFactory;
import export.Exportable;
import export.exception.UnsupportedTypeException;
import export.exporter.ExportOptions;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Feb 1, 2014, 4:15:56 AM 
 */
public abstract class DataVisualizationComponent implements Exportable {

	protected ChartData data;
	
	protected JComponent vis = null;
	
	protected ExportOptions opt;
	
	public DataVisualizationComponent( ChartData data ) {
		setData( data );
	}
	
	public ChartData getData() {
		return data;
	}
	
	public void setData( ChartData data ) {
		this.data = data;;
	}
	
	public abstract JComponent getVisualization();
	
	public void exportAs( ExportType type ) throws UnsupportedTypeException {
		if ( Arrays.asList( this.getAvailableTypes() ).contains( type ) ) {
			ExportFactory.getInstance().get( type ).export( this, opt );
		} else {
			throw new UnsupportedTypeException( "Selected export type not supported by this DataVisualizationComponent" );
		}
	}
	
	public void setExportOptions( ExportOptions opt ) {
		this.opt = opt;
	}
	
	public ExportOptions getExportOptions() {
		return opt;
	}
}