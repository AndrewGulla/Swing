/**
* @author Andrew Gulla (100395486)
* @section DESCRIPTION
* @version 1.0
* @file TreeVisual.java
* This program displays the data.txt
* file using the JTree class
*/

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.tree.*;
import java.util.*;
import java.io.*;

public class TreeVisual {
	
	public static class MainWindow extends JFrame implements ActionListener{
		
		public Container container;
		public JPanel panel;
		public JButton exit;
		public JTree tree;
		public DefaultMutableTreeNode[] states, provinces, mexProvince;//initializes array of tree nodes for each country
		
		public MainWindow() {
			
			//Initializes the amount of nodes each country will contain
			states = new DefaultMutableTreeNode[51];
			provinces = new DefaultMutableTreeNode[13];
			mexProvince = new DefaultMutableTreeNode[30];
		
			File file = new File("data.txt"); //reads file data.txt
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			DataInputStream dis = null;//sets up a Data input stream
	
			DefaultMutableTreeNode country = new DefaultMutableTreeNode("Country", true);//Parent node country
			DefaultMutableTreeNode usa = new DefaultMutableTreeNode("USA");//a node for the country USA
			DefaultMutableTreeNode canada = new DefaultMutableTreeNode("Canada");//a node for the country Canada
			DefaultMutableTreeNode mexico = new DefaultMutableTreeNode("Mexico");//a node for the country Mexico	
			
			//makes each country  a child node of the parent node
			country.add(usa);
			country.add(canada);
			country.add(mexico);
			
			try {
				fis = new FileInputStream(file);  	
				bis = new BufferedInputStream(fis);
				dis = new DataInputStream(bis);//initializes a data input stream
				
				dis.readLine();//skips the first line that contains "USA"
				for(int i = 0; i < 51; i++){//goes through all 51 states
					String tString = dis.readLine();//assigns the name of the state to a string
					states[i] = new DefaultMutableTreeNode(tString);//makes the first state node the name of the string
					usa.add(states[i]);//adds the state node to the USA parent node
				}
				dis.readLine();//skips the "Canada" line
				for(int j = 0; j < 13; j++){
					String tString = dis.readLine();//assigns province name to a string
					provinces[j] = new DefaultMutableTreeNode(tString);//uses string to make that province a node
					canada.add(provinces[j]);//adds node to parent Canada node.
				}
				dis.readLine();//skips "Mexico" line
				for(int z = 0; z < 30; z++){
					String tString = dis.readLine();//assigns a string for each province
					mexProvince[z] = new DefaultMutableTreeNode(tString);//makes a node of name of the string
					mexico.add(mexProvince[z]);//adds nose to parent node Mexico
				}
				
				fis.close();
				bis.close();
				dis.close();//closes input streams

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}// end of try block
			
			tree = new JTree(country);
			
			panel = new JPanel();
			panel.setLayout(new GridLayout(2, 1));
			panel.add(new JScrollPane(tree));
			
			exit = new JButton("EXIT");
			exit.addActionListener(this);
			panel.add(exit);
			
			container = getContentPane();
			container.setLayout(new BorderLayout());
			container.add(panel,BorderLayout.CENTER);
		}//end of MainWindow method
		
		public void actionPerformed(ActionEvent e){//if exit button is clicked, will exit program
			
			if (e.getSource() == exit){
				System.exit(0);
			}
		}
	}//end of MainWindow class
	
	public static void main(String[] args) {
		
		JFrame test = new MainWindow();//initializes new instance of MainWindow
		test.setSize(300, 800);//sets MainWindow resolution
		test.setVisible(true);//Allows user to see class.
	}
}
