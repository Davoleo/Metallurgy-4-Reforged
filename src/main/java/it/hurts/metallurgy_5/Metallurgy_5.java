 package it.hurts.metallurgy_5;

 import it.hurts.metallurgy_5.fluid.ModFluids;
 import it.hurts.metallurgy_5.gui.GuiHandler;
 import it.hurts.metallurgy_5.material.ModMetals;
 import it.hurts.metallurgy_5.proxy.CommonProxy;
 import it.hurts.metallurgy_5.util.handler.TileEntityHandler;
 import it.hurts.metallurgy_5.util.recipe.ModRecipes;
 import it.hurts.metallurgy_5.world.ModWorldGen;
 import net.minecraftforge.fluids.FluidRegistry;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.SidedProxy;
 import net.minecraftforge.fml.common.event.FMLInitializationEvent;
 import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
 import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
 import net.minecraftforge.fml.common.network.NetworkRegistry;
 import net.minecraftforge.fml.common.registry.GameRegistry;
 import org.apache.logging.log4j.Logger;

 /***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

@Mod(modid = Metallurgy_5.MODID, name = Metallurgy_5.NAME, version = Metallurgy_5.VERSION, dependencies = "required-after:forge@[11.16.0.2768,)", acceptedMinecraftVersions = "[1.12]")
public class Metallurgy_5 {

	public static final String MODID = "m5";
	public static final String NAME = "Metallurgy 4: Reforged";
	public static final String VERSION = "0.0.1";

	public static Logger logger;

	@Mod.Instance(MODID)
	public static Metallurgy_5 instance;

	@SidedProxy(serverSide = "it.hurts.metallurgy_5.proxy.CommonProxy", clientSide = "it.hurts.metallurgy_5.proxy.ClientProxy")
	public static CommonProxy proxy;

	static {
		FluidRegistry.enableUniversalBucket();
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger =  event.getModLog();
		System.out.println(NAME + " is loading!");
		ModMetals.registerFluids();
		ModFluids.registerFluids();
		GameRegistry.registerWorldGenerator(new ModWorldGen(),3);
		TileEntityHandler.registerTileEntities();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		ModRecipes.init();
	}


	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
}
