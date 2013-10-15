/**
*  Classe representant un auteur
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class Auteur implements Comparable<Auteur>
{
	String nom;
	String prenom;
	
	/**
	 * Cree un nouvel auteur par defaut
	 */
	public Auteur()
	{
		this("Doe", "John");
	}
	
    /**
     * Cree un nouvel auteur
     * @param prenom Prenom de l'auteur
     * @param nom Nom de l'auteur
     */
	public Auteur(String prenom, String nom)
	{
		this.nom = nom;
		this.prenom = prenom;
	}
	
	@Override
	public String toString()
	{
		return nom + " " + prenom;
	}
	
	@Override
	/**
	 * Implementation de l'interface comparable
	 * @return retourne un int valant -1 si le resultat est superieur,
	 * 0 si ils sont egaux,
	 * +1 si le resultat est inferieur
	 */
	public int compareTo(Auteur autreAuteur) {
		return -1*(this.toString().compareTo(autreAuteur.toString()));
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
	
}
