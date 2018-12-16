package it.hurts.metallurgy_reforged.util;

import net.minecraftforge.fml.common.Loader;

/***************************
*
* Author : PierKnight100
* Project: Metallurgy-5
* Date   : 15 dic 2018
*
***************************/

public class ModChecker {

	public static boolean thereisTick;
	
	public ModChecker() {
		ModChecker.thereisTick = Loader.isModLoaded("tconstruct");
	}
	
}
