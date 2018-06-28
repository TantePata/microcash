/*
 * Java
 *
 * Copyright 2014-2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package ej.demo.ui.widget.page;

import ej.container.Grid;
import ej.mwt.Widget;
import ej.widget.basic.AbstractSlider;
import ej.widget.basic.Label;
import ej.widget.basic.drawing.Slider;
import ej.widget.listener.OnValueChangeListener;

/**
 * Haptic widgets demonstration page.
 */
public class SettingPage extends AbstractDemoPage {

	public static String lvl;
	public static Label txt;
	public static Grid grid = new Grid(true, 1);

	@Override
	protected Widget createMainContent() {
		// layout:
		// | switch | radio 1 |
		// | check box | radio 2 |
		// | slider | button |

		this.txt = new Label(new String("normal"));
		AbstractSlider slider = newSlider(0, 2, 1);
		slider.addOnValueChangeListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(int newValue) {
				// TODO Auto-generated method stub
				if (newValue == 0) {
					SettingPage.txt = new Label(new String("fode"));

					System.out.println("?");
				}
				if (newValue == 1) {
					SettingPage.txt = new Label(new String("normal"));

					System.out.println("??");
				}
				if (newValue == 2) {
					SettingPage.txt = new Label(new String("facile"));

					System.out.println("????");
				}
			}

			@Override
			public void onMinimumVaueChange(int newMinimum) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onMaximumValueChange(int newMaximum) {
				// TODO Auto-generated method stub

			}
		});

		SettingPage.grid.add(SettingPage.txt);
		SettingPage.grid.add(slider);

		return SettingPage.grid;
	}

	@Override
	protected String getTitle() {
		return "Drawings"; //$NON-NLS-1$
	}

	/**
	 * Gets a new slider widget with the given parameters.
	 *
	 * @param min
	 *            the minimum value of the slider.
	 * @param max
	 *            the maximum value of the slider.
	 * @param initial
	 *            the initial value of the slider.
	 *
	 * @return a new slider widget.
	 */
	protected Slider newSlider(int min, int max, int initial) {
		return new Slider(min, max, initial);
	}
}
