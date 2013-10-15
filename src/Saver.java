import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
*  Classe permettant de serializer les donnees de l'application
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class Saver
{
	/**
	 * Sauvegarde les donnees de la bibliotheque
	 * @return true si la serialization s'est bien passe, false sinon
	 */
	public static boolean Save()
	{
		try
		{
			XMLEncoder encoder = new XMLEncoder(new FileOutputStream("Donnees.xml"));
			encoder.writeObject(Bibliotheque.Data);
			encoder.flush();
			encoder.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
