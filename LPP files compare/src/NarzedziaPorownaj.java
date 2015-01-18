import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class NarzedziaPorownaj extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel plik1csv, plik2csv;
	private JButton bPorownaj, bWybierz1, bWybierz2;
	private ButtonGroup bg;
	private JRadioButton rbPlik, rbFolder;
	private JFileChooser fc1, fc2;
	private File plik1, plik2;
	private String sciezka1, sciezka2;
	
	public NarzedziaPorownaj(JFrame glowneOkno) {
		super(glowneOkno, "Porównywanie plików", true);
		setSize(500, 300);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		plik1csv = new JLabel("Pierwszy plik/folder:");
		plik1csv.setBounds(10, 30, 100, 20);
		add(plik1csv);
		
		plik2csv = new JLabel("Drugi plik/folder:");
		plik2csv.setBounds(10, 70, 100, 20);
		add(plik2csv);
		
		bWybierz1 = new JButton("Przegladaj...");
		bWybierz1.setBounds(130, 30, 100, 20);
		add(bWybierz1);
		bWybierz1.addActionListener(this);
		
		bWybierz2 = new JButton("Przegladaj...");
		bWybierz2.setBounds(130, 70, 100, 20);
		add(bWybierz2);
		bWybierz2.addActionListener(this);
		
		bPorownaj = new JButton("Porownaj!");
		bPorownaj.setBounds(130, 120, 100, 40);
		add(bPorownaj);
		bPorownaj.addActionListener(this);
		
		bg = new ButtonGroup();
		
		rbPlik = new JRadioButton("Plik", true);
		rbPlik.setBounds(270, 30, 50, 20);
		add(rbPlik);
		bg.add(rbPlik);
		
		rbFolder = new JRadioButton("Folder", false);
		rbFolder.setBounds(340, 30, 80, 20);
		add(rbFolder);
		bg.add(rbFolder);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		 if(rbPlik.isSelected()) {
		 if(source == bWybierz1) {
		    	fc1 = new JFileChooser();
		    	fc1.setDialogTitle("wybierz pierwszy plik");
		    	fc1.setCurrentDirectory(new File("D:\\workspace_luna"));
				
				if(fc1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					plik1 = fc1.getSelectedFile();
					sciezka1 = plik1.getAbsolutePath();
				}
		 }
		 else if(source == bWybierz2) {
		    	fc2 = new JFileChooser();
		    	fc2.setDialogTitle("wybierz drugi plik");
		    	fc2.setCurrentDirectory(new File("D:\\workspace_luna"));
				
				if(fc2.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					plik2 = fc2.getSelectedFile();
					sciezka2 = plik2.getAbsolutePath();
				}				
		 }
		 else if(source == bPorownaj) {
			 FileCompareManager fcm = new FileCompareManager();
			 boolean wynik = fcm.porownajPliki(plik1.getAbsolutePath(), plik2.getAbsolutePath());
			 if(wynik) JOptionPane.showMessageDialog(null, "Pliki s¹ takie same!");
			 else JOptionPane.showMessageDialog(null, "Pliki siê ró¿ni¹!");
		 }
		 }
		 //--
		 else if(rbFolder.isSelected()) {
			 if(source == bWybierz1) {
			    	fc1 = new JFileChooser();
			    	fc1.setDialogTitle("wybierz pierwszy folder");
			    	fc1.setCurrentDirectory(new File("D:\\workspace_luna"));
			    	fc1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    	fc1.setAcceptAllFileFilterUsed(false);
			    	
					if(fc1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						plik1 = fc1.getSelectedFile();
						sciezka1 = plik1.getAbsolutePath();
					}
			 }
			 else if(source == bWybierz2) {
			    	fc2 = new JFileChooser();
			    	fc2.setDialogTitle("wybierz drugi folder");
			    	fc2.setCurrentDirectory(new File("D:\\workspace_luna"));
			    	fc2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    	fc2.setAcceptAllFileFilterUsed(false);
			    	
					if(fc2.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						plik2 = fc2.getSelectedFile();
						sciezka2 = plik2.getAbsolutePath();
					}				
			 }
			 else if(source == bPorownaj) {
				 FileCompareManager fcm = new FileCompareManager();
				 File katalog = new File(sciezka1);
				 File zawartosc[] = katalog.listFiles();
					for(File f : zawartosc) {
						System.out.println(f.getAbsolutePath());
						//fcm.konwertujArkuszDoCsv(f.getAbsoluteFile());
					}
//				 boolean wynik = fcm.porownajPliki(plik1.getAbsolutePath(), plik2.getAbsolutePath());
//				 if(wynik) JOptionPane.showMessageDialog(null, "Pliki s¹ takie same!");
//				 else JOptionPane.showMessageDialog(null, "Pliki siê ró¿ni¹!");
			 }
		 }
	}
}
