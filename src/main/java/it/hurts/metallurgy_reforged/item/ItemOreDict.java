package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.material.IOreDict;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

public class ItemOreDict extends ItemBase implements IOreDict {

	//Internal Variables ----------------------------------------------------
	private String oreName;
	private String tooltip;

	//Constructors
	public ItemOreDict(String name, String oreName)
	{
		super(name);
		this.oreName = oreName;
	}

	//Overridden methods -----------------------------------------------------
	@Nonnull
    @Override
    public ItemOreDict setCreativeTab(@Nonnull CreativeTabs tab)
	{
        super.setCreativeTab(tab);
        return this;
    }

    @Override
	public void initOreDict()
	{
		OreDictionary.registerOre(oreName, this);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if (this.tooltip != null)
			tooltip.add(this.tooltip);
	}

	//Setters & Getters -----------------------------------------------------

	public ItemOreDict setTooltip(String tooltip)
	{
		this.tooltip = tooltip;
		return this;
	}
}
