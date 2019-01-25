package it.hurts.metallurgy_reforged.util;

import net.minecraftforge.fml.common.Loader;

/***************************
* Project: Metallurgy-4-Reforged
* Date   : 15 dic 2018
***************************/

public class ModChecker {

	public static boolean isTConLoaded;
	
	static{
		ModChecker.isTConLoaded = Loader.isModLoaded("tconstruct");
	}
	
}
