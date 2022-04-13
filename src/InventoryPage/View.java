package InventoryPage;

//import org.json.JSONArray;
//import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class View extends JFrame {
    private JPanel panelLeft;
    private JPanel panelRight;
    private JPanel panelMain;
    private JList listPeople;
    //JScrollPane scrollPane = new JScrollPane(listPeople);
    private JTextField playerName;
    private JTextField cardNumber;
    private JTextField quantity;
    private JTextField cardAge;
    private JButton buttonSaveExisting;
    private JButton buttonSaveNew;
    private JLabel labelPhoto;
    private JPanel panelPhoto;
    private JButton buttonRemove;
    private JTextField SearchField;
    private ArrayList<Person> people;
    private ArrayList<String> peopleNames;
    private DefaultListModel listPeopleModel;



    View(String title) {
//        super(title);
//        people = new ArrayList<Person>();
//        this.setContentPane(this.panelMain);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.pack();
//        this.setSize(1000,400);
//        listPeopleModel = new DefaultListModel();
//        listPeople.setModel(listPeopleModel);
//        buttonSaveExisting.setEnabled(false);
//        loadPeopleFile();
//
//
//        buttonSaveExisting.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                buttonSaveExistingClicked(e);
//            }
//        });
//
//        buttonSaveNew.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                buttonSaveNewClicked(e);
//            }
//        });
//
//        buttonRemove.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                buttonRemoveExistingClicked(e);
//            }
//        });
//
//        listPeople.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                listPeopleSelection(e);
//            }
//        });
    }

//    private void buttonSaveExistingClicked(ActionEvent e) {
//        int personNumber = listPeople.getSelectedIndex();
//        if (personNumber >= 0) {
//            Person p = people.get(personNumber);
//            p.setName( playerName.getText() );
//            p.setCardNumber( cardNumber.getText() );
//            p.setQuantity( quantity.getText() );
//            refreshInventoryList();
//            savePeopleFile();
//            System.out.println(p.toString());
//        } else {
//            System.out.println("Can only save existing if an item is selected in the list");
//        }
//    }
//
//    private void buttonRemoveExistingClicked(ActionEvent e) {
//        int personNumber = listPeople.getSelectedIndex();
//        if (personNumber >= 0) {
//            Person p = people.get(personNumber);
//            people.remove(p);
//            refreshInventoryList();
//            savePeopleFile();
//            System.out.println(p.toString());
//        } else {
//            System.out.println("Removed!");
//        }
//    }
//
//
//    private void buttonSaveNewClicked(ActionEvent e) {
//        Person p = new Person( playerName.getText(), cardNumber.getText(), quantity.getText());
//        people.add(p);
//        savePeopleFile();
//        refreshInventoryList();
//    }
//
//    private void listPeopleSelection(ListSelectionEvent e) {
//        int personNumber = listPeople.getSelectedIndex();
//        if (personNumber >= 0) {
//            Person p = people.get(personNumber);
//            playerName.setText(p.getName());
//            cardNumber.setText(p.getCardNumber());
//            quantity.setText(p.getQuantity());
//            buttonSaveExisting.setEnabled(true);
//        }
//    }
//
//    //--------------------------------------------------------------
//    public void filterModel(DefaultListModel<String> model, String filter) {
//
//        // For each element in listPeopleModel...
//        // p.getName() returns string of PLAYER NAME
//        // Check if string S is contained in the model
//        // Update model accordingly
//
//        // Maybe create second array list to determine
//        for (String s : peopleNames) {
//            if (!s.startsWith(filter)) {
//                if (model.contains(s)) {
//                    model.removeElement(s);
//                }
//            } else {
//                if (!model.contains(s)) {
//                    model.addElement(s);
//                }
//            }
//        }
//    }
//
//    private ListModel<String> createDefaultListModel() {
//        DefaultListModel<String> model = new DefaultListModel<>();
//        for (String s : peopleNames) {
//            model.addElement(s);
//        }
//        return model;
//    }
//
//    //--------------------------------------------------------------
//
//    public void refreshInventoryList() {
//        System.out.println("[refreshPeopleList] With "+Integer.toString(people.size())+" entries");
//        listPeopleModel.removeAllElements();
//        for (Person p : people) {
//            listPeopleModel.addElement(p.getName());
//        }
//    }
//
//    public void addPerson(Person p) {
//        System.out.println("[addPerson] Adding "+p.getName());
//        people.add(p);
//        refreshInventoryList();
//    }
//
//    public void removePerson(Person p) {
//        System.out.println("[addPerson] Removing "+p.getName());
//        people.remove(p);
//        refreshInventoryList();
//    }
//
//    private boolean saveFileFromString(String filename, String data) {
//        try {
//            FileWriter fw = new FileWriter(filename);
//            PrintWriter output = new PrintWriter(fw);
//            output.println( data );
//            output.close();
//            fw.close();
//            return true;
//        } catch(Exception e) {
//            System.out.println("[saveFileFromString] error saving "+filename);
//            return false;
//        }
//    }
//
//    private boolean displayIcon(String filename, JLabel target) {
//        try {
////            ImageIcon icon = new ImageIcon(filename);
//            // Resize the icon to fit the target JLabel
//            ImageIcon icon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(target.getWidth(), target.getHeight(), Image.SCALE_DEFAULT));
//            target.setIcon(icon);
//            return true;
//        } catch (Exception e) {
//            target.setIcon(null);
//            return false;
//        }
//    }
//
//    private void savePeopleFile() {
//        // Convert the ArrayList of Person objects into a JSONArray of JSONObjects
//        JSONArray ja = new JSONArray();
//        for (Person p : people) {
//            JSONObject jo = new JSONObject(p);
//            ja.put(jo);
//        }
//        // Save the JSONArray to a text file
//        saveFileFromString("inventory.json", ja.toString());
//    }
//
//    private String loadFileToString(String filename) {
//        StringBuilder sb = new StringBuilder();
//        try {
//            // Load the JSON file as String data
//            File f = new File(filename);
//            Scanner reader = new Scanner(f);
//            while (reader.hasNextLine()) {
//                sb.append(reader.nextLine());
//            }
//            reader.close();
//            return sb.toString();
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    private void loadPeopleFile() {
//        String filename = "inventory.json";
//        String fileData = loadFileToString(filename);
//        if (fileData != null) {
//            // Create a JSONArray from the String
//            JSONArray ja = new JSONArray(fileData);
//            // Iterate over the JSONObjects within the JSONArray
//            for (int i=0; i< ja.length(); i++) {
//                JSONObject o = ja.getJSONObject(i);
//                // Use each JSONObject to create a Person object
//                Person p = new Person(o.getString("name"),
//                        o.getString("cardNumber"),
//                        o.getString("quantity"));
//                // Add the Person object to the ArrayList of people
//                people.add(p);
//            }
//        }
//        // Refresh the list
//        refreshInventoryList();
//    }
//
//    public static void printLookAndFeels() {
//        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
//        for (UIManager.LookAndFeelInfo look : looks) {
//            System.out.println(look.getClassName());
//        }
//    }


    public static void main(String[] args) {
//        printLookAndFeels();
//        View view = new View("Inventory Page 2.0");
//        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//        } catch (Exception e) {
//
//        }
//        view.setVisible(true);
    }

}
