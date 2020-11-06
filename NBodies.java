import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class NBodies extends JPanel implements ActionListener
{
	//Timer for shape animations
	Timer tm = new Timer(5, this);
	
	//Class to create shapes
	public void paintComponent(Graphics g)
	{
		int randomNum = ThreadLocalRandom.current().nextInt(0, 256);
		super.paintComponent(g);
		//Set color to random
		Color myColor = new Color(randomNum, randomNum, randomNum);
		g.setColor(Color.myColor);
		//Fill the shape
		g.fillOval(list.get(2), list.get(3), list.get(6), list.get(6));
		tm.start();
	}
	
	//Class to calculate the force
	public void calcForce(NBodies list)
	{
		//Sets variables for x and y
		double x = list.get(2);
		double y = list.get(3);
		
		//Finds the square root of distances from the center
		double r = Math.sqrt(x*x+y*y);
		
		//Calculates the force
		double force = ((double)6.67e-11 *list.get(1)/((r*r)/list.get(6)));
	}
	
	//Class to animate the shapes
	public void actionPerformed(ActionEvent e)
	{
		//Creates a variable for x and y
		double x = list.get(2);
		double y = list.get(3);
		
		//Creates a variable for the velocities
		double velX = list.get(4);
		double velY = list.get(5);
		
		//Changes the positions
		x = x + velX;
		y = y + velY;
		repaint();
		
	}
	
	public static void main(String[] args)
	{
		
		NBodies t = new NBodies();
		JFrame jf = new JFrame();
		
		//Set the board size
		jf.setTitle("Celestial Bodies");
		jf.setSize(768, 768);
		jf.add(t);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Open the in-file from command line
		File file = new File(args[0]);
		try//Try if there is a file
		{
			int count1 = 0, count2 = 0;
			//Create a list type of Lists
			Lists<NBodies> listType;
			//Uses scanner to read the file
			Scanner scanner = new Scanner(file);
			//While loop that runs until the file is out of lines
			while(scanner.hasNextLine())
			{
				//Takes the line and puts it into a string
				String line = scanner.nextLine();
				
				//Read the line
				Scanner lineScanner = new Scanner(line);
				//Separates the line by comma
				lineScanner.useDelimiter(",");
				count2 = 0;
				
				//While the line still has something to read
				while(lineScanner.hasNext())
				{
					//Saves the whats at the position in line
					String part = lineScanner.next();
					
					//If its the first line in the file
					if(count1 == 0)
					{
						if(part == "ArrayList")
						{
							listType = new ArrList<NBodies>();
						}
						else
						{
							listType = new LinkedList<NBodies>();
						}
					}
					
					//IF its the second line in the file
					else if(count1 == 1)
					{
						listType.add(part);
					}
					
					//For all of the lines after
					if(count2 > 0)
					{		
						listType.add(part);
					}

					count2++;
					//Update the bodies
					updateBodies(listType);
				}
				
				count1++;
			}
			//Catch a file not found exception
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		
		
		
	}


		
}
