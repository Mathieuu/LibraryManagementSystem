import java.awt.BorderLayout;
import javax.swing.*;

/**
*  Classe affichant l'onglet statistique
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class OngletStatistique extends JPanel
{
    private static final long serialVersionUID = 1L;

    /**
    * Cree un nouvel objet OngletStatistique
    */
    public OngletStatistique()
    {
        this.setLayout(new BorderLayout());
        
        JPanel panelTableau = new JPanel(); 
        panelTableau.setLayout(new BoxLayout(panelTableau, BoxLayout.PAGE_AXIS));
        panelTableau.add(new GraphicStats());
        
        this.add(panelTableau, BorderLayout.CENTER);
    }
}
