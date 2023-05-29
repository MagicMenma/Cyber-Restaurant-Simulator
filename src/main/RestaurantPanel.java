package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.Timer;

import Interf.ItemBar;
import kitchen.Beef;
import kitchen.Chicken;
import kitchen.Egg;
import kitchen.Food;
import kitchen.Pepper;
import kitchen.Scallion;
import kitchen.Scene;
import kitchen.Tomato;
import util.MinimHelper;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

import javax.swing.JPanel;

public class RestaurantPanel extends JPanel implements ActionListener {
	public static int W_WIDTH = 1600;
	public static int W_HEIGHT = 900;

	private double mouseX;
	private double mouseY;
	private double tempX;
	private double tempY;

	private Timer timer;

	private Scene scene;
	private ArrayList<Food> food;
	private ItemBar itemBar;

	private Minim minim;
	private AudioPlayer menuMusic, news;
	private AudioPlayer fridgeOpened, fridgeClosed;
	private AudioPlayer ipadClick;
	private AudioPlayer cutting;
	private AudioPlayer beating;
	private AudioPlayer frying;
	private AudioPlayer morning;
	

	private boolean ActiveItemBar = false;
	private boolean read = false;
	private boolean cut = false;
	private boolean page = false;
	private boolean fry = false;
	private boolean hideItemBar = false;

	private int state = 0;

	// -1 how to play
	// 0 start menu
	// 1 kitchen with ipad notice
	// 2 ipad 1
	// 3 ipad 2
	// 4 ipad 3
	// 5 kitchen
	// 6 fridgeClosed
	// 7 fridgeOped
	// 8 cutting board
	// 9 cut meat 1
	// 10 cut meat 2
	// 11 cut meat 3
	// 12 cut vegt 1
	// 13 cut vegt 2
	// 14 cut vegt 3
	// 15 egg beater
	// 16 egg in A
	// 17 egg in D
	// 18 egg half A
	// 19 egg half D
	// 20 egg done

	// 99 end

