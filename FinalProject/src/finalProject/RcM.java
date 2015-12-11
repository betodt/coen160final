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
class Payment extends JPanel
{
	private double amount;
	private boolean cash = true;
	private boolean ok = false;
	private boolean error = false;

	public Payment()
	{
		this.amount = 0;
		setPreferredSize(new Dimension(500,500));
	}
	
	/**
	 * Add new bar to chart
	 * @param color color to display bar
	 * @param value size of bar
	 */
	public void removePayment() {
		this.ok = false;
		repaint();
	}
    public void setAmount(double amount){
    	this.amount = amount;
    }
    
    public void displayPayment(boolean cash){
    	this.cash = cash;
    	this.ok = true;
    	this.error  = false;
    	repaint();
    }
    
    public void displayError(){
    	this.ok = true;
    	this.error = true;
    	repaint();
    }
    
    private void drawFull(Graphics2D g)
	{
    	int width = getWidth()-10;
		int x = 1;
		int height = getHeight()-10;
		
		g.setColor(Color.red);
		String string = "Sorry, this item does not fit";
		Font font = new Font("Verdana",Font.ITALIC,20);
		g.setFont(font);
        g.drawString(string,  (width - (g.getFontMetrics(font)).stringWidth(string))/2, height/2);	 
	}
    
    private void drawCash(Graphics2D g)
	{
    	int width = getWidth()-10;
		int x = 1;
		int height = getHeight()-10;
            
		g.setPaint(Color.green);
		g.fillRect(x, x, width, height);
		
		g.setColor(Color.black);
		String string = "$"+this.amount;
		Font font = new Font("Verdana",Font.ITALIC,20);
		g.setFont(font);
        g.drawString(string,  (width - (g.getFontMetrics(font)).stringWidth(string))/2, height/2);	 
       
        g.drawRect(x, x, width, height);
	}
	private void drawCoupon(Graphics2D g)
	{
		// paint bars

		int width = getWidth() - 10;
		int x = 1;
		int height = getHeight() - 10;
            
		g.setColor(Color.lightGray);
		g.fillRect(x, x, width, height);

		g.setColor(Color.black);
		String string = "Voucher for $"+this.amount;
		Font font = new Font("Verdana",Font.ITALIC,20);
		g.setFont(font);
        g.drawString(string, (width - (g.getFontMetrics(font)).stringWidth(string))/2, getHeight()/2);
        
        g.drawRect(x, x, width, height);
	}
	@Override
	protected void paintComponent(Graphics gp)
	{ 
		super.paintComponent(gp);
         
         // Cast the graphics objects to Graphics2D
         
         Graphics2D g = (Graphics2D) gp;
		 g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
         g.setStroke(new BasicStroke(2.0f));
		
         if(ok) {
        	 if(error)
        		 drawFull(g);
        	 else if (cash)
				drawCash(g);
			else
				 drawCoupon(g);
         }
	}
}

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
	
	private Payment payment;
	
	private Data data = new Data();

	public RcM(String name) {
		super(name);		
		
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
	    couponOut.addActionListener(this);
	    
	    payment = new Payment();
	    
	    layout.setHorizontalGroup(
	    		layout.createParallelGroup()
	    			.addGroup(layout.createSequentialGroup()
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
			    	)
			    	.addGroup(layout.createSequentialGroup()
			    			.addComponent(payment)
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
	    			
	    			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 20)
	    			
	    			.addGroup(layout.createParallelGroup()
	    					.addComponent(payment)
	    			)
	    );
		
		//create 
		setSize(400,400);
		//setLocationRelativeTo(null);
	    setVisible( true );
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RcM application = new RcM("Test RCM");
		
		application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		payment.removePayment();
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
				total = (Float.parseFloat(totalPrice.getText()));
				payment.removePayment();
				if(!data.updateWeight(label)) { 
					payment.displayError();
					break; 
				}
				
				total += subtotal;
				if(total < 0) {
					label = 0;
					subtotal = 0;
				}
				
				total = (Float.parseFloat(totalPrice.getText()))+subtotal;
				paperLabel.setText(label+"");
				paperTotal.setText(subtotal+"");
				break;
				
			case "glass":
				label = (Integer.parseInt(glassLabel.getText())+weight);
				subtotal = (Float.parseFloat(glassPrice.getText())) * label;
				total = (Float.parseFloat(totalPrice.getText()));
				payment.removePayment();
				if(!data.updateWeight(label)) { 
					payment.displayError();
					break; 
				}
				
				total += subtotal;
				if(total < 0) {
					label = 0;
					subtotal = 0;
				}
				
				glassLabel.setText(label+"");
				glassTotal.setText(subtotal+"");
				break;
			
			case "aluminum":
				label = (Integer.parseInt(alumLabel.getText())+weight);
				subtotal = (Float.parseFloat(alumPrice.getText())) * label;
				total = (Float.parseFloat(totalPrice.getText()));
				payment.removePayment();
				if(!data.updateWeight(label)) { 
					payment.displayError();
					break; 
				}
				
				total += subtotal;
				if(total < 0) {
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
			double amount = (double)Float.parseFloat(totalPrice.getText());
			payment.setAmount(amount);
			if(data.updateCash(amount))
				payment.displayPayment(true);
			else
				payment.displayPayment(false);
			this.reset();
		}
		
		if(e.getActionCommand().equalsIgnoreCase("coupon")) {
			payment.setAmount((int)Float.parseFloat(totalPrice.getText()));
			payment.displayPayment(false);
			this.reset();
		}
	}
	
	private void reset() {
		paperLabel.setText(0+"");
		paperTotal.setText(0+"");
		glassLabel.setText(0+"");
		glassTotal.setText(0+"");
		alumLabel.setText(0+"");
		alumTotal.setText(0+"");
		totalPrice.setText(0+"");
		cashOut.setEnabled(false);
		couponOut.setEnabled(false);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getTitle();
	}
	
	public Data getData() { return data; }
}
