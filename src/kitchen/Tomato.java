package kitchen;

import util.ImageLoader;

public class Tomato extends Food{
	
	public Tomato(double x, double y, double s, int i){
		super(x,y,s,i);
		
		img1 = ImageLoader.loadImage("assets/itemTomato.png");
		img2 = ImageLoader.loadImage("assets/itemTomatoChopped.png");
	}
	
}
