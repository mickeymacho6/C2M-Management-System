import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultRowSorter;
import javax.swing.table.TableRowSorter;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class search_class {
	private static JTextField subject_field;
	private static JTextField professor_field;
	private static JTextField units_field;
	private static JTextField schedule_field;
	private static JTextField lname_field;
	private static JTextField fname_field;
	private static JTextField search_subject_field;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JTable search_class_table = new JTable();
		Object[] columns = {"Course", "Schedule", "Professor", "Units"};
		DefaultTableModel model = new DefaultTableModel();
		
		JFrame search_class_frame = new JFrame("Search class");
		search_class_frame.getContentPane().setBackground(Color.WHITE);
		search_class_frame.getContentPane().setForeground(Color.WHITE);
		search_class_frame.setBounds(100, 100, 962, 710);
		search_class_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		search_class_frame.getContentPane().setLayout(null);
		search_class_frame.setLocationRelativeTo(null);
		
		model.setColumnIdentifiers(columns);
		search_class_table.setModel(model);
		
		search_class_table.setBackground(Color.white);
		search_class_table.setForeground(Color.black);
		search_class_table.setGridColor(Color.gray);
		search_class_table.setSelectionForeground(Color.white);
		search_class_table.setRowHeight(30);
		search_class_table.setAutoCreateRowSorter(true);
		
		JScrollPane pane = new JScrollPane(search_class_table);
		pane.setForeground(Color.RED);
		pane.setBackground(Color.WHITE);
		pane.setBounds(128, 27, 667, 298);
		search_class_frame.getContentPane().add(pane);
		
		JLabel add_class_label = new JLabel("ADD CLASS");
		add_class_label.setBounds(197, 336, 169, 14);
		search_class_frame.getContentPane().add(add_class_label);
		
		JLabel search_prof_label = new JLabel("Search by Professor");
		search_prof_label.setBounds(574, 516, 169, 14);
		search_class_frame.getContentPane().add(search_prof_label);
		
		JLabel first_name_label = new JLabel("Professor's first name");
		first_name_label.setBounds(467, 547, 168, 14);
		search_class_frame.getContentPane().add(first_name_label);
		
		JLabel last_name_label = new JLabel("Professor's last name");
		last_name_label.setBounds(467, 575, 168, 14);
		search_class_frame.getContentPane().add(last_name_label);
		
		Object[] row = new Object[4];
		
		JButton add_class_button = new JButton("Add class");
		add_class_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				row[0] = subject_field.getText();
				row[1] = schedule_field.getText();
				row[2] = professor_field.getText();
				row[3] = units_field.getText();
				
				model.addRow(row);
			}
		});
		add_class_button.setBounds(50, 619, 136, 23);
		search_class_frame.getContentPane().add(add_class_button);
		
		JButton delete_button = new JButton("Delete class");
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i = search_class_table.getSelectedRow();
				
				if (i >= 0) {
					model.removeRow(i);	
					
				}
				//
				else JOptionPane.showMessageDialog(search_class_frame, "Delete error");
			}
		});
		delete_button.setBounds(235, 619, 131, 23);
		search_class_frame.getContentPane().add(delete_button);
		
		subject_field = new JTextField();
		subject_field.setBounds(149, 404, 169, 20);
		search_class_frame.getContentPane().add(subject_field);
		subject_field.setColumns(10);
		
		professor_field = new JTextField();
		professor_field.setBounds(149, 513, 169, 20);
		search_class_frame.getContentPane().add(professor_field);
		professor_field.setColumns(10);
		
		units_field = new JTextField();
		units_field.setBounds(149, 572, 169, 20);
		search_class_frame.getContentPane().add(units_field);
		units_field.setColumns(10);
		
		JLabel enter_subject_label = new JLabel("Enter subject");
		enter_subject_label.setBounds(197, 381, 89, 14);
		search_class_frame.getContentPane().add(enter_subject_label);
		
		JLabel enter_professor_label = new JLabel("Enter professor");
		enter_professor_label.setBounds(197, 491, 115, 14);
		search_class_frame.getContentPane().add(enter_professor_label);
		
		JLabel enter_units_label = new JLabel("Enter units");
		enter_units_label.setBounds(197, 547, 103, 14);
		search_class_frame.getContentPane().add(enter_units_label);
		
		schedule_field = new JTextField();
		schedule_field.setBounds(149, 460, 169, 20);
		search_class_frame.getContentPane().add(schedule_field);
		schedule_field.setColumns(10);
		
		JLabel schedule_label = new JLabel("Enter schedule");
		schedule_label.setBounds(197, 435, 115, 14);
		search_class_frame.getContentPane().add(schedule_label);
		
		lname_field = new JTextField();
		lname_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				DefaultTableModel modelTwo = (DefaultTableModel)search_class_table.getModel();
				TableRowSorter<DefaultTableModel> table_row_two = new TableRowSorter<DefaultTableModel>(modelTwo);
				search_class_table.setRowSorter(table_row_two);
				table_row_two.setRowFilter(RowFilter.regexFilter(lname_field.getText().trim()));
			}
		});
		lname_field.setText("");
		lname_field.setBounds(613, 572, 169, 20);
		search_class_frame.getContentPane().add(lname_field);
		lname_field.setColumns(10);
		
		fname_field = new JTextField();
		fname_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				
				DefaultTableModel modelTwo = (DefaultTableModel)search_class_table.getModel();
				TableRowSorter<DefaultTableModel> table_row = new TableRowSorter<DefaultTableModel>(modelTwo);
				search_class_table.setRowSorter(table_row);
				table_row.setRowFilter(RowFilter.regexFilter(fname_field.getText().trim()));
			}
		});
		fname_field.setBounds(613, 544, 169, 20);
		search_class_frame.getContentPane().add(fname_field);
		fname_field.setColumns(10);
		
		JLabel class_label = new JLabel("SEARCH CLASS");
		class_label.setBounds(574, 336, 154, 14);
		search_class_frame.getContentPane().add(class_label);
		
		search_subject_field = new JTextField();
		search_subject_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				DefaultTableModel modelThree = (DefaultTableModel)search_class_table.getModel();
				TableRowSorter<DefaultTableModel> table_row_three = new TableRowSorter<DefaultTableModel>(modelThree);
				search_class_table.setRowSorter(table_row_three);
				table_row_three.setRowFilter(RowFilter.regexFilter(search_subject_field.getText().trim()));
				
			}
		});
		search_subject_field.setBounds(613, 406, 169, 20);
		search_class_frame.getContentPane().add(search_subject_field);
		search_subject_field.setColumns(10);
		
		JLabel search_subject_enter_field = new JLabel("Enter subject");
		search_subject_enter_field.setBounds(467, 409, 103, 14);
		search_class_frame.getContentPane().add(search_subject_enter_field);
		
		JLabel search_subject_label = new JLabel("Search class by subject");
		search_subject_label.setBounds(574, 378, 154, 14);
		search_class_frame.getContentPane().add(search_subject_label);
		
		JLabel or_label = new JLabel("OR");
		or_label.setBounds(589, 463, 46, 14);
		search_class_frame.getContentPane().add(or_label);
		
		
		search_class_frame.revalidate();
		search_class_frame.setVisible(true);
		
	}
}
