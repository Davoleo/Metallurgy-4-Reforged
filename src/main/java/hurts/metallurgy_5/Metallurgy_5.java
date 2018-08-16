package hurts.metallurgy_5;

import hurts.metallurgy_5.block.ModBlocks;
import hurts.metallurgy_5.item.ModItems;
import hurts.metallurgy_5.proxy.CommonProxy;
import hurts.metallurgy_5.util.tabs.TabBlock;
import hurts.metallurgy_5.util.tabs.TabIngot;
import hurts.metallurgy_5.util.tabs.TabOre;
import hurts.metallurgy_5.world.ModWorldGen;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Metallurgy_5.MODID, name = Metallurgy_5.NAME, version = Metallurgy_5.VERSION, acceptedMinecraftVersions = "[1.12]")
public class Metallurgy_5 {

	public static final String MODID = "m5";
	public static final String NAME = "Metallurgy 5";
	public static final String VERSION = "1.0.0";

	@Mod.Instance(MODID)
	public static Metallurgy_5 instance;

	@SidedProxy(serverSide = "hurts.metallurgy_5.proxy.CommonProxy", clientSide = "hurts.metallurgy_5.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println(NAME + " is loading!");
		GameRegistry.registerWorldGenerator(new ModWorldGen(),3);
	}
	
	//CreativeTabs
	public static final TabIngot tabIngot = new TabIngot();
	public static final TabBlock tabBlock = new TabBlock();
	public static final TabOre tabOre = new TabOre();

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
	
	@Mod.EventBusSubscriber
	public static class RegsitrationHandler {

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			ModItems.register(event.getRegistry());
			ModBlocks.registerItemBlocks(event.getRegistry());
		}
	
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			ModBlocks.register(event.getRegistry());
		}
	
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			ModItems.registerModels();
			ModBlocks.registerModels();
		}
	}
}
