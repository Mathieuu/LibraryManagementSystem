import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.*;

/**
*  Classe affichant l'onglet gestion
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class OngletGestion extends JPanel implements ActionListener{
    private static final long serialVersionUID = 1L;

    static JTable tableauAuteur, tableauCategorie, tableauEditeur, tableauStatut;
    public static JButton newAut, newCat, newEdi, newStatut;
    static JComboBox listeGestion;
    static JTextField nomAut, prenomAut, libelleCat, nomEdi, libelleStatut;
    static JPanel panelCentral;
    
    /**
    * Cree un nouvel objet OngletGestion
    */
    public OngletGestion(){
        this.setLayout(new BorderLayout());
        listeGestion = new JComboBox();
        listeGestion.addItem("Auteur");
        listeGestion.addItem("Catégorie");
        listeGestion.addItem("Editeur");
        listeGestion.addItem("Statut");
        listeGestion.addActionListener(this);
        this.add(listeGestion, BorderLayout.NORTH);
        panelCentral = new JPanel();
        this.add(panelCentral, BorderLayout.CENTER);
        affichageAut();
    }
    
    /**
    * Getters
    */
    public static JTable getTableauAuteur() {
        return tableauAuteur;
    }

    public static JTable getTableauCategorie() {
        return tableauCategorie;
    }

    public static JTable getTableauEditeur() {
        return tableauEditeur;
    }

    public static JTable getTableauStatut() {
        return tableauStatut;
    }
    
    /**
    * Mise a jour de l'affichage suivant le volet a afficher
    */
    public void MaJ(){
        panelCentral.removeAll();
        if(listeGestion.getSelectedItem().equals("Auteur")){
            affichageAut();
        }
        else if(listeGestion.getSelectedItem().equals("Catégorie")){
            affichageCat();
        }
        else if(listeGestion.getSelectedItem().equals("Editeur")){
            affichageEdi();
        }
        else if(listeGestion.getSelectedItem().equals("Statut")){
            affichageStatut();
        }
        this.revalidate();
    }
    
    /**
    * Construction du volet auteur
    */
    public void affichageAut(){
        JPanel panelAuteur = new JPanel();
        ModeleAuteur modelAuteur = new ModeleAuteur(1);
        tableauAuteur = new JTable(modelAuteur);    
        JScrollPane jScrollPaneTabDoc = new JScrollPane(tableauAuteur);
        panelAuteur.add(jScrollPaneTabDoc);
        
        JPanel panelAjout = new JPanel();
        panelAjout.setLayout(new GridLayout(0,2));
        JLabel nom = new JLabel("Nom :");
        nomAut = new JTextField();
        nomAut.addActionListener(this);
        JLabel prenom = new JLabel("Prénom :");
        prenomAut = new JTextField();
        prenomAut.addActionListener(this);
        newAut = new JButton("Ajouter");
        newAut.addActionListener(this);
        panelAjout.add(nom);
        panelAjout.add(nomAut);
        panelAjout.add(prenom);
        panelAjout.add(prenomAut);
        panelAjout.add(newAut);
        
        panelAuteur.add(panelAjout);
        panelCentral.add(panelAuteur);
        
        tableauAuteur.getColumnModel().getColumn(3).setCellRenderer(new RendererModifier());
        tableauAuteur.getColumnModel().getColumn(3).setCellEditor(new RendererModifier());
        tableauAuteur.getColumnModel().getColumn(4).setCellRenderer(new RendererSupprimer());
        tableauAuteur.getColumnModel().getColumn(4).setCellEditor(new RendererSupprimer());
        
        //tableauAuteur.setAutoCreateRowSorter(true);
        tableauAuteur.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableauAuteur.getColumnModel().getColumn(0).setPreferredWidth(5);
        tableauAuteur.getColumnModel().getColumn(1).setPreferredWidth(100);
        tableauAuteur.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableauAuteur.getColumnModel().getColumn(3).setPreferredWidth(5);
        tableauAuteur.getColumnModel().getColumn(4).setPreferredWidth(5);
    }
    
    /**
    * Construction du volet categorie
    */
    public void affichageCat(){
        JPanel panelCat = new JPanel();
        ModeleCategorie modelCat = new ModeleCategorie(1);
        tableauCategorie = new JTable(modelCat);    
        JScrollPane jScrollPaneTabDoc = new JScrollPane(tableauCategorie);
        panelCat.add(jScrollPaneTabDoc);
        
        JPanel panelAjout = new JPanel();
        panelAjout.setLayout(new GridLayout(0,2));
        JLabel libelle = new JLabel("Libellé :");
        libelleCat = new JTextField();
        libelleCat.addActionListener(this);
        newCat = new JButton("Ajouter");
        newCat.addActionListener(this);
        panelAjout.add(libelle);
        panelAjout.add(libelleCat);
        panelAjout.add(newCat);
        
        panelCat.add(panelAjout);
        panelCentral.add(panelCat);
        
        tableauCategorie.getColumnModel().getColumn(2).setCellRenderer(new RendererModifier());
        tableauCategorie.getColumnModel().getColumn(2).setCellEditor(new RendererModifier());
        tableauCategorie.getColumnModel().getColumn(3).setCellRenderer(new RendererSupprimer());
        tableauCategorie.getColumnModel().getColumn(3).setCellEditor(new RendererSupprimer());
        
        //tableauCategorie.setAutoCreateRowSorter(true);
        tableauCategorie.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableauCategorie.getColumnModel().getColumn(0).setPreferredWidth(5);
        tableauCategorie.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableauCategorie.getColumnModel().getColumn(2).setPreferredWidth(5);
        tableauCategorie.getColumnModel().getColumn(3).setPreferredWidth(5);
    }
    
    /**
    * Construction du volet d'edition
    */
    public void affichageEdi(){
        JPanel panelEdi = new JPanel();
        ModeleEditeur modelEdi = new ModeleEditeur(1);
        tableauEditeur = new JTable(modelEdi);  
        JScrollPane jScrollPaneTabDoc = new JScrollPane(tableauEditeur);
        panelEdi.add(jScrollPaneTabDoc);
        
        JPanel panelAjout = new JPanel();
        panelAjout.setLayout(new GridLayout(0,2));
        JLabel nom = new JLabel("Nom :");
        nomEdi = new JTextField();
        newEdi = new JButton("Ajouter");
        newEdi.addActionListener(this);
        panelAjout.add(nom);
        panelAjout.add(nomEdi);
        panelAjout.add(newEdi);
        
        panelEdi.add(panelAjout);
        panelCentral.add(panelEdi);
        
        tableauEditeur.getColumnModel().getColumn(2).setCellRenderer(new RendererModifier());
        tableauEditeur.getColumnModel().getColumn(2).setCellEditor(new RendererModifier());
        tableauEditeur.getColumnModel().getColumn(3).setCellRenderer(new RendererSupprimer());
        tableauEditeur.getColumnModel().getColumn(3).setCellEditor(new RendererSupprimer());
        
        //tableauEditeur.setAutoCreateRowSorter(true);
        tableauEditeur.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableauEditeur.getColumnModel().getColumn(0).setPreferredWidth(5);
        tableauEditeur.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableauEditeur.getColumnModel().getColumn(2).setPreferredWidth(5);
        tableauEditeur.getColumnModel().getColumn(3).setPreferredWidth(5);
    }
    
    /**
    * Construction du volet statut
    */
    public void affichageStatut(){
        JPanel panelStatut = new JPanel();
        ModeleStatut modelStatut = new ModeleStatut(1);
        tableauStatut = new JTable(modelStatut);    
        JScrollPane jScrollPaneTabDoc = new JScrollPane(tableauStatut);
        panelStatut.add(jScrollPaneTabDoc);
        
        JPanel panelAjout = new JPanel();
        panelAjout.setLayout(new GridLayout(0,2));
        JLabel libelle = new JLabel("Libellé :");
        libelleStatut = new JTextField();
        libelleStatut.addActionListener(this);
        newStatut = new JButton("Ajouter");
        newStatut.addActionListener(this);
        panelAjout.add(libelle);
        panelAjout.add(libelleStatut);
        panelAjout.add(newStatut);
        
        panelStatut.add(panelAjout);
        panelCentral.add(panelStatut);
        
        tableauStatut.getColumnModel().getColumn(2).setCellRenderer(new RendererModifier());
        tableauStatut.getColumnModel().getColumn(2).setCellEditor(new RendererModifier());
        tableauStatut.getColumnModel().getColumn(3).setCellRenderer(new RendererSupprimer());
        tableauStatut.getColumnModel().getColumn(3).setCellEditor(new RendererSupprimer());
        
        //tableauStatut.setAutoCreateRowSorter(true);
        tableauStatut.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableauStatut.getColumnModel().getColumn(0).setPreferredWidth(5);
        tableauStatut.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableauStatut.getColumnModel().getColumn(2).setPreferredWidth(5);
        tableauStatut.getColumnModel().getColumn(3).setPreferredWidth(5);
    }

    /**
    * Event listeners
    */
    public void actionPerformed(ActionEvent e)
    {
        if(     e.getSource() == newAut
                || e.getSource() == nomAut
                || e.getSource() == prenomAut)
        {
            if(!prenomAut.getText().trim().contentEquals("") && !nomAut.getText().trim().contentEquals(""))
            {
                Bibliotheque.Data.listeAuteurs.add(new Auteur(prenomAut.getText().trim(), nomAut.getText().trim()));
                Collections.sort(Bibliotheque.Data.listeAuteurs, Collections.reverseOrder());
                nomAut.setText("");
                prenomAut.setText("");
                MaJ();
            }
            else
                JOptionPane.showMessageDialog(this, "La saisie est incomplète !", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        else if(e.getSource() == newCat || e.getSource() == libelleCat)
        {
            if(!libelleCat.getText().trim().contentEquals(""))
            {
                Bibliotheque.Data.listeCategories.add(new Categorie(libelleCat.getText().trim()));
                Collections.sort(Bibliotheque.Data.listeCategories, Collections.reverseOrder());
                libelleCat.setText("");
                MaJ();
            }
            else
                JOptionPane.showMessageDialog(this, "La saisie est incomplète !", "Erreur", JOptionPane.ERROR_MESSAGE);         
        }
        else if(e.getSource() == newEdi || e.getSource() == nomEdi)
        {
            if(!nomEdi.getText().trim().contentEquals(""))
            {
                Bibliotheque.Data.listeEditeurs.add(new Editeur(nomEdi.getText().trim()));
                Collections.sort(Bibliotheque.Data.listeEditeurs, Collections.reverseOrder());
                nomEdi.setText("");
                MaJ();
            }
            else
                JOptionPane.showMessageDialog(this, "La saisie est incomplète !", "Erreur", JOptionPane.ERROR_MESSAGE);         

        }
        else if(e.getSource() == newStatut || e.getSource() == libelleStatut)
        {
            if(!libelleStatut.getText().trim().contentEquals(""))
            {
                Bibliotheque.Data.listeStatuts.add(new Statut(libelleStatut.getText().trim()));
                Collections.sort(Bibliotheque.Data.listeStatuts, Collections.reverseOrder());
                libelleStatut.setText("");
                MaJ();
            }
            else
                JOptionPane.showMessageDialog(this, "La saisie est incomplète !", "Erreur", JOptionPane.ERROR_MESSAGE);         

        }
        else if(e.getSource() == listeGestion)
        {
            panelCentral.removeAll();
            if(listeGestion.getSelectedItem().equals("Auteur"))
            {
                affichageAut();
            }
            else if(listeGestion.getSelectedItem().equals("Catégorie"))
            {
                affichageCat();
            }
            else if(listeGestion.getSelectedItem().equals("Editeur"))
            {
                affichageEdi();
            }
            else if(listeGestion.getSelectedItem().equals("Statut"))
            {
                affichageStatut();
            }
            this.revalidate();
        }
    }
}
