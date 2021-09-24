/*==============================================================================
 = Class: IntegrationTE
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration;

import com.google.common.collect.Table;
import it.hurts.metallurgy_reforged.block.BlockTypes;
import it.hurts.metallurgy_reforged.fluid.ModFluids;
import it.hurts.metallurgy_reforged.item.ModItems;
import it.hurts.metallurgy_reforged.item.armor.ItemArmorBase;
import it.hurts.metallurgy_reforged.material.ModMetals;
import it.hurts.metallurgy_reforged.model.AlloySample;
import it.hurts.metallurgy_reforged.recipe.AlloyerRecipes;
import it.hurts.metallurgy_reforged.util.NBTUtils;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class IntegrationThermal {

	public static final String EXPANSION_MODID = "thermalexpansion";
	public static final String FOUNDATION_MODID = "thermalfoundation";

	public static void init()
	{
		addCentrifugalSeparatorRecipes();
		addDynamoFuels();
		loadInductionSmelterRecipes();
		loadPulverizerRecipes();
		loadCrucibleRecipes();
	}

	private static void addCentrifugalSeparatorRecipes()
	{
		for (Table.Cell<AlloySample, AlloySample, AlloySample> alloyRecipe : AlloyerRecipes.getInstance().getRecipeTable().cellSet())
		{
			NBTTagCompound centrifugeRecipe = new NBTTagCompound();

			NBTTagCompound inputStackTag = alloyRecipe.getValue().getStack().writeToNBT(new NBTTagCompound());
			centrifugeRecipe.setTag("input", inputStackTag);

			NBTTagList outputListTag = new NBTTagList();
			NBTTagCompound output1 = alloyRecipe.getRowKey().getStack().writeToNBT(new NBTTagCompound());
			outputListTag.appendTag(output1);
			NBTTagCompound output2 = alloyRecipe.getColumnKey().getStack().writeToNBT(new NBTTagCompound());
			outputListTag.appendTag(output2);
			centrifugeRecipe.setTag("output", outputListTag);

			int ratio = Math.max(alloyRecipe.getColumnKey().getAmount(), alloyRecipe.getRowKey().getAmount());
			centrifugeRecipe.setInteger("energy", ratio * 2000 + 4000);

			FMLInterModComms.sendMessage(EXPANSION_MODID, "addcentrifugerecipe", centrifugeRecipe);
		}
	}

	public static void addDynamoFuels()
	{
		NBTTagCompound thermiteMolten = new NBTTagCompound();
		thermiteMolten.setString("fluid", ModFluids.THERMITE.getName());
		thermiteMolten.setInteger("energy", 200000);
		FMLInterModComms.sendMessage(EXPANSION_MODID, "addmagmaticfuel", thermiteMolten);
	}

	public static void loadInductionSmelterRecipes()
	{
		final Item foundationMaterial = ForgeRegistries.ITEMS.getValue(new ResourceLocation(FOUNDATION_MODID, "material"));
		assert foundationMaterial != null;

		final NBTTagCompound sand = new ItemStack(Blocks.SAND).writeToNBT(new NBTTagCompound());
		final NBTTagCompound slag = new ItemStack(foundationMaterial, 1, 864).writeToNBT(new NBTTagCompound());

		ModMetals.metalMap.forEach((name, metal) -> {
			ItemStack ingot = new ItemStack(metal.getIngot());

			if (metal.hasArmorSet())
			{
				for (ItemArmorBase armorItem : metal.getArmorSet())
				{
					ItemStack armorStack = new ItemStack(armorItem);

					NBTTagCompound armorRecycleRecipe = new NBTTagCompound();
					armorRecycleRecipe.setInteger("energy", 6000);
					armorRecycleRecipe.setTag("input", sand);
					armorRecycleRecipe.setTag("input2", armorStack.writeToNBT(new NBTTagCompound()));

					//2 4 3 1 ingots depending on equipment slot
					// And slag chance calculation
					int count = -1;
					int chance = -1;
					switch (EntityLiving.getSlotForItemStack(armorStack))
					{
						case HEAD:
							count = 2;
							chance = 15;
							break;
						case CHEST:
							count = 4;
							chance = 25;
							break;
						case LEGS:
							count = 3;
							chance = 20;
							break;
						case FEET:
							count = 1;
							chance = 15;
							break;
						default:
					}

					//Assert neither count nor chance are -1 at this point
					assert count != -1;

					ingot.setCount(count);
					armorRecycleRecipe.setTag("output", ingot.writeToNBT(new NBTTagCompound()));
					armorRecycleRecipe.setTag("output2", slag);
					armorRecycleRecipe.setInteger("chance", chance);
					FMLInterModComms.sendMessage(EXPANSION_MODID, "addsmelterrecipe", armorRecycleRecipe);
				}
			}

			if (metal.hasToolSet())
			{
				for (Item toolItem : metal.getToolSet())
				{
					NBTTagCompound toolRecycleRecipe = new NBTTagCompound();

					toolRecycleRecipe.setInteger("energy", 6000);
					toolRecycleRecipe.setTag("input", sand);
					toolRecycleRecipe.setTag("input2", new ItemStack(toolItem).writeToNBT(new NBTTagCompound()));
					toolRecycleRecipe.setTag("output", ingot.writeToNBT(new NBTTagCompound()));
					toolRecycleRecipe.setTag("output2", slag);
					toolRecycleRecipe.setInteger("chance", 10);
					FMLInterModComms.sendMessage(EXPANSION_MODID, "addsmelterrecipe", toolRecycleRecipe);
				}
			}
		});

		NBTTagCompound gauntletRecycleRecipe = new NBTTagCompound();
		gauntletRecycleRecipe.setInteger("energy", 6000);
		gauntletRecycleRecipe.setTag("input", sand);
		gauntletRecycleRecipe.setTag("input2", new ItemStack(ModItems.GAUNTLET).writeToNBT(new NBTTagCompound()));
		gauntletRecycleRecipe.setTag("output", new ItemStack(ModMetals.RUBRACIUM.getIngot(), 2).writeToNBT(new NBTTagCompound()));
		gauntletRecycleRecipe.setTag("output2", slag);
		gauntletRecycleRecipe.setInteger("chance", 25);
		FMLInterModComms.sendMessage(EXPANSION_MODID, "addsmelterrecipe", gauntletRecycleRecipe);

		NBTTagCompound lemuriteShieldRecycleRecipe = new NBTTagCompound();
		lemuriteShieldRecycleRecipe.setInteger("energy", 6000);
		lemuriteShieldRecycleRecipe.setTag("input", sand);
		lemuriteShieldRecycleRecipe.setTag("input2", new ItemStack(ModItems.INVISIBILITY_SHIELD).writeToNBT(new NBTTagCompound()));
		lemuriteShieldRecycleRecipe.setTag("output", new ItemStack(ModMetals.LEMURITE.getIngot(), 3).writeToNBT(new NBTTagCompound()));
		lemuriteShieldRecycleRecipe.setTag("output2", slag);
		lemuriteShieldRecycleRecipe.setInteger("chance", 25);
		FMLInterModComms.sendMessage(EXPANSION_MODID, "addsmelterrecipe", lemuriteShieldRecycleRecipe);
	}

	public static void loadPulverizerRecipes()
	{
		NBTTagCompound fertilizerRecipe = new NBTTagCompound();
		fertilizerRecipe.setInteger("energy", 4000);
		fertilizerRecipe.setTag("input", new ItemStack(ModItems.POTASH).writeToNBT(new NBTTagCompound()));
		fertilizerRecipe.setTag("output", new ItemStack(ModItems.DUST_POTASH).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage(EXPANSION_MODID, "addpulverizerrecipe", fertilizerRecipe);
	}

	public static void loadCrucibleRecipes()
	{
		ModMetals.metalMap.values().forEach(metal -> {
			FluidStack molten = metal.getMolten().getFluidStack();

			molten.amount = 16;
			NBTTagCompound meltNugget = new NBTTagCompound();
			meltNugget.setTag("output", NBTUtils.writeFluidStackToNBT(molten));
			meltNugget.setInteger("energy", 500);
			meltNugget.setTag("input", new ItemStack(metal.getNugget()).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage(EXPANSION_MODID, "addcruciblerecipe", meltNugget);

			molten.amount = 144;
			NBTTagCompound meltIngot = new NBTTagCompound();
			meltIngot.setTag("output", NBTUtils.writeFluidStackToNBT(molten));
			meltIngot.setInteger("energy", 4000);
			meltIngot.setTag("input", new ItemStack(metal.getIngot()).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage(EXPANSION_MODID, "addcruciblerecipe", meltIngot);

			if (!metal.isAlloy())
			{
				molten.amount = 288;
				NBTTagCompound meltOre = new NBTTagCompound();
				meltOre.setTag("output", NBTUtils.writeFluidStackToNBT(molten));
				meltOre.setInteger("energy", 8000);
				meltOre.setTag("input", new ItemStack(metal.getOre()).writeToNBT(new NBTTagCompound()));
				FMLInterModComms.sendMessage(EXPANSION_MODID, "addcruciblerecipe", meltOre);
			}

			molten.amount = 1296;
			NBTTagCompound meltBlock = new NBTTagCompound();
			meltBlock.setTag("output", NBTUtils.writeFluidStackToNBT(molten));
			meltBlock.setInteger("energy", 36000);
			meltBlock.setTag("input", new ItemStack(metal.getBlock(BlockTypes.BLOCK)).writeToNBT(new NBTTagCompound()));
			FMLInterModComms.sendMessage(EXPANSION_MODID, "addcruciblerecipe", meltBlock);
		});
	}

}
