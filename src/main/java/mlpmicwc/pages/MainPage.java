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
import mlpmicwc.Program;

public class MainPage extends Page {

	private final Split container;

	public MainPage() {
		this.container = new Split(false, (float) 0.2);
		List Btnlist = new List(false);
		Label title = new Label("Containers");
		title.addClassSelector("TITLE");
		ButtonWrapper game = new ButtonWrapper();
		game.setWidget(new Label("Game"));
		ButtonWrapper score = new ButtonWrapper();
		score.setWidget(new Label("Score"));
		ButtonWrapper settings = new ButtonWrapper();
		settings.setWidget(new Label("Settings"));

		Btnlist.add(game);
		Btnlist.add(score);
		Btnlist.add(settings);
		this.container.setFirst(title);
		this.container.setLast(Btnlist);
		setWidget(this.container);

		// ButtonEvent handler
		game.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				Program.show(new GamePage());
			}
		});
	}

}
