/*==============================================================================
 = Class: Utils
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.util;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import it.hurts.metallurgy_reforged.Metallurgy;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

public class Utils {

	public static Random random = new Random();

	private static final Potion[] randomEffectsList = {
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

	@Deprecated
	private static final Map<TextFormatting, Integer> minecraftColors = new HashMap<>();

	static
	{
		minecraftColors.put(TextFormatting.BLACK, 0x000000);
		minecraftColors.put(TextFormatting.DARK_BLUE, 0x0000AA);
		minecraftColors.put(TextFormatting.DARK_GREEN, 0x00AA00);
		minecraftColors.put(TextFormatting.DARK_AQUA, 0x00AAAA);
		minecraftColors.put(TextFormatting.DARK_RED, 0xAA0000);
		minecraftColors.put(TextFormatting.DARK_PURPLE, 0xAA00AA);
		minecraftColors.put(TextFormatting.GOLD, 0xFFAA00);
		//minecraftColors.put(TextFormatting.GRAY, 0xAAAAAA);
		minecraftColors.put(TextFormatting.DARK_GRAY, 0x555555);
		minecraftColors.put(TextFormatting.BLUE, 0x5555FF);
		minecraftColors.put(TextFormatting.GREEN, 0x55FF55);
		minecraftColors.put(TextFormatting.AQUA, 0x55FFFF);
		minecraftColors.put(TextFormatting.RED, 0xFF5555);
		minecraftColors.put(TextFormatting.LIGHT_PURPLE, 0xFF55FF);
		minecraftColors.put(TextFormatting.YELLOW, 0xFFFF55);
		minecraftColors.put(TextFormatting.WHITE, 0xFFFFFF);
	}

	public static void giveExperience(EntityPlayer player, float experience)
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
			player.world.spawnEntity(new EntityXPOrb(player.world, player.posX, player.posY + 0.5D, player.posZ + 0.5D, j));
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

	public static double angle(Vec3d first, Vec3d second)
	{
		double dot = first.dotProduct(second);
		double lengthProd = first.length() * second.length();
		return Math.acos(dot / lengthProd);
	}

	public static String localizeEscapingCustomSequences(String unlocalized)
	{
		String translation = new TextComponentTranslation(unlocalized).getFormattedText();
		translation = translation.replace("<PC>", "%").replace("<NL>", "\n");
		return translation;
	}

	public static String localizeWithParameters(String unlocalized, Object... params)
	{
		return new TextComponentTranslation(unlocalized, params).getFormattedText();
	}

	//CULO, CULO CUULO, CULO, CULO CULO CULO
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

	/**
	 * @deprecated use {@link net.minecraft.util.math.MathHelper#rgb(int, int, int)}
	 */
	@Deprecated
	public static int intColorFromRGB(int r, int g, int b)
	{
		return ((255 << 24) + r * 65536 + g * 256 + b);
	}

	/**
	 * Gets int RGB Components out of a int color code and either returns them in a new array or fills an existing one<br>
	 * Note: Ignores Alpha in ARGB Colors
	 *
	 * @param hex     the color code
	 * @param results The array that needs to be filled (null otherwise)
	 *
	 * @return the array filled with rgb color components
	 */
	public static float[] getRGBComponents(int hex, float[] results)
	{
		int red = (hex >> 16) & 0xFF;
		int green = (hex >> 8) & 0xFF;
		int blue = hex & 0xFF;

		float[] array;
		if (results != null)
			array = results;
		else
			array = new float[3];

		array[0] = red / 255F;
		array[1] = green / 255F;
		array[2] = blue / 255F;
		return array;
	}

	@SideOnly(Side.CLIENT)
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
		char uppercased = Character.toUpperCase(string.charAt(0));
		return string.replace(string.charAt(0), uppercased);
	}

	public static Path getPath(String resource)
	{
		FileSystem filesystem;

		try
		{
			URL url = Metallurgy.class.getResource(resource);

			if (url != null)
			{
				URI uri = url.toURI();
				Path path;

				if ("file".equals(uri.getScheme()))
				{
					path = Paths.get(Metallurgy.class.getResource(resource).toURI());
				}
				else
				{
					try
					{
						filesystem = FileSystems.getFileSystem(uri);
					}
					catch (FileSystemNotFoundException e)
					{
						//If the file system doesn't exist we create a new one
						filesystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
					}

					path = filesystem.getPath(resource);
				}

				return path;
			}
		}
		catch (URISyntaxException | IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public static boolean copyFile(Path originalPath, String newPath, boolean overwrite)
	{
		File userConfigFile = new File(newPath);

		try
		{
			if (!userConfigFile.exists() || overwrite)
			{
				Files.copy(originalPath, userConfigFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				return true;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return false;
	}

}
