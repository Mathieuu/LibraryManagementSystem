import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.*;

/**
*  Classe affichant le formulaire de gestion des statuts
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class FormulaireStatut extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;

	JTextField libelleStatut;
	JButton validerBtn, annulerBtn;
	public static JTable tableauStatut;
	int action;
	Statut statutTemp = null;
	
	/**
	 * Cree un nouveau FormulaireStatut par defaut
	 */
	public FormulaireStatut(){
		this("", 1);
	}
	
	/**
	 * Cree un nouveau FormulairePret
	 * @param monLibelle String
	 * @param monAction int
	 */
	public FormulaireStatut(String monLibelle, int monAction){
		action = monAction;
		if(monAction == 1){
			setTitle("Ajout d'un nouveau statut");
		}
		else{
			setTitle("Modification d'un statut");
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
		tableauStatut = new JTable(new ModeleStatut());
		tableauStatut.setAutoCreateRowSorter(true);
		tableauStatut.setEnabled(false);
		JScrollPane jScrollPaneTabDoc = new JScrollPane(tableauStatut);
		panelTableau.add(jScrollPaneTabDoc);
		
		JPanel panelLibelle = new JPanel();
		panelLibelle.setLayout(new GridLayout(0,2));
		JLabel labelLibelle = new JLabel("Libellé :");
		labelLibelle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		libelleStatut = new JTextField(monLibelle);
		libelleStatut.addActionListener(this);
		panelLibelle.add(labelLibelle);
		panelLibelle.add(libelleStatut );


		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new FlowLayout());
		if(monAction == 1){
			validerBtn = new JButton("Ajouter le statut");
		}
		else{
			for(int i=0;i<Bibliotheque.Data.listeStatuts.size();i++){
				if(Bibliotheque.Data.listeStatuts.get(i).getLibelle().equals(monLibelle)){
					statutTemp = Bibliotheque.Data.listeStatuts.get(i);
				}
			}
			validerBtn = new JButton("Modifier le statut");
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
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == validerBtn || e.getSource() == libelleStatut)
		{
			if(!libelleStatut.getText().contentEquals(""))
			{
				if(action == 1)
				{	//ajout
					Bibliotheque.Data.listeStatuts.add(new Statut(libelleStatut.getText()));
				}
				else{	//modif
					statutTemp.setLibelle(libelleStatut.getText());
					Bibliotheque.ongletGestion.MaJ();
					if(OngletEmprunteur.champRecherche.getText().equals("Rechercher un emprunteur ...")){
						OngletEmprunteur.MaJComplete();
					}
					else{
						OngletEmprunteur.MaJ();
					}
				}
				Collections.sort(Bibliotheque.Data.listeStatuts, Collections.reverseOrder());
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