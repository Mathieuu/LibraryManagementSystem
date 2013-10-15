import javax.swing.table.DefaultTableModel;

/**
 * Table model des categories permettant de construire une JTable
 * @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
 */
public class ModeleCategorie extends DefaultTableModel {
    private static final long serialVersionUID = 1L;
    
    /**
    * Cree un nouvel objet ModeleCategorie par defaut
    */
    public ModeleCategorie(){
        super();
        setColumnIdentifiers(new String[] {"", "Libelle"});

        for(int i=0; i<Bibliotheque.Data.listeCategories.size();i++){
            Categorie categorieTemp = Bibliotheque.Data.listeCategories.get(i);
            addRow(new Object[] {i, categorieTemp.toString()});
        }
    }
    

    /**
    * Cree un nouvel objet ModeleCategorie
    * @param gestion int
    */
    public ModeleCategorie(int gestion){
        super();
        setColumnIdentifiers(new String[] {"", "Libelle", "", ""});

        for(int i=0; i<Bibliotheque.Data.listeCategories.size();i++){
            Categorie categorieTemp = Bibliotheque.Data.listeCategories.get(i);
            addRow(new Object[] {i, categorieTemp.toString(), new RendererModifier(), new RendererSupprimer()});
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
