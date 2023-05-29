package kitchen;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.RestaurantPanel;
import util.ImageLoader;
import util.Util;

public class Scene {
	private BufferedImage menu;
	private BufferedImage howToPlay;
	private BufferedImage menu1;
	private BufferedImage menu2;
	private BufferedImage menu3;
	private BufferedImage menu4;
	private BufferedImage menu5;
	private BufferedImage kitchen1;
	private BufferedImage kitchen2;
	private BufferedImage nightTime, morningTime;
	private BufferedImage ipadNotice;
	private BufferedImage ipad1;
	private BufferedImage ipad2;
	private BufferedImage ipad3;
	private BufferedImage fridgeClosed;
	private BufferedImage fridgeOpened;
	private BufferedImage cuttingBoard1;
	private BufferedImage cuttingBoard2;
	private BufferedImage cuttingBoard3;
	private BufferedImage cuttingBoard4;
	private BufferedImage cuttingBoard5;
	private BufferedImage cuttingBoard6;
	private BufferedImage cuttingBoard7;
	private BufferedImage eggBeater1;
	private BufferedImage eggBeater2;
	private BufferedImage eggBeater3;
	private BufferedImage eggBeater4;
	private BufferedImage eggBeater5;
	private BufferedImage eggBeater6;
	private BufferedImage pan;
	private BufferedImage panA, panS, panD, panW, panCheck;
	private BufferedImage panMeat1, panMeat2, panMeat3, panMeatDone, panMeatWrong;
	private BufferedImage panEgg1, panEgg2, panEgg3, panEggDone, panEggWrong;
	private BufferedImage timeBuilding, timeNight, timeMoring;
	
	private BufferedImage end;

	private int state = 0;
	private int timer = 0;
	private boolean startGame = false;
	private boolean freeAcessIpad = false;
	private int beaterNum = 10;
	private int nightDay = 0;

	private boolean beef = false;
	private boolean chicken = false;
	private boolean pepper = false;
	private boolean egg = false;
	private boolean tomato = false;
	private boolean scallion = false;
	private boolean meatCheck = false;
	private boolean eggCheck = false;
	private boolean meatWrong = false;
	private boolean eggWrong = false;
	private int fryingKey = 0; // WASD
	private int fryingAni = -1;
	private int fryingNum = 2;
	
	private int dish1 = 0;
	private int dish2 = 0;
	private float alpha = 1f;

