/**
* @author Andrew Gulla (100395486)
* @section DESCRIPTION
* @version 1.0
* @file Table.java
* This program displays the data.txt
* file using the JTable class
*/

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.*;
import java.io.*;

public class TableVisual {
	
	public static class MainWindow extends JFrame implements ActionListener {
		
		//declares container where everything is stored
		//JPanel is declared to store table and enable ScrollPane
		//data string contains all countries info
		//col string contains names of columns
		public Container container;
		public JButton exit;
		public JTable table;
		public String[][] data;
		public String[] col = {"USA", "Canada", "Mexico"};
		public JPanel panel;
				
		public MainWindow() {
		
			data = new String[51][3];//sets dimensions of array to be put into table
		
			File file = new File("data.txt"); //reads file data.txt
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			DataInputStream dis = null;//sets up a Data input stream
			
			try {
				fis = new FileInputStream(file);  	
				bis = new BufferedInputStream(fis);
				dis = new DataInputStream(bis);//initializes a data input stream
				
				dis.readLine();//skips USA string as it is not needed in data
				for (int i = 0; i < 51; i++){
				data[i][0] = dis.readLine();
				}
				dis.readLine();//skips Canada string as it is not needed in data
				for (int i = 0; i < 13; i++){
				data[i][1] = dis.readLine();
				}
				dis.readLine();//skips Mexico string as it is not needed in data
				for (int i = 0; i < 30; i++){
				data[i][2] = dis.readLine();
				}
				
				fis.close();
				bis.close();
				dis.close();//closes input streams

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}// end of try block
			
			table = new JTable(data,col);//initializes new table to contain data and columns
			table.setPreferredScrollableViewportSize(new Dimension(450, 600));//sets area that needs to be scrolled in table
			
			panel = new JPanel();//initializes new JPanel that table will be inside
			panel.setLayout(new GridLayout(2, 1));
			panel.add(new JScrollPane(table));//Adds scroll pane to table that is added all into a JPanel
			
			exit = new JButton("EXIT");
			exit.addActionListener(this);
			panel.add(exit);

			container = getContentPane();
			container.setLayout(new BorderLayout());
			container.add(panel,BorderLayout.CENTER);//Adds panel containing scrollable table to container and centers it	
		}//end of MainWindow method
		
		public void actionPerformed(ActionEvent e){//if exit button is clicked, will exit program
			
			if (e.getSource() == exit){
				System.exit(0);
			}
		}
	}//end of MainWindow class
	
	public static void main(String[] args) {
		
		JFrame test = new MainWindow();//initializes new instance of MainWindow
		test.setSize(600, 800);//sets MainWindow resolution
		test.setVisible(true);//Allows user to see class.
	}
}
