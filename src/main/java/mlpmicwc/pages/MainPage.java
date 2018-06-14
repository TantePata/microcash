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
		ButtonWrapper split = new ButtonWrapper();
		split.setWidget(new Label("SPLIT"));
		ButtonWrapper border = new ButtonWrapper();
		border.setWidget(new Label("DOCK"));
		ButtonWrapper list = new ButtonWrapper();
		list.setWidget(new Label("LIST"));
		ButtonWrapper grid = new ButtonWrapper();
		grid.setWidget(new Label("GRID"));

		Btnlist.add(split);
		Btnlist.add(border);
		Btnlist.add(list);
		Btnlist.add(grid);
		this.container.setFirst(title);
		this.container.setLast(Btnlist);
		setWidget(this.container);

		// ButtonEvent handler
		split.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				Program.show(new SplitPage());
			}
		});
	}

}