	public Scene() {
		menu = ImageLoader.loadImage("assets/menu.png");
		howToPlay = ImageLoader.loadImage("assets/howToPlay.png");
		menu1 = ImageLoader.loadImage("assets/menu1.png");
		menu2 = ImageLoader.loadImage("assets/menu2.png");
		menu3 = ImageLoader.loadImage("assets/menu3.png");
		menu4 = ImageLoader.loadImage("assets/menu4.png");
		menu5 = ImageLoader.loadImage("assets/menu5.png");

		kitchen1 = ImageLoader.loadImage("assets/kitchen1.png");
		kitchen2 = ImageLoader.loadImage("assets/kitchen2.png");
		nightTime = ImageLoader.loadImage("assets/nightTime.png");
		morningTime = ImageLoader.loadImage("assets/morningTime.png");

		ipadNotice = ImageLoader.loadImage("assets/ipadNotice.png");
		ipad1 = ImageLoader.loadImage("assets/ipad1.png");
		ipad2 = ImageLoader.loadImage("assets/ipad2.png");
		ipad3 = ImageLoader.loadImage("assets/ipad3.png");

		fridgeClosed = ImageLoader.loadImage("assets/fridgeClosed.png");
		fridgeOpened = ImageLoader.loadImage("assets/fridgeOpened.png");

		cuttingBoard1 = ImageLoader.loadImage("assets/cuttingBoard1.png");
		cuttingBoard2 = ImageLoader.loadImage("assets/cuttingBoard2.png");
		cuttingBoard3 = ImageLoader.loadImage("assets/cuttingBoard3.png");
		cuttingBoard4 = ImageLoader.loadImage("assets/cuttingBoard4.png");
		cuttingBoard5 = ImageLoader.loadImage("assets/cuttingBoard5.png");
		cuttingBoard6 = ImageLoader.loadImage("assets/cuttingBoard6.png");
		cuttingBoard7 = ImageLoader.loadImage("assets/cuttingBoard7.png");

		eggBeater1 = ImageLoader.loadImage("assets/eggBeater1.png");
		eggBeater2 = ImageLoader.loadImage("assets/eggBeater2.png");
		eggBeater3 = ImageLoader.loadImage("assets/eggBeater3.png");
		eggBeater4 = ImageLoader.loadImage("assets/eggBeater4.png");
		eggBeater5 = ImageLoader.loadImage("assets/eggBeater5.png");
		eggBeater6 = ImageLoader.loadImage("assets/eggBeater6.png");

		pan = ImageLoader.loadImage("assets/pan.png");
		panA = ImageLoader.loadImage("assets/panA.png");
		panS = ImageLoader.loadImage("assets/panS.png");
		panD = ImageLoader.loadImage("assets/panD.png");
		panW = ImageLoader.loadImage("assets/panW.png");
		panCheck = ImageLoader.loadImage("assets/panCheck.png");
		panMeat1 = ImageLoader.loadImage("assets/panMeat1.png");
		panMeat2 = ImageLoader.loadImage("assets/panMeat2.png");
		panMeat3 = ImageLoader.loadImage("assets/panMeat3.png");
		panMeatDone = ImageLoader.loadImage("assets/panMeatDone.png");
		panMeatWrong = ImageLoader.loadImage("assets/panMeatWrong.png");
		panEgg1 = ImageLoader.loadImage("assets/panEgg1.png");
		panEgg2 = ImageLoader.loadImage("assets/panEgg2.png");
		panEgg3 = ImageLoader.loadImage("assets/panEgg3.png");
		panEggDone = ImageLoader.loadImage("assets/panEggDone.png");
		panEggWrong = ImageLoader.loadImage("assets/panEggWrong.png");
		
		timeBuilding = ImageLoader.loadImage("assets/timeBuilding.png");
		timeNight = ImageLoader.loadImage("assets/timeNight.png");
		timeMoring = ImageLoader.loadImage("assets/timeMoring.png");

		end = ImageLoader.loadImage("assets/end.png");
	}

	public void restart() {
		timer = 0;
		nightDay = 0;
		state = 0;
		startGame = false;
		freeAcessIpad = false;
		startGame = false;
		freeAcessIpad = false;
		beaterNum = 10;
		fryingKey = 0; // WASD
		fryingAni = -1;
		fryingNum = 2;
		
		dish1 = 0;
		dish2 = 0;
		alpha = 1f;
	}

	public int getFoodFromFridge(double x, double y) {
		if (x > 1084 && x < 1360 && y > 159 && y < 498) {
			return 1; // egg
		} else if (x > 500 && x < 1047 && y > 780 && y < 900) {
			return 2; // scallion
		} else if (x > 497 && x < 1041 && y > 149 && y < 301) {
			return 3; // beef
		} else if (x > 497 && x < 1041 && y > 309 && y < 461) {
			return 4; // chicken
		} else if (x > 497 && x < 733 && y > 586 && y < 738) {
			return 5; // pepper
		} else if (x > 741 && x < 1028 && y > 586 && y < 738) {
			return 6; // tomato
		} else {
			return 0;
		}
	}

