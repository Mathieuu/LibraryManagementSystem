import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

/**
*  Classe affichant l'onglet recherche
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class OngletRecherche extends JPanel implements KeyListener, ActionListener{
    private static final long serialVersionUID = 1L;

    static JTextField iIdentifiant, iTitre, iCategorie, iAuteur, iEditeur, iAnnee;
    static JTextField iNom, iPrenom, iStatut, iDateEmprunt, iDateRetour, iEmprunteur;
    static JComboBox listeRecherche;
    static JPanel panelRequete, panelResult, panelDoc, panelEmprunteur, panelPret;
    static JTable tableResDoc, tableResEmprunteur, tableResPret;
    
    /**
    * Cree un nouvel objet OngletRecherche
    */
    public OngletRecherche(){
        this.setLayout(new BorderLayout());
        listeRecherche = new JComboBox();
        listeRecherche.addItem("Document");
        listeRecherche.addItem("Emprunteur");
        listeRecherche.addItem("Prêt");
        listeRecherche.addActionListener(this);
        this.add(listeRecherche, BorderLayout.NORTH);
        panelRequete= new JPanel();
        panelRequete.setLayout(new GridLayout(2,0));
        this.add(panelRequete, BorderLayout.CENTER);
        /*panelResult = new JPanel();
        this.add(panelResult, BorderLayout.SOUTH);*/
        affichageDocument();        
    }
    
    /**
    * Construction de l'affichage document
    */
    public void affichageDocument(){
        JLabel identifiant = new JLabel("Identifiant :");
        JLabel titre = new JLabel("Titre :                                          ");
        JLabel categorie = new JLabel("Catégorie :");
        JLabel auteur = new JLabel("Auteur(s) :");
        JLabel editeur = new JLabel("Editeur :");
        JLabel annee = new JLabel("Année :");
        iIdentifiant = new JTextField();
        iTitre = new JTextField();
        iCategorie = new JTextField();
        iAuteur = new JTextField();
        iEditeur = new JTextField();
        iAnnee = new JTextField();
        
        iIdentifiant.addKeyListener(this);
        iTitre.addKeyListener(this);
        iCategorie.addKeyListener(this);
        iAuteur.addKeyListener(this);
        iEditeur.addKeyListener(this);
        iAnnee.addKeyListener(this);
        
        panelDoc = new JPanel();    
        panelDoc.setLayout(new GridLayout(0, 2));
        panelDoc.add(identifiant);
        panelDoc.add(iIdentifiant);
        panelDoc.add(titre);
        panelDoc.add(iTitre);
        panelDoc.add(categorie);
        panelDoc.add(iCategorie);
        panelDoc.add(auteur);
        panelDoc.add(iAuteur);
        panelDoc.add(editeur);
        panelDoc.add(iEditeur);
        panelDoc.add(annee);
        panelDoc.add(iAnnee);
        
        JPanel monPan= new JPanel();
        monPan.setLayout(new FlowLayout());
        monPan.add(panelDoc);
        
        panelRequete.add(monPan);
    
        tableResDoc = new JTable(new ModeleDocument(1));
    /*  if(!Bibliotheque.Administrateur){
            tableResDoc = new JTable(new ModeleDocument(1));
        }
        else{
            tableResDoc = new JTable(new ModeleDocument());
            tableResDoc.getColumnModel().getColumn(7).setCellRenderer(new RendererModifier());
            tableResDoc.getColumnModel().getColumn(7).setCellEditor(new RendererModifier());
            tableResDoc.getColumnModel().getColumn(8).setCellRenderer(new RendererSupprimer());
            tableResDoc.getColumnModel().getColumn(8).setCellEditor(new RendererSupprimer());
            tableResDoc.getColumnModel().getColumn(7).setPreferredWidth(5);
            tableResDoc.getColumnModel().getColumn(8).setPreferredWidth(5);
        }*/
        JScrollPane jScrollPaneTabDoc = new JScrollPane(tableResDoc);
        panelRequete.add(jScrollPaneTabDoc);

        //tableResDoc.setAutoCreateRowSorter(true);
        tableResDoc.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableResDoc.getColumnModel().getColumn(0).setPreferredWidth(40);
        tableResDoc.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableResDoc.getColumnModel().getColumn(2).setPreferredWidth(80);
        tableResDoc.getColumnModel().getColumn(3).setPreferredWidth(180);
        tableResDoc.getColumnModel().getColumn(4).setPreferredWidth(120);
        tableResDoc.getColumnModel().getColumn(5).setPreferredWidth(40);
        tableResDoc.getColumnModel().getColumn(6).setPreferredWidth(70);
        
    }

    /**
    * Construction de l'affichage emprunteur
    */
    public void affichageEmprunteur(){
        JLabel nom = new JLabel("Nom :");
        JLabel prenom = new JLabel("Prénom :                  ");
        JLabel statut = new JLabel("Statut :");
        iNom = new JTextField();
        iPrenom = new JTextField();
        iStatut = new JTextField();
        
        iNom.addKeyListener(this);
        iPrenom.addKeyListener(this);
        iStatut.addKeyListener(this);

        panelEmprunteur = new JPanel(); 
        panelEmprunteur.setLayout(new GridLayout(0, 2));
        panelEmprunteur.add(nom);
        panelEmprunteur.add(iNom);
        panelEmprunteur.add(prenom);
        panelEmprunteur.add(iPrenom);
        panelEmprunteur.add(statut);
        panelEmprunteur.add(iStatut);
        
        JPanel monPan= new JPanel();
        monPan.setLayout(new FlowLayout());
        monPan.add(panelEmprunteur);
        
        panelRequete.add(monPan);
        
        tableResEmprunteur = new JTable(new ModeleEmprunteur(1));
        /*if(!Bibliotheque.Administrateur){
            tableResEmprunteur = new JTable(new ModeleEmprunteur(1));
        }
        else{
            tableResEmprunteur = new JTable(new ModeleEmprunteur());
            tableResEmprunteur.getColumnModel().getColumn(5).setCellRenderer(new RendererModifier());
            tableResEmprunteur.getColumnModel().getColumn(5).setCellEditor(new RendererModifier());
            tableResEmprunteur.getColumnModel().getColumn(6).setCellRenderer(new RendererSupprimer());
            tableResEmprunteur.getColumnModel().getColumn(6).setCellEditor(new RendererSupprimer());
            tableResEmprunteur.getColumnModel().getColumn(5).setPreferredWidth(5);
            tableResEmprunteur.getColumnModel().getColumn(6).setPreferredWidth(5);
            
        }*/
        JScrollPane jScrollPaneTabEmprunteur = new JScrollPane(tableResEmprunteur);
        panelRequete.add(jScrollPaneTabEmprunteur);
        
        
        //tableResEmprunteur.setAutoCreateRowSorter(true);
        tableResEmprunteur.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableResEmprunteur.getColumnModel().getColumn(0).setPreferredWidth(10);
        tableResEmprunteur.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableResEmprunteur.getColumnModel().getColumn(2).setPreferredWidth(150);
        tableResEmprunteur.getColumnModel().getColumn(3).setPreferredWidth(250);
        tableResEmprunteur.getColumnModel().getColumn(4).setPreferredWidth(80);
    }
    
    /**
    * Construction de l'affichage pret
    */
    public void affichagerPret(){
        JLabel emprunteur = new JLabel("Emprunteur :");
        JLabel titre = new JLabel("Titre :");
        JLabel dateEmprunt = new JLabel("Date Emprunt :");
        JLabel dateRetour = new JLabel("Date Retour :");
        
        iEmprunteur = new JTextField();
        iTitre = new JTextField();
        iDateEmprunt = new JTextField();
        iDateRetour = new JTextField();
        
        iEmprunteur.addKeyListener(this);
        iTitre.addKeyListener(this);
        iDateEmprunt.addKeyListener(this);
        iDateRetour.addKeyListener(this);
        
        panelPret = new JPanel();   
        panelPret.setLayout(new GridLayout(0, 2));
        panelPret.add(emprunteur);
        panelPret.add(iEmprunteur);
        panelPret.add(titre);
        panelPret.add(iTitre);
        panelPret.add(dateEmprunt);
        panelPret.add(iDateEmprunt);
        panelPret.add(dateRetour);
        panelPret.add(iDateRetour);
        JPanel monPan= new JPanel();
        monPan.setLayout(new FlowLayout());
        monPan.add(panelPret);
        
        panelRequete.add(monPan);
        
        tableResPret = new JTable(new ModelePret(1));
        /*if(!Bibliotheque.Administrateur){
            tableResPret = new JTable(new ModelePret(1));
        }
        else{
            tableResPret = new JTable(new ModelePret());
            tableResPret.getColumnModel().getColumn(6).setCellRenderer(new RendererRendu());
            tableResPret.getColumnModel().getColumn(6).setCellEditor(new RendererRendu());
            tableResPret.getColumnModel().getColumn(7).setCellRenderer(new RendererSupprimer());
            tableResPret.getColumnModel().getColumn(7).setCellEditor(new RendererSupprimer());
            tableResPret.getColumnModel().getColumn(6).setPreferredWidth(5);
            tableResPret.getColumnModel().getColumn(7).setPreferredWidth(5);
        }*/
        JScrollPane jScrollPaneTabPret = new JScrollPane(tableResPret);
        panelRequete.add(jScrollPaneTabPret);
        
        
        //tableResPret.setAutoCreateRowSorter(true);
        tableResPret.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableResPret.getColumnModel().getColumn(0).setPreferredWidth(15);
        tableResPret.getColumnModel().getColumn(1).setPreferredWidth(180);
        tableResPret.getColumnModel().getColumn(2).setPreferredWidth(200);
        tableResPret.getColumnModel().getColumn(3).setPreferredWidth(70);
        tableResPret.getColumnModel().getColumn(4).setPreferredWidth(70);
        tableResPret.getColumnModel().getColumn(5).setPreferredWidth(70);
    }
    
    /**
    * Mise a jour de l'affichage lorsqu'un evenement survient
    */
    public static void MaJAffichage(){
        if(listeRecherche.getSelectedItem().equals("Document")){
            /*if(Bibliotheque.Administrateur){
                tableResDoc.getColumnModel().getColumn(7).setCellRenderer(new RendererModifier());
                tableResDoc.getColumnModel().getColumn(7).setCellEditor(new RendererModifier());
                tableResDoc.getColumnModel().getColumn(8).setCellRenderer(new RendererSupprimer());
                tableResDoc.getColumnModel().getColumn(8).setCellEditor(new RendererSupprimer());
                tableResDoc.getColumnModel().getColumn(7).setPreferredWidth(5);
                tableResDoc.getColumnModel().getColumn(8).setPreferredWidth(5);
            }*/
                
            //tableResDoc.setAutoCreateRowSorter(true);
            tableResDoc.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            tableResDoc.getColumnModel().getColumn(0).setPreferredWidth(5);
            tableResDoc.getColumnModel().getColumn(1).setPreferredWidth(150);
            tableResDoc.getColumnModel().getColumn(2).setPreferredWidth(80);
            tableResDoc.getColumnModel().getColumn(3).setPreferredWidth(180);
            tableResDoc.getColumnModel().getColumn(4).setPreferredWidth(120);
            tableResDoc.getColumnModel().getColumn(5).setPreferredWidth(30);
            tableResDoc.getColumnModel().getColumn(6).setPreferredWidth(70);
        }
        else if(listeRecherche.getSelectedItem().equals("Emprunteur")){
            /*if(Bibliotheque.Administrateur){
                tableResEmprunteur.getColumnModel().getColumn(5).setCellRenderer(new RendererModifier());
                tableResEmprunteur.getColumnModel().getColumn(5).setCellEditor(new RendererModifier());
                tableResEmprunteur.getColumnModel().getColumn(6).setCellRenderer(new RendererSupprimer());
                tableResEmprunteur.getColumnModel().getColumn(6).setCellEditor(new RendererSupprimer());
                tableResEmprunteur.getColumnModel().getColumn(5).setPreferredWidth(5);
                tableResEmprunteur.getColumnModel().getColumn(6).setPreferredWidth(5);
            }*/
        
            //tableResEmprunteur.setAutoCreateRowSorter(true);
            tableResEmprunteur.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            tableResEmprunteur.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableResEmprunteur.getColumnModel().getColumn(1).setPreferredWidth(150);
            tableResEmprunteur.getColumnModel().getColumn(2).setPreferredWidth(150);
            tableResEmprunteur.getColumnModel().getColumn(3).setPreferredWidth(250);
            tableResEmprunteur.getColumnModel().getColumn(4).setPreferredWidth(80);
        }
        else if(listeRecherche.getSelectedItem().equals("Prêt")){
            /*if(Bibliotheque.Administrateur){
                tableResPret.getColumnModel().getColumn(6).setCellRenderer(new RendererRendu());
                tableResPret.getColumnModel().getColumn(6).setCellEditor(new RendererRendu());
                tableResPret.getColumnModel().getColumn(7).setCellRenderer(new RendererSupprimer());
                tableResPret.getColumnModel().getColumn(7).setCellEditor(new RendererSupprimer());
                tableResPret.getColumnModel().getColumn(6).setPreferredWidth(5);
                tableResPret.getColumnModel().getColumn(7).setPreferredWidth(5);
            }*/
            
            //tableResPret.setAutoCreateRowSorter(true);
            tableResPret.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            tableResPret.getColumnModel().getColumn(0).setPreferredWidth(15);
            tableResPret.getColumnModel().getColumn(1).setPreferredWidth(180);
            tableResPret.getColumnModel().getColumn(2).setPreferredWidth(200);
            tableResPret.getColumnModel().getColumn(3).setPreferredWidth(70);
            tableResPret.getColumnModel().getColumn(4).setPreferredWidth(70);
            tableResPret.getColumnModel().getColumn(5).setPreferredWidth(70);
        }
    }

    /**
    * Event listeners
    */
    public void keyTyped(KeyEvent e) {
        
    }


    public void keyPressed(KeyEvent e) {
        
    }

    public void keyReleased(KeyEvent e) {
        if(listeRecherche.getSelectedItem().equals("Document")){
            /*if(!Bibliotheque.Administrateur){
                tableResDoc.setModel(new ModeleDocument(iIdentifiant.getText(), iTitre.getText(), iCategorie.getText(), iAuteur.getText(), iEditeur.getText(), iAnnee.getText(), 1));
            }
            else{
                tableResDoc.setModel(new ModeleDocument(iIdentifiant.getText(), iTitre.getText(), iCategorie.getText(), iAuteur.getText(), iEditeur.getText(), iAnnee.getText()));
            }*/
            tableResDoc.setModel(new ModeleDocument(iIdentifiant.getText(), iTitre.getText(), iCategorie.getText(), iAuteur.getText(), iEditeur.getText(), iAnnee.getText(), 1));
            MaJAffichage();
        }
        else if(listeRecherche.getSelectedItem().equals("Emprunteur")){
            /*if(!Bibliotheque.Administrateur){
                tableResEmprunteur.setModel(new ModeleEmprunteur(iNom.getText(), iPrenom.getText(), iStatut.getText(), 1));
            }
            else{
                tableResEmprunteur.setModel(new ModeleEmprunteur(iNom.getText(), iPrenom.getText(), iStatut.getText()));
            }*/
            tableResEmprunteur.setModel(new ModeleEmprunteur(iNom.getText(), iPrenom.getText(), iStatut.getText(), 1));
            MaJAffichage();
        }
        else if(listeRecherche.getSelectedItem().equals("Prêt")){
            /*if(!Bibliotheque.Administrateur){
                tableResPret.setModel(new ModelePret(iEmprunteur.getText(), iTitre.getText(), iDateEmprunt.getText(), iDateRetour.getText(),1));
            }
            else{
                tableResPret.setModel(new ModelePret(iEmprunteur.getText(), iTitre.getText(), iDateEmprunt.getText(), iDateRetour.getText()));
            }*/
            tableResPret.setModel(new ModelePret(iEmprunteur.getText(), iTitre.getText(), iDateEmprunt.getText(), iDateRetour.getText(),1));
            MaJAffichage();
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == listeRecherche){
            panelRequete.removeAll();
            if(listeRecherche.getSelectedItem().equals("Document")){
                affichageDocument();
            }
            else if(listeRecherche.getSelectedItem().equals("Emprunteur")){
                affichageEmprunteur();
            }
            else if(listeRecherche.getSelectedItem().equals("Prêt")){
                affichagerPret();
            }
            this.revalidate();
        }
    }
}
        