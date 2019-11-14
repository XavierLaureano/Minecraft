package MinecraftMod.Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class Galaxy_Opal_Block extends BlockBase{

	public Galaxy_Opal_Block(String name, Material material) {
		
		super(name, material);
		
		setSoundType(SoundType.METAL);
		setHardness(5.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 2);
		setLightLevel(15.0F);
	}

}
