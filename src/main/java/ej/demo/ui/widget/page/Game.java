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
import ej.microui.display.GraphicsContext;
import ej.microui.event.Event;
import ej.microui.event.generator.Buttons;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;
import ej.mwt.Widget;
import ej.style.Style;
import ej.style.container.Rectangle;
import ej.widget.basic.Image;
import ej.widget.basic.image.ImageHelper;

/**
 * This page illustrates the widgets rendered with some pictos.
 */
public class Game extends AbstractDemoPage implements EventHandler, Animation {

	static int score = 0;
	static int oldx = 0;
	static int oldy = 0;
	static int x = 0;
	static int y = 0;
	static Canvas canvas;

	public Game() {

	}

	@Override
	protected String getTitle() {
		return "Game : " + this.score + "$"; //$NON-NLS-1$
	}

	@Override
	public void renderContent(GraphicsContext g, Style style, Rectangle bounds) {
		// TODO Auto-generated method stub
		super.renderContent(g, style, bounds);
		System.out.println(this.score);
		System.out.println(this.x);
		System.out.println(this.y);
		this.canvas.add(newImage(Images.CASH_500), this.x, this.y, 0, 0);
		// Label scoreLabel = new Label("Score : " + this.score + "€");
		// this.canvas.add(scoreLabel, 440 - 300, 196 - 46, 0, 0);
	}

	@Override
	public Rectangle validateContent(Style style, Rectangle bounds) {
		// TODO Auto-generated method stub
		return super.validateContent(style, bounds);
	}

	@Override
	protected Widget createMainContent() {
		this.score = 50000;

		this.canvas = new Canvas();

		this.canvas.add(newImage(Images.HAND), 480 - 300, 106 - 83, 0, 0);
		this.canvas.add(newImage(Images.CASH_500), 440 - 300, 106 - 46, 0, 0);

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

			if (Buttons.getAction(event) == Pointer.DRAGGED) {
				this.x = ptr.getX();
				this.y = ptr.getY();
			} else if (Buttons.getAction(event) == Buttons.RELEASED) {
				if (this.x + 5 < this.oldx) {
					this.score -= 500;
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
