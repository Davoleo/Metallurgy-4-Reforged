package it.hurts.metallurgy_reforged.item;

import it.hurts.metallurgy_reforged.material.IOreDict;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;

/***************************
*
* Author : ItHurtsLikeHell
* Project: Metallurgy-5
* Date   : 28 ago 2018
* Time   : 18:24:07
*
***************************/

public class ItemOreDict extends ItemBase implements IOreDict {

	private String oreName;

	public ItemOreDict(String name, String oreName)
	{
		super(name);
		this.oreName = oreName;
	}

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
}
