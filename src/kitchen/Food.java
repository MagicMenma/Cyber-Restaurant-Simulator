package kitchen;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import util.ImageLoader;
import util.Util;

public class Food implements FoodInterface{

	protected double xPos;
	protected double yPos;
	protected double sca;
	protected int type;
	protected BufferedImage img1;
	protected BufferedImage img2;

	public boolean cutMeat = false;
	public boolean cutVegt = false;
	public boolean beatEgg = false;
	public boolean chopped = false;
	protected boolean hide = false;

	// constructor
	public Food(double x, double y, double s, int i) {
		xPos = x;
		yPos = y;
		sca = s;
		type = i;
		img1 = ImageLoader.loadImage("");
		img2 = ImageLoader.loadImage("");
	}

	public void draw(Graphics2D g2) {
		if (!hide) {
			AffineTransform transform = g2.getTransform();
			g2.translate(xPos, yPos);
			g2.scale(sca, sca);

			if (type == 1)
				g2.drawImage(img1, -img1.getWidth() / 2, -img1.getHeight() / 2, null);
			if (type == 2)
				g2.drawImage(img2, -img2.getWidth() / 2, -img2.getHeight() / 2, null);

			g2.setTransform(transform);
		}
	}

	public boolean clicked(double x, double y) {
		boolean clicked = false;

		if (x > (xPos - ((double) img1.getWidth()) / 2 * sca) && x < (xPos + ((double) img1.getWidth()) / 2 * sca)
				&& y > (yPos - ((double) img1.getHeight()) / 2 * sca)
				&& y < (yPos + ((double) img1.getHeight()) / 2 * sca))
			clicked = true;

		return clicked;
	}

	public void setPos(double x, double y) {
		xPos = x;
		yPos = y;
	}

	public void itemBarPos(int i, ArrayList<Food> f) {
		if (i == 1) {
			if (xPos > 523 && xPos < 636 && yPos > 38 && yPos < 145) { // #1
				xPos = 579;
				yPos = 91;
			} else if (xPos > 681 && xPos < 794 && yPos > 37 && yPos < 144) { // #2
				xPos = 738;
				yPos = 91;
			} else if (xPos > 832 && xPos < 945 && yPos > 38 && yPos < 144) { // #3
				xPos = 888;
				yPos = 91;
			}
		}
	}

	public void itemBarDrop(int i, ArrayList<Food> f) {
		if (xPos > 986 && xPos < 1100 && yPos > 37 && yPos < 144) { // #drop
			f.remove(this);
		}
	}

	public boolean outsideItemBarClean() {
		if (xPos == 579 && yPos == 91) {
			return false;
		} else if (xPos == 738 && yPos == 91) {
			return false;
		} else if (xPos == 888 && yPos == 91) {
			return false;
		} else if (xPos == 1384 && yPos == 346) {
			return false;
		} else {
			return true;
		}
	}
	
	public void ChoppedItemClean(int state) {
		if(state == 5) {
			if(xPos == 1384 && yPos == 346) {
				xPos = 0;
				yPos = 0;
			}
		}
	}


	public void draggingProtection(int i, ArrayList<Food> food) {
		for (int k = 0; k < food.size(); k++) {
			if (k != i) {
				Food temp = food.get(k);
				if (temp.getPosX() == xPos && temp.getPosY() == yPos) {
					food.remove(k);
				}
			}
		}
	}

	public void putOnBoard() {
		if (xPos > 143 && xPos < 1116 && yPos > 300 && yPos < 794 && !chopped) {
			if (this instanceof Beef || this instanceof Chicken) {
				cutMeat = true;
			}
			if (this instanceof Pepper || this instanceof Tomato || this instanceof Scallion) {
				cutVegt = true;
			}
		}
	}
	
	public void putInBowl() {
		if (xPos > 143 && xPos < 1116 && yPos > 300 && yPos < 794 && !chopped) {
			if (this instanceof Egg) {
				beatEgg = true;
			}
		}
	}
	
	public int putIntoPan() {
		if (xPos > 200 && xPos < 793 && yPos > 400 && yPos < 663 && chopped) {
			if (this instanceof Beef) return 1;
			else if (this instanceof Chicken) return 2;
			else if (this instanceof Pepper) return 3;
			else if (this instanceof Egg) return 4;
			else if (this instanceof Tomato) return 5;
			else if (this instanceof Scallion) return 6;
			else return 0;
		}else {
			return 0;
		}
	}

	public double getPosX() {
		return xPos;
	}

	public double getPosY() {
		return yPos;
	}

	public void changeType() {
		type = 2;
		hide = true;
		chopped = true;
		xPos = 1384;
		yPos = 346;
	}

	public void show() {
		cutMeat = false;
		cutVegt = false;
		beatEgg = false;
		hide = false;
	}
}
