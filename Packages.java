package C2C_Packaging;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.scene.control.ComboBox;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import java.awt.List;
import java.awt.TextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import javax.swing.JTextArea;

public class Packages extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea returnedText;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Packages frame = new Packages();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Packages() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 454);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(182, 149, 11));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(23, 68, 196, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setMaximumRowCount(2);
		comboBox.setBounds(23, 105, 97, 22);
		comboBox.addItem("Recent"); 
		comboBox.addItem("Older"); 
	
		contentPane.add(comboBox);
		
		JButton btn = new JButton("Search");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnSearch();
			}
		});
		btn.setBounds(229, 70, 89, 23);
		contentPane.add(btn);
		
		returnedText = new JTextArea();
		returnedText.setBounds(23, 138, 295, 101);
		contentPane.add(returnedText);
		
		
		
	}
	public void returnSearch() {
		returnedText.setText("");
		String resultList = "";
		if(textField.getText().equals("")) {
			resultList = resultList + "All packages";
		}
		else {
			resultList = resultList + textField.getText();
		}
		resultList = resultList + "\n Sorted by: ";
		resultList = resultList + comboBox.getItemAt(comboBox.getSelectedIndex());
		returnedText.setText(resultList);
		textField.setText("");
	}
}
