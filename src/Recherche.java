import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
*  Classe permettant de faire une recherche
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe

 * Donnees cherchables :
 * - nom / prenom Auteur
 * - libelle Categorie
 * - nom Editeur
 * - libelle Statut
 * 
 * - id / titre / annee / [Cat / Editeur / Auteurs] Document
 * - [Emprunteur / Document / Dates] Emprunt
 * - nom / prenom / mail / [statut] Emprunteur
*/

public class Recherche
{
	/**
	 * Version rapide des methodes de recherches (1 seul parametre).
	 */
	
	/**
	 * Recherche d'un auteur
	 * @param global String a rechercher
	 * @return ArrayList<Auteur> des auteurs correspondants a la recherche
	 */
	public static ArrayList<Auteur> Auteur(String global)
	{
		return Auteur(global, global);
	}
	
	/**
	 * Recherche d'un emprunteur
	 * @param global String a rechercher
	 * @return ArrayList<Emprunteur> des emprunteurs correspondants a la recherche
	 */
	public static ArrayList<Emprunteur> Emprunteur(String global)
	{
		return Emprunteur(global, global, global, global);
	}
	
	/**
	 * Recherche d'un document
	 * @param global String a rechercher
	 * @return ArrayList<Document> des documents correspondants a la recherche
	 */
	public static ArrayList<Document> Document(String global)
	{
		return Document(global, global, Integer.valueOf(global), global, global, global);
	}
	
	
	/**
	 * Version detaillee des methodes de recherches.
	 */
	
	/**
	 * Recherche d'un auteur
	 * @param nom String du nom a rechercher
	 * @param prenom String du prenom a rechercher
	 * @return ArrayList<Auteur> des auteurs correspondants a la recherche
	 */
	public static ArrayList<Auteur> Auteur(String nom, String prenom)
	{
		if(nom.isEmpty() && prenom.isEmpty())
		{
			//Si on les veux tous
			return new ArrayList<Auteur>(Bibliotheque.Data.listeAuteurs);
		}
		
		//On en cree un vide
		ArrayList<Auteur> Results = new ArrayList<Auteur>();
		
		Iterator<Auteur> it = Bibliotheque.Data.listeAuteurs.iterator();
		while (it.hasNext())
		{
			Auteur occu = it.next();
			
			if(occu.getNom().contains(nom) || occu.getPrenom().contains(prenom))
			{
				Results.add(occu);
			}
		}
		
		return Results;
	}
	
	
	/**
	 * Recherche d'une categorie
	 * @param libelle String de la categorie a rechercher
	 * @return ArrayList<Categorie> des categories correspondants a la recherche
	 */
	public static ArrayList<Categorie> Categorie(String libelle)
	{
		if(libelle.isEmpty())
		{
			//Si on les veux tous
			return new ArrayList<Categorie>(Bibliotheque.Data.listeCategories);
		}
		
		//On en cree un vide
		ArrayList<Categorie> Results = new ArrayList<Categorie>();
		
		Iterator<Categorie> it = Bibliotheque.Data.listeCategories.iterator();
		while (it.hasNext())
		{
			Categorie occu = it.next();
			
			if(occu.getLibelle().contains(libelle))
			{
				Results.add(occu);
			}
		}
		
		return Results;
	}
	
	/**
	 * Recherche d'un statut
	 * @param libelle String du statut a rechercher
	 * @return ArrayList<Statut> des statuts correspondants a la recherche
	 */
	public static ArrayList<Statut> Statut(String libelle)
	{
		if(libelle.isEmpty())
		{
			//Si on les veux tous
			return new ArrayList<Statut>(Bibliotheque.Data.listeStatuts);
		}
		
		//On en cree un vide
		ArrayList<Statut> Results = new ArrayList<Statut>();
		
		Iterator<Statut> it = Bibliotheque.Data.listeStatuts.iterator();
		while (it.hasNext())
		{
			Statut occu = it.next();
			
			if(occu.getLibelle().contains(libelle))
			{
				Results.add(occu);
			}
		}
		
		return Results;
	}
	
	/**
	 * Recherche d'un document
	 * @param id String de l'id du document a rechercher
	 * @param titre String du titre du document a rechercher
	 * @param annee Integer du titre du document a rechercher
	 * @param categorie String de la categorie du document a rechercher
	 * @param editeur String de l'editeur du document a rechercher
	 * @param auteurs String des auters a rechercher
	 * @return ArrayList<Statut> des statuts correspondants a la recherche
	 */
	public static ArrayList<Document> Document(String id, String titre, Integer annee, String categorie, String editeur, String auteurs)
	{
		if(	id.isEmpty() && 
			titre.isEmpty() &&
			annee == null && 
			categorie.isEmpty() && 
			editeur.isEmpty() &&
			auteurs.isEmpty())
		{
			//Si on les veux tous
			return new ArrayList<Document>(Bibliotheque.Data.tableDocuments.values());
		}
		
		//On en cree un vide
		ArrayList<Document> Results = new ArrayList<Document>();
		
		Set<String> cles = Bibliotheque.Data.tableDocuments.keySet();
		Iterator<String> it = cles.iterator();
		
		while (it.hasNext())
		{
		   String cle = it.next();
		   Document occu = Bibliotheque.Data.tableDocuments.get(cle);
		   
			if(	occu.getIdDocument().contains(id) || 
				occu.getTitre().contains(titre) ||
				occu.getAnnee() == (annee) ||
				occu.getCategorie().getLibelle().contains(categorie) ||
				occu.getEditeur().getNom().contains(editeur) ||
				contientEditeur(occu, auteurs)
				)
			{
				Results.add(occu);
			}
		}
		
		return Results;
	}
	
	/**
	 * Fonction verifiant si des auteurs ont bien ecrit un document
	 * @param doc Document de l'id du document a rechercher
	 * @param auteurs String des auteurs a rechercher
	 * @return Boolean retourne vrai si les auteurs sont ceux du document
	 */
	private static boolean contientEditeur(Document doc, String auteurs)
	{
		Iterator<Auteur> it = doc.getAuteurs().iterator();
		while (it.hasNext())
		{
			Auteur aut = it.next();
			
			if(aut.getNom().contains(auteurs) || aut.getPrenom().contains(auteurs))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Fonction verifiant si des auteurs ont bien ecrit un document
	 * @param nom String du nom de l'emprunteur a rechercher
	 * @param prenom String du prenom de l'emprunteur a rechercher
	 * @param mail String du mail de l'emprunteur a rechercher
	 * @param statut String du statut de l'emprunteur a rechercher
	 * @return ArrayList<Emprunteur> des emprunteurs correspondant a la recherche
	 */
	public static ArrayList<Emprunteur> Emprunteur(String nom, String prenom, String mail, String statut)
	{
		if(nom.isEmpty() && prenom.isEmpty() && mail.isEmpty() && statut.isEmpty())
		{
			//Si on les veux tous
			return new ArrayList<Emprunteur>(Bibliotheque.Data.listeEmprunteurs);
		}
		
		//On en cree un vide
		ArrayList<Emprunteur> Results = new ArrayList<Emprunteur>();
		
		Iterator<Emprunteur> it = Bibliotheque.Data.listeEmprunteurs.iterator();
		while (it.hasNext())
		{
			Emprunteur occu = it.next();
			
			if(	occu.getNom().contains(nom) || 
				occu.getPrenom().contains(prenom) ||
				occu.getMail().contains(mail) ||
				occu.getStatut().getLibelle().contains(statut))
			{
				Results.add(occu);
			}
		}
		
		return Results;
	}
}
