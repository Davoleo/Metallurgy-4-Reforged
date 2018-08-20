package it.hurts.metallurgy_5;

import it.hurts.metallurgy_5.armor.ModArmors;
import it.hurts.metallurgy_5.block.ModBlocks;
import it.hurts.metallurgy_5.item.ModItems;
import it.hurts.metallurgy_5.item.tool.ModTools;
import it.hurts.metallurgy_5.proxy.CommonProxy;
import hurts.metallurgy_5.util.tabs.*;
import it.hurts.metallurgy_5.world.ModWorldGen;
import it.hurts.metallurgy_5.util.tabs.*;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = Metallurgy_5.MODID, name = Metallurgy_5.NAME, version = Metallurgy_5.VERSION, acceptedMinecraftVersions = "[1.12]")
public class Metallurgy_5 {

	public static final String MODID = "m5";
	public static final String NAME = "Metallurgy 5";
	public static final String VERSION = "1.0.0";

	//CreativeTabs
	public static final TabIngot tabIngot = new TabIngot();
	public static final TabBlock tabBlock = new TabBlock();
	public static final TabOre tabOre = new TabOre();
	public static final TabArmor tabArmor = new TabArmor();
	public static final TabTool tabTool = new TabTool();
    	
//	Armor
	public static final ItemArmor.ArmorMaterial adamantineArmorMaterial = EnumHelper.addArmorMaterial("ADAMANTINE", MODID + ":adamantine", 27, new int[]{3, 4, 5, 3}, 8, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.5F);
	public static final ItemArmor.ArmorMaterial astralSilverArmorMaterial = EnumHelper.addArmorMaterial("ASTRAL_SILVER", MODID + ":astral_silver", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ItemArmor.ArmorMaterial carmotArmorMaterial = EnumHelper.addArmorMaterial("CARMOT", MODID + ":carmot", 17, new int[]{2, 4, 5, 2}, 7, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ItemArmor.ArmorMaterial mithrilArmorMaterial = EnumHelper.addArmorMaterial("MITHRIL", MODID + ":mithril", 20, new int[]{2, 4, 5, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ItemArmor.ArmorMaterial prometheumArmorMaterial = EnumHelper.addArmorMaterial("PROMETHEUM", MODID + ":prometherum", 30, new int[]{1, 2, 3, 2}, 11 ,SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.1F);


//	Tool
	public static final ItemTool.ToolMaterial adamantineToolMaterial = EnumHelper.addToolMaterial("ADAMANTINE", 3, 800, 8F, 10F, 4);
	
	@Mod.Instance(MODID)
	public static Metallurgy_5 instance;

	@SidedProxy(serverSide = "CommonProxy", clientSide = "ClientProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println(NAME + " is loading!");
		GameRegistry.registerWorldGenerator(new ModWorldGen(),3);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		
	}


	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event, LivingHurtEvent e, EntityPlayer player) {

		//TODO : Move method in another class
		DamageSource source = e.getSource();

		if (player.inventory.armorItemInSlot(3).getItem() == ModArmors.prometheum_helmet
				&&player.inventory.armorItemInSlot(2).getItem() == ModArmors.prometheum_chest
				&&player.inventory.armorItemInSlot(1).getItem() == ModArmors.prometheum_legs
				&&player.inventory.armorItemInSlot(0).getItem() == ModArmors.prometheum_boots) {
			if (source == DamageSource.MAGIC) {
				player.removeActivePotionEffect(MobEffects.POISON);
				player.setAbsorptionAmount(2);
		}
	}
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
