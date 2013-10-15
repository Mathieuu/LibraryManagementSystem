import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Collections;

import javax.swing.*;

/**
*  Classe affichant le formulaire de gestion des emprunteurs
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class FormulaireEmprunteur extends JDialog implements ActionListener, FocusListener{
	private static final long serialVersionUID = 1L;

	JTextField nomEmp, prenomEmp, mailEmp;
	JButton validerBtn, annulerBtn, ajoutStatut;
	JComboBox comboBoxStatut;
	int action;
	Emprunteur empTemp = null;
	
	/**
	 * Cree un nouvel FormulaireAuteur par defaut
	 */
	public FormulaireEmprunteur(){
		this("","","",null,1);
	}
	
	/**
	 * Cree un nouvel FormulaireAuteur
	 * @param monNom String
	 * @param monPrenom String
	 * @param monMail String
	 * @param monStat Statut
	 * @param monAction int
	 */
	public FormulaireEmprunteur(String monNom, String monPrenom,String monMail, Statut monStat, int monAction){
		action = monAction;
		if(monAction == 1){
			setTitle("Ajout d'un nouvel emprunteur");
		}
		else{
			setTitle("Mofif d'un emprunteur");
		}
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(350, 230);
		//setResizable(false);
		setLocationRelativeTo(this);

		JPanel panelGeneral = new JPanel();
		panelGeneral.setLayout(new BorderLayout());
		
		JPanel panelForm = new JPanel();
		panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.PAGE_AXIS));
		
		JPanel panelNom = new JPanel();
		panelNom.setLayout(new GridLayout(0,3));
		JLabel labelNom = new JLabel("Nom : ");
		labelNom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		nomEmp = new JTextField(monNom);
		nomEmp.addActionListener(this);
		panelNom.add(labelNom);
		panelNom.add(nomEmp);
		
		JPanel panelPrenom = new JPanel();
		panelPrenom.setLayout(new GridLayout(0,3));
		JLabel labelPrenom = new JLabel("Prénom : ");
		labelPrenom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		prenomEmp = new JTextField(monPrenom);
		prenomEmp.addActionListener(this);
		panelPrenom.add(labelPrenom);
		panelPrenom.add(prenomEmp);
		
		
		JPanel panelMail = new JPanel();
		panelMail.setLayout(new GridLayout(0,3));
		JLabel labelMail = new JLabel("Mail : ");
		labelMail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		mailEmp = new JTextField(monMail);
		mailEmp.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {mailEmp.setText(prenomEmp.getText().toLowerCase().replace('è', 'e').replace('é', 'e')+"."+nomEmp.getText().toLowerCase()+"@utbm.fr");}});
		JLabel labelAuto = new JLabel("(@auto --> 'Entrée')");
		labelNom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		panelMail.add(labelMail);
		panelMail.add(mailEmp);
		panelMail.add(labelAuto);
		
		JPanel panelStatut = new JPanel();
		panelStatut.setLayout(new GridLayout(0,3));
		JLabel labelStatut = new JLabel("Statut : ");
		labelStatut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		ajoutStatut = new JButton("Nouveau Statut");
		ajoutStatut.addActionListener(this);
		panelStatut.add(labelStatut);
		comboBoxStatut = Bibliotheque.Controller.creerListeCombo(Bibliotheque.Data.listeStatuts);
		if(monStat!=null){
			comboBoxStatut.setSelectedIndex(Bibliotheque.Data.getListeStatuts().indexOf(monStat));
		}
		comboBoxStatut.getEditor().getEditorComponent().addFocusListener(this);
		panelStatut.add(comboBoxStatut);
		//panelStatut.add(ajoutStatut);
				
		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new FlowLayout());
		if(monAction == 1){
			validerBtn = new JButton("Ajouter l'emprunteur");
		}
		else{
			for(int i=0;i<Bibliotheque.Data.listeEmprunteurs.size();i++){
				if(Bibliotheque.Data.listeEmprunteurs.get(i).getNom().equals(monNom) &&
						Bibliotheque.Data.listeEmprunteurs.get(i).getPrenom().equals(monPrenom) &&
						Bibliotheque.Data.listeEmprunteurs.get(i).getMail().equals(monMail) &&
						Bibliotheque.Data.listeEmprunteurs.get(i).getStatut() == comboBoxStatut.getSelectedItem()){
					empTemp = Bibliotheque.Data.listeEmprunteurs.get(i);
				}
			}
			validerBtn = new JButton("Modifier l'emprunteur");
		}
		validerBtn.addActionListener(this);
		annulerBtn = new JButton("Annuler");
		annulerBtn.addActionListener(this);
		panelBtn.add(validerBtn);
		panelBtn.add(annulerBtn);
		
		panelForm.add(panelNom);
		panelForm.add(panelPrenom);
		panelForm.add(panelMail);
		panelForm.add(panelStatut);
		panelGeneral.add(panelForm, BorderLayout.CENTER);
		panelGeneral.add(panelBtn, BorderLayout.SOUTH);
		add(panelGeneral);
		pack();
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == validerBtn
				|| e.getSource() == nomEmp
				|| e.getSource() == prenomEmp)
		{
			if(!nomEmp.getText().trim().contentEquals("") && !prenomEmp.getText().trim().contentEquals("") && !mailEmp.getText().trim().contentEquals("")){
				Statut statutSelec = (Statut) comboBoxStatut.getSelectedItem();
				
				if(action == 1)
				{	//ajout
					Bibliotheque.Data.listeEmprunteurs.add(new Emprunteur(nomEmp.getText().trim().toUpperCase(), prenomEmp.getText().trim(), mailEmp.getText().trim(), statutSelec));
				}
				else{	//modif
					empTemp.setNom(nomEmp.getText().toUpperCase());
					empTemp.setPrenom(prenomEmp.getText());
					empTemp.setMail(mailEmp.getText());
					empTemp.setStatut(statutSelec);
				}
				Collections.sort(Bibliotheque.Data.listeEmprunteurs, Collections.reverseOrder());
				if(OngletEmprunteur.champRecherche.getText().equals("Rechercher un emprunteur ...")){
					OngletEmprunteur.MaJComplete();
				}
				else{
					OngletEmprunteur.MaJ();
				}
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
		if(e.getSource() == ajoutStatut){
			 new FormulaireStatut();
		}
	}
	
	public void focusGained(FocusEvent e) {
		Bibliotheque.Controller.majListeCombo(comboBoxStatut, Bibliotheque.Data.listeStatuts);
	}

	public void focusLost(FocusEvent arg0) {
	}
}