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
			ItemStack inputStack = alloyRecipe.getValue().getStack();
			NBTTagCompound output1 = alloyRecipe.getRowKey().getStack().writeToNBT(new NBTTagCompound());
			NBTTagCompound output2 = alloyRecipe.getColumnKey().getStack().writeToNBT(new NBTTagCompound());

			int ratio = Math.max(alloyRecipe.getColumnKey().getAmount(), alloyRecipe.getRowKey().getAmount());
			int energy = ratio * 2000 + 4000;

			NBTTagCompound centrifugeRecipe = buildRecipeCompound(energy, inputStack, output1, output2);

			FMLInterModComms.sendMessage(EXPANSION_MODID, "addcentrifugerecipe", centrifugeRecipe);
		}
	}

	private static void addDynamoFuels()
	{
		NBTTagCompound thermiteMolten = new NBTTagCompound();
		thermiteMolten.setString("fluid", ModFluids.THERMITE.getName());
		thermiteMolten.setInteger("energy", 200000);
		FMLInterModComms.sendMessage(EXPANSION_MODID, "addmagmaticfuel", thermiteMolten);
	}

	private static void loadInductionSmelterRecipes()
	{
		ModMetals.metalMap.forEach((name, metal) -> {
			ItemStack ingot = new ItemStack(metal.getIngot());

			if (metal.hasArmorSet())
			{
				for (ItemArmorBase armorItem : metal.getArmorSet())
				{
					ItemStack armorStack = new ItemStack(armorItem);

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

					NBTTagCompound armorRecycleRecipe = buildInductionSmelterCompound(6000, armorStack, ingot, chance);
					FMLInterModComms.sendMessage(EXPANSION_MODID, "addsmelterrecipe", armorRecycleRecipe);
				}
			}

			if (metal.hasToolSet())
			{
				//Just to be sure
				ingot.setCount(1);

				for (Item toolItem : metal.getToolSet())
				{
					//6000 10
					NBTTagCompound toolRecycleRecipe = buildInductionSmelterCompound(6000, new ItemStack(toolItem), ingot, 10);
					FMLInterModComms.sendMessage(EXPANSION_MODID, "addsmelterrecipe", toolRecycleRecipe);
				}
			}
		});

		NBTTagCompound gauntletRecycleRecipe = buildInductionSmelterCompound(
				6000, new ItemStack(ModItems.GAUNTLET), new ItemStack(ModMetals.RUBRACIUM.getIngot(), 2), 10);
		FMLInterModComms.sendMessage(EXPANSION_MODID, "addsmelterrecipe", gauntletRecycleRecipe);

		NBTTagCompound lemuriteShieldRecycleRecipe = buildInductionSmelterCompound(
				6000, new ItemStack(ModItems.INVISIBILITY_SHIELD), new ItemStack(ModMetals.LEMURITE.getIngot(), 3), 25);
		FMLInterModComms.sendMessage(EXPANSION_MODID, "addsmelterrecipe", lemuriteShieldRecycleRecipe);
	}

	private static void loadPulverizerRecipes()
	{
		NBTTagCompound fertilizerRecipe = buildRecipeCompound(4000, new ItemStack(ModItems.POTASH), new ItemStack(ModItems.DUST_POTASH).writeToNBT(new NBTTagCompound()));
		FMLInterModComms.sendMessage(EXPANSION_MODID, "addpulverizerrecipe", fertilizerRecipe);
	}

	private static void loadCrucibleRecipes()
	{
		ModMetals.metalMap.values().forEach(metal -> {
			FluidStack molten = metal.getMolten().getFluidStack();

			molten.amount = 16;
			NBTTagCompound meltNugget = buildRecipeCompound(500, new ItemStack(metal.getNugget()), NBTUtils.writeFluidStackToNBT(molten));
			FMLInterModComms.sendMessage(EXPANSION_MODID, "addcruciblerecipe", meltNugget);

			molten.amount = 144;
			NBTTagCompound meltIngot = buildRecipeCompound(4000, new ItemStack(metal.getIngot()), NBTUtils.writeFluidStackToNBT(molten));
			FMLInterModComms.sendMessage(EXPANSION_MODID, "addcruciblerecipe", meltIngot);

			if (!metal.isAlloy())
			{
				molten.amount = 288;
				NBTTagCompound meltOre = buildRecipeCompound(8000, new ItemStack(metal.getOre()), NBTUtils.writeFluidStackToNBT(molten));
				FMLInterModComms.sendMessage(EXPANSION_MODID, "addcruciblerecipe", meltOre);
			}

			molten.amount = 1296;
			NBTTagCompound meltBlock = buildRecipeCompound(36000, new ItemStack(metal.getBlock(BlockTypes.BLOCK)), NBTUtils.writeFluidStackToNBT(molten));
			FMLInterModComms.sendMessage(EXPANSION_MODID, "addcruciblerecipe", meltBlock);
		});
	}

	private static NBTTagCompound buildRecipeCompound(int energy, ItemStack input, NBTTagCompound... outputs)
	{
		NBTTagCompound recipe = new NBTTagCompound();
		recipe.setInteger("energy", energy);
		recipe.setTag("input", input.writeToNBT(new NBTTagCompound()));
		if (outputs.length == 1)
			recipe.setTag("output", input.writeToNBT(new NBTTagCompound()));
		else if (outputs.length > 1)
		{
			NBTTagList outListTag = new NBTTagList();
			for (NBTTagCompound output : outputs)
				outListTag.appendTag(output);
			recipe.setTag("output", outListTag);
		}

		return recipe;
	}

	private static final Item FOUNDATION_MATERIAL = ForgeRegistries.ITEMS.getValue(new ResourceLocation(FOUNDATION_MODID, "material"));

	private static final NBTTagCompound SAND = new ItemStack(Blocks.SAND).writeToNBT(new NBTTagCompound());
	private static final NBTTagCompound SLAG = new ItemStack(FOUNDATION_MATERIAL, 1, 864).writeToNBT(new NBTTagCompound());

	private static NBTTagCompound buildInductionSmelterCompound(int energy, ItemStack mainInput, ItemStack mainOutput, int chance)
	{
		NBTTagCompound recipe = new NBTTagCompound();
		recipe.setInteger("energy", energy);
		recipe.setTag("input", SAND);
		recipe.setTag("input2", mainInput.writeToNBT(new NBTTagCompound()));
		recipe.setTag("output", mainOutput.writeToNBT(new NBTTagCompound()));
		recipe.setTag("output2", SLAG);
		recipe.setInteger("chance", chance);
		return recipe;
	}

}
