package hurts.metallurgy_5.armor;

import hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemArmorBase extends net.minecraft.item.ItemArmor{

	private String name;

	public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String name) {
		super(material, 0, slot);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.name = name;
	}
	
	public void registerItemModel(Item item) {
		Metallurgy_5.proxy.registerItemRenderer(this, 0, name);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack item) {
		if (player.inventory.armorItemInSlot(3).getItem() == ModArmor.astral_silver_helmet 
				&&player.inventory.armorItemInSlot(2).getItem() == ModArmor.astral_silver_chest
				&&player.inventory.armorItemInSlot(1).getItem() == ModArmor.astral_silver_legs
				&&player.inventory.armorItemInSlot(0).getItem() == ModArmor.astral_silver_boots){
				player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 0, 2));
				}else {
					
					player.removeActivePotionEffect(MobEffects.JUMP_BOOST);
					
				}
		}


	
}
