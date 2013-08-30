
package org.bisanti.swingx;

import java.util.SortedSet;

/**
 *
 * @author Jason Bisanti
 */
public abstract class MultipleRowFilter extends javax.swing.RowFilter
{
    public abstract SortedSet<Integer> getFilteredColumns();
}
