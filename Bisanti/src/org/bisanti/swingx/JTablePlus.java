package org.bisanti.swingx;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.RowSorter.SortKey;
import javax.swing.SortOrder;
import static javax.swing.SortOrder.ASCENDING;
import static javax.swing.SortOrder.DESCENDING;
import static javax.swing.SortOrder.UNSORTED;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i> 
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
     * Name of {@link PropertyChangeEvent} fired when the current column being
     * sorted has changed.
     * 
     * @see getSortedColumn()
     */
    public static final String COLUMN_SORTED = "JTablePlusColumnSorted";
    
    /**
     * Name of {@link PropertyChangeEvent} fired when the {@link SortOrder} for
     * a given column has changed
     * 
     * @see getSortOrder()
     */
    public static final String SORT_ORDER = "JTablePlusSortOrder";
    
    
    /**
     * Name of {@link PropertyChangeEvent} fired when a {@link TableRowSorter}
     * that has a {@link MultipleRowFilter} has been set or removed.
     * 
     * @see getFilteredColumns()
     */
    public static final String COLUMNS_FILTERED = "JTablePlusColumnsFiltered";
    
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
    
    /** {@link ColumnFilterPopup} instance used for filtering rows */
    private ColumnFilterPopup filterPopup;
    
    /** {@link MultipleRowFilter} used to determine which rows are filtered */
    private MultipleRowFilter multiRowFilter;
    
    private JTablePlusHelp helpPopup;

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
                    TableColumn col = null;
                    int index = columnAtPoint(e.getPoint());
                    if(index >= 0)
                    {
                        col = columnModel.getColumn(index);
                    }
                    createTableMenu(col).show(tableHeader, e.getX(), e.getY());
                }
            }            
        });
        
        TableCellRenderer headerRenderer = this.createHeaderRenderer();
        if(headerRenderer != null)
        {
            super.tableHeader.setDefaultRenderer(headerRenderer);
        }
    }
    
    private void initColumnList()
    {
        if(this.columns == null)
        {
            this.columns = Collections.synchronizedList(new ArrayList<TableColumn>());
        }
        else
        {
            this.columns.clear();
        }
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
        Set<Integer> oldFilters = this.getFilteredColumns();
        Set<Integer> newFilters = Collections.emptySet();
        this.multiRowFilter = null;
        
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
            
            if(rowSorter instanceof TableRowSorter)
            {
                RowFilter filter = ((TableRowSorter)rowSorter).getRowFilter();
                if(filter instanceof MultipleRowFilter)
                {
                    this.multiRowFilter = (MultipleRowFilter)filter;
                    newFilters = this.multiRowFilter.getFilteredColumns();
                }
            }
        }
        
        super.firePropertyChange(SORT_ORDER, oldSortOrder, this.sortOrder); 
        super.firePropertyChange(COLUMN_SORTED, oldSortedColumn, this.sortedColumn);
        super.firePropertyChange(COLUMNS_FILTERED, oldFilters, newFilters);
        
        if(SwingUtilities.isEventDispatchThread())
        {
            super.tableHeader.repaint();
        }
        else
        {
            SwingUtilities.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                    tableHeader.repaint();
                }
            });
        }
    }
    
    @Override
    protected void configureEnclosingScrollPane()
    {
        super.configureEnclosingScrollPane();
        
        final Container scrollPane = SwingUtilities.getAncestorOfClass(JScrollPane.class, this);
        if(scrollPane instanceof JScrollPane)
        {
            ((JScrollPane)scrollPane).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            final JButton menu = new JButton(new javax.swing.ImageIcon(getClass().getResource("question_icon.png")));
            menu.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Point p = menu.getMousePosition();
                    createTableMenu(null).show(menu, p.x, p.y);
                }
            });
            ((JScrollPane)scrollPane).setCorner(JScrollPane.UPPER_RIGHT_CORNER, menu);
        }
    }
    
    protected TableCellRenderer createHeaderRenderer()
    {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                column = table.convertColumnIndexToModel(column);
                
                if(column == sortedColumn)
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
                
                if(multiRowFilter != null && multiRowFilter.getFilteredColumns().contains(column))
                {
                    super.setFont(table.getFont().deriveFont(Font.BOLD));
                }
                else
                {
                    super.setFont(table.getFont().deriveFont(Font.PLAIN));
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
     * Creates a {@link JPopupMenu} that is triggered when the 
     * {@link JTableHeader} is right-clicked or the menu button in the top-right
     * corner of the table is clicked.
     * 
     * @param {@link TableColumn} column for which menu should be created. If 
     * null, a generic menu is created that is applicable for all columns.
     * @return {@link JPopupMenu}
     */
    protected JPopupMenu createTableMenu(final TableColumn column)
    {
        final JPopupMenu menu = new JPopupMenu();
        
        // Add the title
        JLabel info = new JLabel();
        info.setFont(info.getFont().deriveFont(Font.ITALIC + Font.BOLD));
        menu.add(info);
        menu.addSeparator();
        
        // Add the Filter.. option
        JMenuItem filterMenuItem = new JMenuItem("Filter...");
        filterMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(filterPopup == null)
                {
                    filterPopup = new ColumnFilterPopup(JTablePlus.this, column);
                }
                
                if(multiRowFilter == null)
                {
                    filterPopup.reset();
                    filterPopup.setColumn(column);
                }
                else
                {
                    filterPopup.setRowSorter(getRowSorter());
                }
                
                filterPopup.setLocationRelativeTo(JTablePlus.this);
                filterPopup.setVisible(true);
            }
        });
        menu.add(filterMenuItem);
        
        // Add the remove filter/sort option
        if(this.sortedColumn > -1 || this.multiRowFilter != null)
        {
            JMenuItem unsort = new JMenuItem("Unsort/Unfilter");
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
        
        if(column == null)
        {
            info.setText(" Rows: " + super.getRowCount() + "  Columns: " + this.columns.size());
            
            Set<JCheckBoxMenuItem> selected = new HashSet<JCheckBoxMenuItem>();
            for(final TableColumn col: this.columns)
            {
                Object id = col.getIdentifier();
                JCheckBoxMenuItem colCheckBox = new JCheckBoxMenuItem(String.valueOf(id));
                if(!this.isHidden(id))
                {
                    colCheckBox.setSelected(true);
                    selected.add(colCheckBox);
                }
                
                colCheckBox.addItemListener(new ItemListener()
                {
                    @Override
                    public void itemStateChanged(ItemEvent e)
                    {
                        switch(e.getStateChange())
                        {
                            case ItemEvent.SELECTED:
                                columnModel.addColumn(col);
                                break;
                            case ItemEvent.DESELECTED:
                                columnModel.removeColumn(col);
                                break;
                        }
                    }
                });
                menu.add(colCheckBox);
            }
            
            if (selected.size() == 1)
            {
                JCheckBoxMenuItem cbmi = selected.iterator().next();
                cbmi.setEnabled(false);
                cbmi.setToolTipText("At least 1 column must be showing");
            }
            selected.clear(); // clear for garbage collection purposes
        }
        else
        {
            info.setText(" " + String.valueOf(column.getIdentifier()));
            
            // Only allow hiding if this isn't the only column showing
            if (super.columnModel.getColumnCount() > 1)
            {
                JMenuItem hide = new JMenuItem("Hide");
                hide.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        columnModel.removeColumn(column);
                    }
                });
                menu.add(hide);
            }
        }
        
        menu.addSeparator();
        JMenuItem help = new JMenuItem("Help");
        help.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(helpPopup == null)
                {
                    helpPopup = new JTablePlusHelp();
                }
                helpPopup.setLocationRelativeTo(JTablePlus.this);
                helpPopup.setVisible(true);
            }
        });
        menu.add(help);

        return menu;
    }
    
    public boolean isHidden(TableColumn column)
    {
        return this.isHidden(column.getIdentifier());
    }
    
    public boolean isHidden(Object columnIndentifier)
    {
        try
        {
            super.columnModel.getColumnIndex(columnIndentifier);
            return false;
        }
        catch(IllegalArgumentException ex)
        {
            return true;
        }
    }
    
    public List<TableColumn> getColumns()
    {
        return this.columns;
    }
    
    public Set<Integer> getFilteredColumns()
    {
        if(this.multiRowFilter == null)
        {
            return Collections.emptySet();
        }
        else
        {
            return this.multiRowFilter.getFilteredColumns();
        }
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

    @Override
    public void setColumnModel(TableColumnModel columnModel)
    {
        this.initColumnList();
        super.setColumnModel(columnModel);
    }

    @Override
    public void setModel(TableModel dataModel)
    {
        this.initColumnList();
        super.setModel(dataModel);
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
            this.updateRowSorter(sorter);
            
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
