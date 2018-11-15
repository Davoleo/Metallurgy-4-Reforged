 package it.hurts.metallurgy_5;

 import it.hurts.metallurgy_5.block.ModBlocks;
 import it.hurts.metallurgy_5.fluid.ModFluids;
 import it.hurts.metallurgy_5.gui.GuiHandler;
 import it.hurts.metallurgy_5.item.ModItems;
 import it.hurts.metallurgy_5.item.armor.ModArmors;
 import it.hurts.metallurgy_5.item.tool.ModTools;
 import it.hurts.metallurgy_5.proxy.CommonProxy;
 import it.hurts.metallurgy_5.util.handler.CommonTickHandler;
 import it.hurts.metallurgy_5.util.handler.TileEntityHandler;
 import it.hurts.metallurgy_5.util.recipe.ModRecipes;
 import it.hurts.metallurgy_5.world.ModWorldGen;
 import net.minecraft.block.Block;
 import net.minecraft.init.SoundEvents;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemArmor;
 import net.minecraft.item.ItemTool;
 import net.minecraftforge.client.event.ModelRegistryEvent;
 import net.minecraftforge.common.util.EnumHelper;
 import net.minecraftforge.event.RegistryEvent;
 import net.minecraftforge.fluids.FluidRegistry;
 import net.minecraftforge.fml.common.Mod;
 import net.minecraftforge.fml.common.SidedProxy;
 import net.minecraftforge.fml.common.event.FMLInitializationEvent;
 import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
 import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
 import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
 import net.minecraftforge.fml.common.network.NetworkRegistry;
 import net.minecraftforge.fml.common.registry.GameRegistry;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

@Mod(modid = Metallurgy_5.MODID, name = Metallurgy_5.NAME, version = Metallurgy_5.VERSION, acceptedMinecraftVersions = "[1.12]")
public class Metallurgy_5 {

	public static final String MODID = "m5";
	public static final String NAME = "Metallurgy 4: Reforged";
	public static final String VERSION = "0.0.1";

	public static volatile int ticker = 0;

