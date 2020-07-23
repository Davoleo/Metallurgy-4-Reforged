package it.hurts.metallurgy_reforged.effect.tool;

import it.hurts.metallurgy_reforged.config.ToolEffectsConfig;
import it.hurts.metallurgy_reforged.effect.BaseMetallurgyEffect;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.EnumTools;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class AtlarusSwordEffect extends BaseMetallurgyEffect
{
    public AtlarusSwordEffect()
    {
        super(ModMetals.ATLARUS);
    }

    @Override
    public boolean isEnabled()
    {
        return ToolEffectsConfig.atlarusSwordEffect;
    }

    @Override
    public boolean isToolEffect()
    {
        return true;
    }

    @Override
    public EnumTools getToolClass()
    {
        return EnumTools.SWORD;
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if(event instanceof PlayerInteractEvent.RightClickItem)
        {
            World world = event.getWorld();
            EntityPlayer player = event.getEntityPlayer();
            if(event.getItemStack().getItem() == metal.getTool(EnumTools.SWORD))
            {


                AxisAlignedBB box = new AxisAlignedBB(player.posX, player.posY, player.posZ, player.posX, player.posY, player.posZ).grow(5D);
                for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(player, box))
                {
                    double motionX = MathHelper.clamp(entity.posX - player.posX, -1, 1);
                    double motionZ = MathHelper.clamp(entity.posZ - player.posZ, -1, 1);
                    if(!world.isRemote)
                    {
                        entity.motionX = motionX * 1.4D;
                        entity.motionY = 0.4D;
                        entity.motionZ = motionZ * 1.4D;
                        entity.velocityChanged = true;
                    }
                    else
                    {
                        for(int i = 0; i < 4;i++)
                        {
                            double particleX = entity.posX + (Math.random() - 0.5D) * entity.width;
                            double particleY = entity.posY + Math.random() * entity.height;
                            double particleZ = entity.posZ + (Math.random() - 0.5D) * entity.width;
                            world.spawnParticle(EnumParticleTypes.CLOUD, particleX, particleY, particleZ, motionX, 0, motionZ);
                        }
                    }
                }

                if(!world.isRemote)
                     world.playSound(null,player.posX,player.posY,player.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.AMBIENT,1F,0.9F);


                for(int i = 0; i < 60;i++)
                {
                    world.spawnParticle(EnumParticleTypes.CLOUD,player.posX,player.posY + 0.4D,player.posZ,0.7D - Math.random() * 1.4D,0.1D,0.7D - Math.random() * 1.4D);
                }

                player.getCooldownTracker().setCooldown(metal.getTool(EnumTools.SWORD),20 * 2);
            }
        }
    }
}
