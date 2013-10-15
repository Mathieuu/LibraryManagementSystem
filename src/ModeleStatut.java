import javax.swing.table.DefaultTableModel;

/**
 * Table model des statuts permettant de construire une JTable
 * @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
 */
public class ModeleStatut extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	/**
	* Cree un nouvel objet ModeleStatut par defaut
	*/
	public ModeleStatut(){
		super();
		setColumnIdentifiers(new String[] {"","Libelle"});

		for(int i=0; i<Bibliotheque.Data.listeStatuts.size();i++){
			Statut statutTemp = Bibliotheque.Data.listeStatuts.get(i);
			addRow(new Object[] {i,statutTemp.toString()});
		}
	}
	
	/**
	* Cree un nouvel objet ModeleStatut
	* @param gestion int
	*/
	public ModeleStatut(int gestion){
		super();
		setColumnIdentifiers(new String[] {"","Libelle", "",""});

		for(int i=0; i<Bibliotheque.Data.listeStatuts.size();i++){
			Statut statutTemp = Bibliotheque.Data.listeStatuts.get(i);
			addRow(new Object[] {i,statutTemp.toString(), new RendererModifier(), new RendererSupprimer()});
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }
}
