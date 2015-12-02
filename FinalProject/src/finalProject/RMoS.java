/**
 * 
 */
package finalProject;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;

/**
 * @author Alberto Diaz
 *
 */
public class RMoS extends JFrame {

	public RMoS() {
		super("Recycling Monitoring Station");
		
		Container container = getContentPane();
		// set the layout
		GroupLayout layout = new GroupLayout(container);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
	    container.setLayout(layout);
	    
	    layout.setHorizontalGroup(layout.createSequentialGroup());
	    
	    layout.setVerticalGroup(layout.createSequentialGroup());
		
		//create 
		setSize(600,600);
		setLocationRelativeTo(null);
	    setVisible( true );
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RMoS application = new RMoS();

		application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

}
