/**
*  Bean d'un emprunteur
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class Emprunteur implements Comparable<Emprunteur>
{
    String nom;
    String prenom;
    String mail;
    Statut statut;
    
    /**
     * Cree un nouvel emprunteur par defaut
     */
    public Emprunteur()
    {
        this("Doe", "John", "John.Doe@utbm.fr", new Statut());
    }
    
    /**
     * Cree un nouvel emprunt
     * @param nom String
     * @param prenom String
     * @param mail String
     * @param statut String
     */
    public Emprunteur(String nom, String prenom, String mail, Statut statut)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.statut = statut;
    }

    public String toString() {
        return nom + " " + prenom;
    }
    
    @Override
    /**
     * Implementation de l'interface comparable
     * @return retourne un int valant -1 si le resultat est superieur,
     * 0 si ils sont egaux,
     * +1 si le resultat est inferieur
     */
    public int compareTo(Emprunteur autreEmprunteur) {
        return -1*(this.toString().compareTo(autreEmprunteur.toString()));
   }
    
    /**
     * Getters et Setters
     */
    public String getNom()
    {
        return nom;
    }
    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    public String getPrenom()
    {
        return prenom;
    }
    
    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getMail()
    {
        return mail;
    }
    
    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public Statut getStatut()
    {
        return statut;
    }
    
    public void setStatut(Statut statut)
    {
        this.statut = statut;
    }
}
