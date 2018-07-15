package com.microej.lab.pages;

import com.microej.lab.Program;

import ej.widget.basic.AbstractSlider;
import ej.widget.basic.Label;
import ej.widget.basic.drawing.Slider;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.Dock;
import ej.widget.container.List;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.listener.OnValueChangeListener;
import ej.widget.navigation.page.Page;

public class Settings extends Page {

	Label titleLabel = new Label("Settings");

	// Grid grid = new Grid(false, 2);
	Split split;
	Split split2;
	List list;

	Dock content;

	public Label lvlLabel = new Label("Normal");

	public Settings() {
		this.split = new Split(false, 0.23f);
		this.split2 = new Split(false, 0.23f);
		this.list = new List();

		AbstractSlider slider = new Slider(0, 3, 1);
		slider.addOnValueChangeListener(new OnValueChangeListener() {

			@Override
			public void onValueChange(int newValue) {
				// TODO Auto-generated method stub
				if (newValue == 0) {
					lvlLabel.setText("fode");
				} else if (newValue == 1) {
					lvlLabel.setText("normal");
				} else if (newValue == 2) {
					lvlLabel.setText("easy");
				} else if (newValue == 3) {
					lvlLabel.setText("juif");
				}
				Program.lvl = newValue;
			}

			@Override
			public void onMaximumValueChange(int newMaximum) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onMinimumValueChange(int newMinimum) {
				// TODO Auto-generated method stub

			}
		});

		this.list.add(lvlLabel);
		this.list.add(slider);

		this.list.setHorizontal(false);

		this.content = new Dock();
		this.content.setCenter(this.list);

		ButtonWrapper sback = new ButtonWrapper();
		sback.setWidget(new Label("Back"));
		sback.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				Program.home();
			}
		});

		this.content.addBottom(sback);

		setWidget(this.content);

	}

	@Override
	public String getCurrentURL() {
		// TODO Auto-generated method stub
		return super.getCurrentURL();
	}

}
