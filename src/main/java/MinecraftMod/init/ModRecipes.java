package MinecraftMod.init;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
public static void init() {
	GameRegistry.addSmelting(ModItems.RUBY, new ItemStack(ModBlocks.GALAXY_OPAL_BLOCK), 1.5F);
	GameRegistry.addSmelting(ModBlocks.GALAXY_OPAL_BLOCK, new ItemStack(Blocks.DIAMOND_BLOCK, 1), 1.5F);

}
}
