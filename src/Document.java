import java.util.ArrayList;

/**
*  Bean d'un document
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class Document
{
    String idDocument;
    Categorie categorie;
    String titre;
    ArrayList<Auteur> auteurs;
    Editeur editeur;
    int annee;
    
    /**
     * Cree un nouveau document par defaut
     */
    public Document()
    {
    }
    
    /**
     * Cree un nouvel auteur
     * @param id String
     * @param categorie Categorie
     * @param titre String
     * @param auteurs AttayList<Auteur>
     */
    public Document(String id, Categorie categorie, String titre, ArrayList<Auteur> auteurs, Editeur editeur, int annee)
    {
        this.idDocument = id;
        this.categorie = categorie;
        this.titre = titre;
        this.auteurs = auteurs;
        this.editeur = editeur;
        this.annee = annee;
    }
    
    public String toString()
    {
        return titre;
    }
    
    /**
     * Getters et Setters
     */
    public String getIdDocument()
    {
        return idDocument;
    }
    
    public void setIdDocument(String idDocument)
    {
        this.idDocument = idDocument;
    }
    
    public Categorie getCategorie()
    {
        return categorie;
    }

    public void setCategorie(Categorie categorie)
    {
        this.categorie = categorie;
    }
    
    public String getTitre()
    {
        return titre;
    }

    public void setTitre(String titre)
    {
        this.titre = titre;
    }

    public ArrayList<Auteur> getAuteurs()
    {
        return auteurs;
    }
    
    public void setAuteurs(ArrayList<Auteur> auteurs)
    {
        this.auteurs = auteurs;
    }

    public Editeur getEditeur()
    {
        return editeur;
    }
    
    public void setEditeur(Editeur editeur)
    {
        this.editeur = editeur;
    }

    public int getAnnee()
    {
        return annee;
    }
    
    public void setAnnee(int annee)
    {
        this.annee = annee;
    }

}
