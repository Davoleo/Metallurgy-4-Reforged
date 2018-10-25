package it.hurts.metallurgy_5.item.armor;

import it.hurts.metallurgy_5.Metallurgy_5;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

public class ItemArmorBase extends net.minecraft.item.ItemArmor{

	private String name;
	private String tooltip = "";
	private Enchantment enchantment = null;
	private int enchantmentLevel = 0;


	public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String name)
    {
		super(material, 0, slot);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.name = name;
	}

	public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String name, String tooltip)
    {
		super(material, 0, slot);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.tooltip = tooltip;
		this.name = name;
	}

    public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String name, String tooltip, Enchantment enchantment, int enchantmentLevel)
    {
        super(material, 0, slot);
        setRegistryName(name);
        setUnlocalizedName(name);
        this.tooltip = tooltip;
        this.name = name;
        this.enchantment = enchantment;
        this.enchantmentLevel = enchantmentLevel;

    }

	@Override
    @SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if(enchantment != null && tab.equals(Metallurgy_5.tabArmor)){
            ItemStack enchantedArmor = new ItemStack(this);
            enchantedArmor.addEnchantment(enchantment, enchantmentLevel);
            items.add(enchantedArmor);
        }
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(this.tooltip);
	}

	@SideOnly(Side.CLIENT)
	public void registerItemModel(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy_5.MODID + ":armor/" + name, "inventory"));
	}
}
