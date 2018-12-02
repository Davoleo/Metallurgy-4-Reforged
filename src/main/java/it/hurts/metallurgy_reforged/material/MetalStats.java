package it.hurts.metallurgy_reforged.material;

import it.hurts.metallurgy_reforged.Metallurgy;
import it.hurts.metallurgy_reforged.block.BlockOreDict;
import it.hurts.metallurgy_reforged.fluid.FluidMolten;
import it.hurts.metallurgy_reforged.item.ItemOreDict;
import it.hurts.metallurgy_reforged.util.MetallurgyTabs;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;


//TODO: consider implementing crusher or alloyer recipes, maybe worldgen?
public class MetalStats {

    private final String name, oreDictName;
    private final int blockHarvest;
    private final float blockBlastResistance;
    //int minVeinSize, int maxVeinSize, int chance, int minY, int maxY
    private final int oreHarvest;

    private final ArmorStats armor;
    private final ToolStats tool;
    private final FluidStats fluid;

    public Metal createMetal() {
        //name should be in format [allLowerCase], oreName should be in format [Normalcase]
        ItemOreDict dust = new ItemOreDict(name + "_dust", "dust" + oreDictName).setCreativeTab(MetallurgyTabs.tabDust);
        ItemOreDict ingot = new ItemOreDict(name + "_ingot","ingot" + oreDictName).setCreativeTab(MetallurgyTabs.tabIngot);
        BlockOreDict block = new BlockOreDict(name + "_block","block" + oreDictName, "pickaxe", blockHarvest, blockBlastResistance).setCreativeTab(MetallurgyTabs.tabBlock);
        BlockOreDict ore = null;
        if(oreHarvest >= 0) {
            ore = new BlockOreDict(name + "_ore","ore" + oreDictName, "pickaxe", oreHarvest, blockBlastResistance).setCreativeTab(MetallurgyTabs.tabOre);
        }

        FluidMolten molten = fluid.func.apply(new FluidMolten("molten_" + name, fluid.still, fluid.flowing, fluid.mapColor));

        return new Metal(this, ingot, dust, ore, block, molten);
    }

    /**
     * @param name name of the metal - all lowercase with underlines separating words (ex: dark_steel)
     * @param oreDictName ore dict name of the metal - CamelCase with no underscores or spaces (DarkSteel)
     * @param blockHarvest the harvest level of the metal block
     * @param blastResistance the resistance to explosions of the metal block
     * @param armor the ArmorStats instance representing this metal's stats, or null if there is no armor
     * @param tool the ToolStats instance representing this metal's stats, or null if there are no tools
     * @param fluid the ToolStats instance representing this metal's stats, or null if there are no tools
     * @param oreHarvest the harvest level of the metal ore or -1 if no ore should be generated
     */
    public MetalStats(String name, String oreDictName, int blockHarvest, float blastResistance, ArmorStats armor, ToolStats tool, FluidStats fluid, int oreHarvest) {
        this.name = name;
        this.oreDictName = oreDictName;
        this.blockHarvest = blockHarvest;
        this.blockBlastResistance = blastResistance;
        this.armor = armor;
        this.tool = tool;
        this.fluid = fluid;
        this.oreHarvest = oreHarvest;
    }

    public String getName() {
        return name;
    }

    public String getOreDictName() {
        return oreDictName;
    }

    public ArmorStats getArmorStats() {
        return armor;
    }

    public ToolStats getToolStats() {
        return tool;
    }

    public static class FluidStats {
        private final ResourceLocation still;
        private final ResourceLocation flowing;
        private final int mapColor;
        private final Function<FluidMolten, FluidMolten> func;

        private final static ResourceLocation default_still = new ResourceLocation(Metallurgy.MODID, "blocks/molten_metal_still");
        private final static ResourceLocation default_flowing = new ResourceLocation(Metallurgy.MODID, "blocks/molten_metal_flow");
        private final static Function<FluidMolten, FluidMolten> default_function = n -> (FluidMolten) n.setMaterial(Material.IRON)
                .setDensity(800)
                .setGaseous(false)
                .setLuminosity(9)
                .setViscosity(4000)
                .setTemperature(4000);

        public FluidStats(int mapColor) {
            this(default_still, default_flowing, mapColor, default_function);
        }

        public FluidStats(int mapColor, Function<FluidMolten, FluidMolten> func) {
            this(default_still, default_flowing, mapColor, func);
        }

        public FluidStats(ResourceLocation still, ResourceLocation flowing, int mapColor, Function<FluidMolten, FluidMolten> func) {
            this.still = still;
            this.flowing = flowing;
            this.mapColor = mapColor;
            this.func = func;
        }
    }
}
