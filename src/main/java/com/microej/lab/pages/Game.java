package com.microej.lab.pages;

import java.io.IOException;

import com.microej.lab.Program;

import ej.animation.Animation;
import ej.animation.Animator;
import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.event.Event;
import ej.microui.event.generator.Buttons;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;
import ej.mwt.Widget;
import ej.style.Style;
import ej.style.container.Rectangle;
import ej.widget.container.Canvas;
import ej.widget.container.Split;
import ej.widget.navigation.page.Page;

public class Game extends Page implements EventHandler, Animation {
	private static Split split;

	static int score = 0;
	static int oldx = 0;
	static int oldy = 0;
	static int x = 0;
	static int y = 0;
	static int timer = 0;
	static ej.widget.container.Canvas canvas;
	Image cash, hand;
	boolean animated = true;
	boolean timerBlocked = true;

	public Game() {
		try {
			this.hand = Image.createImage("/images/hand.png");

			if (Program.lvl == 0) {
				this.cash = Image.createImage("/images/robot.png");
				this.hand = Image.createImage("/images/black-hand.png");
			} else if (Program.lvl == 1) {
				this.cash = Image.createImage("/images/50euros.png");
			} else if (Program.lvl == 2) {
				this.cash = Image.createImage("/images/100euros.png");
			} else if (Program.lvl == 3) {
				this.cash = Image.createImage("/images/500euros.png");
			}
			System.out.println("toto");
			ServiceLoaderFactory.getServiceLoader().getService(Animator.class, Animator.class).startAnimation(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.init();
	}

	private void init() {
		this.score = 15000;
		this.timer = 300;
		Program.isInsert = new Boolean(false);
		this.timerBlocked = true;
		this.animated = true;
	}

	@Override
	public String getCurrentURL() {
		// TODO Auto-generated method stub
		return super.getCurrentURL();
	}

	@Override
	public void renderContent(GraphicsContext g, Style style, Rectangle bounds) {
		super.renderContent(g, style, bounds);
		g.drawImage(this.hand, 480 - this.hand.getWidth(), 135 - (this.hand.getHeight() / 2), GraphicsContext.LEFT);
		g.drawImage(this.cash, 258 - (this.cash.getWidth() / 2), 135 - (this.cash.getHeight() / 2),
				GraphicsContext.LEFT);
		if (this.x == 0 && this.y == 0) {
			g.drawImage(this.cash, 258 - (this.cash.getWidth() / 2), 135 - (this.cash.getHeight() / 2),
					GraphicsContext.LEFT);
		} else {
			g.drawImage(this.cash, this.x - (this.cash.getWidth() / 2), this.y - (this.cash.getHeight() / 2),
					GraphicsContext.LEFT);
		}
		g.setColor(Colors.WHITE);
		// System.out.println(this.timer);
		if (this.timer <= 0) {
			if (this.score > 7500) {
				g.drawString("Vous etes toujours riche ! Bouh : " + this.score + "$", 150, 25, GraphicsContext.LEFT);
			} else if (this.score > 2000) {
				g.drawString("Vous etes un classe moyenne... Bof : " + this.score + "$", 150, 25, GraphicsContext.LEFT);
			} else if (this.score > 0) {
				g.drawString("Vous etes pauvre, pas mal : " + this.score + "$", 150, 25, GraphicsContext.LEFT);
			} else {
				g.drawString("Super vous etes endetté ! BRAVO : " + this.score + "$", 150, 25, GraphicsContext.LEFT);
			}
			if (!Program.isInsert.booleanValue()) {
				Program.score[(Program.nbScores < 25 ? Program.nbScores : 24)] = this.score;
				Program.nbScores = (Program.nbScores < 24 ? Program.nbScores + 1 : 24);
				System.out.println(this.score);
				Program.isInsert = new Boolean(true);
			}

		} else {
			g.drawString("Score : " + this.score + "$", 20, 250, GraphicsContext.LEFT);
			g.drawString("Timer : " + this.timer / 10 + "s", 20, 235, GraphicsContext.LEFT);
		}
	}

	@Override
	public Rectangle validateContent(Style style, Rectangle bounds) {
		// TODO Auto-generated method stub
		// return super.validateContent(style, bounds);
		Rectangle rect = new Rectangle(bounds);
		return rect;
	}

	protected Widget createMainContent() {
		this.canvas = new Canvas();

		return this.canvas;

	}

	@Override
	public boolean handleEvent(int event) {
		if (Event.getType(event) == Event.POINTER && this.timer > 0) {

			Pointer ptr = (Pointer) Event.getGenerator(event);

			if (Buttons.getAction(event) == Pointer.DRAGGED) {
				this.x = ptr.getX();
				this.y = ptr.getY();
			} else if (Buttons.getAction(event) == Buttons.RELEASED) {
				if (this.x + 5 < this.oldx) {
					int point = 0;

					if (Program.lvl == 0) {
						point = 1;
					} else if (Program.lvl == 1) {
						point = 50;
					} else if (Program.lvl == 2) {
						point = 100;
					} else if (Program.lvl == 3) {
						point = 500;
					}

					this.score -= point;
					/*
					 * while (this.x == this.x + 300) { this.x += 15; }
					 */
				}

			} else if (Buttons.getAction(event) == Buttons.PRESSED) {
				this.timerBlocked = false;
				this.oldx = ptr.getX();
				this.oldy = ptr.getY();
				this.x = ptr.getX();
				this.y = ptr.getY();
			}
			repaint();
			return true;
		} else if (Event.getType(event) == Event.POINTER && this.timer <= 0) {
			System.out.println(this.timer);
			this.init();
			Program.home();
			repaint();
			return true;

		}
		return super.handleEvent(event);
	}

	@Override
	public boolean tick(long currentTimeMillis) {
		if (this.timerBlocked == false) {
			if (this.timer > 0) {
				this.timer -= 1;
				this.animated = true;
			}

			// TODO Auto-generated method stub
		}
		repaint();
		return this.animated;
	}

}