	public void draw(Graphics2D g2, int i) {
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

		state = i;

		AffineTransform at = g2.getTransform();
		g2.translate(0, 0);
		g2.scale(1, 1);

		if (i == 0) { // 0 start menu
			if (timer < 30)
				g2.drawImage(menu, 0, 0, null);
			if (timer >= 30 && timer < 35)
				g2.drawImage(menu3, 0, 0, null);
			if (timer >= 35 && timer < 43)
				g2.drawImage(menu, 0, 0, null);
			if (timer >= 43 && timer < 70)
				g2.drawImage(menu3, 0, 0, null);
			if (timer >= 70 && timer < 85)
				g2.drawImage(menu, 0, 0, null);

			if (timer < 5)
				g2.drawImage(menu5, 0, 0, null);
			if (timer >= 8 && timer < 10)
				g2.drawImage(menu4, 0, 0, null);
			if (timer >= 10 && timer < 17)
				g2.drawImage(menu5, 0, 0, null);
			if (timer >= 17 && timer < 20)
				g2.drawImage(menu4, 0, 0, null);
			if (timer >= 20 && timer < 23)
				g2.drawImage(menu5, 0, 0, null);
			if (timer >= 23 && timer < 85)
				g2.drawImage(menu4, 0, 0, null);

			timer++;
			if (timer == 85) {
				timer = 0;
			}
		}

		if (i == -1) // -1 how to play
			g2.drawImage(howToPlay, 0, 0, null);

		if (i == 1) { // 1 kitchen with ipad notice
			if (nightDay == 0) {
				g2.drawImage(nightTime, 0, 0, null);
			}
			if (nightDay == 1) {
				g2.drawImage(morningTime, 0, 0, null);
			}
			if (timer < 90) {
				g2.drawImage(kitchen1, 0, 0, null);
				g2.drawImage(ipadNotice, 0, 0, null);
			}
			if (timer >= 90 && timer < 180) {
				g2.drawImage(kitchen2, 0, 0, null);
				g2.drawImage(ipadNotice, 0, 0, null);
			}

			timer++;
			if (timer == 180)
				timer = 0;
		}

		if (i == 2) // 2 ipad 1
			g2.drawImage(ipad1, 0, 0, RestaurantPanel.W_WIDTH, RestaurantPanel.W_HEIGHT, null);
		if (i == 3) // 3 ipad 2
			g2.drawImage(ipad2, 0, 0, RestaurantPanel.W_WIDTH, RestaurantPanel.W_HEIGHT, null);
		if (i == 4) // 3 ipad 3
			g2.drawImage(ipad3, 0, 0, RestaurantPanel.W_WIDTH, RestaurantPanel.W_HEIGHT, null);

		if (i == 5) { // 5 kitchen
			if (nightDay == 0) {
				g2.drawImage(nightTime, 0, 0, RestaurantPanel.W_WIDTH, RestaurantPanel.W_HEIGHT, null);
			}
			if (nightDay == 1) {
				g2.drawImage(morningTime, 0, 0, RestaurantPanel.W_WIDTH, RestaurantPanel.W_HEIGHT, null);
			}
			if (timer < 90) {
				g2.drawImage(kitchen1, 0, 0, RestaurantPanel.W_WIDTH, RestaurantPanel.W_HEIGHT, null);
			}
			if (timer >= 90 && timer < 180) {
				g2.drawImage(kitchen2, 0, 0, RestaurantPanel.W_WIDTH, RestaurantPanel.W_HEIGHT, null);
			}

			timer++;
			if (timer == 180)
				timer = 0;
		}

		if (i == 6) // 6 fridgeClosed
			g2.drawImage(fridgeClosed, 0, 0, null);
		if (i == 7) // 7 fridgeOped
			g2.drawImage(fridgeOpened, 0, 0, null);

		if (i == 8) // cuttingBoard
			g2.drawImage(cuttingBoard1, 0, 0, null);
		if (i == 9) // 9 cut meat 1
			g2.drawImage(cuttingBoard2, 0, 0, null);
		if (i == 10) // 10 cut meat 2
			g2.drawImage(cuttingBoard3, 0, 0, null);
		if (i == 11) // 11 cut meat 3
			g2.drawImage(cuttingBoard4, 0, 0, null);
		if (i == 12) // 12 cut vegt 1
			g2.drawImage(cuttingBoard5, 0, 0, null);
		if (i == 13) // 13 cut vegt 2
			g2.drawImage(cuttingBoard6, 0, 0, null);
		if (i == 14) // 14 cut vegt 3
			g2.drawImage(cuttingBoard7, 0, 0, null);

		if (i == 15) // 15 eggBeater
			g2.drawImage(eggBeater1, 0, 0, null);
		if (i == 16) // 16 egg in A
			g2.drawImage(eggBeater2, 0, 0, null);
		if (i == 17) // 17 egg in D
			g2.drawImage(eggBeater3, 0, 0, null);
		if (i == 18) // 18 egg half A
			g2.drawImage(eggBeater4, 0, 0, null);
		if (i == 19) // 19 egg half D
			g2.drawImage(eggBeater5, 0, 0, null);
		if (i == 20) // 20 egg done
			g2.drawImage(eggBeater6, 0, 0, null);

		if (i >= 21 && i <= 31) {
			if (nightDay == 0) {
				g2.drawImage(nightTime, 0, 0, null);
			}
			if (nightDay == 1) {
				g2.drawImage(morningTime, 0, 0, null);
			}
		}
		if (i == 21)
			g2.drawImage(pan, 0, 0, null);
		if (i == 22) {
			g2.drawImage(panMeat1, 0, 0, null);
			if (beef)
				g2.drawImage(panCheck, 1083, 389, null);
			if (chicken)
				g2.drawImage(panCheck, 1083, 459, null);
			if (pepper)
				g2.drawImage(panCheck, 1083, 533, null);
		}
		if (i == 23) {
			g2.drawImage(panMeat2, 0, 0, null);
			panFrying(g2);
		}
		if (i == 24) {
		}
		if (i == 25) {
			if(dish1 == 0) {
				dish1 = 1; //meat
			}
			if(dish1 != 0 && nightDay == 1) {
				dish2 = 1; //meat
			}
			g2.drawImage(panMeatDone, 0, 0, null);
		}
		if (i == 26)
			g2.drawImage(panMeatWrong, 0, 0, null);
		if (i == 27) {
			g2.drawImage(panEgg1, 0, 0, null);
			if (egg)
				g2.drawImage(panCheck, 1083, 389, null);
			if (tomato)
				g2.drawImage(panCheck, 1083, 459, null);
			if (scallion)
				g2.drawImage(panCheck, 1083, 533, null);
		}
		if (i == 28) {
			g2.drawImage(panEgg2, 0, 0, null);
			panFrying(g2);
		}
		if (i == 29) {
		}
		if (i == 30) {
			if(dish1 == 0) {
				dish1 = 2; //egg
			}
			if(dish1 != 0 && nightDay == 1) {
				dish2 = 2; //egg
			}
			g2.drawImage(panEggDone, 0, 0, RestaurantPanel.W_WIDTH, RestaurantPanel.W_HEIGHT, null);
		}
		if (i == 31)
			g2.drawImage(panEggWrong, 0, 0, RestaurantPanel.W_WIDTH, RestaurantPanel.W_HEIGHT, null);
		
		if (i == 32) {
			AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			g2.setComposite(composite);
			g2.drawImage(timeNight, 0, 0, RestaurantPanel.W_WIDTH, RestaurantPanel.W_HEIGHT, null);
			
			composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
			g2.setComposite(composite);
			g2.drawImage(timeMoring, 0, 0, RestaurantPanel.W_WIDTH, RestaurantPanel.W_HEIGHT, null);
			
			g2.drawImage(timeBuilding, 0, 0, RestaurantPanel.W_WIDTH, RestaurantPanel.W_HEIGHT, null);
		}

		if (i == 99) // 99 end
			g2.drawImage(end, 0, 0, RestaurantPanel.W_WIDTH, RestaurantPanel.W_HEIGHT, null);

		g2.setTransform(at);
	}

