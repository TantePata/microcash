package com.microej.lab;

import java.io.IOException;
import java.util.Arrays;

import com.microej.lab.pages.MainPage;

import ej.microui.MicroUI;
import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.mwt.Desktop;
import ej.mwt.MWT;
import ej.mwt.Panel;
import ej.style.State;
import ej.style.Stylesheet;
import ej.style.background.NoBackground;
import ej.style.background.SimpleImageBackground;
import ej.style.outline.SimpleOutline;
import ej.style.selector.AttributeSetSelector;
import ej.style.selector.ClassSelector;
import ej.style.selector.StateSelector;
import ej.style.selector.TypeSelector;
import ej.style.selector.combinator.AndCombinator;
import ej.style.selector.combinator.ChildCombinator;
import ej.style.util.EditableStyle;
import ej.style.util.StyleHelper;
import ej.wadapps.app.Activity;
import ej.widget.basic.ButtonImage;
import ej.widget.basic.Label;
import ej.widget.composed.ButtonWrapper;
import ej.widget.container.transition.SlideScreenshotTransitionContainer;
import ej.widget.container.transition.TransitionContainer;
import ej.widget.navigation.page.Page;


public class Program implements Activity {

	//public static HistorizedNavigator Navigator;
	private static TransitionContainer transition;

	public static int[] score;
	public static Boolean isInsert;

	public static int nbScores = 0;
	public static int lvl = 1;


	public static void show(Page PP)
	{
		transition.show(PP, true);
	}

	public static void home()
	{
		transition.show(new MainPage(), false);
	}

	/////// Style sheet //////////////////
	private static void initializeStylesheet() throws IOException {
		Stylesheet stylesheet =  StyleHelper.getStylesheet();

		EditableStyle defstyle = new EditableStyle();
		defstyle.setBackgroundColor(Colors.SILVER);
		stylesheet.setDefaultStyle(defstyle);

		// un-pressed BUTTON Style ////////////
		EditableStyle lblStyle = new EditableStyle();
		SimpleImageBackground myBackground=
				new SimpleImageBackground(Image.createImage("/images/btnblue.png"),
						GraphicsContext.HCENTER | GraphicsContext.VCENTER, true);
		lblStyle.setBackground(myBackground);
		SimpleOutline myPadding = new SimpleOutline(12); // image integrity
		lblStyle.setPadding(myPadding);
		lblStyle.setForegroundColor(Colors.NAVY);
		lblStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		TypeSelector btnSel = new TypeSelector(ButtonWrapper.class);
		stylesheet.addRule(btnSel, lblStyle);			// Button contains a Label

		EditableStyle transp = new EditableStyle();	// transparent Label if child of a Button
		transp.setBackground(NoBackground.NO_BACKGROUND);
		TypeSelector lblSel = new TypeSelector(Label.class);
		ChildCombinator parentBtnSel = new ChildCombinator(btnSel,lblSel);  // button > label
		stylesheet.addRule(parentBtnSel, transp);

		// Pressed BUTTON Style ///////////////
		EditableStyle pressedStyle = new EditableStyle();
		SimpleImageBackground pressedBackground=
				new SimpleImageBackground(Image.createImage("/images/btngreen.png"),
						GraphicsContext.HCENTER | GraphicsContext.VCENTER, true);
		pressedStyle.setBackground(pressedBackground);
		AndCombinator pressSel = new AndCombinator(btnSel, new StateSelector(State.Active));
		stylesheet.addRule(pressSel, pressedStyle);

		// centered Image Button
		EditableStyle imageBtn = new EditableStyle();
		imageBtn.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		stylesheet.addRule(new TypeSelector(ButtonImage.class), imageBtn);

		// Pressed Image BUTTON Style ///////////////
		TypeSelector btnImg = new TypeSelector(ej.widget.basic.ButtonImage.class);
		ChildCombinator imgInbtnImg = new ChildCombinator(btnImg,
				new TypeSelector(ej.widget.basic.Image.class));
		stylesheet.addRule(imgInbtnImg, transp);
		AndCombinator pressImgSel = new AndCombinator(btnImg, new StateSelector(State.Active));
		stylesheet.addRule(pressImgSel, pressedStyle);

		// TITLE style.Image
		EditableStyle titleStyle = new EditableStyle();
		titleStyle.setForegroundColor(Colors.PURPLE);
		SimpleOutline titlepad = new SimpleOutline(12);
		titleStyle.setPadding(titlepad);
		SimpleImageBackground titleBackground =
				new SimpleImageBackground(Image.createImage("/images/btngrey.png"),
						GraphicsContext.HCENTER | GraphicsContext.VCENTER, true);
		titleStyle.setBackground(titleBackground);
		titleStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		// associate name
		ClassSelector ttlSel = new ClassSelector("TITLE");
		stylesheet.addRule(ttlSel, titleStyle);

		// ITEM style
		EditableStyle itemStyle = new EditableStyle();
		SimpleOutline itemPadding = new SimpleOutline(12);
		itemStyle.setPadding(itemPadding);
		SimpleImageBackground itemBackground =
				new SimpleImageBackground(Image.createImage("/images/btnitm.png"),
						GraphicsContext.HCENTER | GraphicsContext.VCENTER, false);
		itemStyle.setBackground(itemBackground);
		itemStyle.setForegroundColor(Colors.WHITE);
		itemStyle.setAlignment(GraphicsContext.HCENTER | GraphicsContext.VCENTER);
		// associate Class Selector
		ClassSelector itmSel = new ClassSelector("ITEM");
		stylesheet.addRule(itmSel, itemStyle);

		// RED text style Attribute
		EditableStyle redStyle = new EditableStyle();
		redStyle.setForegroundColor(Colors.RED);
		AttributeSetSelector redAttr = new AttributeSetSelector("REDtxt");
		stylesheet.addRule(redAttr, redStyle);
	}


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
		score = new int[25];
		for (int i = 0; i < 10; i++) {
			score[i] = i * -100;
			nbScores += 1;
		}
		Arrays.sort(score);

		this.isInsert = new Boolean(false);

		try {
			initializeStylesheet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MicroUI.start();
		// create Desktop & Panel
		Desktop desk = new Desktop();
		Panel pnl = new Panel( );
		// initialize Navigator & attach to Panel
		transition = new SlideScreenshotTransitionContainer(MWT.LEFT, false, false);
		transition.show(new MainPage(), false);
		pnl.setWidget( transition );
		// attach Panel to Desktop
		pnl.showFullScreen(desk);
		desk.show( );
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
