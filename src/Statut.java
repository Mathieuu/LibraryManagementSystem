/**
*  Bean d'un statut
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class Statut implements Comparable<Statut>
{
	String libelle;

	/**
	 * Cree un nouvel statut par defaut
	 */
	public Statut()
	{
		this("N/A");
	}
	
	/**
	 * Charge les donnees de la bibliotheque
	 * @param libelle String
	 */
	public Statut(String libelle)
	{
		this.libelle = libelle;
	}

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
	public int compareTo(Statut autreStatut) {
		return -1*(this.toString().compareTo(autreStatut.toString()));
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
