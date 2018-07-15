/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found at http://www.is2t.com/open-source-bsd-license/.
 */
package com.microej.lab;

import ej.style.font.FontProfile;
import ej.style.font.loader.AbstractFontLoader;

/**
 * The font loader used in the application. It maps the given font profile to a font.
 */
public class LabFontLoader extends AbstractFontLoader {

	private static final int LARGE_HEIGHT = 50;
	private static final int MEDIUM_HEIGHT = 30;

	@Override
	protected int getFontHeight(FontProfile fontProfile) {
		switch (fontProfile.getSize()) {
		case LENGTH:
			return fontProfile.getSizeValue();
		case LARGE:
			return LARGE_HEIGHT;
		case MEDIUM:
			return MEDIUM_HEIGHT;
		default:
			return MEDIUM_HEIGHT;
		}
	}

}

