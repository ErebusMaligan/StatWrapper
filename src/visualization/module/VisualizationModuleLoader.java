package visualization.module;

import java.util.HashMap;
import java.util.Map;

import visualization.module.jfreechart.JFreeChartModule;

/**
 * @author Daniel J. Rivers
 *         2014
 *
 * Created: Feb 2, 2014, 3:08:43 AM 
 */
public class VisualizationModuleLoader {

	public static enum Module { JFREECHART };
	
	private Map<Module, VisualizationModule> modules;
	
	private static VisualizationModuleLoader instance;
	
	
	private VisualizationModuleLoader() {
		modules = new HashMap<Module, VisualizationModule>();
		modules.put( Module.JFREECHART, new JFreeChartModule() );
	}
	
	public static VisualizationModuleLoader getInstance() {
		if ( instance == null ) {
			instance = new VisualizationModuleLoader();
		}
		return instance;
	}
	
	public VisualizationModule getModule( Module mod ) {
		return modules.get( mod );
	}
}