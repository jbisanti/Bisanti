/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bisanti.swingx;

import java.awt.Component;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.RowFilter;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.bisanti.util.NumberPlus;
import org.bisanti.util.StringUtil;
import org.bisanti.util.Util;

/**
 *
 * @author Jason Bisanti
 */
public class ColumnFilterPanel extends javax.swing.JPanel
{       
    public static final String AND_BUTTON = "columnFilterAndButton";
    
    public static final String OR_BUTTON = "columnFilterOrButton";
    
    private TableColumnModel model;

    /**
     * Creates new form ColumnFilterPanel
     */
    public ColumnFilterPanel()
    {
        this(null);
    }
    
    /**
     * Creates new form ColumnFilterPanel
     */
    public ColumnFilterPanel(TableColumnModel model)
    {
        this(model, null);        
    }
    
    public ColumnFilterPanel(TableColumnModel model, TableColumn column)
    {
        this.model = model;
        initComponents();
        
        if(model != null)
        {           
            this.columnComboBox.setModel(new DefaultComboBoxModel(Util.asList(model.getColumns()).toArray()));
        }
        
        this.columnComboBox.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof TableColumn)
                {
                    super.setText(((TableColumn)value).getIdentifier().toString());
                }
                return this;
            }            
        });
        
        if(column != null)
        {
            this.columnComboBox.setSelectedItem(column);
        }
        
        this.filterComboBox.setRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
            {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof ColumnFilter)
                {
                    super.setText(((ColumnFilter)value).text);
                }
                return this;
            }                   
        });
        
    }
    
    public void reset()
    {
        this.textField.setText("");
        this.filterComboBox.setSelectedIndex(0);
        this.andButton.setSelected(false);
        this.orButton.setSelected(false);
    }
    
    public boolean isAndSelected()
    {
        return this.andButton.isSelected();
    }
    
    public boolean isOrSelected()
    {
        return this.orButton.isSelected();
    }
    
    public void setColumn(TableColumn column)
    {
        this.columnComboBox.setSelectedItem(column);
    }
    
    public void setColumn(int index)
    {
        this.columnComboBox.setSelectedIndex(index);
    }
    
    public TableColumn getColumn()
    {
        return (TableColumn) this.columnComboBox.getSelectedItem();
    }
    
    public void setFilter(ColumnFilter filter)
    {
        this.filterComboBox.setSelectedItem(filter);
    }
    
    public ColumnFilter getFilter()
    {
        return (ColumnFilter) this.filterComboBox.getSelectedItem();
    }
    
    public boolean include(RowFilter.Entry entry)
    {
        int index = this.getColumn().getModelIndex();
        Object val = entry.getValue(index);
        if(val == null)
        {
            return false;
        }
        
        Comparable myValue;
        Comparable tableValue;
        ColumnFilter filter = this.getFilter();
        
        String text = StringUtil.nonNull(this.textField.getText(), true);
        if(val instanceof Number)
        {        
            tableValue = new NumberPlus((Number)val);
            Double numerical = null;
            
            try
            {                
                numerical = Double.valueOf(text);
            }
            catch(NumberFormatException nfe)
            {
                // Do nothing, just check if we can parse text as a number
            }
            
            if(filter.isStringOnlyFilter() || numerical == null)
            {
                myValue = text;
                tableValue = tableValue.toString();
            }
            else
            {
                myValue = new NumberPlus(numerical);
            }
        }
        else
        {
            myValue = text;
            tableValue = StringUtil.nonNull(entry.getStringValue(index), true);
            if(!this.caseCheckBox.isSelected())
            {
                myValue = myValue.toString().toLowerCase();
                tableValue = tableValue.toString().toLowerCase();
            }
            
        }
        
        return filter.matches(tableValue, myValue);
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

        jLabel1 = new javax.swing.JLabel();
        columnComboBox = new javax.swing.JComboBox();
        filterComboBox = new javax.swing.JComboBox(ColumnFilter.values());
        textField = new javax.swing.JTextField();
        andButton = new javax.swing.JToggleButton();
        orButton = new javax.swing.JToggleButton();
        caseCheckBox = new javax.swing.JCheckBox();

        jLabel1.setText("Column");

        columnComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Column1", "Column2", "Column3" }));

        filterComboBox.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                filterComboBoxItemStateChanged(evt);
            }
        });

        andButton.setText("And");
        andButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                andButtonActionPerformed(evt);
            }
        });

        orButton.setText("Or");
        orButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                orButtonActionPerformed(evt);
            }
        });

        caseCheckBox.setText("Match Case");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(columnComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(caseCheckBox)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textField, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(andButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(orButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(columnComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(andButton)
                    .addComponent(orButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(caseCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void andButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_andButtonActionPerformed
    {//GEN-HEADEREND:event_andButtonActionPerformed
        boolean selected = this.andButton.isSelected();
        if(selected)
        {
            this.orButton.setSelected(false);
        }
        super.firePropertyChange(AND_BUTTON, !selected, selected);
    }//GEN-LAST:event_andButtonActionPerformed

    private void orButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_orButtonActionPerformed
    {//GEN-HEADEREND:event_orButtonActionPerformed
        boolean selected = this.andButton.isSelected();
        if(selected)
        {
            this.andButton.setSelected(false);
        }
        super.firePropertyChange(OR_BUTTON, !selected, selected);
    }//GEN-LAST:event_orButtonActionPerformed

    private void filterComboBoxItemStateChanged(java.awt.event.ItemEvent evt)//GEN-FIRST:event_filterComboBoxItemStateChanged
    {//GEN-HEADEREND:event_filterComboBoxItemStateChanged
        switch(evt.getStateChange())
        {
            case ItemEvent.SELECTED:
                this.caseCheckBox.setVisible(this.getFilter().isStringOnlyFilter());
                break;
        }
    }//GEN-LAST:event_filterComboBoxItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton andButton;
    private javax.swing.JCheckBox caseCheckBox;
    private javax.swing.JComboBox columnComboBox;
    private javax.swing.JComboBox filterComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToggleButton orButton;
    private javax.swing.JTextField textField;
    // End of variables declaration//GEN-END:variables
}