	public static final CommonTickHandler tickHandler = new CommonTickHandler();

//	Armor
	public static final ItemArmor.ArmorMaterial adamantineArmorMaterial = EnumHelper.addArmorMaterial("ADAMANTINE", MODID + ":adamantine", 36, new int[]{3, 4, 5, 3}, 8, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.5F);
	public static final ItemArmor.ArmorMaterial amordrineArmorMaterial = EnumHelper.addArmorMaterial("AMORDRINE", MODID + ":amordrine", 50, new int[]{3, 4, 5, 3}, 50, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2F);
	public static final ItemArmor.ArmorMaterial angmallenArmorMaterial = EnumHelper.addArmorMaterial("ANGMALLEN", MODID + ":angmallen", 30, new int[]{3, 5, 6, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0F);
	public static final ItemArmor.ArmorMaterial astralSilverArmorMaterial = EnumHelper.addArmorMaterial("ASTRAL_SILVER", MODID + ":astral_silver", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ItemArmor.ArmorMaterial atlarusArmorMaterial = EnumHelper.addArmorMaterial("ATLARUS", MODID + ":atlarus", 35, new int[]{4, 3, 3, 4}, 2, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.5F);
	public static final ItemArmor.ArmorMaterial blackSteelArmorMaterial = EnumHelper.addArmorMaterial("BLACK_STEEL", MODID + ":black_steel", 50, new int[]{3, 5, 6, 3}, 17, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4F);
	public static final ItemArmor.ArmorMaterial brassArmorMaterial = EnumHelper.addArmorMaterial("BRASS", MODID + ":brass", 15, new int[]{1, 2, 3, 2}, 18, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4F);
	public static final ItemArmor.ArmorMaterial bronzeArmorMaterial = EnumHelper.addArmorMaterial("BRONZE", MODID + ":bronze", 25, new int[]{3, 3, 4, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2F);
	public static final ItemArmor.ArmorMaterial carmotArmorMaterial = EnumHelper.addArmorMaterial("CARMOT", MODID + ":carmot", 28, new int[]{2, 4, 5, 2}, 7, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ItemArmor.ArmorMaterial celenegilArmorMaterial = EnumHelper.addArmorMaterial("CELENEGIL", MODID + ":celenegil", 160, new int[]{4, 6, 7, 5}, 50, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0F);
	public static final ItemArmor.ArmorMaterial damascusSteelArmorMaterial = EnumHelper.addArmorMaterial("DAMASCUS_STEEL", MODID + ":damascus_steel", 50, new int[]{3, 5, 6, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3F);
	public static final ItemArmor.ArmorMaterial deepIronArmorMaterial = EnumHelper.addArmorMaterial("DEEP_IRON", MODID + ":deep_iron", 38, new int[]{2, 4, 5, 2}, 1, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ItemArmor.ArmorMaterial desichalkosArmorMaterial = EnumHelper.addArmorMaterial("DESICHALKOS", MODID + ":desichalkos", 180, new int[]{4, 5, 7, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3F);
	public static final ItemArmor.ArmorMaterial electrumArmorMaterial = EnumHelper.addArmorMaterial("ELECTRUM",MODID + ":electrum", 51, new int[]{3, 5, 6, 2}, 30, SoundEvents.ENTITY_LIGHTNING_IMPACT, 3F);
	public static final ItemArmor.ArmorMaterial eximiteArmorMaterial = EnumHelper.addArmorMaterial("EXIMITE", MODID + ":eximite", 100, new int[]{4, 5, 6, 4}, 25, SoundEvents.ENTITY_ENDERDRAGON_GROWL, 5.0F);
	public static final ItemArmor.ArmorMaterial haderothArmorMaterial = EnumHelper.addArmorMaterial("HADEROTH", MODID + "haderoth", 125, new int[]{4, 5, 7, 4}, 19, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 6F);
	public static final ItemArmor.ArmorMaterial hepatizonArmorMaterial = EnumHelper.addArmorMaterial("HEPATIZON", MODID + ":hepatizon", 57, new int[]{3, 3, 4, 2}, 22, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2F);
	public static final ItemArmor.ArmorMaterial ignatiusArmorMaterial = EnumHelper.addArmorMaterial("IGNATIUS", MODID + ":ignatius", 24, new int[]{2, 5, 6, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
    public static final ItemArmor.ArmorMaterial inolashiteArmorMaterial = EnumHelper.addArmorMaterial("INOLASHITE", MODID + ":inolashite", 70, new int[]{3, 5, 7, 4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4F);
	public static final ItemArmor.ArmorMaterial kalendriteArmorMaterial = EnumHelper.addArmorMaterial("KALENDRITE", MODID + ":kalendrite", 40, new int[]{4, 5, 6, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON,3.0F);
	public static final ItemArmor.ArmorMaterial midasiumArmorMaterial = EnumHelper.addArmorMaterial("MIDASIUM", MODID +  ":midasium", 16, new int[]{3, 3, 5, 2}, 35, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.0F);
	public static final ItemArmor.ArmorMaterial mithrilArmorMaterial = EnumHelper.addArmorMaterial("MITHRIL", MODID + ":mithril", 21, new int[]{2, 4, 5, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ItemArmor.ArmorMaterial orichalcumArmorMaterial = EnumHelper.addArmorMaterial("ORICHALCUM", MODID + ":orichalcum", 20, new int[]{2, 6, 7, 2}, 4, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.4F);
	public static final ItemArmor.ArmorMaterial oureclaseArmorMaterial = EnumHelper.addArmorMaterial("OURECLASE", MODID + ":oureclase", 28, new int[]{3, 6, 5, 4}, 2, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.5F);
	public static final ItemArmor.ArmorMaterial platinumArmorMaterial = EnumHelper.addArmorMaterial("PLATINUM", MODID + ":platinum", 17, new int[]{3, 5, 6, 3}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5F);
	public static final ItemArmor.ArmorMaterial prometheumArmorMaterial = EnumHelper.addArmorMaterial("PROMETHEUM", MODID + ":prometheum", 30, new int[]{1, 2, 3, 2}, 11 ,SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.1F);
	public static final ItemArmor.ArmorMaterial sanguiniteArmorMaterial = EnumHelper.addArmorMaterial("SANGUINITE", MODID + ":sanguinite", 175, new int[]{4, 6, 7, 5}, 25, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.0F);
	public static final ItemArmor.ArmorMaterial shadowIronArmorMaterial = EnumHelper.addArmorMaterial("SHADOW_IRON", MODID + ":shadow_iron", 32, new int[]{4, 5, 6, 3}, 3, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.0F);
	public static final ItemArmor.ArmorMaterial shadowSteelArmorMaterial = EnumHelper.addArmorMaterial("SHADOW_STEEL", MODID + ":shadow_steel", 40, new int[]{4, 5, 6, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.0F);
	public static final ItemArmor.ArmorMaterial silverArmorMaterial = EnumHelper.addArmorMaterial("SILVER", MODID + ":silver", 8, new int[]{2, 3, 4, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.1F);
	public static final ItemArmor.ArmorMaterial steelArmorMaterial = EnumHelper.addArmorMaterial("STEEL", MODID + ":steel", 40, new int[]{3, 5, 6, 3}, 18, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4F);
	public static final ItemArmor.ArmorMaterial tartariteArmorMaterial = EnumHelper.addArmorMaterial("TARTARITE", MODID + ":tartarite", 300, new int[]{5, 6, 7, 5}, 20, SoundEvents.ENTITY_WITHER_SKELETON_AMBIENT, 6.0F);
	public static final ItemArmor.ArmorMaterial vulcaniteArmorMaterial = EnumHelper.addArmorMaterial("VULCANITE", MODID + ":vulcanite", 150, new int[]{4, 6, 7, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.0F);
	public static final ItemArmor.ArmorMaterial vyroxeresArmorMaterial = EnumHelper.addArmorMaterial("VIROXERES", MODID + ":vyroxeres", 37, new int[]{4, 5, 6, 3}, 16, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.0F);
	
//	Tool
	public static final ItemTool.ToolMaterial adamantineToolMaterial = EnumHelper.addToolMaterial("ADAMANTINE", 7, 1550, 8F, 8F, 22);
	public static final ItemTool.ToolMaterial amordrineToolMaterial = EnumHelper.addToolMaterial("AMORDRINE", 5, 500, 11F, 7F, 50);
	public static final ItemTool.ToolMaterial angmallenToolMaterial = EnumHelper.addToolMaterial("ANGMALLEN", 3, 300, 7F, 6F, 30);
	public static final ItemTool.ToolMaterial astralSilverToolMaterial = EnumHelper.addToolMaterial("ASTRAL_SILVER", 5, 35, 12F, 5F, 30);
	public static final ItemTool.ToolMaterial atlarusToolMaterial = EnumHelper.addToolMaterial("ATLARUS", 7, 1750, 9F, 8F, 22);
	public static final ItemTool.ToolMaterial blackSteelToolMaterial = EnumHelper.addToolMaterial("BLACK_STEEL", 3, 500, 6.5F, 6F, 17);
	public static final ItemTool.ToolMaterial brassToolMaterial = EnumHelper.addToolMaterial("BRASS", 1, 15, 12F, 5F, 18);
	public static final ItemTool.ToolMaterial bronzeToolMaterial = EnumHelper.addToolMaterial("BRONZE", 3, 200, 6.5F, 5F, 9);
	public static final ItemTool.ToolMaterial carmotToolMaterial = EnumHelper.addToolMaterial("CARMOT", 5, 50, 10F, 5F, 40);
	public static final ItemTool.ToolMaterial celenegilToolMaterial = EnumHelper.addToolMaterial("CELENEGIL", 6, 1600, 10F, 7F, 50);
	public static final ItemTool.ToolMaterial damascusSteelToolMaterial = EnumHelper.addToolMaterial("DAMASCUS_STEEL", 3, 500, 6.5F, 6F, 15);
	public static final ItemTool.ToolMaterial deepIronToolMaterial = EnumHelper.addToolMaterial("DEEP_IRON", 3, 250, 6.5F, 6F, 14);
	public static final ItemTool.ToolMaterial desichalkosToolMaterial = EnumHelper.addToolMaterial("DESICHALKOS", 4, 1800, 8F, 8F, 30);
	public static final ItemTool.ToolMaterial electrumToolMaterial = EnumHelper.addToolMaterial("ELECTRUM", 2, 100, 13F, 5F, 30);
	public static final ItemTool.ToolMaterial eximiteToolMaterial = EnumHelper.addToolMaterial("EXIMITE", 7, 1000, 8F, 7F, 25);
	public static final ItemTool.ToolMaterial haderothToolMaterial = EnumHelper.addToolMaterial("HADEROTH", 5, 1250, 11F, 7F, 19);
	public static final ItemTool.ToolMaterial hepatizonToolMaterial = EnumHelper.addToolMaterial("HEPATIZON", 3, 300, 8F, 5F, 11);
	public static final ItemTool.ToolMaterial ignatiusToolMaterial = EnumHelper.addToolMaterial("IGNATIUS", 2, 150, 5F, 6F, 15);
    public static final ItemTool.ToolMaterial inolashiteToolMaterial = EnumHelper.addToolMaterial("INOLASHITE", 5, 900, 7F, 7F, 25);
	public static final ItemTool.ToolMaterial kalendriteToolMaterial = EnumHelper.addToolMaterial("KALENDRITE", 5, 1000, 7F, 7F, 20);
	public static final ItemTool.ToolMaterial midasiumToolMaterial = EnumHelper.addToolMaterial("MIDASIUM", 3, 100, 9F, 7F,35);
	public static final ItemTool.ToolMaterial mithrilToolMaterial = EnumHelper.addToolMaterial("MITHRIL", 5, 1000, 8F, 7F, 18);
	public static final ItemTool.ToolMaterial orichalcumToolMaterial = EnumHelper.addToolMaterial("ORICHALCUM", 6, 1350, 9F, 7F, 20);
	public static final ItemTool.ToolMaterial oureclaseToolMaterial = EnumHelper.addToolMaterial("OURECLASE", 4, 750, 9F, 6F, 18);
	public static final ItemTool.ToolMaterial platinumToolMaterial = EnumHelper.addToolMaterial("PLATINUM", 3, 100, 14F, 5F, 50);
	public static final ItemTool.ToolMaterial prometheumToolMaterial = EnumHelper.addToolMaterial("PROMETHEUM", 2, 200, 5F, 5F, 16);
	public static final ItemTool.ToolMaterial sanguiniteToolMaterial = EnumHelper.addToolMaterial("SANGUINITE", 6, 1750, 11F, 8F, 25);
	public static final ItemTool.ToolMaterial shadowIronToolMaterial = EnumHelper.addToolMaterial("SHADOW_IRON", 2, 300, 4F, 6F, 2);
	public static final ItemTool.ToolMaterial shadowSteelToolMaterial = EnumHelper.addToolMaterial("SHADOW_STEEL", 3,  400, 6F, 7F, 5);
	public static final ItemTool.ToolMaterial silverToolMaterial = EnumHelper.addToolMaterial("SILVER", 1, 25, 13F, 5F, 20);
	public static final ItemTool.ToolMaterial steelToolMaterial = EnumHelper.addToolMaterial("STEEL", 4, 750, 7.5F, 7F, 18);
	public static final ItemTool.ToolMaterial tartariteToolMaterial = EnumHelper.addToolMaterial("TARTARITE", 8, 3000, 11F, 9F, 25);
	public static final ItemTool.ToolMaterial vulcaniteToolMaterial = EnumHelper.addToolMaterial("VULCANITE", 6, 1500, 8.5F, 7F, 20);
	public static final ItemTool.ToolMaterial vyroxeresToolMaterial = EnumHelper.addToolMaterial("VYROXERES", 3, 300, 7F, 7F, 16);


	@Mod.Instance(MODID)
	public static Metallurgy_5 instance;

	@SidedProxy(serverSide = "it.hurts.metallurgy_5.proxy.CommonProxy", clientSide = "it.hurts.metallurgy_5.proxy.ClientProxy")
	public static CommonProxy proxy;

	static {
		FluidRegistry.enableUniversalBucket();
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println(NAME + " is loading!");
		GameRegistry.registerWorldGenerator(new ModWorldGen(),3);
		ModFluids.registerFluids();
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

	@Mod.EventBusSubscriber
	public static class RegsitrationHandler {

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			ModItems.register(event.getRegistry());
			ModBlocks.registerItemBlocks(event.getRegistry());
			ModArmors.register(event.getRegistry());
			ModTools.register(event.getRegistry());
			ModFluids.registerFluids();
		}

		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			ModBlocks.register(event.getRegistry());
		}

		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			ModItems.registerModels();
			ModBlocks.registerModels();
			ModArmors.registerModels();
			ModTools.registerModels();
		}

	}
}
