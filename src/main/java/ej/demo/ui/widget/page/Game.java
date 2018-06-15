/*
 * Java
 *
 * Copyright 2014-2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package ej.demo.ui.widget.page;

import ej.animation.Animation;
import ej.container.Canvas;
import ej.demo.ui.widget.style.Images;
import ej.microui.event.Event;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;
import ej.mwt.Widget;
import ej.widget.basic.Image;
import ej.widget.basic.image.ImageHelper;

/**
 * This page illustrates the widgets rendered with some pictos.
 */
public class Game extends AbstractDemoPage implements EventHandler, Animation {

	int score = 0;
	Canvas canvas;
	boolean first = false;

	@Override
	protected String getTitle() {
		return "Game"; //$NON-NLS-1$
	}

	@Override
	protected Widget createMainContent() {
		if (this.first == false) {
			this.score = 50000;
			System.out.println("test");

			this.canvas = new Canvas();

			this.canvas.add(newImage(Images.HAND), 480 - 300, 106 - 83, 0, 0);
			this.canvas.add(newImage(Images.CASH_500), 440 - 300, 106 - 46, 0, 0);
			this.first = true;
		}

		// TODO Auto-generated method stub

		return this.canvas;

	}

	// A button that leads to the given page.
	private Image newImage(final String name) {
		Image cash = new Image(ImageHelper.loadImage(name));
		return cash;
	}

	@Override
	public boolean handleEvent(int event) {
		if (Event.getType(event) == Event.POINTER) {

			Pointer ptr = (Pointer) Event.getGenerator(event);
			this.canvas.add(newImage(Images.CASH_500), ptr.getX(), ptr.getY(), 0, 0);
			this.score -= 500;
			System.out.println(this.score);
			/*
			 * Game.this.score -= 500; Label scoreLabel = new Label("Score : " +
			 * Game.this.score + "€"); this.canvas.add(scoreLabel, 440 - 300,
			 * 196 - 46, 0, 0);
			 */
			/*
			 * if (Buttons.getAction(event) == Pointer.DRAGGED) { this.dragged =
			 * true; } else if (Buttons.getAction(event) == Buttons.PRESSED) {
			 * this.oldX = p.getX(); this.oldY = p.getY(); } else if
			 * (Buttons.getAction(event) == Buttons.RELEASED) { final int x =
			 * p.getX(); final int y = p.getY();
			 *
			 * if (this.dragged) { this.dragged = false;
			 * this.controler.swipe(this.oldX, this.oldY, x, y); } else {
			 * this.controler.input(x, y); } }
			 */
			repaint();
			return true;
		}
		return false;
	}

	@Override
	public boolean tick(long currentTimeMillis) {
		repaint();
		// TODO Auto-generated method stub
		return true;
	}

	// press,drag,release
}
