/*
 * -------------------------------------------------------------------------------------------------------
 * Class: FluidMolten
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2019.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.fluid;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class FluidMolten extends Fluid {

	protected Fluid fluid = this;
	protected static Material material = Material.LAVA;
	private int mapColor = 0xFFFFFFFF;
	private float overlayAlpha = 0.2F;
	private static SoundEvent emptySound = SoundEvents.ITEM_BUCKET_EMPTY_LAVA;
	private static SoundEvent fillSound = SoundEvents.ITEM_BUCKET_FILL_LAVA;
	//protected FluidBlockBase block;
	private final static ResourceLocation default_still = new ResourceLocation(Metallurgy.MODID,
			"blocks/molten_metal_still");
	private final static ResourceLocation default_flowing = new ResourceLocation(Metallurgy.MODID,
			"blocks/molten_metal_flow");

	public FluidMolten(String name, int mapColor, int temperature, boolean isNew) {
		super(name, default_still, default_flowing);
		this.setMaterial(Material.IRON).setDensity(800).setGaseous(false).setLuminosity(9).setViscosity(4000)
				.setTemperature(temperature).setColor(mapColor);
		if (isNew)
			ModFluids.fluidToRegitry.add(this);
	}

	public FluidMolten(String name, ResourceLocation still, ResourceLocation flowing) {
		super(name, still, flowing);
		ModFluids.fluidList.add(this);
	}

	public FluidMolten(String name, ResourceLocation still, ResourceLocation flowing, int mapColor, int temperature) {
		super(name, still, flowing);
		setColor(mapColor);
		ModFluids.fluidList.add(this);
		this.setTemperature(temperature);
	}

	public FluidMolten(String fluidName, ResourceLocation still, ResourceLocation flowing, int mapColor,
			int temperature, float overlayAlpha) {
		this(fluidName, still, flowing, mapColor, temperature);
		setAlpha(overlayAlpha);
	}

	public int getColor() {
		return mapColor;
	}

	public FluidMolten setColor(int mapColor) {
		this.mapColor = mapColor;
		return this;
	}

	public float getAlpha() {
		return overlayAlpha;
	}

	public FluidMolten setAlpha(float overlayAlpha) {
		this.overlayAlpha = overlayAlpha;
		return this;
	}

	@Override
	public FluidMolten setEmptySound(SoundEvent emptySound) {
		FluidMolten.emptySound = emptySound;
		return this;
	}

	@Override
	public SoundEvent getEmptySound() {
		return emptySound;
	}

	@Override
	public FluidMolten setFillSound(SoundEvent fillSound) {
		FluidMolten.fillSound = fillSound;
		return this;
	}

	@Override
	public SoundEvent getFillSound() {
		return fillSound;
	}

	public FluidMolten setMaterial(Material material) {
		FluidMolten.material = material;
		return this;
	}

	public Material getMaterial() {
		return material;
	}

	@Override
	public boolean doesVaporize(FluidStack fluidStack) {
		if (block == null)
			return false;
		return block.getDefaultState().getMaterial() == getMaterial();
	}

	public FluidStack getFluidStack() {
		return new FluidStack(this, 1);
	}

	public ItemStack getBucket() {
		return FluidUtil.getFilledBucket(new FluidStack(this.getFluidStack(), 1));
	}

//    public void initFluidBlock() {
//    	block = new FluidBlockBase(this, Material.LAVA, fluid.getName());
//    }
//    
//    @Nonnull
//    public FluidBlockBase getFluidBlock() {
//        return block;
//    }
}
