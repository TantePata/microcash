/*
 * Java
 *
 * Copyright 2014-2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package ej.demo.ui.widget.page;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.container.Dock;
import ej.demo.ui.widget.WidgetsDemo;
import ej.demo.ui.widget.style.ClassSelectors;
import ej.demo.ui.widget.style.Images;
import ej.demo.ui.widget.style.Pictos;
import ej.exit.ExitHandler;
import ej.microui.display.GraphicsContext;
import ej.mwt.Desktop;
import ej.mwt.Widget;
import ej.navigation.page.Page;
import ej.style.Style;
import ej.style.container.Rectangle;
import ej.widget.basic.Image;
import ej.widget.basic.Label;
import ej.widget.basic.image.ImageHelper;
import ej.widget.composed.Button;
import ej.widget.composed.ButtonComposite;
import ej.widget.listener.OnClickListener;

/**
 * Common abstract page implementation for all the application pages.
 */
public abstract class AbstractDemoPage extends Page {

	private Dock content;
	Label titleLabel;

	/**
	 * Creates a new demo page.
	 */
	public AbstractDemoPage() {
		setWidget(createContent());
	}

	@Override
	public void onTransitionStart() {
		super.onTransitionStart();
		hideNotify();
	}

	@Override
	public void onTransitionStop() {
		super.onTransitionStop();
		if (isShown()) {
			showNotify();
		}
	}

	// @Override
	// public void showNotify() {
	// super.showNotify();
	// System.gc();
	// Runtime runtime = Runtime.getRuntime();
	// System.out.println(runtime.totalMemory() - runtime.freeMemory() + "b");
	// }

	@Override
	public void show(Desktop desktop) throws NullPointerException {
		this.content.setFirst(createTopBar());
		super.show(desktop);
	}

	protected Widget createContent() {
		this.content = new Dock();
		this.content.setHorizontal(false);
		this.content.setFirst(createTopBar());
		this.content.setCenter(createMainContent());
		return this.content;
	}

	@Override
	public void renderContent(GraphicsContext g, Style style, Rectangle bounds) {
		// TODO Auto-generated method stub
		super.renderContent(g, style, bounds);

	}

	/**
	 * Creates the widget representing the top bar of the page.
	 *
	 * @return the top bar widget.
	 */
	protected Widget createTopBar() {
		// The title of the page.
		this.titleLabel = new Label(getTitle());
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

	/**
	 * Gets the title of the page.
	 *
	 * @return the title of the page.
	 */
	protected abstract String getTitle();

	/**
	 * Creates the widget representing the main content of the page.
	 *
	 * @return the composite representing the content of the page.
	 */
	protected abstract Widget createMainContent();

	@Override
	public String getCurrentURL() {
		return getClass().getName();
	}

}