	public void drawArrow(Graphics2D g2, double x, double y) {
		if (x > 175 && x < 390 && y > 151 && y < 216) {
			g2.drawImage(menu1, 0, 0, RestaurantPanel.W_WIDTH, RestaurantPanel.W_HEIGHT, null);
			startGame = true;
		} else if (x > 175 && x < 553 && y > 253 && y < 319) {
			g2.drawImage(menu2, 0, 0, RestaurantPanel.W_WIDTH, RestaurantPanel.W_HEIGHT, null);
			startGame = false;
		} else {
			startGame = false;
		}
	}

	public int chopMeat(ArrayList<Food> food) {
		for (int i = 0; i < food.size(); i++) {
			Food f = food.get(i);
			f.putOnBoard();
			if (f.cutMeat) {
				f.changeType();
				return i;
			}
		}
		return -1;
	}

	public int chopVegt(ArrayList<Food> food) {
		for (int i = 0; i < food.size(); i++) {
			Food f = food.get(i);
			f.putOnBoard();
			if (f.cutVegt) {
				f.changeType();
				return i;
			}
		}
		return -1;
	}

	public int beatEgg(ArrayList<Food> food) {
		for (int i = 0; i < food.size(); i++) {
			Food f = food.get(i);
			f.putInBowl();
			if (f.beatEgg) {
				f.changeType();
				return i;
			}
		}
		return -1;
	}

