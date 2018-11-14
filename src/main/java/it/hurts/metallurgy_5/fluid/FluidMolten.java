package it.hurts.metallurgy_5.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

/*************************************************
 * Author: Davoleo
 * Date: 28/08/2018
 * Hour: 17.25
 * Project: Metallurgy_5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class FluidMolten extends Fluid {

protected Fluid fluid = this;
protected int mapColor = 0xFFFFFFFF;
protected float overlayAlpha = 0.2F;
protected static SoundEvent emptySound = SoundEvents.ITEM_BUCKET_EMPTY_LAVA;
protected static SoundEvent fillSound = SoundEvents.ITEM_BUCKET_FILL_LAVA;
protected static Material material = Material.LAVA;
protected boolean bucketEnabled = false;

    public FluidMolten(String name, ResourceLocation still, ResourceLocation flowing)
    {
        super(name, still, flowing);
        ModFluids.fluidList.add(this);
    }

    public FluidMolten(String name, ResourceLocation still, ResourceLocation flowing, int mapColor)
    {
        super(name, still, flowing);
        setColor(mapColor);
        ModFluids.fluidList.add(this);
    }

    public FluidMolten(String fluidName, ResourceLocation still, ResourceLocation flowing, int mapColor, float overlayAlpha)
    {
        this(fluidName, still, flowing, mapColor);
        setAlpha(overlayAlpha);
    }

    public int getColor()
    {
        return mapColor;
    }

	public FluidMolten setColor(int mapColor)
    {
        this.mapColor = mapColor;
        return this;
    }

    public float getAlpha()
    {
        return overlayAlpha;
    }

    public FluidMolten setAlpha(float overlayAlpha)
    {
        this.overlayAlpha = overlayAlpha;
        return this;
    }

    @Override
    public FluidMolten setEmptySound(SoundEvent emptySound)
    {
        FluidMolten.emptySound = emptySound;
        return this;
    }

    @Override
    public SoundEvent getEmptySound()
    {
        return emptySound;
    }

    @Override
    public FluidMolten setFillSound(SoundEvent fillSound)
    {
        FluidMolten.fillSound = fillSound;
        return this;
    }

    @Override
    public SoundEvent getFillSound()
    {
        return fillSound;
    }

    public FluidMolten setMaterial (Material material)
    {
        FluidMolten.material = material;
        return this;
    }

    public Material getMaterial()
    {
        return material;
    }

    @Override
    public boolean doesVaporize(FluidStack fluidStack)
    {
        if(block == null)
            return false;
        return block.getDefaultState().getMaterial() == getMaterial();
    }

    public FluidStack getFluidStack()
    {
        return new FluidStack(this, 1);
    }

    public ItemStack getBucket()
    {
        return FluidUtil.getFilledBucket(new FluidStack(this.getFluidStack(), 1));
    }

    public boolean isBucketEnabled()
    {
        return bucketEnabled;
    }
}
