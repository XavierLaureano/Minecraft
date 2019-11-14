package MinecraftMod.init;

import java.util.ArrayList;
import java.util.List;

import MinecraftMod.Blocks.BlockBase;
import MinecraftMod.Blocks.Galaxy_Opal_Block;
import MinecraftMod.Blocks.RubyOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class ModBlocks {
	
	public static List <Block> BLOCKS = new ArrayList<Block>();
	
	
	public static final Block GALAXY_OPAL_BLOCK = new Galaxy_Opal_Block("galaxy_opal_block", Material.IRON);
	public static final Block RUBY_ORE = new RubyOre("ruby_ore", Material.ROCK);


}
