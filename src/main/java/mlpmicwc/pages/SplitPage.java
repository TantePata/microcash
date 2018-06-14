/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package mlpmicwc.pages;

import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import mlpmicwc.Program;

/**
 *
 */
public class SplitPage extends Page {
	private static Split split;

	public SplitPage() {

		split = new Split(false, (float) 0.2);
		Label stitle = new Label("SPLIT Page");
		stitle.addClassSelector("TITLE");
		ButtonWrapper sback = new ButtonWrapper();
		sback.setWidget(new Label("Back"));
		sback.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				Program.home();
			}
		});
		split.setFirst(stitle);
		split.setLast(sback);
		setWidget(split);
	}
}
