package org.bisanti.swingx;

import java.awt.GridBagConstraints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import org.bisanti.util.StringUtil;

/**
 * <i>
 * Written and authored by Jason Bisanti. Free to use and reproduce, but please
 * keep my name as the original author!
 * <br><br></i>
 * @author Jason Bisanti
 */
public class ColumnFilterPopup extends javax.swing.JFrame
{
    private final List<ColumnFilterPanel> filters = new ArrayList<ColumnFilterPanel>();
    
    private boolean accepted;
    
    private final JTable table;
    
    private final TableColumn column;

    /**
     * Creates new form ColumnFilterPopup
     */
    public ColumnFilterPopup(JTable table, TableColumn column)
    {
        this.table = table;
        this.column = column;
        initComponents();
        this.filters.add(this.columnFilterPanel1);
        this.columnFilterPanel1.addPropertyChangeListener(this.createPCL());
    }
    
    private PropertyChangeListener createPCL()
    {
        return new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent evt)
            {                
                if(StringUtil.equal(true, evt.getPropertyName(), 
                        ColumnFilterPanel.AND_BUTTON, 
                        ColumnFilterPanel.OR_BUTTON))
                {
                    Boolean selected = Boolean.valueOf(evt.getNewValue().toString());
                    
                    if(selected)
                    {
                        ColumnFilterPanel cfp = new ColumnFilterPanel(table.getColumnModel(), column);
                        cfp.addPropertyChangeListener(createPCL());
                        GridBagConstraints newGBC = new GridBagConstraints();
                        newGBC.gridx = 0;
                        newGBC.gridy = filters.size();
                        panel.add(cfp, newGBC);
                        filters.add(cfp);
                    }
                    else
                    {
                        int index = filters.indexOf(evt.getSource());
                        List<ColumnFilterPanel> remove = new ArrayList<ColumnFilterPanel>();
                        for(int i=index + 1; i<filters.size(); i++)
                        {
                            panel.remove(filters.remove(i));
                        }
                    }
                    revalidate();
                    repaint();
                }
            }
        };
    }
    
    public void reset()
    {
        for(int i=1; i<this.filters.size(); i++)
        {
            panel.remove(this.filters.remove(i));
        }
        this.filters.get(0).reset();
    }
    
    public void setColumn(TableColumn column)
    {
        if(column != null && this.filters.size() == 1)
        {
            this.filters.get(0).setColumn(column);
        }
    }
    
    public void setRowSorter(RowSorter sorter)
    {
        if(sorter instanceof TableRowSorter)
        {
            RowFilter filter = ((TableRowSorter)sorter).getRowFilter();
            
            if(filter instanceof MultipleRowFilter)
            {
                int count = 0;
                for(Integer colIndex: ((MultipleRowFilter)filter).getFilteredColumns())
                {
                    this.filters.get(count++).setColumn(colIndex);
                }
            }
        }
    }
    
    public boolean isAccepted()
    {
        return this.accepted;
    }
    
    public TableRowSorter getRowSorter()
    {
        TableRowSorter trs = new TableRowSorter(this.table.getModel());
        
        MultipleRowFilter mrf = new MultipleRowFilter()
        {
            private SortedSet<Integer> cols = new TreeSet<Integer>();
            
            @Override
            public SortedSet<Integer> getFilteredColumns()
            {
                return cols;
            }

            @Override
            public boolean include(RowFilter.Entry entry)
            {                
                boolean include = true;
                
                boolean previousWasAnd = true;
                for(ColumnFilterPanel cfp: filters)
                {
                    cols.add(cfp.getColumn().getModelIndex());
                    
                    if(!cfp.include(entry) && previousWasAnd)
                    {
                        include = false;
                    }
                    
                    previousWasAnd = cfp.isAndSelected();
                }
                
                return include;
            }
        };
        trs.setRowFilter(mrf);
        
        return trs;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        columnFilterPanel1 = new org.bisanti.swingx.ColumnFilterPanel(this.table.getColumnModel());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                okButtonActionPerformed(evt);
            }
        });
        okButton.setMinimumSize(cancelButton.getMinimumSize());
        okButton.setSize(cancelButton.getSize());

        panel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        panel.add(columnFilterPanel1, gridBagConstraints);

        scrollPane.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(441, Short.MAX_VALUE)
                .addComponent(okButton)
                .addGap(18, 18, 18)
                .addComponent(cancelButton)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(163, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addGap(54, 54, 54)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_okButtonActionPerformed
    {//GEN-HEADEREND:event_okButtonActionPerformed
        this.accepted = true;
        this.table.setRowSorter(this.getRowSorter());
        super.setVisible(false);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelButtonActionPerformed
    {//GEN-HEADEREND:event_cancelButtonActionPerformed
        this.accepted = false;
        super.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private org.bisanti.swingx.ColumnFilterPanel columnFilterPanel1;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables


}
