package it.hurts.metallurgy_reforged.integration.mods.tic.trait;

import java.util.ArrayList;

import javax.annotation.Nullable;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 25 gen 2019
 * Time   : 18:51:03
 *
 ***************************/
@EventBusSubscriber(modid=Metallurgy.MODID)
public class TraitDuplication extends AbstractTrait implements ITrait{

	public TraitDuplication() {
		super("duplication_trait", TextFormatting.YELLOW);
	}

	@Override
	public void blockHarvestDrops(ItemStack tool, BlockEvent.HarvestDropsEvent event) {
		ArrayList<ItemStack> drops = new ArrayList<>();

		if((int) (Math.random() * 100) <= 50) {
			for(ItemStack stack : event.getDrops())
			{
				drops.add(stack);
			}
			event.getDrops().addAll(drops);
		}
	}
	
	@SubscribeEvent
    public static void entityDeathDrop(LivingDropsEvent ev){

        DamageSource source = ev.getSource();
        Entity entity = source.getTrueSource();
        
        if(entity instanceof EntityPlayer)
        {

	            ArrayList<EntityItem> drops = new ArrayList<>();
	
//            	Duplica il drop
	            if((int) (Math.random() * 100) <= 50) {
			        for(EntityItem item : ev.getDrops()){
			            EntityItem clone = new EntityItem(item.world, item.posX, item.posY, item.posZ, item.getItem());
			            drops.add(clone);
			        }
		            ev.getDrops().addAll(drops);
	            }
        }
    }

	@Override
	public void register(String name, @Nullable String tooltip){
		Utils.localize(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localize(String.format(LOC_Name, tooltip));
	}
}
