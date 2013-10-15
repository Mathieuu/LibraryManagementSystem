import java.util.ArrayList;
import java.util.HashMap;

/**
*  Bean de toutes les donnees exploites par l'application
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class Donnees
{
    ArrayList<Categorie> listeCategories;
    ArrayList<Editeur> listeEditeurs;
    ArrayList<Auteur> listeAuteurs;
    ArrayList<Emprunteur> listeEmprunteurs;
    ArrayList<Statut> listeStatuts;
    HashMap<String, Document> tableDocuments;
    ArrayList<Emprunt> listePrets;
    
    /**
     * Cree un nouvel objet Donnees par defaut
     */
    public Donnees()
    {
        listeCategories = new ArrayList<Categorie>();
        listeEditeurs = new ArrayList<Editeur>();
        listeAuteurs = new ArrayList<Auteur>();
        listeEmprunteurs = new ArrayList<Emprunteur>();
        listeStatuts = new ArrayList<Statut>();
        tableDocuments = new HashMap<String, Document>();
        listePrets = new ArrayList<Emprunt>();
    }
    
    /**
     * Cree un nouvel objet Donnees
     * @param cat ArrayList<Editeur>
     * @param aut ArrayList<Auteur>
     * @param empr ArrayList<Emprunteur>
     * @param stat ArrayList<Statut>
     * @param doc HashMap<String, Document>
     * @param emp ArrayList<Emprunt>
     */
    public Donnees(ArrayList<Categorie> cat, 
            ArrayList<Editeur> edi, 
            ArrayList<Auteur> aut,
            ArrayList<Emprunteur> empr, 
            ArrayList<Statut> stat,
            HashMap<String, Document> doc,
            ArrayList<Emprunt> emp)
    {
        listeCategories = cat;
        listeEditeurs = edi;
        listeAuteurs = aut;
        listeEmprunteurs = empr;
        listeStatuts = stat;
        tableDocuments = doc;
        listePrets = emp;
    }
    
    /**
     * Getters et setters
     */
    public ArrayList<Categorie> getListeCategories() {
        return listeCategories;
    }
    public void setListeCategories(ArrayList<Categorie> listeCategories) {
        this.listeCategories = listeCategories;
    }
    public ArrayList<Editeur> getListeEditeurs() {
        return listeEditeurs;
    }
    public void setListeEditeurs(ArrayList<Editeur> listeEditeurs) {
        this.listeEditeurs = listeEditeurs;
    }
    public ArrayList<Auteur> getListeAuteurs() {
        return listeAuteurs;
    }
    public void setListeAuteurs(ArrayList<Auteur> listeAuteurs) {
        this.listeAuteurs = listeAuteurs;
    }
    public ArrayList<Emprunteur> getListeEmprunteurs() {
        return listeEmprunteurs;
    }
    public void setListeEmprunteurs(ArrayList<Emprunteur> listeEmprunteurs) {
        this.listeEmprunteurs = listeEmprunteurs;
    }
    public ArrayList<Statut> getListeStatuts() {
        return listeStatuts;
    }
    public void setListeStatuts(ArrayList<Statut> listeStatuts) {
        this.listeStatuts = listeStatuts;
    }
    public HashMap<String, Document> getTableDocuments() {
        return tableDocuments;
    }
    public void setTableDocuments(HashMap<String, Document> tableDocuments) {
        this.tableDocuments = tableDocuments;
    }
    public ArrayList<Emprunt> getListePrets() {
        return listePrets;
    }
    public void setListePrets(ArrayList<Emprunt> listePrets) {
        this.listePrets = listePrets;
    }
}
