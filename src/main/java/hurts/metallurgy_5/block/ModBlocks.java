package hurts.metallurgy_5.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

//	Ore
	public static BlockOre oreCopper = new BlockOre("ore_copper","oreCopper").setCreativeTab(CreativeTabs.MATERIALS);
	
//	Block
//	public static BlockOre = new BlockBase("","").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	public static BlockOre blockCopper = new BlockOre("copper_block","blockCopper").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	public static BlockOre blockTin= new BlockOre("tin_block","blockTin").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	public static BlockOre blockManganese = new BlockOre("manganese_block","blockManganese").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	public static BlockOre blockBronze = new BlockOre("bronze_block","blockBronze").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	
	public static void register(IForgeRegistry<Block> registry) {
		registry.registerAll(
//				ore
				oreCopper,
				
//				Block
				blockCopper,
				blockTin,
				blockManganese,
				blockBronze
		);
		
	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		registry.registerAll(
//				.createItemBlock()
				
//				Ore
				oreCopper.createItemBlock(),
				
//				Block
				blockCopper.createItemBlock(),
				blockTin.createItemBlock(),
				blockManganese.createItemBlock(),
				blockBronze.createItemBlock()

		);
	}

	public static void registerModels() {
//		Ore
		oreCopper.registerItemModel(Item.getItemFromBlock(oreCopper));
		
//		Block
		blockCopper.registerItemModel(Item.getItemFromBlock(blockCopper));
		blockTin.registerItemModel(Item.getItemFromBlock(blockTin));
		blockManganese.registerItemModel(Item.getItemFromBlock(blockManganese));
		blockBronze.registerItemModel(Item.getItemFromBlock(blockBronze));
	}
	
}
