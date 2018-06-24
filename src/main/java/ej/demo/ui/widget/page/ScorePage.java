/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package ej.demo.ui.widget.page;

import java.util.Arrays;

import ej.bon.Timer;
import ej.bon.TimerTask;
import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.container.List;
import ej.container.Scroll;
import ej.demo.ui.widget.WidgetsDemo;
import ej.demo.ui.widget.style.ClassSelectors;
import ej.mwt.Widget;
import ej.widget.basic.Label;

/**
 * This page illustrates the scrollable list.
 */
public class ScorePage extends AbstractDemoPage {

	private static final String SCORE_PREFIX = "Item "; //$NON-NLS-1$
	private static final int APPEARANCE_DELAY = 1000;
	private static final int ITEM_COUNT = 100;
	private static final int FIRST_SHOT_COUNT = 20;

	private List listComposite;
	int[] sc;

	@Override
	protected String getTitle() {
		return "Scrollable list"; //$NON-NLS-1$
	}

	@Override
	protected Widget createMainContent() {
		this.listComposite = new List(false);

		loadScore();

		Scroll scroll = new Scroll(false, true);
		scroll.setWidget(this.listComposite);
		return scroll;
	}

	private void loadScore() {
		this.sc = WidgetsDemo.score;

		Arrays.sort(this.sc);
		this.listComposite = new List(false);
		for (int i = 0; i < this.sc.length; i++) {
			Label item = new Label("" + this.sc[i]); //$NON-NLS-1$
			item.addClassSelector(ClassSelectors.LIST_ITEM);
			this.listComposite.add(item);
		}
		/*
		 * for (int i = start; i <= end; i++) { Label item = new
		 * Label(SCORE_PREFIX + i);
		 * item.addClassSelector(ClassSelectors.LIST_ITEM);
		 * this.listComposite.add(item); }
		 */
	}

	@Override
	public void showNotify() {
		super.showNotify();
		System.out.println("??");
		System.out.println("?");
		// Add missing items.
		Timer timer = ServiceLoaderFactory.getServiceLoader().getService(Timer.class);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (isShown()) {
					getDesktop().getDisplay().callSerially(new Runnable() {
						@Override
						public void run() {
							loadScore();
						}
					});
					revalidate();
				}
			}
		}, APPEARANCE_DELAY);
	}

}
