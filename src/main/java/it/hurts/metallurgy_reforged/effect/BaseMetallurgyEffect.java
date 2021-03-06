/*==============================================================================
 = Class: BaseMetallurgyEffect
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.effect;

import com.google.common.base.CaseFormat;
import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.config.EffectsConfig;
import it.hurts.metallurgy_reforged.item.tool.IToolEffect;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.model.EnumTools;
import it.hurts.metallurgy_reforged.network.PacketManager;
import it.hurts.metallurgy_reforged.network.client.PacketSpawnOreParticles;
import it.hurts.metallurgy_reforged.render.font.FontColor;
import it.hurts.metallurgy_reforged.util.EventUtils;
import it.hurts.metallurgy_reforged.util.ItemUtils;
import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;

public abstract class BaseMetallurgyEffect {

    public final String name;
    protected Metal metal;

    public BaseMetallurgyEffect(Metal metal)
    {
        this.metal = metal;

        this.name = metal != null ? Utils.localizeEscapingCustomSequences("tooltip.metallurgy.effect." + metal.toString() + "_" + getCategory().toString() + ".name") : "";

        if (isEnabled())
            MetallurgyEffects.effects.add(this);
    }

    public boolean isEnabled()
    {
        if (metal == null)
            return false;
        else
        {
            String camelMetal = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, metal.toString());

            try
            {
                Field enabledField = EffectsConfig.class.getDeclaredField(camelMetal + "Effect" + Utils.capitalize(getCategory().toString()));
                return enabledField.getBoolean(EffectsConfig.class);
            }
            catch (NoSuchFieldException | IllegalAccessException e)
            {
                Metallurgy.logger.warn("IF YOU SEE THIS MESSAGE | THERE'S SOMETHING WRONG WITH EFFECT CONFIG NAMES, GO FIX IT DAVOLEO");
                //e.printStackTrace();
                return true;
            }
        }
    }


    /**
     * @return The category of tools the effect is applied to
     */
    @Nonnull
    public abstract EnumEffectCategory getCategory();

    public float getLevel(EntityLivingBase entity)
    {
        if (entity == null)
            return 0;

        EnumEffectCategory category = getCategory();

        Item toolItem = entity.getHeldItemMainhand().getItem();

        if (category == EnumEffectCategory.ALL)
        {
            if (EventUtils.getArmorPiecesCount(entity, metal) > 0 || ItemUtils.isMadeOfMetal(metal, toolItem))
                return 1;
            return 0;
        }

        if (category == EnumEffectCategory.ARMOR)
        {
            return EventUtils.getArmorPiecesCount(entity, metal) * 0.25F;
        }
        else
        {
            if (ItemUtils.isMadeOfMetal(metal, toolItem))
            {
                if (toolItem instanceof IToolEffect)
                {
                    IToolEffect tool = ((IToolEffect) toolItem);
                    if (ArrayUtils.contains(category.getTools(), tool.getToolClass()))
                        return 1;
                }
            }
        }

        return 0;
    }

    public boolean canBeApplied(EntityLivingBase entity)
    {
        return getLevel(entity) > 0;
    }

    /**
     * @return A pair of Strings, the first containing the effect name and the second containing its description
     */
    public Pair<String, String> getTooltip()
    {
        String format = FontColor.encodeColor(metal.getStats().getColorHex());
        String description = Utils.localizeEscapingCustomSequences("tooltip.metallurgy.effect." + metal.toString() + "_" + getCategory().toString() + ".desc");
        return ImmutablePair.of(format + name, description);
    }

    /**
     * spawn ore particles randomly on a entity
     */
    protected void spawnParticle(Entity entity, float scale, boolean dynamic, int level)
    {
        if (entity.world.isRemote)
            return;

        double x = entity.posX + (Utils.random.nextDouble() - 0.5D) * (double) entity.width;
        double y = entity.posY + Utils.random.nextDouble() * (double) entity.height;
        double z = entity.posZ + (Utils.random.nextDouble() - 0.5D) * (double) entity.width;

        PacketManager.network.sendToAllTracking(new PacketSpawnOreParticles(x, y, z, metal.getStats().getColorHex(), scale, dynamic, level), entity);
        if (entity instanceof EntityPlayerMP)
            PacketManager.network.sendTo(new PacketSpawnOreParticles(x, y, z, metal.getStats().getColorHex(), scale, dynamic, level), (EntityPlayerMP) entity);
    }

    /**
     * spawn ore particles randomly on block position
     */
    protected void spawnParticle(World world, BlockPos pos, float scale, boolean dynamic, int level, double motionX, double motionY, double motionZ)
    {
        if (world.isRemote)
            return;

        AxisAlignedBB box = world.getBlockState(pos).getBoundingBox(world, pos);

        double width = box.maxX - box.minX;
        double height = box.maxY - box.minY;
        double depth = box.maxZ - box.minZ;

        double border = 0.15D;

        double x = pos.getX() + box.minX - border + (width + border * 2) * Utils.random.nextDouble();
        double y = pos.getY() + box.minY - border + (height + border * 2) * Utils.random.nextDouble();
        double z = pos.getZ() + box.minZ - border + (depth + border * 2) * Utils.random.nextDouble();
        spawnParticle(world, x, y, z, motionX, motionY, motionZ, scale, dynamic, level);
    }

    /**
     * spawn ore particles in a specific point with a custom motion
     */
    protected void spawnParticle(World world, double x, double y, double z, double motionX, double motionY, double motionZ, float scale, boolean dynamic, int level)
    {
        if (world.isRemote)
            return;

        NetworkRegistry.TargetPoint targetPoint = new NetworkRegistry.TargetPoint(world.provider.getDimension(), x, y, z, 64D);
        PacketManager.network.sendToAllTracking(new PacketSpawnOreParticles(x, y, z, motionX, motionY, motionZ, metal.getStats().getColorHex(), scale, dynamic, level), targetPoint);
    }

    /**
     * spawn ore particles in a specific point with random motion (like redstone particles)
     */
    protected void spawnParticle(World world, double x, double y, double z, float scale, boolean dynamic, int level)
    {
        if (world.isRemote)
            return;

        NetworkRegistry.TargetPoint targetPoint = new NetworkRegistry.TargetPoint(world.provider.getDimension(), x, y, z, 64D);
        PacketManager.network.sendToAllTracking(new PacketSpawnOreParticles(x, y, z, metal.getStats().getColorHex(), scale, dynamic, level), targetPoint);
    }

    public void rightClickHandler(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn)
    {

    }

    /**
     * Setup tool model overrides to support states in effects
     *
     * @param condition if it returns 1 the alternative state model will load, if it returns 0 the regular state model will load
     */
    protected void setupModelOverrides(IItemPropertyGetter condition)
    {
        for (EnumTools tool : getCategory().getTools())
        {
            metal.getTool(tool).addPropertyOverride(new ResourceLocation("active"), condition);
        }
    }

    public Metal getMetal()
    {
        return metal;
    }

    public final String getName()
    {
        return name;
    }

}
