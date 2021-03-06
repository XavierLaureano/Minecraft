package MinecraftMod.util.handler;

import MinecraftMod.Blocks.container.ContainerTotem;
import MinecraftMod.Blocks.gui.GuiTotem;
import MinecraftMod.Blocks.tileentity.TileEntityTotem;
import MinecraftMod.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		if(ID == Reference.GUI_TOTEM) {
			return new ContainerTotem(player.inventory, (TileEntityTotem)world.getTileEntity(new BlockPos(x,y,z)), player);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		if(ID == Reference.GUI_TOTEM) {
			return new GuiTotem(player.inventory, (TileEntityTotem)world.getTileEntity(new BlockPos(x,y,z)), player);
		}
		return null;
	}
	
	public static void registerGUIs() {
		
	}

}
