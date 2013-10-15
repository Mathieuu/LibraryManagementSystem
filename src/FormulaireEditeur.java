import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.*;

/**
*  Classe affichant le formulaire de gestion des editeurs
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class FormulaireEditeur extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;

	JTextField nomEditeur;
	JButton validerBtn, annulerBtn;
	public static JTable tableauEditeur;
	int action;
	Editeur ediTemp = null;
	
	/**
	 * Cree un nouvel FormulaireEditeur par defaut
	 */
	public FormulaireEditeur(){
		this("", 1);
	}
	
	/**
	 * Cree un nouvel FormulaireEditeur
	 * @param monNom String
	 * @param monAction int
	 */
	public FormulaireEditeur(String monNom, int monAction){
		action = monAction;
		if(monAction == 1){
			setTitle("Ajout d'un nouvel éditeur");
		}
		else{
			setTitle("Modification d'un éditeur");
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
		tableauEditeur = new JTable(new ModeleEditeur());
		tableauEditeur.setAutoCreateRowSorter(true);
		tableauEditeur.setEnabled(false);
		JScrollPane jScrollPaneTabDoc = new JScrollPane(tableauEditeur);
		panelTableau.add(jScrollPaneTabDoc);
		
		JPanel panelNom = new JPanel();
		panelNom.setLayout(new GridLayout(0,2));
		JLabel labelNom = new JLabel("Nom :");
		labelNom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		nomEditeur = new JTextField(monNom);
		nomEditeur.addActionListener(this);
		panelNom.add(labelNom);
		panelNom.add(nomEditeur);


		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new FlowLayout());
		if(monAction == 1){
			validerBtn = new JButton("Ajouter l'éditeur");
		}
		else{
			for(int i=0;i<Bibliotheque.Data.listeEditeurs.size();i++){
				if(Bibliotheque.Data.listeEditeurs.get(i).getNom().equals(monNom)){
					ediTemp = Bibliotheque.Data.listeEditeurs.get(i);
				}
			}
			validerBtn = new JButton("Modifier l'éditeur");
		}
		validerBtn.addActionListener(this);
		annulerBtn = new JButton("Annuler");
		annulerBtn.addActionListener(this);
		panelBtn.add(validerBtn);
		panelBtn.add(annulerBtn);
		
		panelForm.add(panelTableau);
		panelForm.add(panelNom);
		panelGeneral.add(panelForm, BorderLayout.CENTER);
		panelGeneral.add(panelBtn, BorderLayout.SOUTH);
		add(panelGeneral);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == validerBtn || e.getSource() == nomEditeur)
		{
			if(!nomEditeur.getText().contentEquals("")){
				if(action == 1){	//ajout
					Bibliotheque.Data.listeEditeurs.add(new Editeur(nomEditeur.getText()));
				}
				else{	//modif
					ediTemp.setNom(nomEditeur.getText());
					Bibliotheque.ongletGestion.MaJ();
					if(OngletDocument.champRecherche.getText().equals("Rechercher un document ...")){
						OngletDocument.MaJComplete();
					}
					else{
						OngletDocument.MaJ();
					}
				}
				Collections.sort(Bibliotheque.Data.listeEditeurs, Collections.reverseOrder());
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