import javax.swing.table.DefaultTableModel;

/**
 * Table model des edtieurs permettant de construire une JTable
 * @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
 */
public class ModeleEditeur extends DefaultTableModel {
    private static final long serialVersionUID = 1L;

    /**
    * Cree un nouvel objet ModeleEditeur par defaut
    */
    public ModeleEditeur(){
        super();
        setColumnIdentifiers(new String[] {"", "Nom"});

        for(int i=0; i<Bibliotheque.Data.listeEditeurs.size();i++){
            Editeur editeurTemp = Bibliotheque.Data.listeEditeurs.get(i);
            addRow(new Object[] {i, editeurTemp.toString()});
        }
    }
    
    /**
    * Cree un nouvel objet ModeleEditeur
    * @param gestion int
    */
    public ModeleEditeur(int gestion){
        super();
        setColumnIdentifiers(new String[] {"", "Nom", "",""});

        for(int i=0; i<Bibliotheque.Data.listeEditeurs.size();i++){
            Editeur editeurTemp = Bibliotheque.Data.listeEditeurs.get(i);
            addRow(new Object[] {i, editeurTemp.toString(), new RendererModifier(), new RendererSupprimer()});
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }
}
