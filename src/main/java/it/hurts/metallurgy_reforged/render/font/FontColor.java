/*==============================================================================
 = Class: FontColor
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.render.font;

import net.minecraft.util.math.MathHelper;

import java.awt.*;

public class FontColor {

	protected static int MARKER = 0xE700;

	private FontColor()
	{
	}

	public static String encodeColor(int color)
	{
		int r = ((color >> 16) & 255);
		int g = ((color >> 8) & 255);
		int b = ((color) & 255);
		return encodeColor(r, g, b);
	}

	public static String encodeColor(float r, float g, float b)
	{
		return encodeColor((int) r * 255, (int) g * 255, (int) b * 255);
	}

	public static String encodeColor(int r, int g, int b)
	{
		return String.format("%c%c%c",
				((char) (MARKER + (r & 0xFF))),
				((char) (MARKER + (g & 0xFF))),
				((char) (MARKER + (b & 0xFF))));
	}

	/**
	 * Takes a value between 0.0 and 1.0.
	 * Returns a color between red and green, depending on the value. 1.0 is green.
	 * If the value goes above 1.0 it continues along the color spectrum.
	 */
	public static String valueToColorCode(float v)
	{
		// 0.0 -> 0 = red
		// 1.0 -> 1/3 = green
		// 1.5 -> 1/2 = aqua
		v /= 3f;
		v = MathHelper.clamp(v, 0.01f, 0.5f);
		int color = Color.HSBtoRGB(v, 0.65f, 0.8f);
		return encodeColor(color);
	}

}
