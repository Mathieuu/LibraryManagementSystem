import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;

/**
 * Table model des documents permettant de construire une JTable
 * @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
 */
public class ModeleDocument extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	
	/**
	* Cree un nouvel objet ModeleDocument par defaut
	*/
	public ModeleDocument(){
		super();
		setColumnIdentifiers(new String[] {"Id", "Titre", "Catégorie", "Auteurs", "Editeur", "Année", "Date Retour", "",""});

		for(String key : Bibliotheque.Data.tableDocuments.keySet()){
			Document d = Bibliotheque.Data.tableDocuments.get(key);
			
			GregorianCalendar maDate = new GregorianCalendar(0,0,0);
			for(int i=0; i<Bibliotheque.Data.listePrets.size();i++){
				Emprunt pretTemp = Bibliotheque.Data.listePrets.get(i);
				if(pretTemp.getDocument() == d){
					if(maDate.before(pretTemp.getDateRetour()) && GregorianCalendar.getInstance().getTime().before(pretTemp.getDateRetour().getTime())){
						maDate = pretTemp.getDateRetour();
					}
				}
			}
			String dateRetour = "Disponible";
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(!maDate.equals(new GregorianCalendar(0,0,0))){
				dateRetour = dateFormat.format(maDate.getTime());
			}
			
			
			addRow(new Object[] {d.getIdDocument(), d.getTitre(), d.getCategorie().toString(), d.getAuteurs().toString(), d.getEditeur().toString(), d.getAnnee(), dateRetour, new RendererModifier(), new RendererSupprimer()});
		}
	}
	

	/**
	* Cree un nouvel objet ModeleDocument
	* @param gestion int
	*/
	public ModeleDocument(int recherche){
		super();
		setColumnIdentifiers(new String[] {"Id", "Titre", "Catégorie", "Auteurs", "Editeur", "Année", "Date Retour"});

		for(String key : Bibliotheque.Data.tableDocuments.keySet()){
			Document d = Bibliotheque.Data.tableDocuments.get(key);
			
			GregorianCalendar maDate = new GregorianCalendar(0,0,0);
			for(int i=0; i<Bibliotheque.Data.listePrets.size();i++){
				Emprunt pretTemp = Bibliotheque.Data.listePrets.get(i);
				if(pretTemp.getDocument() == d){
					if(maDate.before(pretTemp.getDateRetour()) && GregorianCalendar.getInstance().getTime().before(pretTemp.getDateRetour().getTime())){
						maDate = pretTemp.getDateRetour();
					}
				}
			}
			String dateRetour = "Disponible";
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(!maDate.equals(new GregorianCalendar(0,0,0))){
				dateRetour = dateFormat.format(maDate.getTime());
			}
			
			
			addRow(new Object[] {d.getIdDocument(), d.getTitre(), d.getCategorie().toString(), d.getAuteurs().toString(), d.getEditeur().toString(), d.getAnnee(), dateRetour});
		}
	}
	
	/**
	* Cree un nouvel objet ModeleDocument
	* @param motsCles String
	*/
	public ModeleDocument(String motsCles){
		
		super();
		
		Document d;
		String[] listeMotsCles;
		boolean estDansLaRecherche;

		motsCles = motsCles.toLowerCase();
		listeMotsCles = motsCles.split(" ");
		
		setColumnIdentifiers(new String[] {"Id", "Titre", "Catégorie", "Auteurs", "Editeur", "Année","Date Retour","",""});

		for(String key : Bibliotheque.Data.tableDocuments.keySet()){
			
			estDansLaRecherche = true; 
			
			d = Bibliotheque.Data.tableDocuments.get(key);
			
			GregorianCalendar maDate = new GregorianCalendar(0,0,0);
			for(int i=0; i<Bibliotheque.Data.listePrets.size();i++){
				Emprunt pretTemp = Bibliotheque.Data.listePrets.get(i);
				if(pretTemp.getDocument() == d){
					if(maDate.before(pretTemp.getDateRetour()) && GregorianCalendar.getInstance().getTime().before(pretTemp.getDateRetour().getTime())){
						maDate = pretTemp.getDateRetour();
					}
				}
			}
			String dateRetour = "Disponible";
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(!maDate.equals(new GregorianCalendar(0,0,0))){
				dateRetour = dateFormat.format(maDate.getTime());
			}
			
			for(int i = 0; i < listeMotsCles.length; i++){
				if(!d.getIdDocument().toString().toLowerCase().contains(listeMotsCles[i]) && !d.getTitre().toString().toLowerCase().contains(listeMotsCles[i]) && !d.getAuteurs().toString().toLowerCase().contains(listeMotsCles[i]) && !d.getCategorie().toString().toLowerCase().contains(listeMotsCles[i]) && !d.getEditeur().toString().toLowerCase().contains(listeMotsCles[i]) && !Integer.toString(d.getAnnee()).contains(listeMotsCles[i])){
					estDansLaRecherche = false;
				}
			}
			
			if(estDansLaRecherche == true)
				addRow(new Object[] {d.getIdDocument(), d.getTitre(), d.getCategorie().toString(), d.getAuteurs().toString(), d.getEditeur().toString(), d.getAnnee(), dateRetour, new RendererModifier(), new RendererSupprimer()});
		}		
	}
	
	/**
	* Cree un nouvel objet ModeleDocument
	* @param identifiant String
	* @param titre String
	* @param categorie String
	* @param auteur String
	* @param editeur String
	* @paran annee String
	*/
	public ModeleDocument(String identifiant, String titre, String categorie, String auteur, String editeur, String annee) {
		super();
		
		identifiant = identifiant.toLowerCase();
		titre = titre.toLowerCase();
		categorie = categorie.toLowerCase();
		auteur = auteur.toLowerCase();
		editeur = editeur.toLowerCase();
		annee = annee.toLowerCase();
		
		Document d;
		boolean estDansLaRecherche;
		
		setColumnIdentifiers(new String[] {"Id", "Titre", "Catégorie", "Auteurs", "Editeur", "Année","Date Retour","",""});

		for(String key : Bibliotheque.Data.tableDocuments.keySet()){
			
			estDansLaRecherche = true; 
			
			d = Bibliotheque.Data.tableDocuments.get(key);
			
			GregorianCalendar maDate = new GregorianCalendar(0,0,0);
			for(int i=0; i<Bibliotheque.Data.listePrets.size();i++){
				Emprunt pretTemp = Bibliotheque.Data.listePrets.get(i);
				if(pretTemp.getDocument() == d){
					if(maDate.before(pretTemp.getDateRetour()) && GregorianCalendar.getInstance().getTime().before(pretTemp.getDateRetour().getTime())){
						maDate = pretTemp.getDateRetour();
					}
				}
			}
			
			String dateRetour = "Disponible";
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(!maDate.equals(new GregorianCalendar(0,0,0))){
				dateRetour = dateFormat.format(maDate.getTime());
			}
			
			if(!d.getIdDocument().toString().toLowerCase().contains(identifiant) || !d.getTitre().toString().toLowerCase().contains(titre) || !d.getAuteurs().toString().toLowerCase().contains(auteur) || !d.getCategorie().toString().toLowerCase().contains(categorie) || !d.getEditeur().toString().toLowerCase().contains(editeur) || !Integer.toString(d.getAnnee()).contains(annee)){
				estDansLaRecherche = false;
			}
			
			if(estDansLaRecherche == true)
				addRow(new Object[] {d.getIdDocument(), d.getTitre(), d.getCategorie().toString(), d.getAuteurs().toString(), d.getEditeur().toString(), d.getAnnee(), dateRetour, new RendererModifier(), new RendererSupprimer()});
		}		
		
	}

	/**
	* Cree un nouvel objet ModeleDocument
	* @param identifiant String
	* @param titre String
	* @param categorie String
	* @param auteur String
	* @param editeur String
	* @paran annee String
	* @param int Recherche
	*/
	public ModeleDocument(String identifiant, String titre, String categorie, String auteur, String editeur, String annee, int recherche) {
		super();
		
		identifiant = identifiant.toLowerCase();
		titre = titre.toLowerCase();
		categorie = categorie.toLowerCase();
		auteur = auteur.toLowerCase();
		editeur = editeur.toLowerCase();
		annee = annee.toLowerCase();
		
		Document d;
		boolean estDansLaRecherche;
		
		setColumnIdentifiers(new String[] {"Id", "Titre", "Catégorie", "Auteurs", "Editeur", "Année","Date Retour"});

		for(String key : Bibliotheque.Data.tableDocuments.keySet()){
			
			estDansLaRecherche = true; 
			
			d = Bibliotheque.Data.tableDocuments.get(key);
			
			GregorianCalendar maDate = new GregorianCalendar(0,0,0);
			for(int i=0; i<Bibliotheque.Data.listePrets.size();i++){
				Emprunt pretTemp = Bibliotheque.Data.listePrets.get(i);
				if(pretTemp.getDocument() == d){
					if(maDate.before(pretTemp.getDateRetour()) && GregorianCalendar.getInstance().getTime().before(pretTemp.getDateRetour().getTime())){
						maDate = pretTemp.getDateRetour();
					}
				}
			}
			
			String dateRetour = "Disponible";
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(!maDate.equals(new GregorianCalendar(0,0,0))){
				dateRetour = dateFormat.format(maDate.getTime());
			}
			
			if(!d.getIdDocument().toString().toLowerCase().contains(identifiant) || !d.getTitre().toString().toLowerCase().contains(titre) || !d.getAuteurs().toString().toLowerCase().contains(auteur) || !d.getCategorie().toString().toLowerCase().contains(categorie) || !d.getEditeur().toString().toLowerCase().contains(editeur) || !Integer.toString(d.getAnnee()).contains(annee)){
				estDansLaRecherche = false;
			}
			
			if(estDansLaRecherche == true)
				addRow(new Object[] {d.getIdDocument(), d.getTitre(), d.getCategorie().toString(), d.getAuteurs().toString(), d.getEditeur().toString(), d.getAnnee(), dateRetour});
		}		
		
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 7) {
            return false;
        } else {
            return true;
        }
    }
	
}