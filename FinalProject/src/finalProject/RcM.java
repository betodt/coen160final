/**
 * 
 */
package finalProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.naming.*;
import javax.swing.*;

import java.sql.*;

import javax.sql.*;
/**
 * @author Alberto Diaz
 *
 */
public class RcM extends JFrame implements ActionListener{
	private JLabel paperLabel;
	private JLabel glassLabel;
	private JLabel alumLabel;
    private JLabel paperPrice;
    private JLabel glassPrice;
    private JLabel alumPrice;
	private JLabel paperTotal;
	private JLabel glassTotal;
	private JLabel alumTotal;
	private JLabel totalPrice;
	private JButton paperButton;
	private JButton glassButton;
	private JButton alumButton;	
	private JButton cashOut;
	private JButton couponOut;

	public RcM() {
		super("Recycling Machine");		
		
		Container container = getContentPane();
		// set the layout
		GroupLayout layout = new GroupLayout(container);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
	    container.setLayout(layout);
	    
	    JLabel type = new JLabel("Type");
	    JLabel weight = new JLabel("Total Weight");
	    JLabel price = new JLabel("Price");
	    JLabel amount = new JLabel("Total Price");
	    JLabel totalLabel = new JLabel("Grand Total");
	    
	    JLabel paper = new JLabel("Paper: ");
	    JLabel glass = new JLabel("Glass: ");
	    JLabel alum = new JLabel("Aluminum: ");
	    
	    paperPrice = new JLabel("1.5");
	    glassPrice = new JLabel("1.0");
	    alumPrice = new JLabel("3.0");
	    
	    paperLabel = new JLabel("0");
	    glassLabel = new JLabel("0");
	    alumLabel = new JLabel("0");

	    paperTotal = new JLabel("0");
	    glassTotal = new JLabel("0");
	    alumTotal = new JLabel("0");
	    
	    paperButton = new JButton("Paper");
	    glassButton = new JButton("Glass");
	    alumButton = new JButton("Aluminum");
	    
	    totalPrice = new JLabel("0");
	    
	    cashOut = new JButton("Cash");
	    cashOut.setEnabled(false);
	    
	    couponOut = new JButton("Coupon");
	    couponOut.setEnabled(false);
	    
	    paperButton.addActionListener(this);
	    glassButton.addActionListener(this);
	    alumButton.addActionListener(this);
	    
	    cashOut.addActionListener(this);
	    
	    layout.setHorizontalGroup(
	    		layout.createSequentialGroup()
			    	.addGroup(layout.createParallelGroup()
			    		.addComponent(type)
			    		.addComponent(paper)
			    		.addComponent(glass)
			    		.addComponent(alum)
			    		.addComponent(paperButton)
			    		.addComponent(totalLabel)
			    	)
			    	.addGroup(layout.createParallelGroup()
			    		.addComponent(weight)
			    		.addComponent(paperLabel)
			    		.addComponent(glassLabel)
			    		.addComponent(alumLabel)
			    		.addComponent(glassButton)
			    		.addComponent(totalPrice)
			    	)
			    	.addGroup(layout.createParallelGroup()
			    		.addComponent(price)
			    		.addComponent(paperPrice)
			    		.addComponent(glassPrice)
			    		.addComponent(alumPrice)
			    		.addComponent(alumButton)
			    		.addComponent(cashOut)
			    	)
			    	.addGroup(layout.createParallelGroup()
			    		.addComponent(amount)
			    		.addComponent(paperTotal)
			    		.addComponent(glassTotal)
			    		.addComponent(alumTotal)
			    		.addComponent(couponOut)
			    	)
	    );
	    
	    layout.setVerticalGroup(
	    		layout.createSequentialGroup()
	    			.addGroup(layout.createParallelGroup()
	    					.addComponent(type)
	    					.addComponent(weight)
	    					.addComponent(price)
	    					.addComponent(amount)
	    			)
	    			.addGroup(layout.createParallelGroup()
	    					.addComponent(paper)
	    					.addComponent(paperLabel)
	    					.addComponent(paperPrice)
	    					.addComponent(paperTotal)
	    			)
				    .addGroup(layout.createParallelGroup()
	    					.addComponent(glass)
	    					.addComponent(glassLabel)
	    					.addComponent(glassPrice)
	    					.addComponent(glassTotal)
	    			)
	    			.addGroup(layout.createParallelGroup()
	    					.addComponent(alum)
	    					.addComponent(alumLabel)
	    					.addComponent(alumPrice)
	    					.addComponent(alumTotal)
	    			)
	    			
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 20)
	    			
	    			.addGroup(layout.createParallelGroup()
	    					.addComponent(paperButton)
	    					.addComponent(glassButton)
	    					.addComponent(alumButton)
	    			)
	    			
	    			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 20)
	    			
