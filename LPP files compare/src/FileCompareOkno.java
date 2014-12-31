import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class FileCompareOkno extends JFrame implements ActionListener {
	JButton banalizuj;
	JLabel pierwszyPlik, drugiPlik;
	
	public FileCompareOkno() {
		
		setSize(400,300);
		setTitle("LPP FILES' COMPARER");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
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
		//System.out.println("Test...");
		Object source = e.getSource();
	}

}
