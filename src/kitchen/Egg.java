package kitchen;

import util.ImageLoader;

public class Egg extends Food{
	
	public Egg(double x, double y, double s, int i){
		super(x,y,s,i);
		
		img1 = ImageLoader.loadImage("assets/itemEgg.png");
		img2 = ImageLoader.loadImage("assets/itemEggBeaten.png");

	}
	
	
	
}
