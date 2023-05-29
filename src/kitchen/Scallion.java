package kitchen;

import util.ImageLoader;

public class Scallion extends Food{
	
	public Scallion(double x, double y, double s, int i){
		super(x,y,s,i);
		
		img1 = ImageLoader.loadImage("assets/itemScallion.png");
		img2 = ImageLoader.loadImage("assets/itemScallionChopped.png");

	}
	
	
}
