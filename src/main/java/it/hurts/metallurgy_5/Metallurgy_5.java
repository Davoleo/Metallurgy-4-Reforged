package it.hurts.metallurgy_5;

import it.hurts.metallurgy_5.block.ModBlocks;
import it.hurts.metallurgy_5.item.ModItems;
import it.hurts.metallurgy_5.item.armor.ModArmors;
import it.hurts.metallurgy_5.item.tool.ModTools;
import it.hurts.metallurgy_5.proxy.CommonProxy;
import it.hurts.metallurgy_5.util.ModRecipes;
import it.hurts.metallurgy_5.util.tabs.*;
import it.hurts.metallurgy_5.world.ModWorldGen;
import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
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
	public static final String NAME = "Metallurgy 5";
	public static final String VERSION = "0.0.1";

	//CreativeTabs
	public static final TabIngot tabIngot = new TabIngot();
	public static final TabBlock tabBlock = new TabBlock();
	public static final TabOre tabOre = new TabOre();
	public static final TabArmor tabArmor = new TabArmor();
	public static final TabTool tabTool = new TabTool();

//	Armor
	public static final ItemArmor.ArmorMaterial adamantineArmorMaterial = EnumHelper.addArmorMaterial("ADAMANTINE", MODID + ":adamantine", 36, new int[]{3, 4, 5, 3}, 8, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.5F);
	public static final ItemArmor.ArmorMaterial astralSilverArmorMaterial = EnumHelper.addArmorMaterial("ASTRAL_SILVER", MODID + ":astral_silver", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ItemArmor.ArmorMaterial atlarusArmorMaterial = EnumHelper.addArmorMaterial("ATLARUS", MODID + ":atlarus", 35, new int[]{4, 3, 3, 4}, 2, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.5F);
	public static final ItemArmor.ArmorMaterial carmotArmorMaterial = EnumHelper.addArmorMaterial("CARMOT", MODID + ":carmot", 28, new int[]{2, 4, 5, 2}, 7, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ItemArmor.ArmorMaterial deepIronArmorMaterial = EnumHelper.addArmorMaterial("DEEP_IRON", MODID + ":deep_iron", 38, new int[]{2, 4, 5, 2}, 1, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ItemArmor.ArmorMaterial eximiteArmorMaterial = EnumHelper.addArmorMaterial("EXIMITE", MODID + ":eximite", 39, new int[]{4, 5, 6, 4}, 25, SoundEvents.ENTITY_ENDERDRAGON_GROWL, 5.0F);
	public static final ItemArmor.ArmorMaterial ignatiusArmorMaterial = EnumHelper.addArmorMaterial("IGNATIUS", MODID + ":ignatius", 24, new int[]{2, 5, 6, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ItemArmor.ArmorMaterial kalendriteArmorMaterial = EnumHelper.addArmorMaterial("KALENDRITE", MODID + ":kalendrite", 40, new int[]{4, 5, 6, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON,3.0F);
	public static final ItemArmor.ArmorMaterial midasiumArmorMaterial = EnumHelper.addArmorMaterial("MIDASIUM", MODID +  ":midasium", 16, new int[]{3, 3, 5, 2}, 35, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.0F);
	public static final ItemArmor.ArmorMaterial mithrilArmorMaterial = EnumHelper.addArmorMaterial("MITHRIL", MODID + ":mithril", 21, new int[]{2, 4, 5, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ItemArmor.ArmorMaterial orichalcumArmorMaterial = EnumHelper.addArmorMaterial("ORICHALCUM", MODID + ":mithril", 20, new int[]{2, 6, 7, 2}, 4, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.4F);
	public static final ItemArmor.ArmorMaterial oureclaseArmorMaterial = EnumHelper.addArmorMaterial("OURECLASE", MODID + ":oureclase", 28, new int[]{3, 6, 5, 4}, 2, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.5F);
	public static final ItemArmor.ArmorMaterial platinumArmorMaterial = EnumHelper.addArmorMaterial("PLATINUM", MODID + ":platinum", 17, new int[]{3, 5, 6, 3}, 12, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5F);
	public static final ItemArmor.ArmorMaterial prometheumArmorMaterial = EnumHelper.addArmorMaterial("PROMETHEUM", MODID + ":prometherum", 30, new int[]{1, 2, 3, 2}, 11 ,SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.1F);
	public static final ItemArmor.ArmorMaterial sanguiniteArmorMaterial = EnumHelper.addArmorMaterial("SANGUINITE", MODID + ":sanguinite", 58, new int[]{4, 6, 7, 5}, 25, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
	public static final ItemArmor.ArmorMaterial shadowIronArmorMaterial = EnumHelper.addArmorMaterial("SHADOW_IRON", MODID + ":shadow_iron", 32, new int[]{4, 5, 6, 3}, 3, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.0F);
	public static final ItemArmor.ArmorMaterial silverArmorMaterial = EnumHelper.addArmorMaterial("SILVER", MODID + ":silver", 8, new int[]{2, 3, 4, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.1F);
	public static final ItemArmor.ArmorMaterial vulcaniteArmorMaterial = EnumHelper.addArmorMaterial("VULCANITE", MODID + ":vulcanite", 55, new int[]{4, 6, 7, 4}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.0F);
	public static final ItemArmor.ArmorMaterial vyroxeresArmorMaterial = EnumHelper.addArmorMaterial("VIROXERES", MODID + ":vyroxeres", 37, new int[]{4, 5, 6, 3}, 16, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.0F);
	
//	Tool
	public static final ItemTool.ToolMaterial adamantineToolMaterial = EnumHelper.addToolMaterial("ADAMANTINE", 7, 1550, 8F, 8F, 22);
	public static final ItemTool.ToolMaterial astralSilverToolMaterial = EnumHelper.addToolMaterial("ASTRAL_SILVER", 5, 35, 12F, 5F, 30);
	public static final ItemTool.ToolMaterial atlarusToolMaterial = EnumHelper.addToolMaterial("ATLARUS", 7, 1750, 9F, 8F, 22);
	public static final ItemTool.ToolMaterial carmotToolMaterial = EnumHelper.addToolMaterial("CARMOT", 5, 50, 10F, 5F, 40);
	public static final ItemTool.ToolMaterial deepIronToolMaterial = EnumHelper.addToolMaterial("DEEP_IRON", 3, 250, 6.5F, 6F, 14);
	public static final ItemTool.ToolMaterial eximiteToolMaterial = EnumHelper.addToolMaterial("EXIMITE", 7, 1000, 8F, 7F, 25);
	public static final ItemTool.ToolMaterial ignatiusToolMaterial = EnumHelper.addToolMaterial("IGNATIUS", 2, 150, 5F, 6F, 15);
	public static final ItemTool.ToolMaterial kalendriteToolMaterial = EnumHelper.addToolMaterial("KALENDRITE", 5, 1000, 7F, 7F, 20);
	public static final ItemTool.ToolMaterial midasiumToolMaterial = EnumHelper.addToolMaterial("MIDASIUM", 3, 100, 9F, 7F,35);
	public static final ItemTool.ToolMaterial mithrilToolMaterial = EnumHelper.addToolMaterial("MITHRIL", 5, 1000, 8F, 7F, 18);
	public static final ItemTool.ToolMaterial orichalcumToolMaterial = EnumHelper.addToolMaterial("ORICHALCUM", 6, 1350, 9F, 7F, 20);
	public static final ItemTool.ToolMaterial oureclaseToolMaterial = EnumHelper.addToolMaterial("OURECLASE", 4, 750, 9F, 6F, 18);
	public static final ItemTool.ToolMaterial platinumToolMaterial = EnumHelper.addToolMaterial("PLATINUM", 3, 100, 14F, 5F, 50);
	public static final ItemTool.ToolMaterial prometheumToolMaterial = EnumHelper.addToolMaterial("PROMETHEUM", 2, 200, 5F, 5F, 16);
	public static final ItemTool.ToolMaterial sanguiniteToolMaterial = EnumHelper.addToolMaterial("SANGUINITE", 6, 1750, 11F, 8F, 25);
	public static final ItemTool.ToolMaterial shadowIronToolMaterial = EnumHelper.addToolMaterial("SHADOW_IRON", 2, 300, 4F, 6F, 2);
	public static final ItemTool.ToolMaterial silverToolMaterial = EnumHelper.addToolMaterial("SILVER", 1, 25, 13F, 5F, 20);
	public static final ItemTool.ToolMaterial vulcaniteToolMaterial = EnumHelper.addToolMaterial("VULCANITE", 6, 1500, 8.5F, 7F, 20);
	public static final ItemTool.ToolMaterial vyroxeresToolMaterial = EnumHelper.addToolMaterial("VYROXERES", 3, 300, 7F, 7F, 16);


	@Mod.Instance(MODID)
	public static Metallurgy_5 instance;

	@SidedProxy(serverSide = "it.hurts.metallurgy_5.proxy.CommonProxy", clientSide = "it.hurts.metallurgy_5.proxy.ClientProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println(NAME + " is loading!");
		GameRegistry.registerWorldGenerator(new ModWorldGen(),3);
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
