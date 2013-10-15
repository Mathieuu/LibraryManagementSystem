//import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
*  Bean representant un emprunt
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/

public class Emprunt implements Comparable<Emprunt>
{
	Emprunteur emprunteur;
	Document document;
	GregorianCalendar dateEmprunt;
	GregorianCalendar dateRetour;
	boolean rendu;

	/**
	 * Cree un nouvel emprunt par defaut
	 */
	public Emprunt()
	{
	}
	

	/**
	 * Cree un nouvel emprunt
	 * @param emprunteur Emprunteur
	 * @param emprunteur Document
	 * @param dateEmprunt GregorianCalendar
	 */
	public Emprunt(Emprunteur emprunteur, Document document, GregorianCalendar dateEmprunt)
	{
		this.emprunteur = emprunteur;
		this.document = document;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = null;
		this.rendu = false;
	}
	
	/**
	 * Cree un nouvel emprunt
	 * @param emprunteur Emprunteur
	 * @param emprunteur Document
	 * @param dateEmprunt GregorianCalendar
	 * @param dateRetour GregorianCalendar
	 */
	public Emprunt(Emprunteur emprunteur, Document document, GregorianCalendar dateEmprunt, GregorianCalendar dateRetour)
	{
		this.emprunteur = emprunteur;
		this.document = document;
		this.dateEmprunt = dateEmprunt;
		this.dateRetour = dateRetour;
	}

	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateE = dateFormat.format(dateEmprunt.getTime());
		String dateR = dateFormat.format(dateRetour.getTime());
		return "["+ emprunteur + ", "+ document + ", Emprunt le : "+ dateE + ", Retour le : "+ dateR + "]";
	}

	@Override
    /**
     * Implementation de l'interface comparable
     * @return retourne un int valant -1 si le resultat est superieur,
     * 0 si ils sont egaux,
     * +1 si le resultat est inferieur
     */
	public int compareTo(Emprunt autreEmprunt) {
		return -1*(this.toString().compareTo(autreEmprunt.toString()));
   }
	
    /**
     * Getters et Setters
     */
	public Emprunteur getEmprunteur()
	{
		return emprunteur;
	}

	public void setEmprunteur(Emprunteur emprunteur)
	{
		this.emprunteur = emprunteur;
	}

	public Document getDocument()
	{
		return document;
	}

	public void setDocument(Document document)
	{
		this.document = document;
	}

	public GregorianCalendar getDateEmprunt()
	{
		return dateEmprunt;
	}

	public void setDateEmprunt(GregorianCalendar dateEmprunt)
	{
		this.dateEmprunt = dateEmprunt;
	}

	public GregorianCalendar getDateRetour()
	{
		return dateRetour;
	}

	public void setDateRetour(GregorianCalendar dateRetour)
	{
		this.dateRetour = dateRetour;
	}
	
	public boolean isRendu() {
		return rendu;
	}

	public void setRendu(boolean rendu) {
		this.rendu = rendu;
	}
}
