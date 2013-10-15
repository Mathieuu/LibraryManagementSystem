
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
*  Classe affichant l'onglet document
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class OngletDocument extends JPanel implements ActionListener, KeyListener, FocusListener{
    private static final long serialVersionUID = 1L;

    private static JButton nouveauDocBtn;
    private static JTable tableauDocument;
    public static JTextField champRecherche;
    
    /**
    * Cree un nouvel objet OngletDocument
    */
    public OngletDocument(){
        
        this.setLayout(new BorderLayout());
        
        champRecherche = new JTextField("Rechercher un document ..."); //Adapter focus listener si changement texte
        champRecherche.addKeyListener(this);
        champRecherche.addFocusListener(this);
        
        JPanel panelTableau = new JPanel(); 
        panelTableau.setLayout(new BoxLayout(panelTableau, BoxLayout.PAGE_AXIS));
                
        ModeleDocument modelDoc = new ModeleDocument();
        
        tableauDocument = new JTable(modelDoc);
        MaJAffichage();
        
        JScrollPane jScrollPaneTabDoc = new JScrollPane(tableauDocument);
        panelTableau.add(jScrollPaneTabDoc);
        
        JPanel panelBottom = new JPanel();
        JPanel panelBtn = new JPanel();

        panelBottom.setLayout(new BorderLayout());
        panelBtn.setLayout(new FlowLayout());
        
        nouveauDocBtn = new JButton("Nouveau Document");
        panelBtn.add(nouveauDocBtn);
        nouveauDocBtn.addActionListener(this);
        
        panelBottom.add(champRecherche, BorderLayout.NORTH);
        panelBottom.add(panelBtn, BorderLayout.SOUTH);
        
        this.add(panelTableau, BorderLayout.CENTER);
        this.add(panelBottom, BorderLayout.SOUTH);
    }
    /**
    * Mise a jour de l'affichage hors recherche
    */
    public static void MaJComplete(){
        tableauDocument.setModel(new ModeleDocument());
        MaJAffichage();
    }
    
    /**
    * Mise a jour de l'affichage suite a une recherche
    */
    public static void MaJ(){
        //tableauDocument.setModel(new ModeleDocument());
        tableauDocument.setModel(new ModeleDocument(champRecherche.getText()));
        MaJAffichage();
    }
    
    /**
    * Mise a jour de l'affichage
    */
    public static void MaJAffichage(){
        tableauDocument.getColumnModel().getColumn(7).setCellRenderer(new RendererModifier());
        tableauDocument.getColumnModel().getColumn(7).setCellEditor(new RendererModifier());
        tableauDocument.getColumnModel().getColumn(8).setCellRenderer(new RendererSupprimer());
        tableauDocument.getColumnModel().getColumn(8).setCellEditor(new RendererSupprimer());
        
        //tableauDocument.setAutoCreateRowSorter(true);
        tableauDocument.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableauDocument.getColumnModel().getColumn(0).setPreferredWidth(40);
        tableauDocument.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableauDocument.getColumnModel().getColumn(2).setPreferredWidth(80);
        tableauDocument.getColumnModel().getColumn(3).setPreferredWidth(180);
        tableauDocument.getColumnModel().getColumn(4).setPreferredWidth(120);
        tableauDocument.getColumnModel().getColumn(5).setPreferredWidth(40);
        tableauDocument.getColumnModel().getColumn(6).setPreferredWidth(70);
        tableauDocument.getColumnModel().getColumn(7).setPreferredWidth(5);
        tableauDocument.getColumnModel().getColumn(8).setPreferredWidth(5);
    }
    
    /**
    * Getter
    */
    public static JTable getTableauDocument() {
        return tableauDocument;
    }


    /**
    * Event listeners
    */
    public void actionPerformed(ActionEvent e) {
        if((JButton) e.getSource() == nouveauDocBtn){
            new FormulaireDocument();
        }
    }
    
    public void keyPressed(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }

    public void keyReleased(KeyEvent e) {
        tableauDocument.setModel(new ModeleDocument(champRecherche.getText()));
        MaJAffichage();
    }

    public void focusGained(FocusEvent e) {
        if(champRecherche.getText().equals("Rechercher un document ..."))
            champRecherche.setText(null);
    }

    public void focusLost(FocusEvent e) {
        if(champRecherche.getText().isEmpty())
            champRecherche.setText("Rechercher un document ...");
    }
}
