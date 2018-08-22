package it.hurts.metallurgy_5.item.armor;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.util.EventHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ItemArmorBase extends net.minecraft.item.ItemArmor{

	private String name;

	public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String name) {
		super(material, 0, slot);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.name = name;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerItemModel(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy_5.MODID + ":armor/" + name, "inventory"));
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack item) {
		
		if (player.inventory.armorItemInSlot(3).getItem() == ModArmors.astral_silver_helmet
			&&player.inventory.armorItemInSlot(2).getItem() == ModArmors.astral_silver_chest
			&&player.inventory.armorItemInSlot(1).getItem() == ModArmors.astral_silver_legs
			&&player.inventory.armorItemInSlot(0).getItem() == ModArmors.astral_silver_boots){
			player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 20, 2));
		}else {
			player.removeActivePotionEffect(MobEffects.JUMP_BOOST);
		}
		
		if (player.inventory.armorItemInSlot(3).getItem() == ModArmors.prometheum_helmet
			&&player.inventory.armorItemInSlot(2).getItem() == ModArmors.prometheum_chest
			&&player.inventory.armorItemInSlot(1).getItem() == ModArmors.prometheum_legs
			&&player.inventory.armorItemInSlot(0).getItem() == ModArmors.prometheum_boots){
			player.removePotionEffect(MobEffects.POISON);
		}
		
		if (player.inventory.armorItemInSlot(3).getItem() == ModArmors.carmot_helmet
			&&player.inventory.armorItemInSlot(2).getItem() == ModArmors.carmot_chest
			&&player.inventory.armorItemInSlot(1).getItem() == ModArmors.carmot_legs
			&&player.inventory.armorItemInSlot(0).getItem() == ModArmors.carmot_boots) {
			player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 20, 1));
		}else {
			player.removePotionEffect(MobEffects.HASTE);
		}
		
		if (player.inventory.armorItemInSlot(3).getItem() == ModArmors.adamantine_helmet
				&&player.inventory.armorItemInSlot(2).getItem() == ModArmors.adamantine_chest
				&&player.inventory.armorItemInSlot(1).getItem() == ModArmors.adamantine_legs
				&&player.inventory.armorItemInSlot(0).getItem() == ModArmors.adamantine_boots) {
				player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 20, 1));
			}else {
				player.removePotionEffect(MobEffects.SATURATION);
			}
	}

//	@SideOnly(Side.CLIENT)
//    public static String getPotionDurationString(PotionEffect effect, float durationFactor)
//    {
//        if (effect.getDuration()==20)
//        {
//            return "**:**";
//        }else {
//        	return "**:**";
//        }
//		
//    }
	
}
