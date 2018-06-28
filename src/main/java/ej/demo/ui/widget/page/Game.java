/*
 * Java
 *
 * Copyright 2014-2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package ej.demo.ui.widget.page;

import java.io.IOException;

import ej.animation.Animation;
import ej.animation.Animator;
import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.container.Canvas;
import ej.demo.ui.widget.WidgetsDemo;
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
//import ej.widget.basic.Image;
//import ej.widget.basic.image.ImageHelper;

/**
 * This page illustrates the widgets rendered with some pictos.
 */
public class Game extends AbstractDemoPage implements EventHandler, Animation {

	static int score = 0;
	static int oldx = 0;
	static int oldy = 0;
	static int x = 0;
	static int y = 0;
	static int timer = 0;
	static Canvas canvas;
	Image cash, hand;
	boolean animated = true;
	boolean timerBlocked = true;

	public Game() {
		try {
			this.cash = Image.createImage("/images/500euros.png");
			this.hand = Image.createImage("/images/hand.png");
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
		this.timerBlocked = true;
		this.animated = true;
	}

	@Override
	protected String getTitle() {
		return "Game"; //$NON-NLS-1$
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
			if (!WidgetsDemo.isInsert.booleanValue()) {
				WidgetsDemo.score[(WidgetsDemo.nbScores < 25 ? WidgetsDemo.nbScores : 24)] = this.score;
				WidgetsDemo.nbScores += 1;
				WidgetsDemo.isInsert = new Boolean("true");
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

	@Override
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
					this.score -= 500;
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
			WidgetsDemo.show(MainPage.class.getName());
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

	// press,drag,release
}
