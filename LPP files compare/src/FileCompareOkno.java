import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class FileCompareOkno extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton banalizuj;
	JLabel pierwszyPlik, drugiPlik;
	JMenuBar menuBar;
	JMenu menuProgram, menuNarzedzia;
	JMenuItem mKonwertuj, mOProgramie, mWyjscie;
	
	public FileCompareOkno() {
		
		setSize(400,300);
		setTitle("LPP FILES' COMPARER v1.0");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuProgram = new JMenu("Program");
		menuBar.add(menuProgram);
		menuNarzedzia = new JMenu("Narzedzia");
		menuBar.add(menuNarzedzia);
		
		mKonwertuj = new JMenuItem("Konwertuj");
		mOProgramie = new JMenuItem("O programie");
		mWyjscie = new JMenuItem("Wyjscie");
		
		menuProgram.add(mOProgramie);
		menuProgram.addSeparator();
		menuProgram.add(mWyjscie);
		menuNarzedzia.add(mKonwertuj);
		
		mWyjscie.addActionListener(this);
		
		banalizuj = new JButton("Analizuj!");
		banalizuj.setBounds(120, 180, 120, 30);
		add(banalizuj);
		banalizuj.addActionListener(this);
		
		pierwszyPlik = new JLabel("Pierwszy plik: ");
		pierwszyPlik.setBounds(50, 50, 120, 30);
		add(pierwszyPlik);
		
		drugiPlik = new JLabel("Drugi plik: ");
		drugiPlik.setBounds(50, 100, 120, 30);
		add(drugiPlik);
	}
	public static void main(String[] args) {
		FileCompareOkno fco = new FileCompareOkno();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == mWyjscie) dispose();
	}

}
