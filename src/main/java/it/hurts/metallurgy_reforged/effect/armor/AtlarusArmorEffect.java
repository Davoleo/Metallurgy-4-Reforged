package it.hurts.metallurgy_reforged.effect.armor;

import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.util.EventUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;

public class AtlarusArmorEffect extends BaseMetallurgyEffect
{
    public AtlarusArmorEffect()
    {
        super(ModMetals.ATLARUS);
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public boolean isToolEffect()
    {
        return false;
    }

    @Nullable
    @Override
    public EnumTools getToolClass()
    {
        return null;
    }

    @Override
    public void onPlayerTick(EntityPlayer player)
    {
        World world = player.world;

        if(!player.isCreative() && EventUtils.isPlayerWearingArmor(player, metal) && player.fallDistance >= 4F)
        {
            AxisAlignedBB nearCollitions = player.getEntityBoundingBox().contract(0, 1.7D, 0).offset(0, -4D, 0);
            if(world.collidesWithAnyBlock(nearCollitions))
            {
                double motionX = 2D - Math.random() * 4D;
                double motionZ = 2D - Math.random() * 4D;


                if(!world.isRemote)
                {
                    System.out.println("sono server");
                    player.motionX = motionX;
                    player.motionZ = motionZ;
                    player.velocityChanged = true;


                    if(world instanceof WorldServer)
                    {
                        for (int i = 0; i < 10; i++)
                        {
                            double particleX = player.posX + (Math.random() - 0.5D) * (double) player.width;
                            double particleY = player.posY + Math.random() * (double) player.height;
                            double particleZ = player.posZ + (Math.random() - 0.5D) * (double) player.width;
                            // TODO: 19/07/2020 implement particle motion system (Custom Client Packet)  
                            ((WorldServer) world).spawnParticle(EnumParticleTypes.CLOUD, true, particleX, particleY, particleZ, 4, 0, 0D, 0, 0D);
                        }
                    }
                }
                player.fallDistance = 0F;


            }

        }
    }
}
