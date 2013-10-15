import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.*;

/**
*  Classe affichant le formulaire de gestion des categories
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class FormulaireCategorie extends JDialog implements ActionListener{
    private static final long serialVersionUID = 1L;

    JTextField libelleCat;
    JButton validerBtn, annulerBtn;
    public static JTable tableauCategorie;
    int action;
    Categorie catTemp = null;
    
    /**
     * Cree un nouvel FormulaireCategorie par defaut
     */
    public FormulaireCategorie(){
        this("", 1);
    }
    
    /**
     * Cree un nouvel FormulaireCategorie
     * @param monLibelle String
     * @param monAction int
     */
    public FormulaireCategorie(String monLibelle, int monAction){
        action = monAction;
        if(monAction == 1){
            setTitle("Ajout d'une nouvelle catégorie");
        }
        else{
            setTitle("Modification d'une catégorie");
        }
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 250);
        setResizable(false);
        setLocationRelativeTo(this);

        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new BorderLayout());
        
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.PAGE_AXIS));
        
        JPanel panelTableau = new JPanel(); 
        panelTableau.setLayout(new BoxLayout(panelTableau, BoxLayout.PAGE_AXIS));
        tableauCategorie = new JTable(new ModeleCategorie());
        tableauCategorie.setAutoCreateRowSorter(true);
        tableauCategorie.setEnabled(false);
        JScrollPane jScrollPaneTabDoc = new JScrollPane(tableauCategorie);
        panelTableau.add(jScrollPaneTabDoc);
        
        JPanel panelLibelle = new JPanel();
        panelLibelle.setLayout(new GridLayout(0,2));
        JLabel labelLibelle = new JLabel("Libellé :");
        labelLibelle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        libelleCat = new JTextField(monLibelle);
        libelleCat.addActionListener(this);
        panelLibelle.add(labelLibelle);
        panelLibelle.add(libelleCat);


        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new FlowLayout());
        if(action == 1){
            validerBtn = new JButton("Ajouter la catégorie");
        }
        else{
            for(int i=0;i<Bibliotheque.Data.listeCategories.size();i++){
                if(Bibliotheque.Data.listeCategories.get(i).getLibelle().equals(monLibelle)){
                    catTemp = Bibliotheque.Data.listeCategories.get(i);
                }
            }
            validerBtn = new JButton("Modifier la catégorie");
        }
        validerBtn.addActionListener(this);
        annulerBtn = new JButton("Annuler");
        annulerBtn.addActionListener(this);
        panelBtn.add(validerBtn);
        panelBtn.add(annulerBtn);
        
        panelForm.add(panelTableau);
        panelForm.add(panelLibelle);
        panelGeneral.add(panelForm, BorderLayout.CENTER);
        panelGeneral.add(panelBtn, BorderLayout.SOUTH);
        add(panelGeneral);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == validerBtn || e.getSource() == libelleCat)
        {
            if(!libelleCat.getText().contentEquals(""))
            {
                if(action == 1){    //ajout
                    Bibliotheque.Data.listeCategories.add(new Categorie(libelleCat.getText()));
                }
                else{   //modif
                    catTemp.setLibelle(libelleCat.getText());
                    Bibliotheque.ongletGestion.MaJ();
                    if(OngletDocument.champRecherche.getText().equals("Rechercher un document ...")){
                        OngletDocument.MaJComplete();
                    }
                    else{
                        OngletDocument.MaJ();
                    }
                }
                Collections.sort(Bibliotheque.Data.listeCategories, Collections.reverseOrder());
                
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(this, "La saisie est incomplète !", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == annulerBtn){
            dispose();
        }
    }
}