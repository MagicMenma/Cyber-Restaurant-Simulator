package Interf;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.RestaurantPanel;
import util.ImageLoader;

public class InfFood {
	
	private double xPos;
	private double yPos;
	private double sca;
	private BufferedImage img;

	
	// constructor
	public InfFood(double x, double y, double s){
		xPos = x;
		yPos = y;
		sca = s;
		img = ImageLoader.loadImage("assets/itemBar.png");
	}

	public void draw(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(sca, sca);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}


	public void showString(String st) {
		if(yPos > RestaurantPanel.W_HEIGHT / 2) {   //Upper
			
		}else {										//Lower
			
		}
	}
	
}





