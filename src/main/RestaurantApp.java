package main;

import javax.swing.JFrame;

public class RestaurantApp extends JFrame{
	
	public RestaurantApp(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1615,945);
		
		RestaurantPanel panel = new RestaurantPanel(this.getSize());
		
		this.add(panel);
		
		this.setVisible(true);
	}
	
	public static void main (String[] args){
		new RestaurantApp("Cyber Restaurant Simulator");
		
	}
}
