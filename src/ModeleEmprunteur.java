import javax.swing.table.DefaultTableModel;

/**
 * Table model des emprunteurs permettant de construire une JTable
 * @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
 */
public class ModeleEmprunteur extends DefaultTableModel {
    private static final long serialVersionUID = 1L;

    
    /**
    * Cree un nouvel objet ModeleEmprunteur par defaut
    */
    public ModeleEmprunteur(){
        super();

        setColumnIdentifiers(new String[] {"", "Nom", "Prénom", "Adresse Mail", "Statut", "",""});


        for(int i=0; i<Bibliotheque.Data.listeEmprunteurs.size();i++){
            Emprunteur emprunteurTemp = Bibliotheque.Data.listeEmprunteurs.get(i);
            addRow(new Object[] {i, emprunteurTemp.getNom(), emprunteurTemp.getPrenom(), emprunteurTemp.getMail(), emprunteurTemp.getStatut(), new RendererModifier(), new RendererSupprimer()});
        }
    }
    
    /**
    * Cree un nouvel objet ModeleEmprunteur
    * @param recherche int
    */
    public ModeleEmprunteur(int recherche){
        super();

        setColumnIdentifiers(new String[] {"", "Nom", "Prénom", "Adresse Mail", "Statut"});


        for(int i=0; i<Bibliotheque.Data.listeEmprunteurs.size();i++){
            Emprunteur emprunteurTemp = Bibliotheque.Data.listeEmprunteurs.get(i);
            addRow(new Object[] {i, emprunteurTemp.getNom(), emprunteurTemp.getPrenom(), emprunteurTemp.getMail(), emprunteurTemp.getStatut()});
        }
    }
    

    public ModeleEmprunteur(String motsCles){
        
        super();
        
        Emprunteur e;
        String[] listeMotsCles;
        boolean estDansLaRecherche;

        motsCles = motsCles.toLowerCase();
        listeMotsCles = motsCles.split(" ");
        
        setColumnIdentifiers(new String[] {"", "Nom", "Prénom", "Adresse Mail", "Statut", "",""});

        for(int i=0; i<Bibliotheque.Data.listeEmprunteurs.size();i++){
            
            estDansLaRecherche = true; 
            
            e = Bibliotheque.Data.listeEmprunteurs.get(i);
            
            for(int j = 0; j < listeMotsCles.length; j++){
                if(!Integer.toString(i).contains(listeMotsCles[j]) && !e.getNom().toLowerCase().contains(listeMotsCles[j]) && !e.getPrenom().toLowerCase().contains(listeMotsCles[j]) && !e.getMail().toLowerCase().contains(listeMotsCles[j]) && !e.getStatut().toString().toLowerCase().contains(listeMotsCles[j])){
                    estDansLaRecherche = false;
                }
            }
            
            if(estDansLaRecherche == true)
                addRow(new Object[] {i, e.getNom(), e.getPrenom(), e.getMail(), e.getStatut().toString(), new RendererModifier(), new RendererSupprimer()});
        }       
    }


    public ModeleEmprunteur(String nom, String prenom, String statut) {
        
        super();
        
        Emprunteur e;
        boolean estDansLaRecherche;

        nom = nom.toLowerCase();
        prenom = prenom.toLowerCase();
        statut = statut.toLowerCase();
        
        setColumnIdentifiers(new String[] {"", "Nom", "Prénom", "Adresse Mail", "Statut", "",""});

        for(int i=0; i<Bibliotheque.Data.listeEmprunteurs.size();i++){
            
            estDansLaRecherche = true; 
            
            e = Bibliotheque.Data.listeEmprunteurs.get(i);
            

            if(!e.getNom().toLowerCase().contains(nom) || !e.getPrenom().toLowerCase().contains(prenom) || !e.getStatut().toString().toLowerCase().contains(statut))
                estDansLaRecherche = false;
            
            if(estDansLaRecherche == true)
                addRow(new Object[] {i, e.getNom(), e.getPrenom(), e.getMail(), e.getStatut().toString(), new RendererModifier(), new RendererSupprimer()});
        }   
    }

public ModeleEmprunteur(String nom, String prenom, String statut, int recherche) {
        
        super();
        
        Emprunteur e;
        boolean estDansLaRecherche;

        nom = nom.toLowerCase();
        prenom = prenom.toLowerCase();
        statut = statut.toLowerCase();
        
        setColumnIdentifiers(new String[] {"", "Nom", "Prénom", "Adresse Mail", "Statut"});

        for(int i=0; i<Bibliotheque.Data.listeEmprunteurs.size();i++){
            
            estDansLaRecherche = true; 
            
            e = Bibliotheque.Data.listeEmprunteurs.get(i);
            

            if(!e.getNom().toLowerCase().contains(nom) || !e.getPrenom().toLowerCase().contains(prenom) || !e.getStatut().toString().toLowerCase().contains(statut))
                estDansLaRecherche = false;
            
            if(estDansLaRecherche == true)
                addRow(new Object[] {i, e.getNom(), e.getPrenom(), e.getMail(), e.getStatut().toString()});
        }   
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears.
        if (col < 5) {
            return false;
        } else {
            return true;
        }
    }
}
