/*
 * Java
 *
 * Copyright 2014-2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package ej.demo.ui.widget.page;

import ej.container.Grid;
import ej.demo.ui.widget.style.ClassSelectors;
import ej.mwt.Widget;
import ej.widget.composed.Button;

/**
 * Haptic widgets demonstration page.
 */
public class SettingPage extends AbstractDemoPage {

	@Override
	protected Widget createMainContent() {
		// layout:
		// | switch | radio 1 |
		// | check box | radio 2 |
		// | slider | button |

		Grid grid = new Grid(true, 2);

		Button button = new Button("Button"); //$NON-NLS-1$
		button.addClassSelector(ClassSelectors.ILLUSTRATED_BUTTON);
		grid.add(button);

		return grid;
	}

	@Override
	protected String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
