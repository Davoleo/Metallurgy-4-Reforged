/*==============================================================================
 = Class: ItemCeruclaseShield
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.item.gadget.shield;

import it.hurts.metallurgy_reforged.block.ModBlocks;
import it.hurts.metallurgy_reforged.item.ModItems;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class ItemCeruclaseShield extends ItemShieldBase {

	public ItemCeruclaseShield()
	{
		super("ceruclase_shield", 500);
	}

	@Override
	public int getItemEnchantability()
	{
		return 18;
	}

	@Override
	public int getMaxItemUseDuration(@Nonnull ItemStack stack)
	{
		return 200;
	}

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, EntityPlayer playerIn, @Nonnull EnumHand handIn) {
		if (!worldIn.isRemote) {
			ItemStack stack = playerIn.getHeldItem(handIn);
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null)
				tag = new NBTTagCompound();

			if (!tag.hasKey("playerPos")) {
				BlockPos playerPos = playerIn.getPosition();
				tag.setLong("playerPos", playerPos.toLong());
				stack.setTagCompound(tag);

				playerIn.fallDistance /= 2F;
				manageShield(worldIn, new BlockPos.MutableBlockPos(playerPos), false);
			}
		}

		return super.onItemRightClick(worldIn, playerIn, handIn);
	}


	//---------------------
	@Override
	public void onPlayerStoppedUsing(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull EntityLivingBase entityLiving, int timeLeft) {
		super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
		((EntityPlayer) entityLiving).getCooldownTracker().setCooldown(ModItems.ceruclaseShield, 140);
		removeTagAndShield(worldIn, stack);
	}

	public static void removeTagAndShield(World world, ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();

		if (world.isRemote || tag == null || !tag.hasKey("playerPos"))
			return;

		BlockPos pos = BlockPos.fromLong(tag.getLong("playerPos"));
		tag.removeTag("playerPos");

		manageShield(world, new BlockPos.MutableBlockPos(pos), true);
	}

	//---------------------

	private static final int RANGE = 5;

	private static void manageShield(World world, BlockPos.MutableBlockPos playerPos, boolean destroy)
	{
		for (int x = -RANGE - 1; x < RANGE + 1; x++)
		{
			for (int y = -RANGE - 1; y < RANGE + 1; y++)
			{
				for (int z = -RANGE - 1; z < RANGE + 1; z++)
				{
					BlockPos blockPos = playerPos.add(x, y, z);
					IBlockState blockState = world.getBlockState(blockPos);
					if (Math.ceil(blockPos.getDistance(playerPos.getX(), playerPos.getY(), playerPos.getZ())) == RANGE)
					{
						boolean canPlaceIce = world.mayPlace(ModBlocks.iceShield, blockPos, true, EnumFacing.UP, null) || blockState.getBlock() instanceof BlockBush;

						if (!destroy)
						{
							if (canPlaceIce)
							{
								world.setBlockState(blockPos, ModBlocks.iceShield.getDefaultState());
							}

						}
						else if (blockState.getBlock() == ModBlocks.iceShield)
						{
							Random random = new Random();
							world.scheduleUpdate(blockPos, ModBlocks.iceShield, 10 + random.nextInt(11));
						}
					}
				}
			}
		}

		if (!destroy)
			world.playSound(null, playerPos, SoundEvents.BLOCK_GLASS_PLACE, SoundCategory.BLOCKS, 1F, 2F);
	}

}
