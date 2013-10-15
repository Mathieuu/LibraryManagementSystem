import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.*;

/**
*  Classe affichant le formulaire de gestion des auteurs
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class FormulaireAuteur extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;

	JTextField nomAut, prenomAut;
	JButton validerBtn, annulerBtn;
	public static JTable tableauAuteur;
	int action;
	Auteur autTemp = null;
	
	/**
	 * Cree un nouvel FormulaireAuteur par defaut
	 */
	public FormulaireAuteur(){
		this("","", 1);
	}
	
	/**
	 * Cree un nouvel FormulaireAuteur
	 * @param monNom String
	 * @param monPrenom String
	 * @param monAction int
	 */
	public FormulaireAuteur(String monNom, String monPrenom, int monAction){
		action = monAction;
		if(monAction == 1){
			setTitle("Ajout d'un nouvel auteur");
		}
		else{
			setTitle("Modification d'un auteur");
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
		tableauAuteur = new JTable(new ModeleAuteur());
		tableauAuteur.setAutoCreateRowSorter(true);
		tableauAuteur.setEnabled(false);
		JScrollPane jScrollPaneTabDoc = new JScrollPane(tableauAuteur);
		panelTableau.add(jScrollPaneTabDoc);
		
		JPanel panelNom = new JPanel();
		panelNom.setLayout(new GridLayout(0,2));
		JLabel labelNom = new JLabel("Nom :");
		labelNom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		nomAut = new JTextField(monNom);
		nomAut.addActionListener(this);
		panelNom.add(labelNom);
		panelNom.add(nomAut);

		JPanel panelPrenom = new JPanel();
		panelPrenom.setLayout(new GridLayout(0,2));
		JLabel labelPrenom = new JLabel("Prénom :");
		labelPrenom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		prenomAut = new JTextField(monPrenom);
		prenomAut.addActionListener(this);
		panelNom.add(labelPrenom);
		panelNom.add(prenomAut);

		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new FlowLayout());
		if(action == 1){
			validerBtn = new JButton("Ajouter l'auteur");
		}
		else{
			for(int i=0;i<Bibliotheque.Data.listeAuteurs.size();i++){
				if(Bibliotheque.Data.listeAuteurs.get(i).getNom().equals(monNom) &&
						Bibliotheque.Data.listeAuteurs.get(i).getPrenom().equals(monPrenom)){
					autTemp = Bibliotheque.Data.listeAuteurs.get(i);
				}
			}
			validerBtn = new JButton("Modifier l'auteur");
		}
		validerBtn.addActionListener(this);
		annulerBtn = new JButton("Annuler");
		annulerBtn.addActionListener(this);
		panelBtn.add(validerBtn);
		panelBtn.add(annulerBtn);
		
		panelForm.add(panelTableau);
		panelForm.add(panelNom);
		panelForm.add(panelPrenom);
		panelGeneral.add(panelForm, BorderLayout.CENTER);
		panelGeneral.add(panelBtn, BorderLayout.SOUTH);
		add(panelGeneral);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == validerBtn || e.getSource() == nomAut || e.getSource() == prenomAut)
		{
			if(!prenomAut.getText().contentEquals("") && !nomAut.getText().contentEquals("")){
				if(action == 1){	//ajout
					Bibliotheque.Data.listeAuteurs.add(new Auteur(prenomAut.getText(), nomAut.getText()));
				}
				else{	//modif
					autTemp.setNom(nomAut.getText());
					autTemp.setPrenom(prenomAut.getText());
					Bibliotheque.ongletGestion.MaJ();
					if(OngletDocument.champRecherche.getText().equals("Rechercher un document ...")){
						OngletDocument.MaJComplete();
					}
					else{
						OngletDocument.MaJ();
					}
				}
				Collections.sort(Bibliotheque.Data.listeAuteurs, Collections.reverseOrder());
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