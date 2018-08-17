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

	
	public static void register(IForgeRegistry<Item> registry) {
		registry.registerAll(
				astral_silver_helmet,
				astral_silver_chest,
				astral_silver_legs,
				astral_silver_boots
		);
	}
	
	public static void registerModels() {
		astral_silver_helmet.registerItemModel(astral_silver_helmet);
		astral_silver_chest.registerItemModel(astral_silver_chest);
		astral_silver_legs.registerItemModel(astral_silver_legs);
		astral_silver_boots.registerItemModel(astral_silver_boots);
	}
	
//	ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy_5.MODID + ":armor/" + id, "inventory"));
	
}
