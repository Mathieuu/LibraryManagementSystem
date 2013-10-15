/**
*  Bean d'une categorie
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class Categorie implements Comparable<Categorie>
{
	String libelle;

	/**
	 * Cree un nouvel categorie par defaut
	 */
	public Categorie()
	{
		this("N/A");
	}
	
    /**
     * Cree un nouvel auteur
     * @param libelle String
     */
	public Categorie(String libelle)
	{
		super();
		this.libelle = libelle;
	}
	
	@Override
	public String toString()
	{
		return libelle;
	}
	
	@Override
    /**
     * Implementation de l'interface comparable
     * @return retourne un int valant -1 si le resultat est superieur,
     * 0 si ils sont egaux,
     * +1 si le resultat est inferieur
     */
	public int compareTo(Categorie autreCategorie) {
		return -1*(this.toString().compareTo(autreCategorie.toString()));
	}
	
    /**
     * Getters et Setters
     */
	public String getLibelle()
	{
		return libelle;
	}
	
	public void setLibelle(String libelle)
	{
		this.libelle = libelle;
	}
}
