import java.awt.Component;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
*  Classe d'un custom bouton "rendre livre"
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class RendererRendu extends JButton implements TableCellRenderer, TableCellEditor
{
	private static final long serialVersionUID = 1L;
	private Object value;
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
    	//setIcon(new ImageIcon("edit.gif"));
    	if(Bibliotheque.Data.listePrets.get((Integer) row).isRendu()){
        	setIcon(new ImageIcon("checkboxChecked.gif"));
        }
        else{
        	setIcon(new ImageIcon("checkboxUnchecked.gif"));
        }
        return this;
    }
 
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        this.value=value;
        int reponse = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir changer l'état de ce prêt ?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if(reponse == JOptionPane.YES_OPTION){	
			Bibliotheque.Controller.rendu((Integer)table.getModel().getValueAt(row, 0));
			if(Bibliotheque.Data.listePrets.get((Integer) row).isRendu()){
				setIcon(new ImageIcon("checkboxChecked.gif"));
			}
			else{
				setIcon(new ImageIcon("checkboxUnchecked.gif"));
			}
		}
		if(OngletPret.champRecherche.getText().equals("Rechercher un prêt ...")){
			OngletPret.MaJComplete();
		}
		else{
			OngletPret.MaJ();
		}
        
       // OngletRecherche.tableResPret.setModel(new ModelePret());
        OngletRecherche.MaJAffichage();
        
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