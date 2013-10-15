import javax.swing.table.DefaultTableModel;

/**
 * Table model des auteurs permettant de construire une JTable
 * @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
 */
public class ModeleAuteur extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	/**
    * Cree un nouvel objet ModeleAuteur par defaut
    */
	public ModeleAuteur() {
		super();
		setColumnIdentifiers(new String[] { "Nom", "Prénom" });

		for (int i = 0; i < Bibliotheque.Data.listeAuteurs.size(); i++) {
			Auteur auteurTemp = Bibliotheque.Data.listeAuteurs.get(i);
			addRow(new Object[] { auteurTemp.getNom(), auteurTemp.getPrenom() });
		}
	}
	
	/**
	* Cree un nouvel objet ModeleAuteur
	* @param gestion int
	*/
	public ModeleAuteur(int gestion) {
		super();
		setColumnIdentifiers(new String[] { "", "Nom", "Prénom", "", "" });

		for (int i = 0; i < Bibliotheque.Data.listeAuteurs.size(); i++) {
			Auteur auteurTemp = Bibliotheque.Data.listeAuteurs.get(i);
			addRow(new Object[] { i, auteurTemp.getNom(),
					auteurTemp.getPrenom(), new RendererModifier(),
					new RendererSupprimer() });
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
		if (col < 3) {
			return false;
		} else {
			return true;
		}
	}
}
