/*==============================================================================
 = Class: Metallurgy
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged;

import it.hurts.metallurgy_reforged.advancement.ModAdvancements;
import it.hurts.metallurgy_reforged.capabilities.effect.EffectDataStorage;
import it.hurts.metallurgy_reforged.capabilities.effect.PlayerEffectData;
import it.hurts.metallurgy_reforged.capabilities.entity.EntityData;
import it.hurts.metallurgy_reforged.capabilities.entity.EntityDataStorage;
import it.hurts.metallurgy_reforged.capabilities.punch.IPunchEffect;
import it.hurts.metallurgy_reforged.capabilities.punch.PunchEffect;
import it.hurts.metallurgy_reforged.capabilities.punch.PunchEffectStorage;
import it.hurts.metallurgy_reforged.config.GeneralConfig;
import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
import it.hurts.metallurgy_reforged.fluid.ModFluids;
import it.hurts.metallurgy_reforged.gui.GuiHandler;
import it.hurts.metallurgy_reforged.handler.OreDictHandler;
import it.hurts.metallurgy_reforged.handler.TileEntityHandler;
import it.hurts.metallurgy_reforged.integration.*;
import it.hurts.metallurgy_reforged.integration.conarm.IntegrationCArmory;
import it.hurts.metallurgy_reforged.integration.crafttweaker.IntegrationCT;
import it.hurts.metallurgy_reforged.integration.silentgems.IntegrationSilentGems;
import it.hurts.metallurgy_reforged.integration.tic.IntegrationTIC;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.proxy.IProxy;
import it.hurts.metallurgy_reforged.recipe.CrusherRecipes;
import it.hurts.metallurgy_reforged.recipe.ModRecipes;
import it.hurts.metallurgy_reforged.util.ModChecker;
import it.hurts.metallurgy_reforged.util.SubEvent;
import it.hurts.metallurgy_reforged.world.ModWorldGen;
import it.hurts.metallurgy_reforged.world.WorldTickHandler;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = Metallurgy.MODID, name = Metallurgy.NAME, version = Metallurgy.VERSION, dependencies = "required-after:forge@[14.23.5.2768,)", acceptedMinecraftVersions = "[1.12.2]")
public class Metallurgy {

	public static final String MODID = "metallurgy";
	public static final String NAME = "Metallurgy 4: Reforged";
	public static final String VERSION = "1.3.0-beta.2";

	public static Logger logger;

	public static String materialConfig;
	public static String enderIOAlloyRecipes;

	@Mod.Instance(MODID)
	public static Metallurgy instance;

	@SidedProxy(serverSide = "it.hurts.metallurgy_reforged.proxy.ServerProxy", clientSide = "it.hurts.metallurgy_reforged.proxy.ClientProxy")
	public static IProxy proxy;

	static
	{
		FluidRegistry.enableUniversalBucket();
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		logger.info(NAME + " is entering pre-initialization!");
		proxy.preInit(event);

		materialConfig = event.getModConfigurationDirectory().getAbsolutePath() + "/metallurgy_reforged/materials.json";
		enderIOAlloyRecipes = event.getModConfigurationDirectory().getAbsolutePath() + "/metallurgy_reforged/metallurgy_enderio_alloys.xml";

		ModMetals.init();

		//ModRecipes.checkForUnboundRecipes();
		//logger.info("JSON Recipes have been enabled/disabled depending on the registered mod content!");

		ModFluids.registerFluids();
		logger.info("Fluid registration complete!");

		GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
		MinecraftForge.EVENT_BUS.register(ModWorldGen.instance);
		MinecraftForge.EVENT_BUS.register(WorldTickHandler.instance);
		logger.info("World generation successful!");

		SubEvent.init();

		TileEntityHandler.registerTileEntities();
		logger.info("Tile Entities Registered!");

		ModAdvancements.registerTriggers();
		logger.info("Metallurgy Advancement Criteria Registered!");

		//checks if tinker is installed
		if (ModChecker.isTConLoaded && !GeneralConfig.tinkerIntegration)
		{
			IntegrationTIC.preInit();
			logger.info("Tinkers' Construct Compatibility module has been pre-initialized");

			if (ModChecker.isConarmLoaded && !GeneralConfig.armoryIntegration)
			{
				IntegrationCArmory.preInit();
				logger.info("Construct's Armory Compatibility module has been pre-initialized");
			}
		}

		if (ModChecker.isIFLoaded && !GeneralConfig.inForegoingIntegration)
		{
			IntegrationIF.preInit();
			logger.info("Industrial Foregoing Compatibility module has been pre-initialized");
		}

		if (ModChecker.isCraftTweakerLoaded)
		{
			IntegrationCT.preInit();
			logger.info("CraftTweaker Compatibility module has been pre-initialized");
		}

		if (ModChecker.isSilentGemsLoaded)
		{
			IntegrationSilentGems.init();
			logger.info("CraftTweaker Compatibility module has been initialized");
		}

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		logger.info("GUIs have been registered!");

		CapabilityManager.INSTANCE.register(IPunchEffect.class, new PunchEffectStorage(), PunchEffect::new);
		logger.info("Punch effect capability Registered");

		CapabilityManager.INSTANCE.register(PlayerEffectData.class, new EffectDataStorage(), PlayerEffectData::new);
		logger.info("Metallurgy Effects capability Registered");

		CapabilityManager.INSTANCE.register(EntityData.class, new EntityDataStorage(), EntityData::new);
		logger.info("Entity Data capability Registered");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
		logger.info(NAME + " is entering initialization!");
		ModRecipes.initFurnaceRecipes();
		logger.info("Furnace Recipes loaded!");

		if (ModChecker.isTConLoaded && !GeneralConfig.tinkerIntegration)
		{
			IntegrationTIC.init();
			logger.info("Tinkers' Construct Compatibility module has been initialized");

			if (ModChecker.isConarmLoaded && !GeneralConfig.armoryIntegration)
			{
				IntegrationCArmory.init();
				logger.info("Construct's Armory Compatibility module has been initialized");
			}
		}

		if (ModChecker.isProjectELoaded && !GeneralConfig.projectEIntegration)
		{
			IntegrationProjectE.init();
			logger.info("ProjectE's Compatibility module has been initialized");
		}

		if (ModChecker.isEnderIOLoaded)
			IntegrationEnderIO.init(enderIOAlloyRecipes);

	    if (GeneralConfig.enableOreDictCrusherRecipes)
	    {
		    CrusherRecipes.registerDefaultOreRecipes();
		    logger.info("Ore Dictionary based Crusher recipe loaded!");
	    }

		if (ModChecker.isChiselLoaded)
			IntegrationChisel.init();

		if (ModChecker.isTELoaded)
			IntegrationThermal.init();

		if (ModChecker.isMekanismLoaded)
			IntegrationMekanism.init();

		MetallurgyEffects.initTooltips();
	}


	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
		logger.info(NAME + " is entering post-initialization!");

		PacketManager.init();
		logger.info(NAME + "'s Network System Loaded");

		if (ModChecker.isTConLoaded && !GeneralConfig.tinkerIntegration)
			IntegrationTIC.postInit();
		logger.info("Tinker's alloy recipes loaded");

		OreDictHandler.populateOredictCache();

		logger.info(NAME + " has been completely loaded");
	}

	@Mod.EventHandler
	public void serverLoaded(FMLServerStartedEvent event)
	{
		boolean isDevEnv = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
		File ciFile = new File("../ci_env");
		if (isDevEnv && ciFile.exists())
			FMLServerHandler.instance().getServer().stopServer();
	}

}
