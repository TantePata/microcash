/*
 * Java
 *
 * Copyright 2014-2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package ej.demo.ui.widget.page;

import java.io.IOException;

import ej.animation.Animation;
import ej.container.Canvas;
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

	public Game() {
		this.score = 5000;
		this.timer = 30;
		try {
			this.cash = Image.createImage("/images/500euros.png");
			this.hand = Image.createImage("/images/hand.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected String getTitle() {
		return "Game"; //$NON-NLS-1$
	}

	@Override
	public void renderContent(GraphicsContext g, Style style, Rectangle bounds) {
		// TODO Auto-generated method stub
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
		System.out.println(this.timer);
		if (this.timer <= 0) {
			if (this.score > 2500) {
				g.drawString("Vous etes toujours riche ! Bouh : " + this.score + "$", 200, 25, GraphicsContext.LEFT);
			} else if (this.score > 1000) {
				g.drawString("Vous etes un classe moyenne... Bof : " + this.score + "$", 200, 25, GraphicsContext.LEFT);
			} else if (this.score > 0) {
				g.drawString("Vous etes pauvre, pas mal : " + this.score + "$", 200, 25, GraphicsContext.LEFT);
			} else {
				g.drawString("Super vous etes endetter ! BRAVO : " + this.score + "$", 200, 25, GraphicsContext.LEFT);
			}
		} else {
			g.drawString("Score : " + this.score + "$", 20, 250, GraphicsContext.LEFT);
			g.drawString("Timer : " + this.timer + "s", 20, 235, GraphicsContext.LEFT);
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

		// this.canvas.add(newImage(Images.HAND), 480 - 300, 106 - 83, 0, 0);
		// this.canvas.add(newImage(Images.CASH_500), 440 - 300, 106 - 46, 0,
		// 0);

		// TODO Auto-generated method stub
		return this.canvas;

	}

	// A button that leads to the given page.
	// static Image newImage(final String name) {
	// Image img = new Image(.loadImage(name));
	// return img;
	// }

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
					this.timer -= 1;

				}

			} else if (Buttons.getAction(event) == Buttons.PRESSED) {
				this.oldx = ptr.getX();
				this.oldy = ptr.getY();
				this.x = ptr.getX();
				this.y = ptr.getY();
			}
			repaint();
			return true;
		}
		return super.handleEvent(event);
	}

	@Override
	public boolean tick(long currentTimeMillis) {
		if (this.timer > 0) {
			this.timer -= 1;
		}
		repaint();
		// TODO Auto-generated method stub
		return this.animated;
	}

	// press,drag,release
}
