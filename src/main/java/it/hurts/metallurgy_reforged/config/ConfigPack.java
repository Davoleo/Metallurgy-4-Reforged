/*==============================================================================
 = Class: ConfigPack
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.config;

import com.google.common.io.Files;
import it.hurts.metallurgy_reforged.Metallurgy;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;


@SuppressWarnings("UnstableApiUsage")
public class ConfigPack {

	private final Path metallurgyConfigDir;

	private boolean justCreated = false;
	private String configPackVersion;

	public ConfigPack(File configDir)
	{
		metallurgyConfigDir = configDir.toPath().resolve("metallurgy_reforged");
		File configPackVer = configDir.toPath().resolve("metallurgy_reforged/config_pack_version").toFile();
		try
		{
			//Make sure parent dirs exist
			Files.createParentDirs(configPackVer);

			if (configPackVer.createNewFile())
			{
				updateConfigPackVersion(configPackVer);
				justCreated = true;
			}
			else
				configPackVersion = Files.readFirstLine(configPackVer, Charset.defaultCharset());
		}
		catch (IOException e)
		{
			Metallurgy.logger.error("Critical error while doing I/O Operations on config_pack_version");
			e.printStackTrace();
		}
	}

	/**
	 * Updates the file responsible to store the current ConfigPack Version
	 *
	 * @param configVersionFile {@code .minecraft/config/metallurgy_reforged/config_pack_version}
	 */
	private void updateConfigPackVersion(File configVersionFile) throws IOException
	{
		String[] versionNums = Metallurgy.VERSION.split("\\.");
		configPackVersion = versionNums[0] + "." + versionNums[1];
		Files.write(versionNums[0] + "." + versionNums[1], configVersionFile, Charset.defaultCharset());
	}

	public boolean isNewUpdate()
	{
		return justCreated || !Metallurgy.VERSION.startsWith(configPackVersion);
	}

	public void backupAndResetPack()
	{
		String backupName = "backup_" + (justCreated ? "pre-1.3" : configPackVersion);
		File backupDir = metallurgyConfigDir.resolve(backupName).toFile();

		try
		{
			if (!backup(backupDir))
				return;
		}
		catch (IOException e)
		{
			Metallurgy.logger.error("Critical I/O error while backing up old Config Pack");
			e.printStackTrace();
		}

		try
		{
			resetConfig();
		}
		catch (IOException e)
		{
			Metallurgy.logger.error("Critical I/O error while RESETTING Metallurgy 4: Reforged Config Pack");
			e.printStackTrace();
		}
	}

	/**
	 * Creates a backup of all the config files in a subdirectory with the version of the old config pack
	 */
	private boolean backup(File backupDir) throws IOException
	{
		if (backupDir.mkdir())
		{
			File[] configFiles = metallurgyConfigDir.toFile().listFiles();
			assert configFiles != null;
			for (File configFile : configFiles)
			{
				if (configFile.getName().startsWith("backup_") || configFile.getName().equals("config_pack_version"))
					continue;

				File backupFile = backupDir.toPath().resolve(configFile.getName()).toFile();
				Files.copy(configFile, backupFile);
			}

			return true;
		}
		return false;
	}

	private void resetConfig() throws IOException
	{

		File[] configFiles = metallurgyConfigDir.toFile().listFiles();
		assert configFiles != null;
		for (File configFile : configFiles)
		{
			if (configFile.getName().startsWith("backup_") || configFile.getName().equals("config_pack_version"))
				continue;

			configFile.delete();
		}

		//Writes out the new config pack version to config_pack_version
		//We update after removing all the files just to be sure config_pack_version isn't nuked
		updateConfigPackVersion(metallurgyConfigDir.resolve("config_pack_version").toFile());
	}

}
