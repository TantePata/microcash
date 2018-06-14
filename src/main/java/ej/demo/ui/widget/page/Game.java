/*
 * Java
 *
 * Copyright 2014-2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package ej.demo.ui.widget.page;

import ej.widget.basic.AbstractSlider;
import ej.widget.basic.picto.PictoCheck;
import ej.widget.basic.picto.PictoRadio;
import ej.widget.basic.picto.PictoSlider;
import ej.widget.basic.picto.PictoSwitch;
import ej.widget.composed.Toggle;
import ej.widget.composed.ToggleComposite;
import ej.widget.toggle.RadioModel;

/**
 * This page illustrates the widgets rendered with some pictos.
 */
public class Game extends WidgetPage {

	@Override
	protected String getTitle() {
		return "Pictos"; //$NON-NLS-1$
	}

	@Override
	protected ToggleComposite newCheckBox(String string) {
		return new Toggle(new PictoCheck(), string);
	}

	@Override
	protected ToggleComposite newRadioButton(String string) {
		return new Toggle(new RadioModel(), new PictoRadio(), string);
	}

	@Override
	protected ToggleComposite newSwitch(String string) {
		return new Toggle(new PictoSwitch(), string);
	}

	@Override
	protected AbstractSlider newSlider(int min, int max, int initial) {
		return new PictoSlider(min, max, initial);
	}
}
