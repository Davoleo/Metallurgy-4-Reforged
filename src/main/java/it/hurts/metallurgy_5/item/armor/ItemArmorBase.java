package it.hurts.metallurgy_5.item.armor;

import it.hurts.metallurgy_5.Metallurgy_5;
import it.hurts.metallurgy_5.util.MetallurgyTabs;
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
		this(material, slot, name, "", null, 0);
	}

	public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String name, String tooltip)
	{
		this(material, slot, name, tooltip, null, 0);
	}

	public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String name, Enchantment enchantment, int enchantmentLevel)
	{
		this(material, slot, name, "", enchantment, enchantmentLevel);
	}
	
	public ItemArmorBase(ArmorMaterial material, EntityEquipmentSlot slot, String name, String tooltip, Enchantment enchantment, int enchantmentLevel){
		super(material, 0, slot);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.name = name;
		this.tooltip = tooltip;
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