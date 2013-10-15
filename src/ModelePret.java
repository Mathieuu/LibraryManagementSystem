import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.table.DefaultTableModel;

/**
 * Table model des prets permettant de construire une JTable
 * @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
 */
public class ModelePret extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	/**
	* Cree un nouvel objet ModelePret par defaut
	*/
	public ModelePret(){
		super();
		
		setColumnIdentifiers(new String[] {"","Emprunteur", "Document", "Date Emprunt", "Date Retour","Retard", "", ""});

		for(int i=0; i<Bibliotheque.Data.listePrets.size();i++){
			Emprunt pretTemp = Bibliotheque.Data.listePrets.get(i);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			int testNbJours = GregorianCalendar.getInstance().getTime().compareTo(pretTemp.getDateRetour().getTime());
			int nbJours = 0;
			String retard="";
			if(testNbJours == 1){
				if(!pretTemp.isRendu()){
					nbJours = GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_YEAR) -pretTemp.getDateRetour().get(GregorianCalendar.DAY_OF_YEAR);
					retard = nbJours+"jours";
				}
			}
			else if(testNbJours == 0){
				retard = "Dernier jour";
			}
		
			String dateEmprunt = dateFormat.format(pretTemp.getDateEmprunt().getTime());
			String dateRetour = dateFormat.format(pretTemp.getDateRetour().getTime());
			addRow(new Object[] {i, pretTemp.getEmprunteur(), pretTemp.getDocument().getTitre(), dateEmprunt, dateRetour, retard});
		}
	}
	
	/**
	* Cree un nouvel objet ModelePret
	* @param recherche int
	*/
	public ModelePret(int recherche){
		super();
		
		setColumnIdentifiers(new String[] {"","Emprunteur", "Document", "Date Emprunt", "Date Retour","Retard"});

		for(int i=0; i<Bibliotheque.Data.listePrets.size();i++){
			Emprunt pretTemp = Bibliotheque.Data.listePrets.get(i);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			int testNbJours = GregorianCalendar.getInstance().getTime().compareTo(pretTemp.getDateRetour().getTime());
			int nbJours = 0;
			String retard="";
			if(testNbJours == 1){
				if(!pretTemp.isRendu()){
					nbJours = GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_YEAR) -pretTemp.getDateRetour().get(GregorianCalendar.DAY_OF_YEAR);
					retard = nbJours+"jours";
				}
			}
			else if(testNbJours == 0){
				retard = "Dernier jour";
			}
			
			String dateEmprunt = dateFormat.format(pretTemp.getDateEmprunt().getTime());
			String dateRetour = dateFormat.format(pretTemp.getDateRetour().getTime());
			
			addRow(new Object[] {i, pretTemp.getEmprunteur(), pretTemp.getDocument().getTitre(), dateEmprunt, dateRetour, retard});
		}
	}
	
	/**
	* Cree un nouvel objet ModelePret
	* @param motsCles String
	*/
	public ModelePret(String motsCles){
		
		super();
		
		Emprunt e;
		String[] listeMotsCles;
		boolean estDansLaRecherche;

		motsCles = motsCles.toLowerCase();
		listeMotsCles = motsCles.split(" ");
		
		setColumnIdentifiers(new String[] {"","Emprunteur", "Document", "Date Emprunt", "Date Retour","Retard","",""});

		for(int i=0; i<Bibliotheque.Data.listePrets.size();i++){
			estDansLaRecherche = true; 
			
			e = Bibliotheque.Data.listePrets.get(i);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			int testNbJours = GregorianCalendar.getInstance().getTime().compareTo(e.getDateRetour().getTime());
			int nbJours = 0;
			String retard="";
			if(testNbJours == 1){
				if(!e.isRendu()){
					nbJours = GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_YEAR) -e.getDateRetour().get(GregorianCalendar.DAY_OF_YEAR);
					retard = nbJours+"jours";
				}
			}
			else if(testNbJours == 0){
				retard = "Dernier jour";
			}
			
			String dateEmprunt = dateFormat.format(e.getDateEmprunt().getTime());
			String dateRetour = dateFormat.format(e.getDateRetour().getTime());
			
			for(int j = 0; j < listeMotsCles.length; j++){
				
				if(!Integer.toString(i).contains(listeMotsCles[j]) && !e.getEmprunteur().toString().toLowerCase().contains(listeMotsCles[j]) && !e.getDocument().getTitre().toString().toLowerCase().contains(listeMotsCles[j]) && !dateEmprunt.toLowerCase().contains(listeMotsCles[j]) && !dateRetour.toLowerCase().contains(listeMotsCles[j])){
					estDansLaRecherche = false;
				}
			}
			
			if(estDansLaRecherche == true)
				addRow(new Object[] {i, e.getEmprunteur(), e.getDocument().getTitre(), dateEmprunt, dateRetour, retard,new RendererRendu(), new RendererSupprimer()});
		}		
	}
	
	/**
	* Cree un nouvel objet ModelePret
	* @param titre String
	* @param rechercheDateEmprunt String
	* @param rechercheDateRetour String
	*/
	public ModelePret(String emprunteur, String titre, String rechercheDateEmprunt, String rechercheDateRetour) {
		super();
		
		Emprunt e;
		boolean estDansLaRecherche;

		emprunteur = emprunteur.toLowerCase();
		titre = titre.toLowerCase();
		rechercheDateEmprunt = rechercheDateEmprunt.toLowerCase();
		rechercheDateRetour = rechercheDateRetour.toLowerCase();
		
		setColumnIdentifiers(new String[] {"","Emprunteur", "Document", "Date Emprunt", "Date Retour","Retard","",""});

		for(int i=0; i<Bibliotheque.Data.listePrets.size();i++){
			estDansLaRecherche = true; 
			
			e = Bibliotheque.Data.listePrets.get(i);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			int testNbJours = GregorianCalendar.getInstance().getTime().compareTo(e.getDateRetour().getTime());
			int nbJours = 0;
			String retard="";
			if(testNbJours == 1){
				if(!e.isRendu()){
					nbJours = GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_YEAR) -e.getDateRetour().get(GregorianCalendar.DAY_OF_YEAR);
					retard = nbJours+"jours";
				}
			}
			else if(testNbJours == 0){
				retard = "Dernier jour";
			}
			
			String dateEmprunt = dateFormat.format(e.getDateEmprunt().getTime());
			String dateRetour = dateFormat.format(e.getDateRetour().getTime());
			
			if(!e.getEmprunteur().toString().toLowerCase().contains(emprunteur) || !e.getDocument().getTitre().toString().toLowerCase().contains(titre) || !dateEmprunt.toLowerCase().contains(rechercheDateEmprunt) || !dateRetour.toLowerCase().contains(rechercheDateRetour))
				estDansLaRecherche = false;
			
			if(estDansLaRecherche == true)
				addRow(new Object[] {i, e.getEmprunteur(), e.getDocument().getTitre(), dateEmprunt, dateRetour, retard, new RendererRendu(), new RendererSupprimer()});
		}	
	}
	
	/**
	* Cree un nouvel objet ModelePret
	* @param titre String
	* @param rechercheDateEmprunt String
	* @param rechercheDateRetour String
	* @param recherche int
	*/
	public ModelePret(String emprunteur, String titre, String rechercheDateEmprunt, String rechercheDateRetour, int recherche) {
		super();
		
		Emprunt e;
		boolean estDansLaRecherche;
		
		emprunteur = emprunteur.toLowerCase();
		titre = titre.toLowerCase();
		rechercheDateEmprunt = rechercheDateEmprunt.toLowerCase();
		rechercheDateRetour = rechercheDateRetour.toLowerCase();
		
		setColumnIdentifiers(new String[] {"","Emprunteur", "Document", "Date Emprunt", "Date Retour","Retard"});

		for(int i=0; i<Bibliotheque.Data.listePrets.size();i++){
			estDansLaRecherche = true; 
			
			e = Bibliotheque.Data.listePrets.get(i);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			int testNbJours = GregorianCalendar.getInstance().getTime().compareTo(e.getDateRetour().getTime());
			int nbJours = 0;
			String retard="";
			if(testNbJours == 1){
				if(!e.isRendu()){
					nbJours = GregorianCalendar.getInstance().get(GregorianCalendar.DAY_OF_YEAR) -e.getDateRetour().get(GregorianCalendar.DAY_OF_YEAR);
					retard = nbJours+"jours";
				}
			}
			else if(testNbJours == 0){
				retard = "Dernier jour";
			}
			
			String dateEmprunt = dateFormat.format(e.getDateEmprunt().getTime());
			String dateRetour = dateFormat.format(e.getDateRetour().getTime());
			
			if(!e.getEmprunteur().toString().toLowerCase().contains(emprunteur) || !e.getDocument().getTitre().toString().toLowerCase().contains(titre) || !dateEmprunt.toLowerCase().contains(rechercheDateEmprunt) || !dateRetour.toLowerCase().contains(rechercheDateRetour)){
				estDansLaRecherche = false;
			}
			
			if(estDansLaRecherche == true)
				addRow(new Object[] {i, e.getEmprunteur(), e.getDocument().getTitre(), dateEmprunt, dateRetour, retard});
		}	
	}

	@Override
	public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 6) {
            return false;
        } else {
            return true;
        }
    }
}
