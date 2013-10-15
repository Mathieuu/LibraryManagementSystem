import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;

/**
*  Classe permettant de deserializer les donnees de l'application
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class Loader
{
    /**
     * Charge les donnees de la bibliotheque
     * @return true si la deserialization s'est bien passe, false sinon
     */
    public static boolean Load()
    {
        try
        {
            XMLDecoder decoder = new XMLDecoder(new FileInputStream("Donnees.xml"));
            
            Bibliotheque.Data = (Donnees) decoder.readObject();
            
            //Tri des listes
            Collections.sort(Bibliotheque.Data.listeAuteurs, Collections.reverseOrder());
            Collections.sort(Bibliotheque.Data.listeCategories, Collections.reverseOrder());
            Collections.sort(Bibliotheque.Data.listeEditeurs, Collections.reverseOrder());
            Collections.sort(Bibliotheque.Data.listeEmprunteurs, Collections.reverseOrder());
            Collections.sort(Bibliotheque.Data.listePrets, Collections.reverseOrder());
            Collections.sort(Bibliotheque.Data.listeStatuts, Collections.reverseOrder());
            
            decoder.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
}
