/*
 * -------------------------------------------------------------------------------------------------------
 * Class: Utils
 * This class is part of Metallurgy 4 Reforged
 * Complete source code is available at: https://github.com/Davoleo/Metallurgy-4-Reforged
 * This code is licensed under GNU GPLv3
 * Authors: ItHurtsLikeHell & Davoleo
 * Copyright (c) 2020.
 * --------------------------------------------------------------------------------------------------------
 */

package it.hurts.metallurgy_reforged.util;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.*;
import it.hurts.metallurgy_reforged.material.Metal;
import it.hurts.metallurgy_reforged.material.ModMetals;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Utils {

	public static Random random = new Random();

	private static Potion[] randomEffectsList = {
			MobEffects.BLINDNESS,
			MobEffects.LEVITATION,
			MobEffects.HUNGER,
			MobEffects.INSTANT_DAMAGE,
			MobEffects.NAUSEA,
			MobEffects.NIGHT_VISION,
			MobEffects.POISON,
			MobEffects.SLOWNESS,
			MobEffects.REGENERATION
	};

	public static void giveExperience(EntityPlayer thePlayer, float experience)
	{
		int intExp = (int) experience;
		float fractional = experience - intExp;
		if (fractional > 0.0F && (float) Math.random() < fractional)
		{
			intExp++;
		}
		while (intExp > 0)
		{
			int j = EntityXPOrb.getXPSplit(intExp);
			intExp -= j;
			thePlayer.world.spawnEntity(new EntityXPOrb(thePlayer.world, thePlayer.posX, thePlayer.posY + 0.5D, thePlayer.posZ + 0.5D, j));
		}

	}

	//maxPercent is the max percentage that the player can reach when they're in complete darkness
	public static float getLightArmorPercentage(EntityPlayer pl, float maxPercent)
	{
		BlockPos pos = new BlockPos(pl.posX, pl.posY, pl.posZ);
		//check if it is day
		boolean isDay = (pl.world.getWorldTime() % 23300) <= 12800;
		//get sky light level,if it is night the light will be 0
		float lightSky = Math.min(isDay ? pl.world.getLightFor(EnumSkyBlock.SKY, pos) : 0F, 14F);
		//get light emitted by a block(like a torch)
		float lightBlock = Math.min(pl.world.getLightFor(EnumSkyBlock.BLOCK, pos), 14);
		//get the light based on the lightSky and the lightBlock
		float light = lightSky <= lightBlock ? lightBlock : lightSky;

		//14 is the max Light possible
		return maxPercent - (light * maxPercent / 14F);
	}

	public static String localize(String unlocalized)
	{
		return new TextComponentTranslation(unlocalized).getFormattedText();
	}

	public static String localizeIgnoreFormat(String unlocalized)
	{
		return new TextComponentTranslation(unlocalized).getUnformattedComponentText();
	}

	public static Potion getRandomEffect()
	{
		return randomEffectsList[(int) (Math.random() * Utils.getMaxIndexEffect())];
	}

	private static int getMaxIndexEffect()
	{
		return randomEffectsList.length;
	}

	public static int intColorFromRGB(int r, int g, int b)
	{
		return ((255 << 24) + r * 65536 + g * 256 + b);
	}

	//TODO : @ItHurtsLikeHell Document this fucking method please
	public static String getName(String name)
	{
		String[] str = name.split("_");
		String[] space = space(str.length);
		name = "";
		for (int i = 0; i < str.length; i++)
		{
			name = name + str[i].substring(0, 1).toUpperCase() + str[i].substring(1) + space[i];
		}
		return name;
	}

	//TODO : @ItHurtsLikeHell Document this fucking method please
	private static String[] space(int len)
	{
		String[] str = new String[len];
		for (int i = 0; i < len; i++)
		{
			if (i < len - 1)
			{
				str[i] = " ";
			}
			else
				str[i] = "";
		}
		return str;
	}

	public static Metal getMetalFromString(String string)
	{
		for (Map.Entry<String, Metal> entry : ModMetals.metalMap.entrySet())
		{
			if (string.contains(entry.getKey()))
				return entry.getValue();
		}
		return null;
	}


	public static void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
	{
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos((x), (y + height), 1D).tex(((float) (textureX) * 0.00390625F), ((float) (textureY + height) * 0.00390625F)).endVertex();
		bufferbuilder.pos((x + width), (y + height), 1D).tex(((float) (textureX + width) * 0.00390625F), ((float) (textureY + height) * 0.00390625F)).endVertex();
		bufferbuilder.pos((x + width), (y), 1D).tex(((float) (textureX + width) * 0.00390625F), ((float) (textureY) * 0.00390625F)).endVertex();
		bufferbuilder.pos((x), (y), 1D).tex(((float) (textureX) * 0.00390625F), ((float) (textureY) * 0.00390625F)).endVertex();
		tessellator.draw();
	}

	/**
	 * @param context The parsing context
	 * @param json    The recipe's JSON object
	 *
	 * @return A NonNullList containing the ingredients specified in the JSON object
	 * @author Choonster
	 * <p>
	 * Parse the input of a shapeless recipe.
	 * <p>
	 * Adapted from {@link ShapelessOreRecipe#factory}.
	 */
	public static NonNullList<Ingredient> parseShapelessRecipe(final JsonContext context, final JsonObject json)
	{
		final NonNullList<Ingredient> ingredients = NonNullList.create();
		for (final JsonElement element : JsonUtils.getJsonArray(json, "ingredients"))
			ingredients.add(CraftingHelper.getIngredient(element, context));

		if (ingredients.isEmpty())
			throw new JsonParseException("No ingredients for shapeless recipe");

		return ingredients;
	}

	/**
	 * Parse the input of a shaped recipe.
	 * <p>
	 * Adapted from {@link ShapedOreRecipe#factory}.
	 *
	 * @param context The parsing context
	 * @param json    The recipe's JSON object
	 *
	 * @return A ShapedPrimer containing the input specified in the JSON object
	 */
	public static CraftingHelper.ShapedPrimer parseShapedRecipe(final JsonContext context, final JsonObject json)
	{
		final Map<Character, Ingredient> ingredientMap = Maps.newHashMap();
		for (final Map.Entry<String, JsonElement> entry : JsonUtils.getJsonObject(json, "key").entrySet())
		{
			if (entry.getKey().length() != 1)
				throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
			if (" ".equals(entry.getKey()))
				throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");

			ingredientMap.put(entry.getKey().toCharArray()[0], CraftingHelper.getIngredient(entry.getValue(), context));
		}

		ingredientMap.put(' ', Ingredient.EMPTY);

		final JsonArray patternJ = JsonUtils.getJsonArray(json, "pattern");

		if (patternJ.size() == 0)
			throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");

		final String[] pattern = new String[patternJ.size()];
		for (int x = 0; x < pattern.length; ++x)
		{
			final String line = JsonUtils.getString(patternJ.get(x), "pattern[" + x + "]");
			if (x > 0 && pattern[0].length() != line.length())
				throw new JsonSyntaxException("Invalid pattern: each row must  be the same width");
			pattern[x] = line;
		}

		final CraftingHelper.ShapedPrimer primer = new CraftingHelper.ShapedPrimer();
		primer.width = pattern[0].length();
		primer.height = pattern.length;
		primer.mirrored = JsonUtils.getBoolean(json, "mirrored", true);
		primer.input = NonNullList.withSize(primer.width * primer.height, Ingredient.EMPTY);

		final Set<Character> keys = Sets.newHashSet(ingredientMap.keySet());
		keys.remove(' ');

		int index = 0;
		for (final String line : pattern)
		{
			for (final char chr : line.toCharArray())
			{
				final Ingredient ing = ingredientMap.get(chr);
				if (ing == null)
					throw new JsonSyntaxException("Pattern references symbol '" + chr + "' but it's not defined in the key");
				primer.input.set(index++, ing);
				keys.remove(chr);
			}
		}

		if (!keys.isEmpty())
			throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + keys);

		return primer;
	}

	public static String capitalize(String string)
	{
		String up = string.substring(0, 1);
		return string.replaceFirst(up, up.toUpperCase());
	}

}