	public int eggBeater(int i) {
		if (beaterNum <= 0) {
			return 20;
		} else if (beaterNum % 2 == 0) {
			if (i == 0) { // A
				beaterNum -= 1;
				if (beaterNum > 5) {
					return 17;
				} else {
					return 19;
				}
			} else
				return 0;
		} else if (beaterNum % 2 == 1) {
			if (i == 1) { // D
				beaterNum -= 1;
				if (beaterNum > 5) {
					return 16;
				} else {
					return 18;
				}
			} else
				return 0;
		} else {
			return 0;
		}
	}

	public void resetBeaterNum() {
		beaterNum = 10;
	}

	public void panFoodDetector(ArrayList<Food> food) {
		if (state == 5) {
			beef = false;
			chicken = false;
			pepper = false;
			egg = false;
			tomato = false;
			scallion = false;
			meatCheck = false;
			eggCheck = false;
			meatWrong = false;
			eggWrong = false;
		}

		if (food.size() != 0) {
			for (int i = 0; i < food.size(); i++) {
				Food f = food.get(i);
				if (f.putIntoPan() != 0) {

					// 1 beef
					// 2 chicken
					// 3 pepper
					// 4 egg
					// 5 tomato
					// 6 scallion

					if (f.putIntoPan() == 1) {
						beef = true;
						food.remove(f);
					} else if (f.putIntoPan() == 2) {
						chicken = true;
						food.remove(f);
					} else if (f.putIntoPan() == 3) {
						pepper = true;
						food.remove(f);
					} else if (f.putIntoPan() == 4) {
						egg = true;
						food.remove(f);
					} else if (f.putIntoPan() == 5) {
						tomato = true;
						food.remove(f);
					} else if (f.putIntoPan() == 6) {
						scallion = true;
						food.remove(f);
					}
				}
			}
		}

		if (beef && chicken && pepper)
			meatCheck = true;
		if (egg && tomato && scallion)
			eggCheck = true;

		if (state == 22) {
			if (egg || tomato || scallion) {
				meatWrong = true;
			}
		}
		if (state == 27) {
			if (beef || chicken || pepper) {
				eggWrong = true;
			}
		}
	}

	private void panFrying(Graphics2D g2) {
		// W A S D
		if (fryingKey == 0) {
			fryingKey = Util.randomInt(0, 5);
		}
		if (fryingKey == 1 && fryingAni == -1) {
			g2.drawImage(panW, 0, 0, null);
		}
		if (fryingKey == 2 && fryingAni == -1) {
			g2.drawImage(panA, 0, 0, null);
		}
		if (fryingKey == 3 && fryingAni == -1) {
			g2.drawImage(panS, 0, 0, null);
		}
		if (fryingKey == 4 && fryingAni == -1) {
			g2.drawImage(panD, 0, 0, null);
		}

		if (fryingAni != -1) {
			if (state == 23) {
				g2.drawImage(panMeat3, 0, 0, null);
			}
			if (state == 28) {
				g2.drawImage(panEgg3, 0, 0, null);
			}
		}

		if (fryingAni > -1)
			fryingAni--;
		if (fryingAni == 0)
			fryingKey = 0;
	}

	public void setFryingAni(int i) {
		if (i == fryingKey) {
			fryingNum -= 1;
			fryingAni = 30;
		}
	}

	public void resetFryingAni() {
		beef = false;
		chicken = false;
		pepper = false;
		egg = false;
		tomato = false;
		scallion = false;
		meatCheck = false;
		eggCheck = false;
		meatWrong = false;
		eggWrong = false;
		fryingKey = 0; // WASD
		fryingAni = -1;
		fryingNum = 2;
	}

	public int panSwitchScene() {
		if (state == 21) {
			if (beef || chicken || pepper) {
				return 22;
			} else if (egg || tomato || scallion) {
				return 27;
			} else {
				return 0;
			}
		} else if (fryingNum == 0 && meatCheck) {
			return 25;
		} else if (fryingNum == 0 && eggCheck) {
			return 30;
		} else if (meatCheck) {
			return 23;
		} else if (eggCheck) {
			return 28;
		} else if (meatWrong) {
			return 26;
		} else if (eggWrong) {
			return 31;

		} else {
			return 0;
		}
	}
	
	public int morningAni() {
		if(alpha > 0.02) {
			alpha -= 0.005;
			return 0;
		}else if(alpha <= 0.02) {
			nightDay = 1;
			return 5;
		}else {
			return 0;
		}
	}
	
