/*==============================================================================
 = Class: BuildMetallurgyTweaks
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.util;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.config.GeneralConfig;

import java.nio.file.Path;

@Deprecated
public class MetallurgyTweaks {

	private final Path metallurgyTweaksScripts = Utils.getPath("/assets/metallurgy/tweaks/script/");

	private final Path minecraftDir;

	private static MetallurgyTweaks instance;

	private MetallurgyTweaks(Path minecraftDir)
	{
		this.minecraftDir = minecraftDir;
	}

	public void install()
	{

		if (ModChecker.isCraftTweakerLoaded)
		{
			String scriptFolder = minecraftDir.resolve("scripts").toFile().getAbsolutePath();

			Path zip = metallurgyTweaksScripts.resolve("metallurgy_tweaks.zip");
			Path zsConfig = metallurgyTweaksScripts.resolve("metallurgy_tweaks_config.zs");

			if (GeneralConfig.enableMetallurgyTweaksUpdateReplace)
			{
				Utils.copyFile(zip, scriptFolder.concat('/' + zip.getFileName().toString()), true);
				Utils.copyFile(zsConfig, scriptFolder.concat('/' + zsConfig.getFileName().toString()), true);
				Metallurgy.logger.info("Replacing Metallurgy Tweaks!");
			}
			else
				Metallurgy.logger.info("Metallurgy Tweaks replacement is disabled!");
		}
	}

	public static void init(Path minecraft)
	{
		instance = new MetallurgyTweaks(minecraft);
	}

	public static MetallurgyTweaks get()
	{
		return instance;
	}

}
