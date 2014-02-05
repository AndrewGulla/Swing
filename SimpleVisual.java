/**
* @author Andrew Gulla (100395486)
* @section DESCRIPTION
* @version 1.0
* @file SimpleVisual.java
* This program displays the data.txt
* file using JLabel and JButton classes
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class SimpleVisual {
	
	public static class MainWindow extends JFrame implements ActionListener {
		
		//declares container where everything is stored
		//JPanels for country buttons and panel containing states labels
		public Container container;
		public JPanel countries, states;
		public JButton usa, can, mex, exit;
		public JLabel stateLabels[];
		
		public MainWindow() {
			
			container = getContentPane();//sets new container 3 rows one column
			container.setLayout(new GridLayout(3, 1));
			
			states = new JPanel();//11 rows and 5 columns for JLabels to fit
			states.setLayout(new GridLayout(11, 5));
			
			countries = new JPanel();//country panel which will contain 3 buttons
			countries.setLayout(new GridLayout(1, 3));
			
			usa = new JButton("USA");//USA button
			usa.addActionListener(this);
			
			can = new JButton("Canada");//Canada button
			can.addActionListener(this);
			
			mex = new JButton("Mexico");//Mexico button
			mex.addActionListener(this);
			
			countries.add(usa);//Adds buttons to countries panel
			countries.add(can);
			countries.add(mex);
			
			stateLabels = new JLabel[51];//makes a max of 51 labels for data(max 51 for 51 states)
			
			for ( int i = 0; i < 51; i++)//initializes and adds all labels to the states panel
			{
				stateLabels[i] = new JLabel("");
				states.add(stateLabels[i]);
			}
			
			stateLabels[0].setText("No information to display.");//initializes default JLabel
			
			exit = new JButton("EXIT");//Exit button
			exit.addActionListener(this);
			
			
			container.add(countries);//adds panels to container all in order top to bottom
			container.add(states);
			container.add(exit);
		}
		
		public void actionPerformed(ActionEvent e){//if country button is clicked, will do specific readFile function
		
			if (e.getSource() == usa){
				readFile(1);
			}
			else if (e.getSource() == can){
				readFile(2);
			}
			else if (e.getSource() == mex){
				readFile(3);
			}
			else if (e.getSource() == exit){//Adds exit capability to exit button
				System.exit(0);
			}
		}	
		
		public void readFile(int x) {
			
			for(int j = 0; j < 51; j++){ //clearing all JLabels each time a new country button is clicked.
				stateLabels[j].setText("");
			}
			
			File file = new File("data.txt"); //reads file data.txt
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			DataInputStream dis = null;//sets up a Data input stream
			
			try {
				fis = new FileInputStream(file);  	
				bis = new BufferedInputStream(fis);
				dis = new DataInputStream(bis);//initializes a data input stream
      	
				if(x == 1){//IF USA BUTTON IS CLICKED
					dis.readLine();//skips first line containing "USA" string
					for(int i = 0; i < 51; i++){
						stateLabels[i].setText(dis.readLine());//sets each label to next line containing states	
					}
				}
				else if(x == 2){//IF CANADA BUTTON IS CLICKED
					for(int i = 0; i < 53; i++){//FOR LOOPS SKIPS ALL USA DATA
						dis.readLine();
					}
					for(int j = 0; j < 13; j++){
						stateLabels[j].setText(dis.readLine());//sets each label to next line containing provinces
					}
				}
				else if(x == 3){//IF MEXICO BUTTON IS CLICKED
					for(int i = 0; i < 67; i++){//FOR LOOPS SKIPS ALL STATES,PROVINCES AND MEXICO STRING
						dis.readLine();
					}
					for(int j = 0; j < 30; j++){
						stateLabels[j].setText(dis.readLine());//sets each label to next line containing Mexico data
					}
				}
				fis.close();
				bis.close();
				dis.close();//closes input streams

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}// end of try block
		}//end of readFile
	}//end of MainWindow class
	
	public static void main(String[] args) {
		
		JFrame test = new MainWindow();//initializes new instance of MainWindow
		test.setSize(1152, 648);//sets MainWindow resolution
		test.setVisible(true);//Allows user to see class.
	}
}
