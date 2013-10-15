import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
*  Classe affichant le formulaire de gestion des documents
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class FormulaireDocument extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;

	JTextField titreDocument, identifiantDocument, anneeDocument;
	JButton validerBtn, annulerBtn, ajoutCat, ajoutAut, ajoutEdi;
	JList listeAuteurs;
	JComboBox comboBoxCategorie, comboBoxEditeur;
	int action;

	/**
	 * Cree un nouvel FormulaireDocument par defaut
	 */
	public FormulaireDocument(){
		this("","","",null,null,null, 1);
	}
	
	/**
	 * Cree un nouvel FormulaireAuteur
	 * @param monIdentifiant String
	 * @param monTitre String
	 * @param monAnnee String
	 * @param maCat Categorie
	 * @param mesAut ArrayList<Auteur>
	 * @param monEdi Editeur
	 * @param monAction int
	 */
	public FormulaireDocument(String monIdentifiant, String monTitre, String monAnnee, Categorie maCat, ArrayList<Auteur> mesAut, Editeur monEdi, int monAction){
		action = monAction;
		if(monAction == 1){
			setTitle("Ajout d'un nouveau document");
		}
		else{
			setTitle("Modification d'un document");
		}
		
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400, 240);
		setResizable(false);
		setLocationRelativeTo(this);

		JPanel panelGeneral = new JPanel();
		panelGeneral.setLayout(new BorderLayout());
		
		JPanel panelForm = new JPanel();
		panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.PAGE_AXIS));
		
		JPanel panelIdentifiant = new JPanel();
		panelIdentifiant.setLayout(new GridLayout(0,2));
		JLabel labelIdentifiant = new JLabel("Identifiant : ");
		labelIdentifiant.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		identifiantDocument = new JTextField(monIdentifiant);
		identifiantDocument.addActionListener(this);
		panelIdentifiant.add(labelIdentifiant);
		if(monAction == 0){
			identifiantDocument.setEnabled(false);
		}
		panelIdentifiant.add(identifiantDocument);
		
		JPanel panelTitre = new JPanel();
		panelTitre.setLayout(new GridLayout(0,2));
		JLabel labelTitre = new JLabel("Titre : ");
		labelTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		titreDocument = new JTextField(monTitre);
		titreDocument.addActionListener(this);
		panelTitre.add(labelTitre);
		panelTitre.add(titreDocument);
		
		JPanel panelAnnee = new JPanel();
		panelAnnee.setLayout(new GridLayout(0,2));
		JLabel labelAnnee = new JLabel("Année : ");
		labelAnnee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		anneeDocument = new JTextField(monAnnee);
		anneeDocument.addActionListener(this);
		panelAnnee.add(labelAnnee);
		panelAnnee.add(anneeDocument);
			
		JPanel panelCat = new JPanel();
		panelCat.setLayout(new GridLayout(0,2));
		JLabel labelCategorie = new JLabel("Catégorie : ");
		labelCategorie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		ajoutCat = new JButton("Nouvelle Catégorie");
		ajoutCat.addActionListener(this);
		panelCat.add(labelCategorie);
		comboBoxCategorie = Bibliotheque.Controller.creerListeCombo(Bibliotheque.Data.listeCategories);
		/*comboBoxCategorie.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) { 
		    	  comboBoxCategorie = creerListeCombo(Bibliotheque.Data.listeEditeurs);
		      }
		});*/
		if(maCat!=null){
			comboBoxCategorie.setSelectedIndex(Bibliotheque.Data.getListeCategories().indexOf(maCat));
		}
		panelCat.add(comboBoxCategorie);
		//panelCat.add(ajoutCat);
				
		JPanel panelAut = new JPanel();
		panelAut.setLayout(new GridLayout(0,2));
		JLabel labelAuteur = new JLabel("Auteur : ");
		labelAuteur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		JLabel labelChoix = new JLabel("(Ctrl + Click)");
		labelChoix.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		JPanel panelLabelAut = new JPanel();
		panelLabelAut.setLayout(new FlowLayout());
		panelLabelAut.add(labelAuteur);
		panelLabelAut.add(labelChoix);
		panelAut.add(panelLabelAut);
		listeAuteurs = Bibliotheque.Controller.creerListeJList(Bibliotheque.Data.listeAuteurs);
		listeAuteurs.setVisibleRowCount( 5 );
		JScrollPane spList = new JScrollPane(listeAuteurs);
		ajoutAut = new JButton("Nouvel Auteur");
		ajoutAut.addActionListener(this);
		if(mesAut!=null){
			int taille = mesAut.size();
			int[] auteurSelec = new int[taille];// = listeAuteurs.getSelectedIndices();
			for(int i=0; i<mesAut.size(); i++){
				for(int j=0; j<Bibliotheque.Data.listeAuteurs.size(); j++){
					if(Bibliotheque.Data.listeAuteurs.get(j) == mesAut.get(i)){
						auteurSelec[i] = j;
					}
				}
			}
			listeAuteurs.setSelectedIndices(auteurSelec);
		}
		panelAut.add(spList);
		//panelAut.add(ajoutAut);	
		
		JPanel panelEdi = new JPanel();
		panelEdi.setLayout(new GridLayout(0,2));
		JLabel labelEditeur = new JLabel("Editeur : ");
		labelEditeur.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		panelEdi.add(labelEditeur);
		comboBoxEditeur = Bibliotheque.Controller.creerListeCombo(Bibliotheque.Data.listeEditeurs);
		ajoutEdi = new JButton("Nouvel Editeur");
		ajoutEdi.addActionListener(this);
		if(monEdi!=null){
			comboBoxEditeur.setSelectedIndex(Bibliotheque.Data.getListeEditeurs().indexOf(monEdi));
		}
		panelEdi.add(comboBoxEditeur);
		//panelEdi.add(ajoutEdi);
		
		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new FlowLayout());
		if(action == 1){
			validerBtn = new JButton("Ajouter le document");
		}
		else{
			validerBtn = new JButton("Modifier le document");
		}
		validerBtn.addActionListener(this);
		annulerBtn = new JButton("Annuler");
		annulerBtn.addActionListener(this);
		panelBtn.add(validerBtn);
		panelBtn.add(annulerBtn);
		
		panelForm.add(panelIdentifiant);
		panelForm.add(panelTitre);
		panelForm.add(panelAnnee);
		panelForm.add(panelCat);
		panelForm.add(panelAut);
		panelForm.add(panelEdi);
		panelGeneral.add(panelForm, BorderLayout.CENTER);
		panelGeneral.add(panelBtn, BorderLayout.SOUTH);
		add(panelGeneral);
		pack();
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == validerBtn 
				|| e.getSource() == titreDocument 
				|| e.getSource() == anneeDocument
				|| e.getSource() == identifiantDocument)
		{
			if(		!identifiantDocument.getText().trim().contentEquals("") && 
					!titreDocument.getText().trim().contentEquals("") && 
					!anneeDocument.getText().trim().contentEquals(""))
			{
				Categorie categorieSelec = (Categorie) comboBoxCategorie.getSelectedItem();
				Editeur editeurSelec = (Editeur) comboBoxEditeur.getSelectedItem();
				ArrayList<Auteur> listeAuteursSelec = new ArrayList<Auteur>();
				int[] auteurSelec = listeAuteurs.getSelectedIndices();
				
				for(int i=0; i<auteurSelec.length; i++)
				{
					listeAuteursSelec.add(Bibliotheque.Data.listeAuteurs.get(auteurSelec[i]));
				}
				
				if(action == 1)
				{	//ajout
					Bibliotheque.Data.tableDocuments.put(identifiantDocument.getText().trim(), 
							new Document(identifiantDocument.getText().trim(), 
							categorieSelec, titreDocument.getText().trim(), 
							listeAuteursSelec, editeurSelec, 
							Integer.decode(anneeDocument.getText().trim())));
				}
				else
				{	//modif
					Document docTemp = Bibliotheque.Data.tableDocuments.get(identifiantDocument.getText().trim());
					docTemp.setAnnee(Integer.decode(anneeDocument.getText().trim()));
					docTemp.setAuteurs(listeAuteursSelec);
					docTemp.setCategorie(categorieSelec);
					docTemp.setEditeur(editeurSelec);
					docTemp.setTitre(titreDocument.getText().trim());
					//Bibliotheque.Data.tableDocuments.put(identifiantDocument.getText(),docTemp);
				}
				if(OngletDocument.champRecherche.getText().equals("Rechercher un document ...")){
					OngletDocument.MaJComplete();
				}
				else{
					OngletDocument.MaJ();
				}
				if(OngletPret.champRecherche.getText().equals("Rechercher un prêt ...")){
					OngletPret.MaJComplete();
				}
				else{
					OngletPret.MaJ();
				}
		        
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "La saisie est incomplète !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == annulerBtn){
			dispose();
		}
		
		if(e.getSource() == ajoutAut){
			new FormulaireAuteur();
		}
		if(e.getSource() == ajoutCat){
			new FormulaireCategorie();
		}
		if(e.getSource() == ajoutEdi){
			new FormulaireEditeur();
		}
	}
}