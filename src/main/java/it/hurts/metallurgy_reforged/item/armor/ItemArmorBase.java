package it.hurts.metallurgy_reforged.item.armor;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

public class ItemArmorBase extends ItemArmor {

	private String name;
	private String tooltip;
	private Enchantment enchantment;
	private int enchantmentLevel;


	public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String name)
	{
		this(material, slot, name, null, 0);
	}

	public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String name, Enchantment enchantment, int enchantmentLevel)
	{
		super(material, 0, slot);
		setRegistryName(name);
		setTranslationKey(name);
		this.name = name;
		this.enchantment = enchantment;
		this.enchantmentLevel = enchantmentLevel;
		setCreativeTab(MetallurgyTabs.tabArmor);
		ModArmors.armorList.add(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items)
	{
		if(this.isInCreativeTab(tab)) {
			ItemStack enchantedArmor = new ItemStack(this);
			if(enchantment != null) {
				enchantedArmor.addEnchantment(enchantment, enchantmentLevel);
			}
			items.add(enchantedArmor);
		}
	}

	public ItemArmorBase setTooltip(String tooltip)
	{
		if (ModArmors.isEffectActive(this))
			this.tooltip = tooltip;
		return this;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if(this.tooltip != null)
			tooltip.add(this.tooltip);
	}

	@SideOnly(Side.CLIENT)
	public void registerItemModel(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Metallurgy.MODID + ":armor/" + name, "inventory"));
	}
}