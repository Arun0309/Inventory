import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Inventory {

	private JFrame frame;
	private JTextField searchBar;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPanel Main;
	private JPanel Save;
	private Scanner read;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory window = new Inventory();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

	}

	/**
	 * Create the application.
	 */
	public Inventory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		OpenFiles open = new OpenFiles();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 603);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel Main = new JPanel();
		frame.getContentPane().add(Main, "name_285774075751044");
		Main.setVisible(true);
		
		JPanel Save = new JPanel();
		frame.getContentPane().add(Save, "name_285777120975930");
		Save.setVisible(false);
		Main.setLayout(null);
		
		searchBar = new JTextField();
		searchBar.setBounds(24, 26, 651, 36);
		searchBar.setHorizontalAlignment(SwingConstants.LEFT);
		Main.add(searchBar);
		searchBar.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 76, 651, 477);
		Main.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton itemBtn = new JButton("New Item");
		itemBtn.setBounds(685, 74, 89, 30);
		itemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setVisible(false);
				Save.setVisible(true);
			}
		});
		Main.add(itemBtn);
		Save.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(145, 63, 241, 36);
		Save.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(145, 110, 241, 38);
		Save.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(145, 159, 241, 36);
		Save.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel itemName = new JLabel("Item Name");
		itemName.setBounds(54, 66, 59, 14);
		Save.add(itemName);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(67, 113, 46, 14);
		Save.add(lblQuantity);
		
		JLabel lblModel = new JLabel("Model #");
		lblModel.setBounds(67, 162, 46, 14);
		Save.add(lblModel);
		
		open.createFile();
		
		ArrayList<String> words = open.readFromFile("Data.txt");
		
		String[] wordsToSearch = new String[words.size()];
		words.toArray(wordsToSearch);
		
		textArea.setText(open.format(words));
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 16));
		textArea.setSelectionStart(0);
		textArea.setSelectionEnd(0);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Save.setVisible(false);
				Main.setVisible(true);
				String Name = textField.getText();
				String Quantity = textField_1.getText();
				String Mod = textField_2.getText();
				words.add(Name);
				words.add(Mod);
				words.add(Quantity);
				open.saveToFile("Data.txt", words);
				textArea.setText(open.format(words));
			}
		});
		btnDone.setBounds(365, 447, 89, 36);
		Save.add(btnDone);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = searchBar.getText();
				for(int i=0;i<wordsToSearch.length;i++){
				    if(wordsToSearch[i].equals(search)){
				      String a = wordsToSearch[i];
				      String b = wordsToSearch[i+1];
				      String c = wordsToSearch[i+2];
				      textArea.setText("Name: "+a+" Model#: "+b+" Quantity: "+c);
				    }
				}
			}
		});
		searchBtn.setBounds(685, 29, 89, 30);
		Main.add(searchBtn);
	}
}
