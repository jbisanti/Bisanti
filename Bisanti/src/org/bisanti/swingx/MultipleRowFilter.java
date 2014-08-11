package org.bisanti.swingx;

import java.util.SortedSet;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 * @author Jason Bisanti
 */
public abstract class MultipleRowFilter extends javax.swing.RowFilter
{
    public abstract SortedSet<Integer> getFilteredColumns();
}
