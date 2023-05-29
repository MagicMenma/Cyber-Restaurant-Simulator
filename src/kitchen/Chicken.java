package kitchen;

import util.ImageLoader;

public class Chicken extends Food{
	
	public Chicken(double x, double y, double s, int i){
		super(x,y,s,i);
		
		img1 = ImageLoader.loadImage("assets/itemChicken.png");
		img2 = ImageLoader.loadImage("assets/itemChickenChopped.png");
	}
	
}
