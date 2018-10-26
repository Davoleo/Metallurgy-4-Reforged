package it.hurts.metallurgy_5.item.tool;

import it.hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/*************************************************
 * Author: Davoleo
 * Date: 20/08/2018
 * Hour: 11.25
 * Project: Metallurgy 5
 * Copyright - © - Davoleo - 2018
 **************************************************/

public class ItemSwordBase extends ItemSword {

    private String name;
    private String tooltip = "";
	private Enchantment enchantment = null;
	private int enchantmentLevel = 0;

    public ItemSwordBase(ToolMaterial material, String name)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        this.name = name;
        setCreativeTab(Metallurgy_5.tabTool);
    }

    public ItemSwordBase(ToolMaterial material, String name,  String tooltip)
    {
        super(material);
        setUnlocalizedName(name);
        setRegistryName(name);
        this.name = name;
        this.tooltip = tooltip;
        setCreativeTab(Metallurgy_5.tabTool);
    }
    
    public ItemSwordBase(ToolMaterial material, String name, Enchantment enchantment, int enchantmentLevel)
    {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(name);
        this.name = name;
        this.enchantment = enchantment;
        this.enchantmentLevel = enchantmentLevel;
	setCreativeTab(Metallurgy_5.tabTool);

    }
    
    @Override
    @SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
    	
        if(enchantment != null && tab.equals(Metallurgy_5.tabTool))
        {
        	ItemStack enchantedSword = new ItemStack(this);
            enchantedSword.addEnchantment(enchantment, enchantmentLevel);
            items.add(enchantedSword);
        }
	}

    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add(this.tooltip);
    }


    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item, int meta)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy_5.MODID + ":tool/" + name, "inventory"));
    }
}
