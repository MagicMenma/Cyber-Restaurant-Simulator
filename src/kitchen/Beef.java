package kitchen;

import util.ImageLoader;

public class Beef extends Food{
	
	public Beef(double x, double y, double s, int i){
		super(x,y,s,i);
		
		img1 = ImageLoader.loadImage("assets/itemBeef.png");
		img2 = ImageLoader.loadImage("assets/itemBeefChopped.png");
	}
}
