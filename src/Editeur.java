/**
*  Bean d'un editeur
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/

public class Editeur implements Comparable<Editeur>
{
	String nom;

	/**
	 * Cree un nouvel editeur par defaut
	 */
	public Editeur()
	{
		this("N/A");
	}
	
    /**
     * Cree un editeur
     * @param nom String
     */
	public Editeur(String nom)
	{
		this.nom = nom;
	}

	public String toString()
	{
		return nom;
	}
	
	@Override
    /**
     * Implementation de l'interface comparable
     * @return retourne un int valant -1 si le resultat est superieur,
     * 0 si ils sont egaux,
     * +1 si le resultat est inferieur
     */
	public int compareTo(Editeur autreEditeur) {
		return -1*(this.toString().compareTo(autreEditeur.toString()));
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

}
