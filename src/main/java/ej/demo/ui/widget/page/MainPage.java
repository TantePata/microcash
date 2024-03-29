/*
 * Java
 *
 * Copyright 2014-2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package ej.demo.ui.widget.page;

import ej.container.List;
import ej.container.Scroll;
import ej.demo.ui.widget.WidgetsDemo;
import ej.demo.ui.widget.style.ClassSelectors;
import ej.mwt.Widget;
import ej.widget.composed.Button;
import ej.widget.composed.ButtonComposite;
import ej.widget.listener.OnClickListener;

/**
 * Main page of the application. It allows to access to all the pages of the
 * application.
 */
public class MainPage extends AbstractDemoPage {

	@Override
	protected String getTitle() {
		return "Micro Ca$h"; //$NON-NLS-1$
	}

	@Override
	protected Widget createMainContent() {

		List listComposite = new List(false);
		listComposite.add(newSelectableItem("Game", Game.class.getName())); //$NON-NLS-1$
		listComposite.add(newSelectableItem("Score", ScorePage.class.getName())); //$NON-NLS-1$
		listComposite.add(newSelectableItem("Settings", Settings.class.getName())); //$NON-NLS-1$
		Scroll scroll = new Scroll(false, true);
		scroll.setWidget(listComposite);
		return scroll;
	}

	// A button that leads to the given page.
	private ButtonComposite newSelectableItem(String name, final String url) {
		Button button = new Button(name);
		button.addClassSelector(ClassSelectors.LIST_ITEM);
		button.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				WidgetsDemo.show(url);
			}
		});
		return button;
	}

}
