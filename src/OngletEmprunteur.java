import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

/**
*  Classe affichant l'onglet emprunteur
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class OngletEmprunteur extends JPanel implements ActionListener, KeyListener, FocusListener{
    private static final long serialVersionUID = 1L;

    private static JButton nouveauEmpBtn; 
    private static JTable tableauEmprunteur;
    public static JTextField champRecherche;
    
    /**
    * Cree un nouvel objet OngletEmprunteur
    */
    public OngletEmprunteur(){
        this.setLayout(new BorderLayout());
        
        JPanel panelTableau = new JPanel(); 
        panelTableau.setLayout(new BoxLayout(panelTableau, BoxLayout.PAGE_AXIS));
        ModeleEmprunteur modelEmp = new ModeleEmprunteur();
        
        tableauEmprunteur = new JTable(modelEmp);
        MaJAffichage();
        
        JScrollPane jScrollPaneTabDoc = new JScrollPane(tableauEmprunteur);
        panelTableau.add(jScrollPaneTabDoc);
        
        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new FlowLayout());
        nouveauEmpBtn = new JButton("Nouvel Emprunteur");
        panelBtn.add(nouveauEmpBtn);
        nouveauEmpBtn.addActionListener(this);
        
        champRecherche = new JTextField("Rechercher un emprunteur ..."); //Adapter focus listener si changement texte
        champRecherche.addKeyListener(this);
        champRecherche.addFocusListener(this);
        
        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new BorderLayout());
        
        panelBottom.add(champRecherche, BorderLayout.NORTH);
        panelBottom.add(panelBtn, BorderLayout.SOUTH);
        
        this.add(panelTableau, BorderLayout.CENTER);
        this.add(panelBottom, BorderLayout.SOUTH);
    }

    /**
    * Mise a jour de l'affichage hors recherche
    */
    public static void MaJComplete(){
        tableauEmprunteur.setModel(new ModeleEmprunteur());
        MaJAffichage();
    }
    
    /**
    * Mise a jour de l'affichage suite a une recherche
    */
    public static void MaJ(){
        //tableauEmprunteur.setModel(new ModeleEmprunteur());
        tableauEmprunteur.setModel(new ModeleEmprunteur(champRecherche.getText()));
        MaJAffichage();
    }
    
    /**
    * Mise a jour de l'affichage
    */
    public static void MaJAffichage(){
        tableauEmprunteur.getColumnModel().getColumn(5).setCellRenderer(new RendererModifier());
        tableauEmprunteur.getColumnModel().getColumn(5).setCellEditor(new RendererModifier());
        tableauEmprunteur.getColumnModel().getColumn(6).setCellRenderer(new RendererSupprimer());
        tableauEmprunteur.getColumnModel().getColumn(6).setCellEditor(new RendererSupprimer());
        
        //tableauEmprunteur.setAutoCreateRowSorter(true);
        tableauEmprunteur.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableauEmprunteur.getColumnModel().getColumn(0).setPreferredWidth(10);
        tableauEmprunteur.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableauEmprunteur.getColumnModel().getColumn(2).setPreferredWidth(150);
        tableauEmprunteur.getColumnModel().getColumn(3).setPreferredWidth(250);
        tableauEmprunteur.getColumnModel().getColumn(4).setPreferredWidth(80);
        tableauEmprunteur.getColumnModel().getColumn(5).setPreferredWidth(5);
        tableauEmprunteur.getColumnModel().getColumn(6).setPreferredWidth(5);
    }
    
    /**
    * Getter
    */
    public static JTable getTableauEmprunteur() {
        return tableauEmprunteur;
    }

    /**
    * Event listeners
    */
    public void actionPerformed(ActionEvent e) {
        if((JButton) e.getSource() == nouveauEmpBtn){
            new FormulaireEmprunteur();
        }
    }

    public void keyReleased(KeyEvent e) {
        tableauEmprunteur.setModel(new ModeleEmprunteur(champRecherche.getText()));
        MaJAffichage();
    }

    public void keyPressed(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }
    
    public void focusGained(FocusEvent e) {
        if(champRecherche.getText().equals("Rechercher un emprunteur ..."))
            champRecherche.setText(null);
    }

    public void focusLost(FocusEvent e) {
        if(champRecherche.getText().isEmpty())
            champRecherche.setText("Rechercher un emprunteur ...");
    }
}
