/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bisanti.swingx;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Written and authored by Jason Bisanti. Free to use and reproduce.
 *
 * @author Jason Bisanti
 */
public class JTablePlus extends JTable
{
    private List<TableColumn> columns;

    public JTablePlus()
    {
    }

    public JTablePlus(TableModel dm)
    {
        super(dm);
    }

    public JTablePlus(TableModel dm, TableColumnModel cm)
    {
        super(dm, cm);
    }

    public JTablePlus(TableModel dm, TableColumnModel cm, ListSelectionModel sm)
    {
        super(dm, cm, sm);
    }

    public JTablePlus(Vector rowData, Vector columnNames)
    {
        super(rowData, columnNames);
    }

    public JTablePlus(Object[][] rowData, Object[] columnNames)
    {
        super(rowData, columnNames);
    }
    
    public List<TableColumn> getColumns()
    {
        return this.columns;
    }

    @Override
    public void setColumnModel(TableColumnModel columnModel)
    {
        super.setColumnModel(columnModel);
        
        if(this.columns == null)
        {
            this.columns = new ArrayList<TableColumn>();
        }
        else
        {
            this.columns.clear();
        }
    }

    @Override
    public void columnAdded(TableColumnModelEvent e)
    {
        TableColumn column = super.columnModel.getColumn(e.getToIndex());
        if(!this.columns.contains(column))
        {
            this.columns.add(column);
        }
        
        super.columnAdded(e);
    }
    
    
    
}
