package com.microej.lab.pages;

import com.microej.lab.Program;

import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.List;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;

public class MainPage extends Page {

	private final Split container;

	public MainPage() {
		container = new Split(false, (float) 0.2);
		List Btnlist = new List(false);
		Label title = new Label("MicroCash");
		title.addClassSelector("TITLE");

		ButtonWrapper split = new ButtonWrapper();
		split.setWidget(new Label("Game"));

		ButtonWrapper list = new ButtonWrapper();
		list.setWidget(new Label("Scores"));

		ButtonWrapper border = new ButtonWrapper();
		border.setWidget(new Label("Setting"));

		// Button Event handler
		split.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				Program.show(new Game());
			}
		});
		// Button Event handler
		list.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				Program.show(new Scores());
			}
		});

		// Button Event handler
		border.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				Program.show(new Settings());
			}
		});
		// Add Button as base Widget of MainPage
		Btnlist.add(split);
		Btnlist.add(list);
		Btnlist.add(border);
		container.setFirst(title);
		container.setLast(Btnlist);
		setWidget(container);
	}

	@Override
	public String getCurrentURL() {
		// TODO Auto-generated method stub
		return getClass().getName();
	}

}
