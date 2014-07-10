package com;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class DialogHelp  {

	

	String[] columnNames = {"Button",
            "Action"};
	
	String[][] data = {
	        {"UP", "fly forward"},
	        {"DOWN", "fly backward"},
	        {"RIGHT", "fly right"},
	        {"LEFT", "fly left"},
	        {"RIGHT+SHIFT", "turn to right"},
	        {"LEFT+SHIFT", "turn to left"},
	        {"U", "fly up"},
	        {"D", "fly down"},
	        {"E", "reset"},
	        {"ENTER", "take off"},
	        {"SPACE", "landing"},
	        };
	
	 JTable jTabPeople;
	 
	 public DialogHelp(){
		 JFrame jfrm = new JFrame("Control");
	        
	        jfrm.getContentPane().setLayout(new FlowLayout());
	        
	        jfrm.setSize(300, 250);
	       
	        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        jTabPeople = new JTable(data, columnNames);
	       
	        JScrollPane jscrlp = new JScrollPane(jTabPeople);
	        
	        jTabPeople.setPreferredScrollableViewportSize(new Dimension(250,300) );
	        
	        jfrm.getContentPane().add(jscrlp);
	      
	       // jTabPeople.isCellEditable(false);
	        
	        jfrm.setVisible(true);
	        
	       
		 
	 }
	 
	 
	 
	public static void main(String[] args) {
		
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DialogHelp();
            }
        });
	
}
	
	
	
}

