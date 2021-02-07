/*==============================================================================
 = Class: AtlarusAxeEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.effect.EnumEffectCategory;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

@Deprecated
public class AtlarusAxeEffect extends BaseMetallurgyEffect {

    public AtlarusAxeEffect() {
        super(ModMetals.ATLARUS);
    }

    @Nonnull
    @Override
    public EnumEffectCategory getCategory() {
        return null;
    }


    public void onPlayerInteract(PlayerInteractEvent.RightClickItem event)
    {
        World world = event.getWorld();
        ItemStack stack = event.getItemStack();
        EntityPlayer player = event.getEntityPlayer();
        if (stack.getItem() == metal.getTool(EnumTools.AXE))
        {
            Vec3d eyePosition = player.getPositionEyes(1.0F);
            Vec3d scaledLookVec = player.getLookVec().scale(30D);
            Vec3d targetPos = new Vec3d(eyePosition.x + scaledLookVec.x, eyePosition.y + scaledLookVec.y, eyePosition.z + scaledLookVec.z);


            RayTraceResult result = world.rayTraceBlocks(eyePosition, targetPos, false, true, true);

                player.swingArm(event.getHand());


                if (result != null) {
                    targetPos = new Vec3d(result.getBlockPos());

                    BlockPos.getAllInBox(result.getBlockPos().add(-2, -2, -2), result.getBlockPos().add(2, 2, 2)).forEach(leavesPos ->
                    {
                        IBlockState state = world.getBlockState(leavesPos);
                        if ((state.getBlock() instanceof BlockLeaves || state.getBlock() instanceof BlockVine) && !world.isRemote) {
                            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0) {
                                int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
                                List<ItemStack> drops = ((IShearable) state.getBlock()).onSheared(stack, world, leavesPos, fortune);

                                for (ItemStack drop : drops)
                                {
                                    float f = 0.7F;
                                    double d = Utils.random.nextFloat() * f + (1.0F - f) * 0.5D;
                                    double d1 = Utils.random.nextFloat() * f + (1.0F - f) * 0.5D;
                                    double d2 = Utils.random.nextFloat() * f + (1.0F - f) * 0.5D;
                                    EntityItem entityitem = new EntityItem(player.getEntityWorld(), leavesPos.getX() + d, leavesPos.getY() + d1, leavesPos.getZ() + d2, drop);
                                    entityitem.setDefaultPickupDelay();
                                    world.spawnEntity(entityitem);
                                }
                            }
                            stack.damageItem(1, player);
                        }
                    });
                }
                if (world.isRemote) {
                    Random random = new Random();
                    Vec3d particleVec = player.getLookVec().scale(1.3D);

                    for (int j = 0; j < 170; ++j)
                    {
                        double d6 = (double) j / 169.0D;
                        double d3 = eyePosition.x + (targetPos.x - player.posX) * d6 + (random.nextDouble() - 0.5D) * 2.0D;
                        double d4 = eyePosition.y + (targetPos.y - player.posY) * d6 + random.nextDouble() * 2.0D - 1D;
                        double d5 = eyePosition.z + (targetPos.z - player.posZ) * d6 + (random.nextDouble() - 0.5D) * 2.0D;
                        player.world.spawnAlwaysVisibleParticle(EnumParticleTypes.CLOUD.getParticleID(), d3, d4, d5, particleVec.x, particleVec.y, particleVec.z);
                    }
                }
                else
                    world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.AMBIENT, 1F, 0.9F);
        }
    }
}
