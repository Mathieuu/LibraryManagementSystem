import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
*  Classe affichant le formulaire de connexion utilisateur
*  @author Mathieu Savy, Thibaut Ackermann, Aurélien Signe
*/
public class FormulaireConnexion extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	boolean admin;
	private JButton connectEtu;
	private JButton connectAdmin;
	private JLabel picLogin;

	/**
	 * Cree un nouvel FormulaireConnexion par defaut
	 */
	public FormulaireConnexion()
	{
		admin = false;
		
		setModal(true);
		setTitle("Password désactivé");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 125);
		setResizable(false);
		setLocationRelativeTo(this);
		
		try
		{
			BufferedImage myPicture	= ImageIO.read(new File("login.png"));
			picLogin = new JLabel(new ImageIcon( myPicture ));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		

		
		connectEtu = new JButton("Connexion Etudiant");
		connectEtu.addActionListener(this);
		connectEtu.setFocusable(false);
		
		connectAdmin = new JButton("Connexion Admin");
		connectAdmin.addActionListener(this);

		JPanel panelGeneral = new JPanel();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		panel.add(connectAdmin);
		panel.add(connectEtu);

		panelGeneral.add(panel);
		panelGeneral.add(picLogin);
		
		add(panelGeneral);
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == connectEtu) {
			admin = false;
		}
		else if(e.getSource() == connectAdmin) {
			admin = true;
		}
		dispose();
	}
	
/*	private static String encode(String password)
	{
		byte[] uniqueKey = password.getBytes();
		byte[] hash      = null;
		
		try
		{
			hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new Error("No MD5 support in this VM.");
		}
		
		StringBuilder hashString = new StringBuilder();
		
		for (int i = 0; i < hash.length; i++)
		{
			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1)
			{
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			}
			else
				hashString.append(hex.substring(hex.length() - 2));
		}

		return hashString.toString();
	}*/
}
