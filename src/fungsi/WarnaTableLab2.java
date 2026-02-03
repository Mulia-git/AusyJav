/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fungsi;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author zunafif
 */
public class WarnaTableLab2 extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if(table.getValueAt(row,12).toString().isEmpty()){
            component.setBackground(new Color(255,255,255));
            component.setForeground(new Color(50,50,50));
        } else {
            component.setBackground(new Color(200,0,0));
            component.setForeground(new Color(255,230,230));
        }
        
        return component;
    }
}
