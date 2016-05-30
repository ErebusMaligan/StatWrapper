package visualization.module.jfreechart.conversion;

import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.SlidingCategoryDataset;

import data.ChartData;
import data.DataPair;
import data.DataSet;
import data.DataSetListener;

/**
 * @author Daniel J. Rivers
 *         2015
 *
 * Created: Apr 26, 2015, 6:09:11 PM 
 */
public class CategoryConversionUtils {

	public static CategoryDataset toCategoryDataset( ChartData cd, boolean dynamic ) {
		List<DataSet<?, ?>> data = cd.getData();
		CategoryDataset ret = null;
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		final int amount = cd.getViewableRange();
		final SlidingCategoryDataset scd = new SlidingCategoryDataset( dataset, 0, amount );
		if ( dynamic ) {
			ret = scd;
			cd.addListener( getCategoryListener( dataset, scd, amount ) );
		} else {
			ret = dataset;
		}
		for ( final DataSet<?, ?> ds : data ) {
			for ( DataPair<?, ?> p : ds ) {
				dataset.addValue( (Number)p.getSecond(), ds.getName(), (Comparable<?>)p.getFirst() );
			}
		}
		return ret;
	}
	
	private static DataSetListener getCategoryListener( final DefaultCategoryDataset dataset, final SlidingCategoryDataset scd, final int amount ) {
		return new DataSetListener() {
			@Override
			public void dataAdded( String name, DataPair<?, ?> data ) {
				dataset.addValue( (Number)data.getSecond(), name, (Comparable<?>)data.getFirst() );
				int newIndex = scd.getFirstCategoryIndex();
				int sz = 0;
				for ( Object c : dataset.getColumnKeys() ) {
					if ( dataset.getValue( name, (Comparable<?>)c ) != null ) {
						sz++;
					}
				}
				if ( sz > amount && newIndex + 1 + amount <= sz  ) {
//					if ( newIndex + 1 < ds.size() ) {
//						newIndex++;	
//					} else {
//						newIndex = ds.size() - 1;
//					}
					newIndex++;
				}
				scd.setFirstCategoryIndex( newIndex );
			}

			@Override
			public void dataCleared( String name ) {
				try {
					scd.setFirstCategoryIndex( 0 );
				} catch ( Exception e ) {
					//means the graph was never started not a bug, just don't care
				}
				dataset.removeRow( dataset.getRowIndex( name ) );
			}

			@Override
			public void dataSetAdded( DataSet<?, ?> set ) {
				for ( DataPair<?, ?> p : set ) {
					dataset.addValue( (Number)p.getSecond(), set.getName(), (Comparable<?>)p.getFirst() );
				}
			}

			@Override
			public void dataSetRemoved( String name ) {
				dataset.removeRow( name );
			}
		};
	}
	
}
