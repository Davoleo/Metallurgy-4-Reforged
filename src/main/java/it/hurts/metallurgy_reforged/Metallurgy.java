 /*
 * -------------------------------------------------------------------------------------------------------
 * Class: Metallurgy
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.hurts.metallurgy_reforged.proxy.ClientProxy;
import it.hurts.metallurgy_reforged.proxy.CommonProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Metallurgy.MODID)
public class Metallurgy {

	public static final String MODID = "metallurgy";
	public static final String NAME = "Metallurgy 4: Reforged";
	public static final String VERSION = "0.2.2";

	public static Logger logger = LogManager.getLogger();

	public static Metallurgy instance;
	public static CommonProxy proxy;

	//static {
	//	FluidRegistry.enableUniversalBucket();
	//}
	
	public Metallurgy() {
		instance = this;
		
		proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
		
		//ModLoadingContext.get().registerConfig(Type.COMMON, ArmorConfig.SPEC, "armor.toml");
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
	}

	public void setup(FMLCommonSetupEvent event) {
        logger.info(NAME + " is entering pre-initialization!");
        proxy.preInit(event);

		//ModMetals.registerFluids();
		//ModFluids.registerFluids();
		logger.info("Fluid registration complete!");
		
		//ModWorldGen.addGenFeatures();
		//MinecraftForge.EVENT_BUS.register(ModWorldGen.instance);
		//MinecraftForge.EVENT_BUS.register(WorldTickHandler.instance);
		logger.info("World generation successful!");
		
		//SubEvent.init();
		
		//TileEntityHandler.registerTileEntities();
		logger.info("Tile Entities Registered!");
		
//		check if tinker is installed
		/*
		if(ModChecker.isTConLoaded && !GeneralConfig.tinkerIntegraton) {
            IntegrationTIC.preInit();
            logger.info("Tinkers' Construct integration has been pre-initialized");
            
            if(ModChecker.isConarmLoaded && !GeneralConfig.armoryIntegraton) {
    			IntegrationCArmory.preInit();
                logger.info("Construct's Armory integration has been pre-initialized");
            }
        }

		if(ModChecker.isIFLoaded && !GeneralConfig.inForegoingIntegraton){
			IntegrationIF.preInit();
			logger.info("Industrial Foregoing integration has been pre-initialized");
		}

		if (ModChecker.isCraftTweakerLoaded)
		{
			IntegrationCT.preInit();
			logger.info("CraftTweaker Integration has been pre-initialized");
		*/

		//NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		logger.info(NAME + ": GUIs have been registered!");
		
		//CapabilityManager.INSTANCE.register(IPunchEffect.class, new PunchEffectStorage(), new PunchEffectCallable());
		logger.info(NAME + ": Punch effect capability Registered");

	}
	
/*
	public void init(FMLInitializationEvent event) {
        logger.info(NAME + " is entering initialization!");
		ModRecipes.init();
        logger.info("Recipes loaded!");
        
		if(ModChecker.isTConLoaded && !GeneralConfig.tinkerIntegraton) {
            IntegrationTIC.init();
            logger.info("Tinkers' Construct integration has been initialized");
            
            if(ModChecker.isConarmLoaded && !GeneralConfig.armoryIntegraton) {
                IntegrationCArmory.init();
                logger.info("Construct's Armory integration has been initialized");
            }
        }

		if(ModChecker.isProjectELoaded && !GeneralConfig.projectEIntegration){
			IntegrationProjectE.init();
			logger.info("ProjectE's Integration has been initialized");
		}

		MinecraftForge.EVENT_BUS.register(new OnPlayerJoin());
		BlockCrusherRecipes.registerDefaultOreRecipes();
	}

	public void postInit(FMLPostInitializationEvent event) {
        logger.info(NAME + " is entering post-initialization!");
        
        proxy.postInit(event);
        
		PacketManager.init();
		logger.info(NAME + "'s Network System Loaded");

		if (ModChecker.isTConLoaded && !GeneralConfig.tinkerIntegraton)
			IntegrationTIC.postInit();
		logger.info("Tinker's alloy recipes loaded");

		logger.info(NAME + " has been completely loaded");
	}
	*/
}
