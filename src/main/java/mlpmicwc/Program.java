/*
 * Java
 *
 * Copyright 2018 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package mlpmicwc;

import ej.microui.MicroUI;
import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.mwt.Desktop;
import ej.mwt.MWT;
import ej.mwt.Panel;
import ej.style.Stylesheet;
import ej.style.background.SimpleRoundedPlainBackground;
import ej.style.border.ComplexRectangularBorder;
import ej.style.outline.SimpleOutline;
import ej.style.selector.ClassSelector;
import ej.style.selector.TypeSelector;
import ej.style.selector.combinator.ChildCombinator;
import ej.style.util.EditableStyle;
import ej.style.util.StyleHelper;
import ej.wadapps.app.Activity;
import ej.widget.basic.Button;
import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.transition.SlideScreenshotTransitionContainer;
import ej.widget.container.transition.TransitionContainer;
import ej.widget.navigation.page.Page;
import mlpmicwc.pages.MainPage;

public class Program implements Activity {
	private static TransitionContainer transition;

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRestart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart() {
		MicroUI.start();
		initializeStyle();

		Panel panel = new Panel();
		Desktop desktop = new Desktop();
		transition = new SlideScreenshotTransitionContainer(MWT.LEFT, false, false);
		transition.show(new MainPage(), false);
		panel.setWidget(transition);
		panel.showFullScreen(desktop);
		desktop.show();
	}

	public static void show(Page PP) {
		transition.show(PP, true);
	}

	public static void home() {
		transition.show(new MainPage(), false);
	}

	private void initializeStyle() {
		Stylesheet stylesheet = StyleHelper.getStylesheet();

		// BUTTON Style based on Label & Button classes
		EditableStyle btnStyle = new EditableStyle();
		SimpleOutline btnMargin = new SimpleOutline(5);
		btnStyle.setMargin(btnMargin);
		btnStyle.setPadding(btnMargin);
		SimpleOutline myOutline = new SimpleOutline(5);
		btnStyle.setMargin(myOutline);
		SimpleRoundedPlainBackground myBackground = new SimpleRoundedPlainBackground(20);
		btnStyle.setBackground(myBackground);
		btnStyle.setBackgroundColor(Colors.NAVY);
		btnStyle.setForegroundColor(Colors.YELLOW);
		btnStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);

		// Rule for labels child of buttons
		TypeSelector lblSel = new TypeSelector(Label.class);
		TypeSelector btnSel = new TypeSelector(ButtonWrapper.class);
		ChildCombinator parentBtnSel = new ChildCombinator(btnSel, lblSel);
		stylesheet.addRule(parentBtnSel, btnStyle);
		stylesheet.addRule(new TypeSelector(Button.class), btnStyle);

		// TITLE
		EditableStyle titleStyle = new EditableStyle();
		titleStyle.setForegroundColor(Colors.BLACK);
		titleStyle.setForegroundColor(Colors.SILVER);
		ComplexRectangularBorder titleBorder = new ComplexRectangularBorder();
		titleBorder.setBottom(2);
		titleStyle.setBorder(titleBorder);
		titleStyle.setBorderColor(Colors.GRAY);

		// Rule for class selector title
		ClassSelector titlClassSelector = new ClassSelector("TITLE");
		stylesheet.addRule(titlClassSelector, titleStyle);

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

	}

}
