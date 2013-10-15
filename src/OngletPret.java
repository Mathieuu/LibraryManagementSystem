import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

/**
*  Classe affichant l'onglet pret
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class OngletPret extends JPanel implements ActionListener, KeyListener, FocusListener{
	private static final long serialVersionUID = 1L;

	private static JTable tableauPret;
	private static JButton nouveauBtn;
	public static JTextField champRecherche;
	
	/**
	* Cree un nouvel objet OngletPret
	*/
	public OngletPret(){
		this.setLayout(new BorderLayout());
		
		JPanel panelTableau = new JPanel();	
		panelTableau.setLayout(new BoxLayout(panelTableau, BoxLayout.PAGE_AXIS));
		ModelePret modelPret = new ModelePret();		
		tableauPret = new JTable(modelPret);
	
		MaJAffichage();
		
		JScrollPane jScrollPaneTabDoc = new JScrollPane(tableauPret);
		panelTableau.add(jScrollPaneTabDoc);
		
	 	JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new FlowLayout());

		nouveauBtn = new JButton("Nouveau Prêt");
		panelBtn.add(nouveauBtn);
		nouveauBtn.addActionListener(this);
		
		champRecherche = new JTextField("Rechercher un prêt ..."); //Adapter focus listener si changement texte
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
		tableauPret.setModel(new ModelePret());
		MaJAffichage();
	}
	
	/**
	* Mise a jour de l'affichage suite a une recherche
	*/
	public static void MaJ(){
		//tableauPret.setModel(new ModelePret());
		tableauPret.setModel(new ModelePret(champRecherche.getText()));
		MaJAffichage();
	}
	
	/**
	* Mise a jour de l'affichage
	*/
	public static void MaJAffichage(){
		tableauPret.getColumnModel().getColumn(6).setCellRenderer(new RendererRendu());
		tableauPret.getColumnModel().getColumn(6).setCellEditor(new RendererRendu());
		tableauPret.getColumnModel().getColumn(7).setCellRenderer(new RendererSupprimer());
		tableauPret.getColumnModel().getColumn(7).setCellEditor(new RendererSupprimer());
		
		//tableauPret.setAutoCreateRowSorter(true);
		tableauPret.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableauPret.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableauPret.getColumnModel().getColumn(1).setPreferredWidth(180);
		tableauPret.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableauPret.getColumnModel().getColumn(3).setPreferredWidth(70);
		tableauPret.getColumnModel().getColumn(4).setPreferredWidth(70);
		tableauPret.getColumnModel().getColumn(5).setPreferredWidth(70);
		tableauPret.getColumnModel().getColumn(6).setPreferredWidth(5);
		tableauPret.getColumnModel().getColumn(7).setPreferredWidth(5);
	}

	/**
	* Getter
	*/
	public static JTable getTableauPret() {
		return tableauPret;
	}

	/**
	* Event listeners
	*/
	public void actionPerformed(ActionEvent e) {
		if((JButton) e.getSource() == nouveauBtn){
			new FormulairePret();
		}
	}

	public void keyReleased(KeyEvent e) {
		tableauPret.setModel(new ModelePret(champRecherche.getText()));
		MaJAffichage();
	}

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void focusGained(FocusEvent e) {
		if(champRecherche.getText().equals("Rechercher un prêt ..."))
			champRecherche.setText(null);
	}

	public void focusLost(FocusEvent e) {
		if(champRecherche.getText().isEmpty())
			champRecherche.setText("Rechercher un prêt ...");
	}

}
