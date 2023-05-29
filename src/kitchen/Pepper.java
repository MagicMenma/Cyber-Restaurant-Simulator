package kitchen;

import util.ImageLoader;

public class Pepper extends Food{
	
	public Pepper(double x, double y, double s, int i){
		super(x,y,s,i);
		
		img1 = ImageLoader.loadImage("assets/itemPepper.png");
		img2 = ImageLoader.loadImage("assets/itemPepperChopped.png");
	}
	
}
