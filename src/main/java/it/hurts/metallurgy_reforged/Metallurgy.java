 /*
 * -------------------------------------------------------------------------------------------------------
 * Class: Metallurgy
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

 package it.hurts.metallurgy_reforged;

 import it.hurts.metallurgy_reforged.capabilities.entity.EntityData;
 import it.hurts.metallurgy_reforged.capabilities.entity.EntityDataCallable;
 import it.hurts.metallurgy_reforged.capabilities.entity.EntityDataStorage;
 import it.hurts.metallurgy_reforged.capabilities.krik.IKrikEffect;
 import it.hurts.metallurgy_reforged.capabilities.krik.KrikEffectCallable;
 import it.hurts.metallurgy_reforged.capabilities.krik.KrikEffectStorage;
 import it.hurts.metallurgy_reforged.capabilities.punch.IPunchEffect;
 import it.hurts.metallurgy_reforged.capabilities.punch.PunchEffectCallable;
 import it.hurts.metallurgy_reforged.capabilities.punch.PunchEffectStorage;
 import it.hurts.metallurgy_reforged.config.GeneralConfig;
 import it.hurts.metallurgy_reforged.effect.MetallurgyEffects;
 import it.hurts.metallurgy_reforged.fluid.ModFluids;
 import it.hurts.metallurgy_reforged.gui.GuiHandler;
 import it.hurts.metallurgy_reforged.handler.TileEntityHandler;
 import it.hurts.metallurgy_reforged.integration.IntegrationEnderIO;
 import it.hurts.metallurgy_reforged.integration.IntegrationIF;
 import it.hurts.metallurgy_reforged.integration.IntegrationProjectE;
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
 import net.minecraftforge.common.MinecraftForge;
 import net.minecraftforge.common.capabilities.CapabilityManager;
 import net.minecraftforge.fluids.FluidRegistry;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.SidedProxy;
 import net.minecraftforge.fml.common.event.FMLInitializationEvent;
 import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
 import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
 import net.minecraftforge.fml.common.network.NetworkRegistry;
 import net.minecraftforge.fml.common.registry.GameRegistry;
 import org.apache.logging.log4j.Logger;

@Mod(modid = Metallurgy.MODID, name = Metallurgy.NAME, version = Metallurgy.VERSION, dependencies = "required-after:forge@[14.23.5.2768,)", acceptedMinecraftVersions = "[1.12.2]")
public class Metallurgy {

	public static final String MODID = "metallurgy";
	public static final String NAME = "Metallurgy 4: Reforged";
	public static final String VERSION = "1.2.0-alpha.1";

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

		materialConfig = event.getModConfigurationDirectory().getAbsolutePath() + "/metallurgy_reforged/materials.json";
		enderIOAlloyRecipes = event.getModConfigurationDirectory().getAbsolutePath() + "/metallurgy_reforged/metallurgy_enderio_alloys.xml";

		ModMetals.init();

		ModFluids.registerFluids();
		logger.info("Fluid registration complete!");

		GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
		MinecraftForge.EVENT_BUS.register(ModWorldGen.instance);
		MinecraftForge.EVENT_BUS.register(WorldTickHandler.instance);
		logger.info("World generation successful!");

		SubEvent.init();

		TileEntityHandler.registerTileEntities();
		logger.info("Tile Entities Registered!");

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
		logger.info(NAME + ": GUIs have been registered!");

		CapabilityManager.INSTANCE.register(IPunchEffect.class, new PunchEffectStorage(), new PunchEffectCallable());
		logger.info(NAME + ": Punch effect capability Registered");

		CapabilityManager.INSTANCE.register(IKrikEffect.class, new KrikEffectStorage(), new KrikEffectCallable());
		logger.info(NAME + ": Krik effect capability Registered");

		CapabilityManager.INSTANCE.register(EntityData.class, new EntityDataStorage(), new EntityDataCallable());
		logger.info(NAME + ": Entity Data capability Registered");


		proxy.preInit(event);
	}

	 @Mod.EventHandler
	 public void init(FMLInitializationEvent event)
	 {
		 proxy.init(event);
		 logger.info(NAME + " is entering initialization!");
		 ModRecipes.init();
		 logger.info("Recipes loaded!");

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
		 {
			 IntegrationEnderIO.init(enderIOAlloyRecipes);
		 }

		 if (GeneralConfig.enableOreDictCrusherRecipes)
		 {
			 CrusherRecipes.registerDefaultOreRecipes();
			 logger.info("Ore Dictionary based Crusher recipe loaded!");
		 }

		 MetallurgyEffects.initTooltips();
	 }


	 @Mod.EventHandler
	 public void postInit(FMLPostInitializationEvent event)
	 {
		 logger.info(NAME + " is entering post-initialization!");

		 PacketManager.init();
		 logger.info(NAME + "'s Network System Loaded");

		 if (ModChecker.isTConLoaded && !GeneralConfig.tinkerIntegration)
			 IntegrationTIC.postInit();
		 logger.info("Tinker's alloy recipes loaded");

		 logger.info(NAME + " has been completely loaded");
	 }

}
