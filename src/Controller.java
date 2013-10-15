import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.jdesktop.swingx.autocomplete.Configurator;

/**
*  Classe permettant de manipuler les données
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class Controller {
	
	static ArrayList<Document> docDispo;

	public Controller(){
		
	}

	public static ArrayList<Document> getDocDispo() {
		return docDispo;
	}

	/**
	*Suppression d'un objet dans une table
	*@param table dans laquelle l'operation est operee
	*@param object l'objet a supprimer dans la table
	*/
	void suppression(JTable table, Object object){
		if(table.equals(OngletDocument.getTableauDocument())){
			boolean trouve = false;
			for(int i=0; i<Bibliotheque.Data.getListePrets().size(); i++){
				if(Bibliotheque.Data.getListePrets().get(i).getDocument().equals(Bibliotheque.Data.getTableDocuments().get(object))){
					trouve = true;
				}
			}
			if(!trouve){
				int reponse = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer le document \""+ Bibliotheque.Data.getTableDocuments().get(object).getTitre() +"\" ?", "Confirmation Suppression", JOptionPane.YES_NO_OPTION);
				if(reponse == JOptionPane.YES_OPTION){	
					Bibliotheque.Data.getTableDocuments().remove(object);
					if(OngletDocument.champRecherche.getText().equals("Rechercher un document ...")){
						OngletDocument.MaJComplete();
					}
					else{
						OngletDocument.MaJ();
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Ce document est utilisé ailleurs dans la base, impossible de le supprimer !", "Suppression impossible", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(table.equals(OngletEmprunteur.getTableauEmprunteur())){
			boolean trouve = false;
			for(int i=0; i<Bibliotheque.Data.getListePrets().size(); i++){
				if(Bibliotheque.Data.getListePrets().get(i).getEmprunteur().equals(Bibliotheque.Data.getListeEmprunteurs().get((Integer) object))){
					trouve = true;
				}
			}
			if(!trouve){
				int reponse = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer l'emprunteur \""+ Bibliotheque.Data.getListeEmprunteurs().get((Integer) object) +"\" ?", "Confirmation Suppression", JOptionPane.YES_NO_OPTION);
				if(reponse == JOptionPane.YES_OPTION){	
					Bibliotheque.Data.getListeEmprunteurs().remove(Bibliotheque.Data.getListeEmprunteurs().get((Integer) object));
					if(OngletEmprunteur.champRecherche.getText().equals("Rechercher un emprunteur ...")){
						OngletEmprunteur.MaJComplete();
					}
					else{
						OngletEmprunteur.MaJ();
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Cet emprunteur est utilisé ailleurs dans la base, impossible de le supprimer !", "Suppression impossible", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(table.equals(OngletPret.getTableauPret())){
			int reponse = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer le prêt \""+ Bibliotheque.Data.getListePrets().get((Integer) object) +"\" ?", "Confirmation Suppression", JOptionPane.YES_NO_OPTION);
			System.out.println(object);
			if(reponse == JOptionPane.YES_OPTION){	
				Bibliotheque.Data.getListePrets().remove(Bibliotheque.Data.getListePrets().get((Integer) object));
				System.out.println(OngletPret.champRecherche.getText());
				if(OngletPret.champRecherche.getText().equals("Rechercher un prêt ...")){
					OngletPret.MaJComplete();
				}
				else{
					OngletPret.MaJ();
				}
			}
		}
		else if(table.equals(OngletGestion.getTableauAuteur())){
			boolean trouve = false;
			for(String key : Bibliotheque.Data.tableDocuments.keySet()){
				if(Bibliotheque.Data.getTableDocuments().get(key).getAuteurs().contains(Bibliotheque.Data.getListeAuteurs().get((Integer) object))){	
					trouve = true;
				}
			}
			if(!trouve){
				int reponse = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer l'auteur \""+ Bibliotheque.Data.getListeAuteurs().get((Integer) object) +"\" ?", "Confirmation Suppression", JOptionPane.YES_NO_OPTION);
				if(reponse == JOptionPane.YES_OPTION){	
					Bibliotheque.Data.getListeAuteurs().remove(Bibliotheque.Data.getListeAuteurs().get((Integer) object));
					Bibliotheque.ongletGestion.MaJ();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Cet auteur est utilisé ailleurs dans la base, impossible de le supprimer !", "Suppression impossible", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(table.equals(OngletGestion.getTableauCategorie())){
			boolean trouve = false;
			for(String key : Bibliotheque.Data.tableDocuments.keySet()){
				if(Bibliotheque.Data.getTableDocuments().get(key).getCategorie().equals(Bibliotheque.Data.getListeCategories().get((Integer) object))){	
					trouve = true;
				}
			}
			if(!trouve){
				int reponse = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer la catégorie \""+ Bibliotheque.Data.getListeCategories().get((Integer) object) +"\" ?", "Confirmation Suppression", JOptionPane.YES_NO_OPTION);
				if(reponse == JOptionPane.YES_OPTION){	
					Bibliotheque.Data.getListeCategories().remove(Bibliotheque.Data.getListeCategories().get((Integer) object));
					Bibliotheque.ongletGestion.MaJ();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Cette catégorie est utilisé ailleurs dans la base, impossible de le supprimer !", "Suppression impossible", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(table.equals(OngletGestion.getTableauEditeur())){
			boolean trouve = false;
			for(String key : Bibliotheque.Data.tableDocuments.keySet()){
				if(Bibliotheque.Data.getTableDocuments().get(key).getEditeur().equals(Bibliotheque.Data.getListeEditeurs().get((Integer) object))){	
					trouve = true;
				}
			}
			if(!trouve){
				int reponse = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer l'éditeur \""+ Bibliotheque.Data.getListeEditeurs().get((Integer) object) +"\" ?", "Confirmation Suppression", JOptionPane.YES_NO_OPTION);
				if(reponse == JOptionPane.YES_OPTION){	
					Bibliotheque.Data.getListeEditeurs().remove(Bibliotheque.Data.getListeEditeurs().get((Integer) object));
					Bibliotheque.ongletGestion.MaJ();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Cet éditeur est utilisé ailleurs dans la base, impossible de le supprimer !", "Suppression impossible", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(table.equals(OngletGestion.getTableauStatut())){
			boolean trouve = false;
			for(int i=0; i<Bibliotheque.Data.getListeEmprunteurs().size(); i++){
				if(Bibliotheque.Data.getListeEmprunteurs().get(i).getStatut().equals(Bibliotheque.Data.getListeStatuts().get((Integer) object))){	
					trouve = true;
				}
			}
			if(!trouve){
				int reponse = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer le statut \""+ Bibliotheque.Data.getListeStatuts().get((Integer) object) +"\" ?", "Confirmation Suppression", JOptionPane.YES_NO_OPTION);
				if(reponse == JOptionPane.YES_OPTION){	
					Bibliotheque.Data.getListeStatuts().remove(Bibliotheque.Data.getListeStatuts().get((Integer) object));
					Bibliotheque.ongletGestion.MaJ();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Ce statut est utilisé ailleurs dans la base, impossible de le supprimer !", "Suppression impossible", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	*Modification d'un objet dans une table
	*@param table dans laquelle l'operation est operee
	*@param object l'objet a remplacer dans la table
	*/
	void modification(JTable table, Object object){
		
		if(table.equals(OngletDocument.getTableauDocument())){
			Document docTemp = Bibliotheque.Data.tableDocuments.get(object);
			Integer annee = docTemp.getAnnee();
			ArrayList<Auteur>  aut = docTemp.getAuteurs();
			Categorie cat = docTemp.getCategorie();
			Editeur edi = docTemp.getEditeur();
			String titre = docTemp.getTitre();
			String id = docTemp.getIdDocument();
			new FormulaireDocument(id, titre, annee.toString(), cat, aut, edi, 0);
		}
		else if(table.equals(OngletEmprunteur.getTableauEmprunteur())){
			System.out.println("control"+object.getClass());
			Emprunteur emprunteur = Bibliotheque.Data.listeEmprunteurs.get((Integer)object);
			String nom = emprunteur.getNom();
			String prenom = emprunteur.getPrenom();
			String mail = emprunteur.getMail();
			Statut stat = emprunteur.getStatut();
			new FormulaireEmprunteur(nom, prenom, mail, stat, 0);
		}
		else if(table.equals(OngletGestion.getTableauAuteur())){
			Auteur autTemp = Bibliotheque.Data.listeAuteurs.get((Integer)object);
			String nom = autTemp.getNom();
			String prenom = autTemp.getPrenom();
			new FormulaireAuteur(nom, prenom, 0);
		}
		else if(table.equals(OngletGestion.getTableauCategorie())){
			Categorie catTemp = Bibliotheque.Data.listeCategories.get((Integer)object);
			String libelle = catTemp.getLibelle();
			new FormulaireCategorie(libelle, 0);
		}
		else if(table.equals(OngletGestion.getTableauEditeur())){
			Editeur ediTemp = Bibliotheque.Data.listeEditeurs.get((Integer)object);
			String nom = ediTemp.getNom();
			new FormulaireEditeur(nom, 0);
		}
		else if(table.equals(OngletGestion.getTableauStatut())){
			Statut statutTemp = Bibliotheque.Data.listeStatuts.get((Integer)object);
			String libelle = statutTemp.getLibelle();
			new FormulaireStatut(libelle, 0);
		}
	}
	
	/**
	*Change l'etat du rendu
	*@param (Integer)object Integer dans lequel changer le rendu
	*/
	void rendu(Integer i){
		if(Bibliotheque.Data.listePrets.get(i).isRendu()){
			Bibliotheque.Data.listePrets.get(i).setRendu(false);
		}
		else{
			Bibliotheque.Data.listePrets.get(i).setRendu(true);
		}
	}

	/**
	*Creation d'une liste pour un comboBox a partir d'une ArrayList
	*@param maListe ArrayList a placer dans la combobox
	*/
	public JComboBox creerListeCombo(ArrayList<?> maListe){
		JComboBox combo = new JComboBox();
		Configurator.enableAutoCompletion(combo);
		
		for(int i=0;i<maListe.size();i++){
			combo.addItem(maListe.get(i));
		}
		return combo;
	}
	
	/**
	*Creation d'une liste pour un comboBox a partir d'une HashMap
	*@param maListe HashMap a placer dans la combobox
	*/
	public JComboBox creerListeCombo(HashMap<String, Document> maListe){
		JComboBox combo = new JComboBox();
		Configurator.enableAutoCompletion(combo);
		boolean ajouter;
		for(String key : Bibliotheque.Data.tableDocuments.keySet()){
			ajouter = true;
			for(int j=0;j<Bibliotheque.Data.listePrets.size();j++){
				//changer cette condition
				GregorianCalendar today = new GregorianCalendar();
				if(Bibliotheque.Data.listePrets.get(j).getDateRetour().after(today)  && 
					Bibliotheque.Data.listePrets.get(j).getDocument().equals(maListe.get(key))){
					ajouter = false;
				}
			}
			if(ajouter){
				docDispo.add(maListe.get(key));
				combo.addItem(maListe.get(key));
			}
		}
		return combo;
	}
	
	/**
	*Creation d'une liste pour une JList a partir d'une ArrayList
	*@param maListe HashMap a placer dans la combobox
	*/
	public JList creerListeJList(ArrayList<Auteur> maListe){
		JList list = new JList();
		
		DefaultListModel listModel = new DefaultListModel();
		for(int i=0; i<maListe.size(); i++){
		     listModel.addElement(maListe.get(i));
		}
		list.setModel(listModel);
		return list;
	}
	
	/**
	*Mise à jour d'une liste pour une comboBox a partir d'une ArrayList
	*@param monCombo Combobox a modifier
	*@param maListe ArrayList<?> nouvelle liste a placer dans la comboBox
	*/
	public void majListeCombo(JComboBox monCombo, ArrayList<?> maListe){
		monCombo.removeAllItems();
		
		for(int i=0;i<maListe.size();i++){
			monCombo.addItem(maListe.get(i));
		}
	}
}
