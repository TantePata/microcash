/*
 * Java
 *
 * Copyright 2014-2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package ej.demo.ui.widget.page;

import ej.container.Canvas;
import ej.demo.ui.widget.style.Images;
import ej.mwt.Widget;
import ej.widget.basic.Image;
import ej.widget.basic.image.ImageHelper;

/**
 * This page illustrates the widgets rendered with some pictos.
 */
public class Game extends AbstractDemoPage {

	@Override
	protected String getTitle() {
		return "Game"; //$NON-NLS-1$
	}

	@Override
	protected Widget createMainContent() {
		Image hand = new Image(ImageHelper.loadImage(Images.HAND));
		Image cash = new Image(ImageHelper.loadImage(Images.CASH_500));
		Canvas canvas = new Canvas();
		canvas.add(hand, 480 - 300, 106 - 83, 0, 0); // 480,272 largeur ecrans
		canvas.add(cash, 440 - 300, 106 - 46, 0, 0);
		// TODO Auto-generated method stub
		return canvas;
	}

	// press,drag,release
}
