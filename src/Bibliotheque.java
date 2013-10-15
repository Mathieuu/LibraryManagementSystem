import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

/**
*  Classe principale de l'application
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class Bibliotheque extends JFrame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;

	public static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static int nbMaximumEmprunts = 2;
	
	public static Donnees Data; //Modele
	
	public static Controller Controller; //Controleur
	
	public static OngletDocument ongletDocuments;	//Vues
	public static OngletEmprunteur ongletEmprunteur;
	public static OngletPret ongletPret;
	public static OngletRecherche ongletRecherche;
	public static OngletStatistique ongletStatistique;
	public static OngletGestion ongletGestion;
	
    private JMenuItem itemDoc;
    private JMenuItem itemSave;
    private JMenuItem itemAut;
    private JMenuItem itemCat;
    private JMenuItem itemEdi;
    private JMenuItem itemEmp;
    private JMenuItem itemStat;
    private JMenuItem itemPret;
    private JMenuItem itemAprop;
    private JMenuItem itemQuit;
    
    public static boolean Administrateur = false;
	
    /**
     * Method launching the application
     */
	public Bibliotheque()
	{
		super("Bibliothèque UTBM");
		
		//if(System.getProperty("os.name").startsWith("Windows")){ //Pour activer le changement de skin seuleument pour windows
			try {
			   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			   SwingUtilities.updateComponentTreeUI(this);
			   //Force chaque composant de la fenêtre à appeler sa méthode updateUI
			} catch (InstantiationException e) {
			} catch (ClassNotFoundException e) {
			} catch (UnsupportedLookAndFeelException e) {
			} catch (IllegalAccessException e) {}
		//}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(this);
		setSize(800, 600);
		setLocationRelativeTo(this);
		
		FormulaireConnexion fC = new FormulaireConnexion();
		
		Administrateur = fC.admin;
		
		Data = new Donnees();
		Controller = new Controller();
	    
	    Loader.Load();
        
		JPanel panelGeneral = new JPanel();
		panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.PAGE_AXIS));
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
	    
		if(Administrateur)
	    {
		    ongletDocuments = new OngletDocument();
		    onglets.addTab("Documents", ongletDocuments);
		    ongletEmprunteur = new OngletEmprunteur();
		    onglets.addTab("Emprunteurs", ongletEmprunteur);
		    ongletPret = new OngletPret();
		    onglets.addTab("Prêts", ongletPret);
	    }
		
	    ongletRecherche = new OngletRecherche();
	    onglets.addTab("Recherches", ongletRecherche);
	    
	    if(Administrateur)
	    {
		    ongletStatistique = new OngletStatistique();
		    onglets.addTab("Statistiques", ongletStatistique);
		    ongletGestion = new OngletGestion();
		    onglets.addTab("Gestion", ongletGestion);
	    }
	    
	    
	    //Menu de l'application
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menuFichier = new JMenu("Fichier");
        menuBar.add(menuFichier);
        
        itemAprop = new JMenuItem("A Propos");
        itemAprop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
        itemAprop.addActionListener(this);
        menuFichier.add(itemAprop);
        
        if(Administrateur)
        {
        	itemSave = new JMenuItem("Enregistrer les modifications");
        	itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        	itemSave.addActionListener(this);
            menuFichier.add(itemSave);
        }
        
        itemQuit = new JMenuItem("Quitter");
        itemQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        itemQuit.addActionListener(this);
        menuFichier.add(itemQuit);
        
        
        if(Administrateur)
        {
	        JMenu menuAjout = new JMenu("Ajouter");
	        menuBar.add(menuAjout);
	        
	        itemDoc = new JMenuItem("Nouveau Document");
	        itemDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
	        itemDoc.addActionListener(this);
	        menuAjout.add(itemDoc);
	        
	        itemAut = new JMenuItem("Nouvel Auteur");
	        itemAut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
	        itemAut.addActionListener(this);
	        menuAjout.add(itemAut);
	
	        itemEdi = new JMenuItem("Nouvel Editeur");
	        itemEdi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
	        itemEdi.addActionListener(this);
	        menuAjout.add(itemEdi);
	        
	        itemEmp = new JMenuItem("Nouvel Emprunteur");
	        itemEmp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
	        itemEmp.addActionListener(this);
	        menuAjout.add(itemEmp);
	        
	        itemStat = new JMenuItem("Nouveau Statut");
	        itemStat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
	        itemStat.addActionListener(this);
	        menuAjout.add(itemStat);
	        
	        itemCat = new JMenuItem("Nouvelle Catégorie");
	        itemCat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
	        itemCat.addActionListener(this);
	        menuAjout.add(itemCat);
	        
	        JMenu menuPret = new JMenu("Gestion des Prêts");
	        menuBar.add(menuPret);
	        
	        itemPret = new JMenuItem("Enregistrer un nouveau Prêt");
	        itemPret.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
	        itemPret.addActionListener(this);
	        menuPret.add(itemPret);
	        
        }
    
        setJMenuBar(menuBar);

        
	    panelGeneral.add(onglets);
	    getContentPane().add(panelGeneral);
	    setVisible(true);
    }
	
    /**
     * Entry point
     */
	public static void main (String[] args)
	{
		new Bibliotheque();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		if(Administrateur)
        {
			Saver.Save();
        }
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}

	@Override
    public void actionPerformed(ActionEvent e)
    {
	    if(e.getSource() == itemDoc)
	    {
	            new FormulaireDocument();
	    }
	    else if(e.getSource() == itemAut)
	    {
	            new FormulaireAuteur();
	    }
	    else if(e.getSource() == itemCat)
	    {
	            new FormulaireCategorie();
	    }
	    else if(e.getSource() == itemEdi)
	    {
	            new FormulaireEditeur();
	    }
	    else if(e.getSource() == itemEmp)
	    {
	            new FormulaireEmprunteur();
	    }
	    else if(e.getSource() == itemStat)
	    {
	            new FormulaireStatut();
	    }
	    else if(e.getSource() == itemPret)
	    {
	            new FormulairePret();
	    }
	    else if(e.getSource() == itemAprop)
	    {
	            JOptionPane.showMessageDialog(this, 
	                            "Bibliotheque UTBM\n\n" +
	                            "Version 1.0a\n\n" +
	                            "Logiciel développé dans le cadre du projet LO43\n" +
	                            "Par Aurélien Signe, Thibaut Ackermann et Mathieu Savy", 
	                            "A Propos de Bibliothèque UTBM", 
	                            JOptionPane.INFORMATION_MESSAGE);
	    }
	    else if(e.getSource() == itemSave)
	    {
	    	Saver.Save();
	    	
	    	JOptionPane.showMessageDialog(this, 
                    "Données Sauvegardées.", 
                    "Sauvegarde", 
                    JOptionPane.INFORMATION_MESSAGE);
	    	
	    }
	    else if(e.getSource() == itemQuit)
	    {
	    	dispose();
	    }
    }

}
