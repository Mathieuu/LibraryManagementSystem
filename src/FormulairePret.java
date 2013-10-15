import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Date;

/**
*  Classe affichant le formulaire de gestion des prets
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class FormulairePret extends JDialog implements ActionListener{
    private static final long serialVersionUID = 1L;

    JTextField titreDocument, identifiantDocument, dateDocument;
    JButton validerBtn, annulerBtn;
    JComboBox comboBoxEmprunteur, comboBoxDocument;
    int action;
    Emprunt empruntTemp = null;
    Date now;
    
    /**
     * Cree un nouveau FormulairePret par defaut
     */
    public FormulairePret(){
        this(null,null,null,1);
    }
    
    /**
     * Cree un nouveau FormulairePret
     * @param maDateEmprunt GregorianCalendar
     * @param monEmprunteur Emprunteur
     * @param monDocument Document
     * @param monAction int
     */
    public FormulairePret(GregorianCalendar maDateEmprunt, Emprunteur monEmprunteur, Document monDocument, int monAction){
        action = monAction;
        if(action == 1){
            setTitle("Ajout d'un nouveau prêt");
        }
        else{
            setTitle("Modification d'un prêt");
        }
    
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(250, 240);
        setResizable(false);
        setLocationRelativeTo(this);

        Controller.docDispo = new ArrayList<Document>();
        
        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new BorderLayout());
        
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.PAGE_AXIS));

        JPanel panelDate = new JPanel();
        panelDate.setLayout(new GridLayout(0,2));
        JLabel labelDate = new JLabel("Date d'emprunt : ");
        labelDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        now = new Date();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        if(action == 1){
            dateDocument = new JTextField(formatter.format(now));
        }
        else{
            dateDocument = new JTextField(formatter.format(maDateEmprunt.getTime()));
        }
        panelDate.add(labelDate);
        panelDate.add(dateDocument);
            
        JPanel panelEmprunteur = new JPanel();
        panelEmprunteur.setLayout(new GridLayout(0,2));
        JLabel labelEmprunteur = new JLabel("Emprunteur : ");
        labelEmprunteur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelEmprunteur.add(labelEmprunteur);
        comboBoxEmprunteur = Bibliotheque.Controller.creerListeCombo(Bibliotheque.Data.listeEmprunteurs);
        if(monEmprunteur!=null){
            comboBoxEmprunteur.setSelectedIndex(Bibliotheque.Data.getListeEmprunteurs().indexOf(monEmprunteur));
        }
        panelEmprunteur.add(comboBoxEmprunteur);
                
        JPanel panelDoc = new JPanel();
        panelDoc.setLayout(new GridLayout(0,2));
        JLabel labelDoc = new JLabel("Document : ");
        labelDoc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelDoc.add(labelDoc);
        comboBoxDocument = Bibliotheque.Controller.creerListeCombo(Bibliotheque.Data.tableDocuments);
        if(monDocument!=null){
            for(int i=0;i<Controller.getDocDispo().size();i++){
                if(Controller.getDocDispo().get(i) == monDocument){
                    comboBoxDocument.setSelectedIndex(Controller.getDocDispo().indexOf(i));
                }
            }
        }
        panelDoc.add(comboBoxDocument);
        
        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new FlowLayout());
        if(action == 1){
            validerBtn = new JButton("Ajouter le prêt");
        }
        else{
            for(int i=0;i<Bibliotheque.Data.listePrets.size();i++){
                if(Bibliotheque.Data.listePrets.get(i).getDateEmprunt() == maDateEmprunt &&
                        Bibliotheque.Data.listePrets.get(i).getDocument() == monDocument &&
                        Bibliotheque.Data.listePrets.get(i).getEmprunteur() == monEmprunteur){
                    empruntTemp = Bibliotheque.Data.listePrets.get(i);
                }
            }
            validerBtn = new JButton("Modifier le prêt");
        }
        validerBtn.addActionListener(this);
        annulerBtn = new JButton("Annuler");
        annulerBtn.addActionListener(this);
        panelBtn.add(validerBtn);
        panelBtn.add(annulerBtn);
        
        panelForm.add(panelDate);
        panelForm.add(panelEmprunteur);
        panelForm.add(panelDoc);
        panelGeneral.add(panelForm, BorderLayout.CENTER);
        panelGeneral.add(panelBtn, BorderLayout.SOUTH);
        add(panelGeneral);
        pack();
        setVisible(true);
    }

    

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == validerBtn){
            if(!dateDocument.getText().contentEquals("")){
                Emprunteur emprunteurSelec = (Emprunteur) comboBoxEmprunteur.getSelectedItem();
                System.out.println(comboBoxDocument.getSelectedItem());
                Document docSelec = (Document) comboBoxDocument.getSelectedItem();
                
                GregorianCalendar dateEmprunt = new GregorianCalendar();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    dateEmprunt.setTime(format.parse(dateDocument.getText()));
                } catch (ParseException ex) {
                    // bad date
                }
                GregorianCalendar dateRetour = (GregorianCalendar) dateEmprunt.clone();
               
                dateRetour.add(Calendar.MONTH,1);
                if(action == 1){
                    int cptPrets = 0;
                    for(int i=0;i<Bibliotheque.Data.listePrets.size();i++){
                        if(Bibliotheque.Data.listePrets.get(i).getEmprunteur() ==  emprunteurSelec && Bibliotheque.Data.listePrets.get(i).getDateRetour().getTime().after(now)){
                            cptPrets++;
                        }
                    }
                    if(cptPrets < Bibliotheque.nbMaximumEmprunts){
                        Bibliotheque.Data.listePrets.add(new Emprunt(emprunteurSelec, docSelec, dateEmprunt, dateRetour));
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Nombre d'emprunts maximum atteint. Veuillez rendre les ouvrages déjà empruntés avant d'effectuer un nouveau prêt", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    empruntTemp.setDateEmprunt(dateEmprunt);
                    empruntTemp.setDateRetour(dateRetour);
                    empruntTemp.setEmprunteur(emprunteurSelec);
                    empruntTemp.setDocument(docSelec);
                }
                Collections.sort(Bibliotheque.Data.listePrets, Collections.reverseOrder());
                if(OngletPret.champRecherche.getText().equals("Rechercher un prêt ...")){
                    OngletPret.MaJComplete();
                }
                else{
                    OngletPret.MaJ();
                }
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