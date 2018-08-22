package it.hurts.metallurgy_5.item.armor;

import it.hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModArmors {

	public static ItemArmorBase astral_silver_helmet = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.astralSilverArmorMaterial, EntityEquipmentSlot.HEAD, "astral_silver_helmet").setCreativeTab(Metallurgy_5.tabArmor);
	public static ItemArmorBase astral_silver_chest = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.astralSilverArmorMaterial, EntityEquipmentSlot.CHEST, "astral_silver_chest").setCreativeTab(Metallurgy_5.tabArmor);
	public static ItemArmorBase astral_silver_legs = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.astralSilverArmorMaterial, EntityEquipmentSlot.LEGS, "astral_silver_legs").setCreativeTab(Metallurgy_5.tabArmor);
	public static ItemArmorBase astral_silver_boots = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.astralSilverArmorMaterial, EntityEquipmentSlot.FEET, "astral_silver_boots").setCreativeTab(Metallurgy_5.tabArmor);

	public static ItemArmorBase prometheum_helmet = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.prometheumArmorMaterial, EntityEquipmentSlot.HEAD, "prometheum_helmet").setCreativeTab(Metallurgy_5.tabArmor);
	public static ItemArmorBase prometheum_chest = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.prometheumArmorMaterial, EntityEquipmentSlot.CHEST, "prometheum_chest").setCreativeTab(Metallurgy_5.tabArmor);
	public static ItemArmorBase prometheum_legs = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.prometheumArmorMaterial, EntityEquipmentSlot.LEGS, "prometheum_legs").setCreativeTab(Metallurgy_5.tabArmor);
	public static ItemArmorBase prometheum_boots = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.prometheumArmorMaterial, EntityEquipmentSlot.FEET, "prometheum_boots").setCreativeTab(Metallurgy_5.tabArmor);

	public static ItemArmorBase mithril_helmet = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.mithrilArmorMaterial,EntityEquipmentSlot.HEAD,"mithril_helmet").setCreativeTab(Metallurgy_5.tabArmor);
	public static ItemArmorBase mithril_chest = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.mithrilArmorMaterial,EntityEquipmentSlot.CHEST,"mithril_chest").setCreativeTab(Metallurgy_5.tabArmor);
	public static ItemArmorBase mithril_legs = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.mithrilArmorMaterial,EntityEquipmentSlot.LEGS,"mithril_legs").setCreativeTab(Metallurgy_5.tabArmor);
	public static ItemArmorBase mithril_boots = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.mithrilArmorMaterial,EntityEquipmentSlot.FEET,"mithril_boots").setCreativeTab(Metallurgy_5.tabArmor);

	public static ItemArmorBase carmot_helmet = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.carmotArmorMaterial,EntityEquipmentSlot.HEAD,"carmot_helmet").setCreativeTab(Metallurgy_5.tabArmor);
	public static ItemArmorBase carmot_chest = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.carmotArmorMaterial,EntityEquipmentSlot.CHEST,"carmot_chest").setCreativeTab(Metallurgy_5.tabArmor);
	public static ItemArmorBase carmot_legs = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.carmotArmorMaterial,EntityEquipmentSlot.LEGS,"carmot_legs").setCreativeTab(Metallurgy_5.tabArmor);
	public static ItemArmorBase carmot_boots = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.carmotArmorMaterial,EntityEquipmentSlot.FEET,"carmot_boots").setCreativeTab(Metallurgy_5.tabArmor);

	public static ItemArmorBase adamantine_helmet = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.adamantineArmorMaterial,EntityEquipmentSlot.HEAD,"adamantine_helmet").setCreativeTab(Metallurgy_5.tabArmor);
	public static ItemArmorBase adamantine_chest = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.adamantineArmorMaterial,EntityEquipmentSlot.CHEST,"adamantine_chest").setCreativeTab(Metallurgy_5.tabArmor);
	public static ItemArmorBase adamantine_legs = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.adamantineArmorMaterial,EntityEquipmentSlot.LEGS,"adamantine_legs").setCreativeTab(Metallurgy_5.tabArmor);
	public static ItemArmorBase adamantine_boots = (ItemArmorBase) new ItemArmorBase(Metallurgy_5.adamantineArmorMaterial,EntityEquipmentSlot.FEET,"adamantine_boots").setCreativeTab(Metallurgy_5.tabArmor);

	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
				astral_silver_helmet,
				astral_silver_chest,
				astral_silver_legs,
				astral_silver_boots,
				
				prometheum_helmet,
				prometheum_chest,
				prometheum_legs,
				prometheum_boots,
				
				mithril_helmet,
				mithril_chest,
				mithril_legs,
				mithril_boots,
				
				carmot_helmet,
				carmot_chest,
				carmot_legs,
				carmot_boots,
				
				adamantine_helmet,
				adamantine_chest,
				adamantine_legs,
				adamantine_boots
		);
	}
	
	public static void registerModels() {
		astral_silver_helmet.registerItemModel(astral_silver_helmet, 0);
		astral_silver_chest.registerItemModel (astral_silver_chest, 0);
		astral_silver_legs.registerItemModel(astral_silver_legs, 0);
		astral_silver_boots.registerItemModel(astral_silver_boots, 0);
		
		prometheum_helmet.registerItemModel(prometheum_helmet, 0);
		prometheum_chest.registerItemModel(prometheum_chest, 0);
		prometheum_legs.registerItemModel(prometheum_legs, 0);
		prometheum_boots.registerItemModel(prometheum_boots, 0);
		
		mithril_helmet.registerItemModel(mithril_helmet, 0);
		mithril_chest.registerItemModel(mithril_chest, 0);
		mithril_legs.registerItemModel(mithril_legs, 0);
		mithril_boots.registerItemModel(mithril_boots, 0);
		
		carmot_helmet.registerItemModel(carmot_helmet, 0);
		carmot_chest.registerItemModel(carmot_chest, 0);
		carmot_legs.registerItemModel(carmot_legs, 0);
		carmot_boots.registerItemModel(carmot_boots, 0);
		
		adamantine_helmet.registerItemModel(adamantine_helmet,0);
		adamantine_chest.registerItemModel(adamantine_chest, 0);
		adamantine_legs.registerItemModel(adamantine_legs, 0);
		adamantine_boots.registerItemModel(adamantine_boots, 0);
	}
	
}