	RestaurantPanel(Dimension initialSize) {
		this.setBackground(Color.white);
		setPreferredSize(new Dimension(W_WIDTH, W_HEIGHT));

		scene = new Scene();
		itemBar = new ItemBar(W_WIDTH / 2, 152 / 2, 1);

		food = new ArrayList<Food>();

		minim = new Minim(new MinimHelper());
		menuMusic = minim.loadFile("mp3/menuMusic.mp3");
		news = minim.loadFile("mp3/news.mp3");
		fridgeOpened = minim.loadFile("mp3/fridgeOpened.mp3");
		fridgeClosed = minim.loadFile("mp3/fridgeClosed.mp3");
		ipadClick = minim.loadFile("mp3/ipadClick.mp3");
		cutting = minim.loadFile("mp3/cutting.mp3");
		beating = minim.loadFile("mp3/beating.mp3");
		frying = minim.loadFile("mp3/frying.mp3");
		morning = minim.loadFile("mp3/morning.mp3");

		MyMouseListener ml = new MyMouseListener();
		addMouseListener(ml);
		addMouseMotionListener(new MyMouseMotionAdapter());

		timer = new Timer(30, this);
		timer.start();

		menuMusic.loop();

		MyKeyAdapter m2 = new MyKeyAdapter();
		addKeyListener(m2);
		addKeyListener(new MyKeyAdapter());
		setFocusable(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		scene.draw(g2, state);

		if (state == 0) { // 0 start menu
			scene.drawArrow(g2, tempX, tempY);
		}

		if (ActiveItemBar) {
			if (state != 2 && state != 3 && state != 4 && state != 99 && !hideItemBar) {
				itemBar.draw(g2);

				if (food.size() > 0) {
					for (int i = 0; i < food.size(); i++) {
						Food temp = food.get(i);
						temp.draw(g2);
					}
				}
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (state == 0) {
			try {
				tempX = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().x;
				tempY = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().y;
			} catch (Exception a) {

			}
			if(!menuMusic.isPlaying()) {
				menuMusic.play(0);
			}
		}

		// System.out.println(state);

		if (food.size() > 0) {
			for (int i = 0; i < food.size(); i++) {
				Food temp = food.get(i);
				// remove the items
				temp.itemBarDrop(1, food);
				temp.ChoppedItemClean(state);

				if (state != 7 && state != 8 && state != 11 && state != 14 && state != 15 && state != 20 && state != 21
						&& state != 22 && state != 27) {
					if (temp.outsideItemBarClean()) {
						food.remove(i);
					}
				}

				if (state == 11 || state == 14 || state == 20) {
					temp.show();
				}
			}
		}
		
		if (state == 1 || state == 5) {
			news.unmute();
			frying.pause();
		}

		if (state == 7) {
			hideItemBar = false;
		}

		// chop food
		if (state == 8 || state == 11 || state == 14) {
			if (scene.chopMeat(food) != -1) {
				state = 9;
			}
			if (scene.chopVegt(food) != -1) {
				state = 12;
			}
		}

		// beat egg
		if (state == 15 || state == 20) {
			beating.pause();
			if (scene.beatEgg(food) != -1) {
				state = 16;
			}
		}
		if (state == 20) {
			scene.resetBeaterNum();
		}
		if (state == 16 || state == 19) {
			if(!beating.isPlaying()) {
				beating.play(0);
			}
		}

		// System.out.println(food.size());

		// pan
		if (state >= 21) {
			scene.panFoodDetector(food);
			if (scene.panSwitchScene() != 0) {
				state = scene.panSwitchScene();
			}

			if (food.size() == 0)
				hideItemBar = true;
		}
		
		if (state >= 22) {
			news.mute();
			if(state >= 22 && state <= 30) {
				if(state != 26) {
					if(!frying.isPlaying()) {
						frying.play(0);
					}
				}
			}
		}
		
		if (state == 25 || state == 30) {
			scene.resetFryingAni();
		}

		if (state == 32) {
			frying.pause();
			if(!morning.isPlaying()) {
				morning.play(0);
			}
			if (scene.morningAni() != 0) {
				state = scene.morningAni();
			}
		}else {
			morning.pause();
		}
		
		if (state == 99) {
			frying.pause();
		}

		repaint();
	}

	public class MyMouseListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {

			if (state == 7) {
				if (scene.getFoodFromFridge(mouseX, mouseY) == 1) { // egg
					food.add(new Egg(1186, 325, 1, 1));
				} else if (scene.getFoodFromFridge(mouseX, mouseY) == 2) { // scallion
					food.add(new Scallion(910, 843, 1, 1));
				} else if (scene.getFoodFromFridge(mouseX, mouseY) == 3) { // beef
					food.add(new Beef(919, 215, 1, 1));
				} else if (scene.getFoodFromFridge(mouseX, mouseY) == 4) { // chicken
					food.add(new Chicken(669, 376, 1, 1));
				} else if (scene.getFoodFromFridge(mouseX, mouseY) == 5) { // pepper
					food.add(new Pepper(616, 666, 1, 1));
				} else if (scene.getFoodFromFridge(mouseX, mouseY) == 6) { // tomato
					food.add(new Tomato(881, 672, 1, 1));
				}
			}

		}

		public void mouseReleased(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();

			if (state == 0) { // 0 start menu
				if (scene.switchScene(mouseX, mouseY) == 1) {
					ipadClick.play(0);
					state = 1;
				}
				if (scene.switchScene(mouseX, mouseY) == -1) {
					ipadClick.play(0);
					state = -1;
				}

			}

			if (state == -1) { // -1 how to play
				if (scene.switchScene(mouseX, mouseY) == 0) {
					ipadClick.play(0);
					state = 0;
				}
			}

			if (state == 1) { // 1 kitchen with ipad notice
				menuMusic.pause();
				if (!news.isPlaying()) {
					news.loop();
				}
				if (scene.switchScene(mouseX, mouseY) == 2) {
					ipadClick.play(0);
					state = 2;
				}
			}

			if (state == 2) { // 2 ipad 1
				if (scene.switchScene(mouseX, mouseY) == 3) {
					ipadClick.play(0);
					state = 3;
				}

				if (scene.switchScene(mouseX, mouseY) == 5) {
					ipadClick.play(0);
					state = 5;
				}
			}

			if (state == 3) { // 3 ipad 2
				if (scene.switchScene(mouseX, mouseY) == 2) {
					ipadClick.play(0);
					state = 2;
				}
				if (scene.switchScene(mouseX, mouseY) == 4) {
					ipadClick.play(0);
					state = 4;
				}
				if (scene.switchScene(mouseX, mouseY) == 5) {
					ipadClick.play(0);
					state = 5;
				}

			}

			if (state == 4) { // 4 ipad 3
				if (scene.switchScene(mouseX, mouseY) == 3) {
					ipadClick.play(0);
					state = 3;
				}
				if (scene.switchScene(mouseX, mouseY) == 5) {
					ipadClick.play(0);
					state = 5;
				}
			}

			if (state == 5) { // 5 kitchen
				read = true;

				if (scene.switchScene(mouseX, mouseY) == 2) {
					ipadClick.play(0);
					state = 2;
				}
				if (scene.switchScene(mouseX, mouseY) == 6)
					state = 6;
				if (scene.switchScene(mouseX, mouseY) == 8 && ActiveItemBar)
					state = 8;
				if (scene.switchScene(mouseX, mouseY) == 15 && ActiveItemBar)
					state = 15;
				if (scene.switchScene(mouseX, mouseY) == 21 && ActiveItemBar)
					state = 21;
			}

			if (state == 6) { // 6 fridgeClosed
				if (scene.switchScene(mouseX, mouseY) == 7) {
					fridgeOpened.play(0);
					state = 7;
				}
			}

			if (state == 7) { // 7 fridgeOped
				ActiveItemBar = true;
				if (scene.switchScene(mouseX, mouseY) == 5) {
					fridgeClosed.play(0);
					state = 5;
				}
			}

			if (state == 8) { // 8 cutting board
				if (scene.switchScene(mouseX, mouseY) == 5)
					state = 5;
			}

			if (state == 11) { // 11 meat
				if (scene.switchScene(mouseX, mouseY) == 5)
					state = 5;
			}

			if (state == 14) { // 14 vegt
				if (scene.switchScene(mouseX, mouseY) == 5)
					state = 5;
			}

			if (state == 15) { // 15 egg beater
				if (scene.switchScene(mouseX, mouseY) == 5)
					state = 5;
				if (scene.switchScene(mouseX, mouseY) == 16)
					state = 16;
			}

			if (state == 20) {
				if (scene.switchScene(mouseX, mouseY) == 5)
					state = 5;
				if (scene.switchScene(mouseX, mouseY) == 16)
					state = 16;
			}

			

			if (state == 21 || state == 22 || state == 26 || state == 27 || state == 31) {
				if (scene.switchScene(mouseX, mouseY) == 5)
					state = 5;
			}

			if (state == 25 || state == 30) {
				if (scene.switchScene(mouseX, mouseY) == 5)
					state = 5;
				if (scene.switchScene(mouseX, mouseY) == 32)
					state = 32;
				if (scene.switchScene(mouseX, mouseY) == 99)
					state = 99;
			}

			////////////////////////// restart//////////////////////////////
			if (state == 99) { // 99 end
				if (scene.switchScene(mouseX, mouseY) == -5) {
					scene.restart();
					ActiveItemBar = false;
					read = false;
					cut = false;
					page = false;
					fry = false;
					hideItemBar = false;
					food = new ArrayList<Food>();
					menuMusic.play(0);
					state = 0;
				}
			}

			if (food.size() > 0) {
				for (int i = 0; i < food.size(); i++) {
					Food temp = food.get(i);
					temp.itemBarPos(1, food);
				}
			}
		}
	}

	public class MyMouseMotionAdapter extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent e) {
			if (state == 7 || state == 8 || state == 11 || state == 14 || state == 15 || state == 20 || state == 21
					|| state == 22 || state == 27) {
				mouseX = e.getX();
				mouseY = e.getY();

				if (food.size() > 0) {
					for (int i = 0; i < food.size(); i++) {
						Food temp = food.get(i);
						if (temp.clicked(mouseX, mouseY)) {
							temp.setPos(mouseX, mouseY);
						}
						temp.draggingProtection(i, food);
					}
				}
			}

		}
	}

