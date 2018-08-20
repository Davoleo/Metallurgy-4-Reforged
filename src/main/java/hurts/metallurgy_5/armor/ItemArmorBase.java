package hurts.metallurgy_5.armor;

import hurts.metallurgy_5.Metallurgy_5;
import hurts.metallurgy_5.util.EventHandler;
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
		
		boolean b = true;
		
		PlayerTickEvent event = new PlayerTickEvent(null, player);
		
		if (player.inventory.armorItemInSlot(3).getItem() == ModArmor.astral_silver_helmet 
			&&player.inventory.armorItemInSlot(2).getItem() == ModArmor.astral_silver_chest
			&&player.inventory.armorItemInSlot(1).getItem() == ModArmor.astral_silver_legs
			&&player.inventory.armorItemInSlot(0).getItem() == ModArmor.astral_silver_boots){
			player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 20, 2));
		}else {
			player.removeActivePotionEffect(MobEffects.JUMP_BOOST);
		}
		
		if (player.inventory.armorItemInSlot(3).getItem() == ModArmor.prometheum_helmet 
			&&player.inventory.armorItemInSlot(2).getItem() == ModArmor.prometheum_chest
			&&player.inventory.armorItemInSlot(1).getItem() == ModArmor.prometheum_legs
			&&player.inventory.armorItemInSlot(0).getItem() == ModArmor.prometheum_boots){
			player.removePotionEffect(MobEffects.POISON);
		}
		
		if (player.inventory.armorItemInSlot(3).getItem() == ModArmor.mithril_helmet 
			&&player.inventory.armorItemInSlot(2).getItem() == ModArmor.mithril_chest
			&&player.inventory.armorItemInSlot(1).getItem() == ModArmor.mithril_legs
			&&player.inventory.armorItemInSlot(0).getItem() == ModArmor.mithril_boots){
			b=true;
			}else{
				b=false;
			}
		EventHandler.checkEntity(event, b);
		
		if (player.inventory.armorItemInSlot(3).getItem() == ModArmor.carmot_helmet 
			&&player.inventory.armorItemInSlot(2).getItem() == ModArmor.carmot_chest
			&&player.inventory.armorItemInSlot(1).getItem() == ModArmor.carmot_legs
			&&player.inventory.armorItemInSlot(0).getItem() == ModArmor.carmot_boots) {
			player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 20, 1));
		}else {
			player.removePotionEffect(MobEffects.HASTE);
		}
		
		if (player.inventory.armorItemInSlot(3).getItem() == ModArmor.adamantine_helmet 
				&&player.inventory.armorItemInSlot(2).getItem() == ModArmor.adamantine_chest
				&&player.inventory.armorItemInSlot(1).getItem() == ModArmor.adamantine_legs
				&&player.inventory.armorItemInSlot(0).getItem() == ModArmor.adamantine_boots) {
				player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 20, 1));
			}else {
				player.removePotionEffect(MobEffects.SATURATION);
			}
	}

	@SideOnly(Side.CLIENT)
    public static String getPotionDurationString(PotionEffect effect, float durationFactor)
    {
        if (effect.getDuration()==20)
        {
            return "**:**";
        }else {
        	return "**:**";
        }
		
    }
	
}
