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

public class NarzedziaKonwertuj extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel plikOrfolder;
	private JButton bKonwertuj, bWybierz;
	private ButtonGroup bg;
	private JRadioButton rbPlik, rbFolder;
	private JFileChooser fc;
	private File plik, katalog;
	private String sciezka;
	
	public NarzedziaKonwertuj(JFrame glowneOkno) {
		super(glowneOkno, "Konwersja", true);
		setSize(500, 300);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		try {
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
//		}
		plikOrfolder = new JLabel("Plik/Folder:");
		plikOrfolder.setBounds(10, 30, 100, 20);
		add(plikOrfolder);
		
		bWybierz = new JButton("Przegladaj...");
		bWybierz.setBounds(130, 30, 100, 20);
		add(bWybierz);
		bWybierz.addActionListener(this);
		
		bKonwertuj = new JButton("Konwertuj!");
		bKonwertuj.setBounds(130, 120, 100, 40);
		add(bKonwertuj);
		bKonwertuj.addActionListener(this);
		
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
			    if(source == bWybierz) {
			    	fc = new JFileChooser();
					fc.setDialogTitle("wybierz plik");
					fc.setCurrentDirectory(new File("D:\\workspace"));
					
					if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						plik = fc.getSelectedFile();
						sciezka = plik.getAbsolutePath();					
						JOptionPane.showMessageDialog(this, sciezka);
					}
			    }	
			    else if(source == bKonwertuj) {
					FileCompareManager fcm = new FileCompareManager();
					fcm.konwertujArkuszDoCsv(plik);
					JOptionPane.showMessageDialog(this, "Konwersja pliku " +plik.getName() + " ukonczona pomyslnie!");		
				}
		}
		else if(rbFolder.isSelected()) {
			if(source == bWybierz) {
		    	JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("wybierz folder");
				fc.setCurrentDirectory(new File("D:\\workspace"));
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setAcceptAllFileFilterUsed(false);
				
				if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					plik = fc.getSelectedFile();
					sciezka = plik.getAbsolutePath();
					JOptionPane.showMessageDialog(null, sciezka);
				}
		    }
			else if(source == bKonwertuj) {
				FileCompareManager fcm = new FileCompareManager();
				katalog = new File(sciezka);
				File zawartosc[] = katalog.listFiles();
				for(File f : zawartosc) 
					fcm.konwertujArkuszDoCsv(f.getAbsoluteFile());
				JOptionPane.showMessageDialog(null, "Konwersja ukoñczona pomyœlnie!");
			}
		}
	}

}
