import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JPanel;

/**
*  Classe permettant de dessiner des graphes
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class GraphicStats extends JPanel
{
    private static final long serialVersionUID = 1L;
    //private Color PrevColor;
    
    private int graphSize;
    
    /**
     * Constructeur
     */
    GraphicStats()
    {
        graphSize = 120;
    }

    /**
     * Fonction principale represantant la "toile"
     * @param g Graphics
     */
    public void paint(Graphics g)
    {
        g.setColor(new Color(240, 240, 240));
        g.fill3DRect(0, 0, getSize().width, getSize().height, true);
        
        g.setColor(Color.black);
        
        paintCategorie(g, 10, 20);
        paintStatut(g, 10, graphSize+50);
        paintPrets(g, 10+graphSize+240, 20);
        paintInfos(g, 20, 20+graphSize*2+80);
    }
    
    /**
     * Paint le graphe des categories
     * @param g Graphics
     * @param x int coordonnees de positionnement en x
     * @param y int coordonnees de positionnement en y
     */
    private void paintCategorie(Graphics g, int x, int y)
    {
        // calcul des stats :
        Map<String, Integer> stats = new HashMap<String, Integer>();
        
        Iterator<String> itr = Bibliotheque.Data.tableDocuments.keySet().iterator();
        int tot = Bibliotheque.Data.tableDocuments.size();
        
        while (itr.hasNext())
        {
            String idDoc = itr.next();
            Document doc = Bibliotheque.Data.tableDocuments.get(idDoc);
            String categorie = doc.getCategorie().getLibelle();
            
            int val = 0;
            
            if(stats.containsKey(categorie))
                val = stats.get(categorie);
            
            stats.put(categorie, val+1);
        }
        
        
        // ensuite on affiche.
        
        Iterator<String> it = stats.keySet().iterator();
        
        int debutArc = 0;
        int posLegende = y+15;
        int iColor = 0;
        
        g.drawString("Catégories : ", x, y);
        
        while (it.hasNext())
        {
            String categ = it.next();
            int nombre = stats.get(categ);

            float Q = float_round(nombre/(float)tot, 2);
            int TailleArc = (int)(Q*360);
            
//          g.setColor(getNextColor());
            
            g.setColor(getIemeColor(iColor));
            iColor++;
            
            g.fillArc(x, y+10, graphSize, graphSize, debutArc, TailleArc);
            
            debutArc += TailleArc;
            
            // et la légende.
            g.fillRect(x+graphSize+5, posLegende-7, 7, 7);
            g.setColor(Color.black);
            g.drawString(categ+"    "+floor(((nombre/(float)tot)*100), 2)+"%", x+graphSize+20, posLegende);
            posLegende += 12;
        }
        
    }
    
    /**
     * Paint le graphe des statut
     * @param g Graphics
     * @param x int coordonnees de positionnement en x
     * @param y int coordonnees de positionnement en y
     */
    private void paintStatut(Graphics g, int x, int y)
    {
        // calcul des stats :
        Map<String, Integer> stats = new HashMap<String, Integer>();
        
        Iterator<Emprunteur> itr = Bibliotheque.Data.listeEmprunteurs.iterator();
        int tot = Bibliotheque.Data.listeEmprunteurs.size();
        
        while (itr.hasNext())
        {
            Emprunteur element = itr.next();
            
            int val = 0;
            
            if(stats.containsKey(element.getStatut().getLibelle()))
                val = stats.get(element.getStatut().getLibelle());
            
            stats.put(element.getStatut().getLibelle(), val+1);
        }
        
        
        // ensuite on affiche.
        
        Iterator<String> it = stats.keySet().iterator();
        
        int debutArc = 0;
        int posLegende = y+15;
        int iColor = 0;
        
        g.drawString("Statuts : ", x, y);
        
        while (it.hasNext())
        {
            String statut = it.next();
            int nombre = stats.get(statut);

            float Q = float_round(nombre/(float)tot, 2);
            int TailleArc = (int)(Q*360);
            
            // couleurs en mode random :
            //g.setColor(getNextColor());
            
            g.setColor(getIemeColor(iColor));
            iColor++;
            
            g.fillArc(x, y+10, graphSize, graphSize, debutArc, TailleArc);
            
            debutArc += TailleArc;
            
            // et la légende.
            g.fillRect(x+graphSize+5, posLegende-7, 7, 7);
            g.setColor(Color.black);
            g.drawString(statut+"    "+floor(((nombre/(float)tot)*100), 2)+"%", x+graphSize+20, posLegende);
            posLegende += 12;
        }
        
        
    }
    
    /**
     * Paint le graphe des prets
     * @param g Graphics
     * @param x int coordonnees de positionnement en x
     * @param y int coordonnees de positionnement en y
     */
    private void paintPrets(Graphics g, int x, int y)
    {
        g.drawString("Evolution des prêts : ", x, y);
        
        int largeur = 3*graphSize;
        int hauteur = y+graphSize+20;
        
        g.drawLine(x, y+20, x, hauteur);
        g.drawLine(x, hauteur, x+largeur, hauteur);
        
        // calcul des stats
        // mois - nombre
        //Map<Integer, Integer> stats = new HashMap<Integer, Integer>();
        int stats[] = new int[12];
        
        for(int i = 0; i<12; i++)
            stats[i] = 0;
        
        Iterator<Emprunt> itr = Bibliotheque.Data.listePrets.iterator();
        
        Calendar calendar = new GregorianCalendar();
        
        int max = 0;
        
        int prev_x = 0;
        int prev_y = 0;
        
        while (itr.hasNext())
        {
            Emprunt elem = itr.next();
            
            // ceux de cette année :
            if(elem.dateEmprunt.get(Calendar.YEAR) == calendar.get(Calendar.YEAR))
            {
                stats[elem.dateEmprunt.get(Calendar.MONTH)]++;
                
                if(stats[elem.dateEmprunt.get(Calendar.MONTH)] > max)
                    max = stats[elem.dateEmprunt.get(Calendar.MONTH)];
            }
            
            // ceux de l'année passée
            if(elem.dateEmprunt.get(Calendar.YEAR) == (calendar.get(Calendar.YEAR)-1))
            {
                // seulement ceux de moins d'un an.
                if(elem.dateEmprunt.get(Calendar.MONTH) >= calendar.get(Calendar.MONTH))
                {
                    stats[elem.dateEmprunt.get(Calendar.MONTH)]++;
                    
                    if(stats[elem.dateEmprunt.get(Calendar.MONTH)] > max)
                        max = stats[elem.dateEmprunt.get(Calendar.MONTH)];
                }
            }
        }
        
        int new_x = 0;
        int new_y = 0;
        
        int j = 0;
        for(int i = calendar.get(Calendar.MONTH)+1; i<calendar.get(Calendar.MONTH)+13; i++)
        {
            g.drawString(lettreMois(i%12), x+3+(j*(largeur/12)), y+hauteur);
            
            if(stats[i%12] == 0)
            {
                new_x = x+3+(j*(largeur/12));
                new_y = hauteur-1;
                g.fillRect(new_x-1, new_y-1, 3, 3);
            }
            else
            {
                float ratio = stats[i%12]/(float)max;
                new_x = x+3+(j*(largeur/12));
                new_y = (int)(hauteur-(graphSize*ratio));
                g.fillRect(new_x-1, new_y-1, 3, 3);
            }
            
            // puis finalement les traits
            if(prev_x == 0 && prev_y == 0)
            {
                prev_x = new_x;
                prev_y = new_y;
            }
            g.drawLine(prev_x, prev_y, new_x, new_y);
            prev_x = new_x;
            prev_y = new_y;
            
            j++;
        }
        
        // on place le max
        g.drawString(String.valueOf(max), x-9, y+23);
        
        // et le 0
        g.drawString("0", x-9, hauteur+3);
    }
    
    /**
     * Fonctionnant retournant la lettre correspondant au mois
     * @param mois int correspondant au moins: Janvier = 0 - Decembre = 11
     * @return String contenant la premiere lettre du mois correspondant
     */
    String lettreMois(int mois)
    {
        switch(mois)
        {
            case 0:
                return "J";
            case 1:
                return "F";
            case 2:
                return "M";
            case 3:
                return "A";
            case 4:
                return "M";
            case 5:
            case 6:
                return "J";
            case 7:
                return "A";
            case 8:
                return "S";
            case 9:
                return "O";
            case 10:
                return "N";
            case 11:
                return "D";
        }
        return "X";
    }
    
    
    /**
     * Paint le graphe des infos
     * @param g Graphics
     * @param x int coordonnees de positionnement en x
     * @param y int coordonnees de positionnement en y
     */
    private void paintInfos(Graphics g, int x, int y)
    {
        g.drawString("Informations :", x, y);
        
        g.drawString("Emprunteurs enregistrés : "+Bibliotheque.Data.listeEmprunteurs.size(), x, y+40);
        
        g.drawString("Documents enregistrés : "+Bibliotheque.Data.tableDocuments.size(), x, y+60);
        
        g.drawString("Prêts enregistrés : "+Bibliotheque.Data.listePrets.size(), x, y+80);
        
        
        
        g.drawString("Nombre de catégories : "+Bibliotheque.Data.listeCategories.size(), x+200, y+40);
        
        g.drawString("Nombre de statuts : "+Bibliotheque.Data.listeStatuts.size(), x+200, y+60);
        
        
        
        g.drawString("Nombre d'auteurs : "+Bibliotheque.Data.listeAuteurs.size(), x+400, y+40);
        
        g.drawString("Nombre d'éditeurs : "+Bibliotheque.Data.listeEditeurs.size(), x+400, y+60);
        
    }
    
    public static double floor(double a, int n)
    {
        double p = Math.pow(10.0, n);
        return  Math.ceil((a*p)+0.5) / p;
    }
    
    public static float float_round(float a, int n)
    {
        double p = Math.pow(10.0, n);
        return  (float) (Math.ceil((a*p)+0.5) / p);
    }
    
    /**
     * Fonctionnant retournant une couleur pour un mois
     * @param i int entre 0 et 12
     * @return Color correspondant a un nombre
     */
    private Color getIemeColor(int i)
    {
        switch(i)
        {
        case 0:
            return Color.green;
        case 1:
            return Color.orange;
        case 2:
            return Color.magenta;
        case 3:
            return Color.red;
        case 4:
            return Color.gray;
        case 5:
            return Color.yellow;
        case 6:
            return Color.lightGray;
        case 7:
            return Color.pink;
        case 8:
            return Color.blue;
        case 9:
            return Color.darkGray;
        case 10:
            return Color.cyan;
        case 11:
            return Color.white;
        case 12:
            return Color.black;
        }
        
        return Color.black;
    }
}