	public int switchScene(double x, double y) {
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
		System.out.println("1:"+dish1+"  2:"+dish2);
		if (x > 175 && x < 390 && y > 151 && y < 216 && startGame && state == 0) {
			return 1;

			// how to play && exit
		} else if (x > 175 && x < 553 && y > 253 && y < 319 && state == 0) {
			return -1;
		} else if (x > 1417 && x < 1591 && y > 0 && y < 163 && state == -1) {
			return 0;

		} else if (x > 1196 && x < 1507 && y > 581 && y < 881 && state == 1) {
			return 2;
		} else if (x > 1473 && x < 1591 && y > 450 && y < 523 && state == 2) { // next
			return 3;
		} else if (x > 0 && x < 118 && y > 405 && y < 523 && state == 3) { // back
			return 2;
		} else if (x > 1473 && x < 1591 && y > 450 && y < 523 && state == 3) { // next
			return 4;
		} else if (x > 0 && x < 118 && y > 405 && y < 523 && state == 4) { // back
			return 3;
		} else if (x > 1417 && x < 1591 && y > 0 && y < 163 && state == 4) {
			freeAcessIpad = true;
			return 5;
		} else if (x > 1108 && x < 1549 && y > 0 && y < 578 && state == 5) {
			return 6;
		} else if (x > 441 && x < 1095 && y > 0 && y < 900 && state == 6) {
			return 7;
		} else if (x > 27 && x < 144 && y > 757 && y < 875 && state == 7) {
			return 5;

			// Free to go back to ipad && exit
		} else if (x > 1231 && x < 1468 && y > 578 && y < 663 && state == 5) {
			return 2;
		} else if (x > 1417 && x < 1591 && y > 0 && y < 163 && state == 2 && freeAcessIpad) {
			return 5;
		} else if (x > 1417 && x < 1591 && y > 0 && y < 163 && state == 3 && freeAcessIpad) {
			return 5;

		} else if (x > 667 && x < 1149 && y > 457 && y < 656 && state == 5) {
			return 8;

			// cuuting board
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 8) {
			return 5;
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 11) {
			return 5;
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 14) {
			return 5;

			// egg beater
		} else if (x > 687 && x < 999 && y > 695 && y < 900 && state == 5) {
			return 15;
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 15) {
			return 5;
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 20) {
			return 5;

			// pan
		} else if (x > 30 && x < 587 && y > 473 && y < 653 && state == 5) {
			return 21;
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 21) {
			return 5;
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 22) {
			return 5;
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 26) {
			return 5;
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 27) {
			return 5;
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 31) {
			return 5;

			// pan done
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 25 && nightDay == 0) {
			return 32;
		} else if (x > 1037 && x < 1505 && y > 174 && y < 717 && state == 25 && nightDay == 0) {
			return 32;
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 30 && nightDay == 0) {
			return 32;
		} else if (x > 1037 && x < 1505 && y > 174 && y < 717 && state == 30 && nightDay == 0) {
			return 32;
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 25 && nightDay == 1 && dish1 != 0 && dish2 != 0 && dish1 != dish2) {
			return 99;
		} else if (x > 1037 && x < 1505 && y > 174 && y < 717 && state == 25 && nightDay == 1 && dish1 != 0 && dish2 != 0 && dish1 != dish2) {
			return 99;
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 30 && nightDay == 1 && dish1 != 0 && dish2 != 0 && dish1 != dish2) {
			return 99;
		} else if (x > 1037 && x < 1505 && y > 174 && y < 717 && state == 30 && nightDay == 1 && dish1 != 0 && dish2 != 0 && dish1 != dish2) {
			return 99;
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 25 && nightDay == 1) {
			return 5;
		} else if (x > 1037 && x < 1505 && y > 174 && y < 717 && state == 25 && nightDay == 1) {
			return 5;
		} else if (x > 21 && x < 145 && y > 28 && y < 152 && state == 30 && nightDay == 1) {
			return 5;
		} else if (x > 1037 && x < 1505 && y > 174 && y < 717 && state == 30 && nightDay == 1) {
			return 5;

		} else if (x > 1417 && x < 1591 && y > 0 && y < 163 && state == 99) {
			return -2; // restart
		} else {
			return -5;
		}

	}
}
