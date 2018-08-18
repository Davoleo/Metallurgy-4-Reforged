package hurts.metallurgy_5.armor;

import hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModArmor {

	public static ItemArmorBase astral_silver_helmet = new ItemArmorBase(Metallurgy_5.astralSilverArmorMaterial, EntityEquipmentSlot.HEAD, "astral_silver_helmet");
	public static ItemArmorBase astral_silver_chest = new ItemArmorBase(Metallurgy_5.astralSilverArmorMaterial, EntityEquipmentSlot.CHEST, "astral_silver_chest");
	public static ItemArmorBase astral_silver_legs = new ItemArmorBase(Metallurgy_5.astralSilverArmorMaterial, EntityEquipmentSlot.LEGS, "astral_silver_legs");
	public static ItemArmorBase astral_silver_boots = new ItemArmorBase(Metallurgy_5.astralSilverArmorMaterial, EntityEquipmentSlot.FEET, "astral_silver_boots");
	
	public static ItemArmorBase prometheum_helmet = new ItemArmorBase(Metallurgy_5.prometheumMaterial, EntityEquipmentSlot.HEAD, "prometheum_helmet");
	public static ItemArmorBase prometheum_chest = new ItemArmorBase(Metallurgy_5.prometheumMaterial, EntityEquipmentSlot.CHEST, "prometheum_helmet");
	public static ItemArmorBase prometheum_legs = new ItemArmorBase(Metallurgy_5.prometheumMaterial, EntityEquipmentSlot.LEGS, "prometheum_helmet");
	public static ItemArmorBase prometheum_boots = new ItemArmorBase(Metallurgy_5.prometheumMaterial, EntityEquipmentSlot.FEET, "prometheum_helmet");
	
	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
				astral_silver_helmet,
				astral_silver_chest,
				astral_silver_legs,
				astral_silver_boots,
				
				prometheum_helmet,
				prometheum_chest,
				prometheum_legs,
				prometheum_boots
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
		
	}
	
}
