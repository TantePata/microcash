/*
 * Java
 *
 * Copyright 2014-2016 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package ej.demo.ui.widget;

import java.util.Arrays;

import ej.demo.ui.widget.page.DirectURLResolver;
import ej.demo.ui.widget.page.MainPage;
import ej.demo.ui.widget.style.StylesheetPopulator;
import ej.microui.MicroUI;
import ej.navigation.desktop.HorizontalNavigationDesktop;
import ej.navigation.desktop.HorizontalScreenshotNavigationDesktop;
import ej.navigation.desktop.NavigationDesktop;
import ej.navigation.page.PagesStack;
import ej.navigation.page.PagesStackURL;
import ej.navigation.page.URLResolver;

/**
 * This demo illustrates the widgets library.
 */
public class WidgetsDemo {

	private static final boolean WITH_SCREENSHOT_TRANSITION = System
			.getProperty("com.microej.demo.widgets.transition.screenshot") != null; //$NON-NLS-1$
	private static NavigationDesktop Desktop;

	private static boolean GoingForward;
	private static boolean GoingBackward;

	public static int[] score;
	public static Boolean isInsert;

	public static int nbScores = 0;

	// Prevents initialization.
	private WidgetsDemo() {
	}

	public static int[] getScoreList() {
		return score;
	}

	/**
	 * Application entry point.
	 *
	 * @param args
	 *            not used.
	 */
	public static void main(String[] args) {
		score = new int[25];
		for (int i = 0; i < 10; i++) {
			score[i] = i * -100;
			nbScores += 1;
		}
		Arrays.sort(score);
		MicroUI.start();

		isInsert = new Boolean(false);

		Desktop = newTransitionDesktop();
		StylesheetPopulator.initialize();
		Desktop.show(MainPage.class.getName());
		Desktop.show();
	}

	private static NavigationDesktop newTransitionDesktop() {
		URLResolver urlResolver = new DirectURLResolver();
		PagesStack pagesStack = new PagesStackURL(urlResolver);
		if (WITH_SCREENSHOT_TRANSITION) {
			return new HorizontalScreenshotNavigationDesktop(urlResolver, pagesStack);
		} else {
			return new HorizontalNavigationDesktop(urlResolver, pagesStack);
		}
	}

	/**
	 * Shows the page corresponding to the given URL.
	 *
	 * @param url
	 *            the URL of the page to show.
	 */
	public static void show(String url) {
		GoingForward = true;
		Desktop.show(url);
		GoingForward = false;
	}

	/**
	 * Shows the previous panel.
	 */
	public static void back() {
		GoingBackward = true;
		Desktop.back();
		GoingBackward = false;
	}

	/**
	 * Checks whether or not it is possible to go back in the navigation
	 * history.
	 * <p>
	 * Beware, the result of this method consider that it is called while
	 * creating the new page.
	 *
	 * @return <code>true</code> it is possible to go back, <code>false</code>
	 *         otherwise.
	 */
	public static boolean canGoBack() {
		int historySize = Desktop.getHistorySize();
		return (historySize > 1 || GoingForward) && !(historySize == 1 && GoingBackward);
	}

}
