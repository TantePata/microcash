/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package mlpmicwc.pages;

import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.List;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;
import ej.widget.toggle.ToggleGroup;
import mlpmicwc.Program;

/**
 *
 */
public class SettingsPage extends Page {
	private static Split split;

	public SettingsPage() {

		split = new Split(false, (float) 0.2);
		Label stitle = new Label("SPLIT Page");
		stitle.addClassSelector("TITLE");

		List SettingList = new List(false);

		ToggleGroup toggleGroup = new ToggleGroup();

		ToggleComposite radio1 = newRadioButton("Radio1"); //$NON-NLS-1$

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
