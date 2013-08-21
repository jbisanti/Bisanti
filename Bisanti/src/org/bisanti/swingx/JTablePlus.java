/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bisanti.swingx;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Written and authored by Jason Bisanti. Free to use and reproduce.
 * <br><br> 
 * Extension of {@link JTable} that provides additional features such as the
 * capability to sort rows on multiple and-or criteria and to hide/show columns.
 * Convenient getter methods such as {@link getColumns()}, 
 * {@link getSortedColumn()}, and {@link getSortOrder()} have also been added.
 * In addition, this class has been designed in a way to allow sub-classes to
 * easily customize and expand the look and features of this class through
 * methods such as {@link createTableMenu()}.
 *
 * @author Jason Bisanti
 */
public class JTablePlus extends JTable
{
    /** 
     * Name of the {@link PropertyChangeEvent} fired when the current column
     * being sorted has changed.
     * 
     * @see getSortedColumn()
     */
    public static final String COLUMN_SORTED = "jTablePlusColumnSorted";
    
    /**
     * Name of the {@link PropertyChangeEvent} fired when the {@link SortOrder}
     * for a given column has changed
     * 
     * @see getSortOrder()
     */
    public static final String SORT_ORDER = "jTablePlusSortOrder";
    
    /** 
     * A {@link List} of all the {@link TableColumn}s, both shown and hidden. 
     * Because of {@link JTable} instantiation code, this is lazily instantiated
     * in method {@link setColumnModel(TableColumnModel)}.
     */
    private List<TableColumn> columns;
    
    /** Index of currently sorted column */
    private int sortedColumn = -1;
    
    /** Current {@link SortOrder} of the sorted column */
    private SortOrder sortOrder = SortOrder.UNSORTED;

    public JTablePlus()
    {
        super();
        this.init();
    }

    public JTablePlus(TableModel dm)
    {
        super(dm);
        this.init();
    }

    public JTablePlus(TableModel dm, TableColumnModel cm)
    {
        super(dm, cm);
        this.init();
    }

    public JTablePlus(TableModel dm, TableColumnModel cm, ListSelectionModel sm)
    {
        super(dm, cm, sm);
        this.init();
    }

    public JTablePlus(Vector rowData, Vector columnNames)
    {
        super(rowData, columnNames);
        this.init();
    }

    public JTablePlus(Object[][] rowData, Object[] columnNames)
    {
        super(rowData, columnNames);
        this.init();
    }
    
    public List<TableColumn> getColumns()
    {
        return this.columns;
    }
    
    /**
     * Returns the index of the {@link TableColumn} that is currently sorted. If
     * no column is sorted, -1 is returned.
     * 
     * @return Index or sorted column or -1
     * @see getSortOrder()
     */
    public int getSortedColumn()
    {
        return this.sortedColumn;
    }
    
    /**
     * Returns the current {@link SortOrder} for the sorted column.
     * 
     * @return {@link SortOrder}
     * @see getSortedColumn()
     */
    public SortOrder getSortOrder()
    {
        return this.sortOrder;
    }
    
    /**
     * Common initialization method that should be called by each constructor.
     */
    private void init()
    {
        super.setAutoCreateRowSorter(true);
        
        super.tableHeader.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(SwingUtilities.isRightMouseButton(e))
                {
                    createTableMenu().show(tableHeader, e.getX(), e.getY());
                }
            }            
        });
        
        super.tableHeader.setDefaultRenderer(this.createHeaderRenderer());
    }
    
    /**
     * Helper method to ensure that a {@link RowSorter} is always installed and
     * that column related sorting {@link PropertyChangeEvent}s are fired.
     * 
     * @param rowSorter {@link RowSorter}, can also be null
     */
    private void updateRowSorter(RowSorter rowSorter)
    {
        SortOrder oldSortOrder = this.sortOrder;
        int oldSortedColumn = this.sortedColumn;
        
        if(rowSorter == null)
        {
            super.setAutoCreateRowSorter(true);
            this.sortOrder = SortOrder.UNSORTED;
            this.sortedColumn = -1;
        }
        else
        {            
            List<SortKey> sortKeys = rowSorter.getSortKeys();
            for(SortKey sortKey: sortKeys)
            {                
                this.sortOrder = sortKey.getSortOrder();
                              
                
                switch(this.sortOrder)
                {
                    case ASCENDING:
                    case DESCENDING:
                        this.sortedColumn = sortKey.getColumn();
                        break;
                    case UNSORTED:
                        this.sortedColumn = -1;
                        break;
                }
                break;
            }
        }
        
        super.firePropertyChange(SORT_ORDER, oldSortOrder, this.sortOrder); 
        super.firePropertyChange(COLUMN_SORTED, oldSortedColumn, this.sortedColumn);
    }
    
    protected TableCellRenderer createHeaderRenderer()
    {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if(table.convertColumnIndexToModel(column) == sortedColumn)
                {
                    switch(sortOrder)
                    {
                        case ASCENDING:
                            super.setIcon(UIManager.getIcon("Table.ascendingSortIcon"));
                            break;
                        case DESCENDING:
                            super.setIcon(UIManager.getIcon("Table.descendingSortIcon"));
                            break;
                        default:
                           super.setIcon(null); 
                    }
                }
                else
                {
                    super.setIcon(null);
                }
                return this;
            }

            @Override
            public void setBorder(Border border)
            {
                super.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            }            
        };
        
        renderer.setBackground(UIManager.getColor("TableHeader.background"));
        return renderer;
    }
    
    /**
     * Creates this table's {@link JPopupMenu} that is triggered when the 
     * {@link JTableHeader} is right-clicked or the menu button in the top-right
     * corner of the table is clicked.
     * 
     * @return {@link JPopupMenu}
     */
    protected JPopupMenu createTableMenu()
    {
        final JPopupMenu menu = new JPopupMenu();
        
        JMenuItem filter = new JMenuItem("Filter...");
        filter.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ColumnFilterPopup cfp = new ColumnFilterPopup();
                cfp.setLocationRelativeTo(JTablePlus.this);
                cfp.setVisible(true);
            }
        });
        menu.add(filter);
        
        if(sortOrder != SortOrder.UNSORTED && this.sortedColumn != -1)
        {
            JMenuItem unsort = new JMenuItem("Unsort/UnFilter");
            unsort.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    setRowSorter(null);
                    tableHeader.repaint();
                }
            });
            
            menu.add(unsort);
        }
        
        return menu;
    }

    @Override
    protected void configureEnclosingScrollPane()
    {
        super.configureEnclosingScrollPane();
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

    @Override
    public void setRowSorter(RowSorter<? extends TableModel> sorter)
    {
        super.setRowSorter(sorter);
        
        if(sorter == null)
        {
            this.updateRowSorter(null);
        }
        else
        {
            sorter.addRowSorterListener(new RowSorterListener()
            {
                @Override
                public void sorterChanged(RowSorterEvent e)
                {
                    updateRowSorter(e.getSource());
                }
            });
        }
    }
    
    
    
}