	    			.addGroup(layout.createParallelGroup()
	    					.addComponent(totalLabel)
	    					.addComponent(totalPrice)
	    					.addComponent(cashOut)
	    					.addComponent(couponOut)
	    			)
	    );
		
		//create 
		setSize(400,250);
		setLocationRelativeTo(null);
	    setVisible( true );
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RcM application = new RcM();
		
		application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equalsIgnoreCase("paper")||e.getActionCommand().equalsIgnoreCase("glass")||e.getActionCommand().equalsIgnoreCase("aluminum")) {
			int weight;
			int label = 0;
			float subtotal = 0;
			float total = 0;
			
			try {
				weight = Integer.parseInt((String)JOptionPane.showInputDialog(this, 
						"Enter Weight",
						"Recycle Item", 
						JOptionPane.INFORMATION_MESSAGE, 
						null, 
						null,
						null));
				
			} catch (Exception e2) {			
				weight = 0;
				
				JOptionPane.showMessageDialog(this, "This is not a number", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			switch (e.getActionCommand().toLowerCase().trim()) {
			case "paper":
				label = (Integer.parseInt(paperLabel.getText())+weight);
				subtotal = (Float.parseFloat(paperPrice.getText())) * label;
				total = (Float.parseFloat(totalPrice.getText()))+subtotal;
				if(total <= 0) {
					label = 0;
					subtotal = 0;
				}
				paperLabel.setText(label+"");
				paperTotal.setText(subtotal+"");
				break;
				
			case "glass":
				label = (Integer.parseInt(glassLabel.getText())+weight);
				subtotal = (Float.parseFloat(glassPrice.getText())) * label;
				total = (Float.parseFloat(totalPrice.getText()))+subtotal;
				if(total <= 0) {
					label = 0;
					subtotal = 0;
				}
				glassLabel.setText(label+"");
				glassTotal.setText(subtotal+"");
				break;
			
			case "aluminum":
				label = (Integer.parseInt(alumLabel.getText())+weight);
				subtotal = (Float.parseFloat(alumPrice.getText())) * label;
				total = (Float.parseFloat(totalPrice.getText()))+subtotal;
				if(total <= 0) {
					label = 0;
					subtotal = 0;
				}
				alumLabel.setText(label+"");
				alumTotal.setText(subtotal+"");
				break;
	
			default:
				System.out.println("None of the above " + e.getActionCommand());
				break; 
			}			
			
			if(total > 0) {
				cashOut.setEnabled(true);
				couponOut.setEnabled(true);
				
			}
			else {
				cashOut.setEnabled(false);
				couponOut.setEnabled(false);
				total = 0;
			}
			totalPrice.setText(total+"");
		}
		
		if (e.getActionCommand().equalsIgnoreCase("cash")) {
			System.out.println("RcM.actionPerformed() cash");
		}
		if (e.getActionCommand().equalsIgnoreCase("coupon")) {
			System.out.println("RcM.actionPerformed() coupon");
		}
	}
}