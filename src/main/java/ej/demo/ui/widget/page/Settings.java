/*
 * Java
 *
 * Copyright 2014-2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package ej.demo.ui.widget.page;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.container.Dock;
import ej.container.List;
import ej.container.Split;
import ej.demo.ui.widget.WidgetsDemo;
import ej.demo.ui.widget.style.ClassSelectors;
import ej.demo.ui.widget.style.Images;
import ej.demo.ui.widget.style.Pictos;
import ej.exit.ExitHandler;
import ej.mwt.Widget;
import ej.navigation.page.Page;
import ej.widget.basic.AbstractSlider;
import ej.widget.basic.Image;
import ej.widget.basic.Label;
import ej.widget.basic.drawing.Slider;
import ej.widget.basic.image.ImageHelper;
import ej.widget.composed.Button;
import ej.widget.composed.ButtonComposite;
import ej.widget.listener.OnClickListener;
import ej.widget.listener.OnValueChangeListener;

/**
 * Haptic widgets demonstration page.
 */
public class Settings extends Page {

	Label titleLabel = new Label("Settings");
	public static Label lvlLabel = new Label("Normal");

	// Grid grid = new Grid(false, 2);
	Split split = new Split(false, 0.23f);
	Split split2 = new Split(false, 0.23f);
	List list = new List();

	public static Dock content = new Dock();

	@SuppressWarnings("javadoc")
	public Settings() {

		// this.split.setFirst(createTopBar());
		// this.split.setLast(this.btn2);

		AbstractSlider slider = new Slider(0, 3, 1);
		slider.addOnValueChangeListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(int newValue) {
				// TODO Auto-generated method stub
				if (newValue == 0) {
					Settings.lvlLabel.setText("fode");
				}
				if (newValue == 1) {
					Settings.lvlLabel.setText("normal");
				}
				if (newValue == 2) {
					Settings.lvlLabel.setText("easy");
				}
				if (newValue == 3) {
					Settings.lvlLabel.setText("juif");
				}
				WidgetsDemo.lvl = newValue;
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

		this.list.add(Settings.lvlLabel);
		this.list.add(slider);

		this.list.setHorizontal(false);

		this.content = new Dock();
		this.content.setHorizontal(false);
		this.content.setFirst(createTopBar());
		this.content.setCenter(this.list);
		setWidget(this.content);

	}

	/**
	 * Creates the widget representing the top bar of the page.
	 *
	 * @return the top bar widget.
	 */
	protected Widget createTopBar() {
		// The title of the page.
		this.titleLabel.addClassSelector(ClassSelectors.TITLE);

		Dock topBar = new Dock();
		topBar.setCenter(this.titleLabel);

		if (WidgetsDemo.canGoBack()) {
			// Add a back button.
			Button backButton = new Button(Character.toString(Pictos.BACK));
			backButton.getLabel().addClassSelector(ClassSelectors.LARGE_ICON);
			backButton.addOnClickListener(new OnClickListener() {

				@Override
				public void onClick() {
					WidgetsDemo.back();
				}
			});
			topBar.setFirst(backButton);
		} else {
			// Add an exit button.
			ButtonComposite exitButton = new ButtonComposite();
			exitButton.addOnClickListener(new OnClickListener() {

				@Override
				public void onClick() {
					ExitHandler exitHandler = ServiceLoaderFactory.getServiceLoader().getService(ExitHandler.class);
					if (exitHandler != null) {
						exitHandler.exit();
					}
				}
			});
			Image exitIcon = new Image(ImageHelper.loadImage(Images.LOGO));
			exitButton.setWidget(exitIcon);
			topBar.setFirst(exitButton);
		}
		return topBar;
	}
}
