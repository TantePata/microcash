package com.microej.lab.pages;

import java.util.Arrays;

import com.microej.lab.Program;

import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.List;
import ej.widget.container.Scroll;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;

public class Scores extends Page {

	private static Scroll scroll;
	private static List list;

	public Scores() {
		list = new List(false);

		Arrays.sort(Program.score);
		Scores.list = new List(false);
		for (int i = 0; i < Program.score.length; i++) {
			Label lbl = new Label(String.valueOf(Program.score[i]));
			lbl.addClassSelector("ITEM");
			list.add(lbl);
		}

		ButtonWrapper sback = new ButtonWrapper();
		sback.setWidget(new Label("Back"));
		sback.addOnClickListener(new OnClickListener() {
			@Override
			public void onClick() {
				Program.home();

			}
		});
		list.add(sback);

		scroll = new Scroll(false, true);
		scroll.setWidget(list);
		setWidget(scroll);
	}

	@Override
	public String getCurrentURL() {

		return super.getCurrentURL();
	}

}
