import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Okno extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField loginField;
	private JPasswordField passwordField;
	private JTextField iopTextField;
	private JTextField ckTextField;
	private JTextField tnTextField;
	private JMenu Menu;
	private JMenuItem oProgramieItem;
	/**
	 * Create the frame.
	 */
	public Okno() 
	{
//		try {
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//		} catch (ClassNotFoundException | InstantiationException
//				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
//			e1.printStackTrace();
//		}
		setTitle("Bot");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 323);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		Menu = new JMenu("Menu");
		menuBar.add(Menu);
		
		oProgramieItem = new JMenuItem("O programie");
		Menu.add(oProgramieItem);
		oProgramieItem.addActionListener(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelLogin = new JLabel("Login:");
		labelLogin.setBounds(10, 21, 46, 14);
		contentPane.add(labelLogin);
		
		JLabel labelHaso = new JLabel("Has\u0142o:");
		labelHaso.setBounds(10, 46, 46, 14);
		contentPane.add(labelHaso);
		
		loginField = new JTextField();
		loginField.setBounds(66, 18, 116, 20);
		contentPane.add(loginField);
		loginField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(66, 43, 116, 20);
		contentPane.add(passwordField);
		
		JLabel labelIOP = new JLabel("Ilo\u015B\u0107 odwiedzonych profili:");
		labelIOP.setBounds(10, 87, 223, 14);
		contentPane.add(labelIOP);
		
		iopTextField = new JTextField();
		iopTextField.setBounds(270, 84, 86, 20);
		contentPane.add(iopTextField);
		iopTextField.setColumns(10);
		iopTextField.setToolTipText("Uwaga, aby nie przeci¹¿aæ serwisu polecam jednorazowo ustawiæ do 500 odwiedzonych kont.");
		
		JLabel labelCK = new JLabel("Cz\u0119stotliwo\u015B\u0107 klikania (w sekundach)");
		labelCK.setBounds(10, 112, 223, 14);
		contentPane.add(labelCK);
		
		ckTextField = new JTextField();
		ckTextField.setBounds(270, 109, 86, 20);
		contentPane.add(ckTextField);
		ckTextField.setColumns(10);
		ckTextField.setToolTipText("Uwaga, czêstotliwoœæ nie mo¿e byæ za wysoka, poniewa¿ mo¿e skutkowaæ to zablokowaniem konta. "
				+ "Polecam 1.5 sekundy nastawiæ.");
		
		JLabel labelTN = new JLabel("Tak/Nie (w procentach)");
		labelTN.setBounds(10, 136, 223, 14);
		contentPane.add(labelTN);
		
		tnTextField = new JTextField();
		tnTextField.setBounds(270, 133, 86, 20);
		contentPane.add(tnTextField);
		tnTextField.setColumns(10);
		tnTextField.setToolTipText("Aby administratorzy nie po³apali siê ¿e same TAK s¹ klikane, polecam wpisaæ tutaj nie 100%, ale 90%.");
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(112, 216, 165, 37);
		contentPane.add(progressBar);
		
		JButton bStart = new JButton("Start!");
		bStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Klik!");
			}
		});
		bStart.setBounds(112, 168, 165, 37);
		contentPane.add(bStart);
		
		JLabel labelPostp = new JLabel("Post\u0119p:");
		labelPostp.setBounds(10, 226, 46, 14);
		contentPane.add(labelPostp);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Okno frame = new Okno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
		if(source == oProgramieItem)
			JOptionPane.showMessageDialog(null, "Program napisany przez £ukasza Jadanowskiego w celach edukacyjnych."
					+ "\nNie biorê odpowiedzialnoœci za u¿ycie go niezgodne z prawem.", 
					"O programie", 
					JOptionPane.INFORMATION_MESSAGE);
	}
}
