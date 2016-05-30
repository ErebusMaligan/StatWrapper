package visualization.chart;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import spreadsheet.data.SpreadSheetData;
import visualization.DataVisualizationComponent;
import visualization.module.VisualizationModule;
import visualization.module.VisualizationModuleLoader;
import data.ChartData;
import data.DataPair;
import data.DataSet;
import export.Exportable;
import export.exporter.ExportOptions;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Feb 1, 2014, 6:28:49 AM 
 */
public class Chart extends DataVisualizationComponent {

	protected VisualizationModule module = VisualizationModuleLoader.getInstance().getModule( VisualizationModuleLoader.Module.JFREECHART );
	
	public enum ChartType { BAR_CHART, LINE_CHART, PIE_CHART, CATEGORY_LINE_CHART, SCATTER_PLOT, TIME_SERIES_LINE_CHART }
	
	protected ChartType type;
	
	public Chart( ChartData data, ChartType type ) {
		super( data );
		this.type = type;
	}
	
	protected void setModule( VisualizationModule module ) {
		this.module = module;
	}

	@Override
	public JComponent getVisualization() {
		if ( vis == null ) {
			switch ( type ) {
				case BAR_CHART: vis = module.getBarChart( data ); break;
				case LINE_CHART: vis = module.getLineChart( data ); break;
				case PIE_CHART: vis = module.getPieChart( data ); break;
				case CATEGORY_LINE_CHART: vis = module.getCategoryLineChart( data ); break;
				case SCATTER_PLOT: vis = module.getScatterPlot( data ); break;
				case TIME_SERIES_LINE_CHART: vis = module.getTimeSeriesLineChart( data ); break;
			}
		}
		return vis;
	}

	@Override
	public ExportType[] getAvailableTypes() {
		return new ExportType[] { Exportable.ExportType.TEXT, Exportable.ExportType.XLS };
	}

	@Override
	public StringBuffer getTextExportObject( ExportOptions opt ) {
		StringBuffer b = new StringBuffer();
		b.append( data.getName() + "\n\n" );
		for ( DataSet<?, ?> set : data.getData() ) {
			b.append( set.getName() + "\n\n" );
			b.append( data.getXName() + opt.getDelimiter() + data.getYName() + "\n\n" );
			for ( DataPair<?, ?> pair : set ) {
				b.append( opt.getX().format( pair.getFirst() ) + opt.getDelimiter() + opt.getY().format( pair.getSecond() ) + "\n" );
			}
			b.append( "\n" );
		}
		return b;
	}
	
	public List<SpreadSheetData> getSpreadSheetExportObject( ExportOptions opt ) {
		List<SpreadSheetData> ret = new ArrayList<SpreadSheetData>();
		for ( DataSet<?, ?> set : data.getData() ) {
			SpreadSheetData ssd = new SpreadSheetData( set.getName(), new String[] { data.getXName(), data.getYName() } );
			for ( DataPair<?, ?> pair : set ) {
				ssd.addRow( new Object[] { opt.getX().format( pair.getFirst() ), opt.getY().format( pair.getSecond() ) } );
			}
			ret.add( ssd );
		}
		return ret;
	}
}