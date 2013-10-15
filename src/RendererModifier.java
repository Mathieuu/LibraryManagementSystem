import java.awt.Component;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
*  Classe d'un custom bouton "modifier"
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class RendererModifier extends JButton implements TableCellRenderer, TableCellEditor
{
	private static final long serialVersionUID = 1L;
	private Object value;
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
    	//setText("Modifier");
    	setIcon(new ImageIcon("edit.gif"));
        return this;
    }
 
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        this.value=value;
       // setIcon(new ImageIcon("edit.gif"));
        System.out.println(row+" "+table.getModel().getValueAt(row, 0).getClass());
        Bibliotheque.Controller.modification(table, table.getModel().getValueAt(row, 0));
        return null;
    }
 
    public void cancelCellEditing(){}
 
    public boolean stopCellEditing(){
        return false;
    }
 
    public Object getCellEditorValue(){
        return value;
    }
 
    public boolean isCellEditable(EventObject anEvent){
        return true;
    }
 
    public boolean shouldSelectCell(EventObject anEvent){
        return false;
    }
 
    public void addCellEditorListener(CellEditorListener l){}
 
    public void removeCellEditorListener(CellEditorListener l){}
}