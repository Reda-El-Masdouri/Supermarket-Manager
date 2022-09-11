import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class MyFrame extends JFrame implements ActionListener, KeyListener{
	
	static final int FRAME_WIDTH = 800;
	static final int FRAME_HEIGHT = 600;
	
	ArrayList<Product> products;
	
	JPanel allProductPanel;
	JPanel selectedProductPanel;
	JPanel optionPanel;
	JPanel propertiesPanel;
	
	JLabel searchProductLabel;
	JTextField searchProductField;
	
	JLabel totalLabel;
	JLabel amountLabel;
	
	
	DefaultTableModel productTableModel;
	DefaultTableModel selectedProductTableModel;
	JTable productsTable;
	JTable selectedProductTable;
	
	JButton productChoosenButton;
	JButton productRemovedButton;
	JButton deleteButton;
	JButton payButton;
	
	JButton addButton;
	JButton modifyButton;
	JButton searchButton;
	JButton clearButton;
	JButton saveButton;
	JButton exitButton;
	
	Border textFieldBorder1 = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY);
	Border emptyBorder = BorderFactory.createEmptyBorder();
	
	MyFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		//this.setBackground(Color.white);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		this.setUndecorated(true);
		
		products = new ArrayList();
		
		searchProductLabel = new JLabel("Search :");
		searchProductLabel.setBounds(10, 10, 70, 30);
		searchProductLabel.setFont(new Font("Consolas",Font.PLAIN,16));
		searchProductLabel.setForeground(Color.black);
		
		searchProductField = new JTextField();
		searchProductField.setBounds(90, 10, 200, 30);
		searchProductField.setFont(new Font("Consolas",Font.PLAIN,16));
		searchProductField.setForeground(Color.black);
		searchProductField.setBackground(Color.LIGHT_GRAY);
		searchProductField.setBorder(textFieldBorder1);
		searchProductField.addKeyListener(this);
		
		productTableModel = new DefaultTableModel();
		productTableModel.addColumn("Product");
		productTableModel.addColumn("Price");
		productsTable = new JTable(productTableModel);
		JScrollPane productsScrollPanel = new JScrollPane(productsTable);
		productsScrollPanel.setBounds(10, 45, 280, 350);
		//productsTable.setBackground(Color.yellow);
		
		allProductPanel = new JPanel();
		allProductPanel.setBounds(50, 80, 300, 400);
		allProductPanel.setLayout(null);
		allProductPanel.setBackground(Color.white);
		allProductPanel.add(searchProductLabel);
		allProductPanel.add(searchProductField);
		allProductPanel.add(productsScrollPanel);
		
		selectedProductTableModel = new DefaultTableModel();
		selectedProductTableModel.addColumn("Product");
		selectedProductTableModel.addColumn("Price");
		selectedProductTable = new JTable(selectedProductTableModel);
		JScrollPane selectedProductScrollPanel = new JScrollPane(selectedProductTable);
		selectedProductScrollPanel.setBounds(10, 10, 230, 280);
		//selectedProductTable.setBackground(Color.yellow);
		
		totalLabel = new JLabel("Total :");
		totalLabel.setBounds(10, 330, 70, 20);
		totalLabel.setFont(new Font("Ink Free", Font.BOLD, 18));
		totalLabel.setForeground(Color.black);
		
		amountLabel = new JLabel("0.00");
		amountLabel.setBounds(90, 320, 100, 30);
		amountLabel.setHorizontalAlignment(JTextField.CENTER);
		amountLabel.setFont(new Font("Ink Free", Font.BOLD, 18));
		amountLabel.setBackground(new Color(153,255,153));
		amountLabel.setForeground(Color.black);
		amountLabel.setOpaque(true);
		
		selectedProductPanel = new JPanel();
		selectedProductPanel.setBounds(500, 80, 250, 370);
		selectedProductPanel.setLayout(null);
		selectedProductPanel.setBackground(Color.white);
		selectedProductPanel.add(totalLabel);
		selectedProductPanel.add(amountLabel);
		selectedProductPanel.add(selectedProductScrollPanel);
		
		
		productChoosenButton = new JButton();
		productChoosenButton.setFocusable(false);
		productChoosenButton.setBounds(15, 50, 75, 50);
		productChoosenButton.setIcon(new ImageIcon(new ImageIcon("./right.png").getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT)));
		productChoosenButton.setBackground(Color.white);
		productChoosenButton.setBorder(emptyBorder);
		productChoosenButton.addActionListener(this);
		
		productRemovedButton = new JButton();
		productRemovedButton.setFocusable(false);
		productRemovedButton.setBounds(15, 125, 75, 50);
		productRemovedButton.setIcon(new ImageIcon(new ImageIcon("./left.png").getImage().getScaledInstance(100, 50, Image.SCALE_DEFAULT)));
		productRemovedButton.setBackground(Color.white);
		productRemovedButton.setBorder(emptyBorder);
		productRemovedButton.addActionListener(this);
		
		deleteButton = new JButton("Delete");
		deleteButton.setFont(new Font("Ink Free",Font.BOLD,18));
		deleteButton.setForeground(Color.black);
		deleteButton.setFocusable(false);
		deleteButton.setBounds(0, 200, 100, 50);
		deleteButton.addActionListener(this);
		
		payButton = new JButton("Pay");
		payButton.setFont(new Font("Ink Free",Font.BOLD,18));
		payButton.setForeground(Color.black);
		payButton.setFocusable(false);
		payButton.setBounds(0, 275, 100, 50);
		payButton.addActionListener(this);
		
		optionPanel = new JPanel();
		optionPanel.setBounds(375, 80, 100, 400);
		optionPanel.setLayout(null);
		optionPanel.setBackground(Color.white);
		optionPanel.add(productChoosenButton);
		optionPanel.add(productRemovedButton);
		optionPanel.add(deleteButton);
		optionPanel.add(payButton);
		
		addButton = new JButton("Add");
		addButton.setBounds(0, 0, 100, 50);
		addButton.setFocusable(false);
		addButton.setFont(new Font("Ink Free",Font.BOLD,18));
		addButton.setForeground(Color.black);
		addButton.addActionListener(this);
		
		modifyButton = new JButton("Modify");
		modifyButton.setBounds(100, 0, 100, 50);
		modifyButton.setFocusable(false);
		modifyButton.setFont(new Font("Ink Free",Font.BOLD,18));
		modifyButton.setForeground(Color.black);
		modifyButton.addActionListener(this);
		
		searchButton = new JButton("Search");
		searchButton.setBounds(200, 0, 100, 50);
		searchButton.setFocusable(false);
		searchButton.setFont(new Font("Ink Free",Font.BOLD,18));
		searchButton.setForeground(Color.black);
		searchButton.addActionListener(this);
		
		clearButton = new JButton("Clear");
		clearButton.setBounds(300, 0, 100, 50);
		clearButton.setFocusable(false);
		clearButton.setFont(new Font("Ink Free",Font.BOLD,18));
		clearButton.setForeground(Color.black);
		clearButton.addActionListener(this);
		
		saveButton = new JButton("Save");
		saveButton.setBounds(400, 0, 100, 50);
		saveButton.setFocusable(false);
		saveButton.setFont(new Font("Ink Free",Font.BOLD,18));
		saveButton.setForeground(Color.black);
		saveButton.addActionListener(this);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(500, 0, 100, 50);
		exitButton.setFocusable(false);
		exitButton.setFont(new Font("Ink Free",Font.BOLD,18));
		exitButton.setForeground(Color.black);
		exitButton.addActionListener(this);
		
		propertiesPanel = new JPanel();
		propertiesPanel.setBounds(50, 500, 600, 50);
		propertiesPanel.setLayout(null);
		propertiesPanel.add(addButton);
		propertiesPanel.add(modifyButton);
		propertiesPanel.add(searchButton);
		propertiesPanel.add(clearButton);
		propertiesPanel.add(saveButton);
		propertiesPanel.add(exitButton);
		
		fillProductsTable();
		
		this.add(optionPanel);
		this.add(allProductPanel);
		this.add(selectedProductPanel);
		this.add(propertiesPanel);
		this.setVisible(true);
	}
	
	private void fillProductsTable() {
		File file = new File("file.txt");
		try {
			Scanner scanner = new Scanner(file);
			
			boolean isName = true;
			String productName ="", productPrice ="";
			while(scanner.hasNext()) { // we have data to write content by content
				
				if(isName) {
					productName = scanner.next();
					isName = false;
				}
				else {
					productPrice = scanner.next();
					Product product = new Product(productName,productPrice);
					products.add(product);
					productTableModel.addRow(new String[]{productName,productPrice});
					isName = true;
				}
				
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "File not found", "ERROR", JOptionPane.OK_OPTION);;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addButton) {
			String productName = JOptionPane.showInputDialog(this, "Enter the product name", "Add product", JOptionPane.INFORMATION_MESSAGE);
			String productPrice = JOptionPane.showInputDialog(this, "Enter its price", "Add product", JOptionPane.INFORMATION_MESSAGE);
			// create new Product object, and add it in the array list products if the arguments are not null or empty
			Product product = new Product(productName,productPrice);
			if(!(productName == null || productName.equals("")|| productPrice == null || productPrice.equals(""))) {
				products.add(product);
				// print the product in the table of all products.
				productTableModel.addRow(new String[]{productName,productPrice});
			}
		}
		if(e.getSource()==clearButton) {
			int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to delete this product ?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				// we get the index of the selected product in the all products table
				int index = productsTable.getSelectedRow();
				if(index != -1) { // a row is selected
					products.remove(index); // remove the product from the array list
					productTableModel.removeRow(index); // remove it from the table 
				}
			}
		}
		if(e.getSource()==modifyButton) {
			int index = productsTable.getSelectedRow();
			if(index != -1) { // a row is selected
				String productName = JOptionPane.showInputDialog(this, "modify the product name", productTableModel.getValueAt(index, 0));
				String productPrice = JOptionPane.showInputDialog(this, "modify its price", productTableModel.getValueAt(index, 1));
				if(!(productName == null || productName.equals("")|| productPrice == null || productPrice.equals(""))) {
					productTableModel.setValueAt(productName, index, 0);
					productTableModel.setValueAt(productPrice, index, 1);
					products.get(index).setNameProduct(productName); // we change the values on the array list products
					products.get(index).setNameProduct(productPrice);
				}
			}
		}
		if(e.getSource()==searchButton) {
			String productName = JOptionPane.showInputDialog(this, "Type the name product", "Search", JOptionPane.QUESTION_MESSAGE);
			if(productName != null) { // we ignore the exception if press "Cancel"
			for(int i=0; i<products.size() ;i++) {
				Product p = products.get(i);
				if(productName.equalsIgnoreCase(p.getNameProduct())) {
					productsTable.setRowSelectionInterval(i, i);
					break;
				}
			}
			}
		}
		if(e.getSource()==saveButton) {
			File file = new File("file.txt");
			FileWriter fw = null;
			try {
				fw = new FileWriter(file);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this, "Impossible to write on the file", "ERROR", JOptionPane.OK_OPTION);;
			}
			PrintWriter pw = new PrintWriter(fw);
			for(Product p : products) {
				pw.println(p); // Product.toString
			}
			pw.close();
		}
		if(e.getSource()==productChoosenButton) {
			float totalAmount = Float.valueOf(amountLabel.getText());
			int index = productsTable.getSelectedRow();
			if(index != -1) {
				int quatity = Integer.valueOf(JOptionPane.showInputDialog(this, "How much units","1"));
				String productName = products.get(index).getNameProduct();
				float price = Float.valueOf(products.get(index).getPriceProduct())*quatity;
				selectedProductTableModel.addRow(new String[]{productName,String.valueOf(price)});
				totalAmount+=price;
				amountLabel.setText(String.valueOf(totalAmount));
			}
		}
		if(e.getSource()==deleteButton) {
			int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to cleare the table ?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				selectedProductTableModel.setRowCount(0);
				amountLabel.setText("0.00");
			}
			
		}
		if(e.getSource()==productRemovedButton) {
			int index = selectedProductTable.getSelectedRow();
			if(index != -1) {
				
				float priceSelectedProduct = Float.valueOf((String) selectedProductTableModel.getValueAt(index, 1));
				float totalAmount = Float.valueOf(amountLabel.getText()) - priceSelectedProduct;
				amountLabel.setText(String.valueOf(totalAmount));
				selectedProductTableModel.removeRow(index);
			}
		}
		if(e.getSource()==exitButton) {
			int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to exit ?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				this.dispose();
			}
		}
		if(e.getSource() == payButton) {
			String payedAmountString = "";
			while(payedAmountString.equals("")) {
				payedAmountString = JOptionPane.showInputDialog(this, "Enter the payed amount", "payment", JOptionPane.INFORMATION_MESSAGE);
			}
			float payedAmount = Float.valueOf(payedAmountString);
			if(payedAmount >= Float.valueOf(amountLabel.getText())) {
				String ticket = "****************************************************************\n\n";
				ticket += "	    ** Your Bill ** \n\n";
				ticket += "*************************************************************\n";
				Date date = new Date();
				ticket += date + "\n\n";
				ticket += "Product			Price\n";
				for(int i=0;i<selectedProductTableModel.getRowCount();i++) {
					ticket += selectedProductTableModel.getValueAt(i, 0)+"			"+selectedProductTableModel.getValueAt(i, 1)+"\n";
				}
				ticket += "\n\n";
				ticket += "Total: 				"+ amountLabel.getText()+"\n";
				ticket += "Payed amount: 		"+ String.valueOf(payedAmount)+"\n";
				float changeToGive = (payedAmount - Float.valueOf(amountLabel.getText()));
				ticket += "Change to give: 		"+changeToGive+"\n\n";
				ticket += "	** See you soon **";
				
				Bill bill = new Bill();
				bill.textArea.setText(ticket);
				bill.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(this, "You enter an amount less than total","ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// release each character of the name product to search
		String productName = searchProductField.getText();
		if(productName != null) { // we ignore the exception if press "Cancel"
		for(int i=0; i<products.size() ;i++) {
			Product p = products.get(i);
			if(productName.equalsIgnoreCase(p.getNameProduct())) {
				productsTable.setRowSelectionInterval(i, i);
				break;
			}
		}
		}
	}

}
