package kitchen;

import java.awt.Graphics2D;

public interface FoodInterface {
	void draw(Graphics2D g2);
	boolean clicked(double x, double y);
	void setPos(double x, double y);
}