	private class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				if (state == 9 || state == 10 || state == 12 || state == 13) {
					if (!cut) {
						state += 1;
						cutting.play(0);
						cut = true;
					}
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_W) {
				if (state == 23 || state == 28) {
					if (!fry) {
						scene.setFryingAni(1);
						fry = true;
					}
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_A) {
				if (state == 3 || state == 4) {
					if (!page) {
						state--;
						page = true;
					}
				}
				if (state == 16 || state == 17 || state == 18 || state == 19) {
					int tempState = scene.eggBeater(0);
					if (tempState != 0)
						state = tempState;
				}
				if (state == 23 || state == 28) {
					if (!fry) {
						scene.setFryingAni(2);
						fry = true;
					}
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_S) {
				if (state == 23 || state == 28) {
					if (!fry) {
						scene.setFryingAni(3);
						fry = true;
					}
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_D) {
				if (state == 2 || state == 3) {
					if (!page) {
						state++;
						page = true;
					}
				}
				if (state == 16 || state == 17 || state == 18 || state == 19) {
					int tempState = scene.eggBeater(1);
					if (tempState != 0)
						state = tempState;
				}
				if (state == 23 || state == 28) {
					if (!fry) {
						scene.setFryingAni(4);
						fry = true;
					}
				}
			}

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if (state == -1) {
					state = 0;
				}

				if (state == 4 && !read) {
					state = 5;
				}
				if (state == 2 || state == 3 || state == 4) {
					if (read) {
						state = 5;
					}
				}

				if (state == 7 || state == 8 || state == 11 || state == 14 || state == 15 || state == 20) {
					state = 5;
				}
			}
		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				cut = false;
			}

			if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
				page = false;
			}

			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S
					|| e.getKeyCode() == KeyEvent.VK_D) {
				fry = false;
			}
		}
	}

}
