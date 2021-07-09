/*==============================================================================
 = Class: MetallurgyToolPart
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.silentgems;

import it.hurts.metallurgy_reforged.Metallurgy;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.silentchaos512.gems.SilentGems;
import net.silentchaos512.gems.api.lib.EnumMaterialTier;
import net.silentchaos512.gems.api.lib.IPartPosition;
import net.silentchaos512.gems.api.lib.ToolPartPosition;
import net.silentchaos512.gems.api.tool.part.ToolPartMain;
import net.silentchaos512.gems.item.tool.ItemGemBow;

import javax.annotation.Nonnull;

public class MetallurgyToolPart extends ToolPartMain {

	private final MetallurgyPartProperties properties;

	public MetallurgyToolPart(MetallurgyPartProperties properties)
	{
		super(Metallurgy.MODID + ":" + properties.getMetalStats().getName() + "_ingot", properties.getCraftingStack());
		this.tier = properties.getTier();
		this.properties = properties;
	}

	@Override
	public ModelResourceLocation getModel(ItemStack tool, ToolPartPosition pos, int frame)
	{

		Item item = tool.getItem();
		String name = item.getRegistryName().getPath();
		name = SilentGems.MODID + ":" + name.toLowerCase() + "/" + name;
		boolean isBow = item instanceof ItemGemBow;
		String num = isBow ? "" : "15";
		String frameNum = isBow && frame == 3 ? "_3" : "";

		switch (pos)
		{
			case HEAD:
				name += num + frameNum;
				break;
			case ROD_DECO:
				name += "_deco";
				break;
			default:
				return null;
		}

		return new ModelResourceLocation(name.toLowerCase(), "inventory");
	}

	@Nonnull
	@Override
	public String getCraftingOreDictName()
	{
		return properties.getCraftingOreName();
	}

	@Override
	public int getDurability()
	{
		return properties.getDurability();
	}

	@Override
	public float getHarvestSpeed()
	{
		return properties.getMiningSpeed();
	}

	@Override
	public int getHarvestLevel()
	{
		return properties.getHarvestLevel();
	}

	@Override
	public float getMeleeDamage()
	{
		return properties.getMeleeDamage();
	}

	@Override
	public float getMagicDamage()
	{
		return 0;
	}

	@Override
	public int getEnchantability()
	{
		return properties.getEnchantability();
	}

	@Override
	public float getMeleeSpeed()
	{
		return properties.getMeleeSpeed();
	}

	@Override
	public float getChargeSpeed()
	{
		return properties.getChargeSpeed();
	}

	@Override
	public float getProtection()
	{
		return properties.getProtection();
	}

	@Override
	public EnumMaterialTier getTier()
	{
		return tier;
	}

	@Override
	public int getColor(ItemStack toolOrArmor, IPartPosition position, int animationFrame)
	{
		return properties.getColor();
	}

	@Override
	public String getDisplayNamePrefix(ItemStack partRep)
	{
		return properties.getNamePrefix();
	}

	@Override
	public String getDisplayName(ItemStack partRep)
	{
		if (partRep.hasDisplayName())
			return partRep.getDisplayName();

		return properties.getName();
	}

}
