package it.hurts.metallurgy_reforged.integration.mods.tic.trait;

import javax.annotation.Nullable;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;

/***************************
 *
 * Author : ItHurtsLikeHell
 * Project: Metallurgy-4-Reforged
 * Date   : 25 gen 2019
 * Time   : 16:19:39
 *
 ***************************/
public class TraitKingDice extends AbstractTrait implements ITrait{

	public TraitKingDice() {
		super("king_dice_trait", TextFormatting.WHITE);
	}
	
	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt,boolean wasCritical, boolean wasHit) {
		target.addPotionEffect(new PotionEffect(Utils.getRandomEffect(), 80, 0));
	}
	
	@Override
	public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
//		TODO Aggiungere la possibilità di aggiungere al player effetti positivi, o negativi, randomicamente.
//		Si potrebbe eliminare questo metodo
	}
	
	@Override
    public void register(String name, @Nullable String tooltip){
        Utils.localize(String.format(LOC_Name, name));
        if (tooltip != null)
            Utils.localize(String.format(LOC_Name, tooltip));
    }

}
