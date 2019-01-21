
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchFilm;
	private JTextField searchFilmId;
	String searchById = "";
	String searchText = "";
	private JTextField searchFilmByActor;
	
	
	 // Huvudmetod
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	// Huvudförnstret skapas
	
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTextArea ConsoleWindow;
		
		
		
		ConsoleWindow = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane(ConsoleWindow);
		scrollPane.setBounds(162, 232, 574, 303);
		 
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		ConsoleWindow.setBounds(162, 162, 800, 283);
		contentPane.add(scrollPane);
		
		ConsoleWindow.setLineWrap(true);
		ConsoleWindow.setWrapStyleWord(true);
		
		searchFilm = new JTextField();
		searchFilm.setBounds(303, 91, 130, 26);
		contentPane.add(searchFilm);
		searchFilm.setColumns(10);
		
		searchFilmId = new JTextField();
		searchFilmId.setBounds(303, 129, 130, 26);
		contentPane.add(searchFilmId);
		searchFilmId.setColumns(10);
		
		JButton btnNewButton = new JButton("Sök film");
		
		// Sökning efter filmer
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsoleWindow.setText("");
				searchFilmByActor.setText("");
				searchFilmId.setText("");
				
				searchText = searchFilm.getText();	
				
				RestClient.SearchMovie(searchText);
				
				ArrayList<Film> filmer = JSONUtility.list;
				
				if (filmer.size()<1) {ConsoleWindow.setText("Sökningen gav inga resultat");}
				
				else {
				for (Film doc : filmer) 
				{
					ConsoleWindow.append(doc + "\n" + "\n" +"______________________________________________________________________________" + "\n"+ "\n");
				}
				ConsoleWindow.setCaretPosition(0);
				
				JSONUtility.list = new ArrayList<Film>();
				}
				
			}
		});
		
		btnNewButton.setBounds(459, 91, 139, 29);
		contentPane.add(btnNewButton);
		
		// Sökning baserad på film ID
		
		JButton btnSkFilmid = new JButton("Sök Film(ID)");
		btnSkFilmid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				searchFilmByActor.setText("");
				searchFilm.setText("");
				ConsoleWindow.setText("");
				
				searchById = searchFilmId.getText();
				
				try {ConsoleWindow.append(RestClient.searchID(searchById));
					
				} catch (Exception e2) {
					ConsoleWindow.setText("Sökningen gav inga resultat");
				}
				
				
			}
		});
		btnSkFilmid.setBounds(459, 129, 139, 29);
		contentPane.add(btnSkFilmid);
		
		searchFilmByActor = new JTextField();
		searchFilmByActor.setBounds(303, 170, 130, 26);
		contentPane.add(searchFilmByActor);
		searchFilmByActor.setColumns(10);
		
		
		JButton btnSkSkdespelare = new JButton("Sök Skådespelare");
		
		// Sökning baserad på skådespelare
		
		btnSkSkdespelare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsoleWindow.setText("");
				searchFilm.setText("");
				searchFilmId.setText("");
				String actorName = searchFilmByActor.getText();
				RestClient.SearchActor(actorName);
				ArrayList<Film> filmer = JSONUtility.list;
				if (filmer.size()<1) {ConsoleWindow.setText("Sökningen gav inga resultat");}
				
				else {
					int count = 0;
				for (Film doc : filmer) 
				{
					
					if (count==0) {ConsoleWindow.append("Skådespelaren " + doc.name + " medverkar i: " + "\n" + "\n"); count++;}
					ConsoleWindow.append(doc + "\n" + "\n" +"______________________________________________________________________________" + "\n"+ "\n");
				}
				JSONUtility.list = new ArrayList<Film>();
				}
				
				ConsoleWindow.setCaretPosition(0);
				
				
			}
		});
		btnSkSkdespelare.setBounds(459, 170, 139, 29);
		contentPane.add(btnSkSkdespelare);
		
		JLabel lblLab = new JLabel("Sök Film");
		lblLab.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		lblLab.setBounds(412, 28, 95, 16);
		contentPane.add(lblLab);
	}
}
