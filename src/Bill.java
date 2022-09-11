import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.awt.print.PrinterException;

import javax.swing.*;

public class Bill extends JFrame implements ActionListener{
	
	static final int FRAME_WIDTH = 300;
	static final int FRAME_HEIGHT = 620;
	
	JButton printButton;
	JButton exitButton;
	
	JTextArea textArea;
	
	Bill(){
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		
		textArea = new JTextArea();
		textArea.setBounds(20,20,FRAME_WIDTH-20,480);
		textArea.setBackground(Color.white);
		
		printButton = new JButton("Print");
		printButton.setBounds(0, 500, FRAME_WIDTH, 35);
		printButton.setFocusable(false);
		printButton.setFont(new Font("Ink Free",Font.BOLD,18));
		printButton.addActionListener(this);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(0, 540, FRAME_WIDTH, 35);
		exitButton.setFocusable(false);
		exitButton.setFont(new Font("Ink Free",Font.BOLD,18));
		exitButton.addActionListener(this);
		
		this.add(textArea);
		this.add(printButton);
		this.add(exitButton);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitButton) {
			int confirm = JOptionPane.showConfirmDialog(this, "Are you sure to exit ?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if(confirm == JOptionPane.YES_OPTION) {
				//System.exit(0);
				this.dispose();
			}
		}
		if(e.getSource() == printButton) {
			try {
				textArea.print();
			} catch (PrinterException e1) {
				JOptionPane.showMessageDialog(this, "Printing failed", "ERROR", JOptionPane.OK_OPTION);;
			}
		}
		
	}
}
